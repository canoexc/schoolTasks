package com.example.reserve.controller;

import com.example.api.entity.Book;
import com.example.api.entity.Product;
import com.example.api.entity.User;
import com.example.api.service.BookService;
import com.example.api.service.FactoryService;
import org.apache.dubbo.config.annotation.Reference;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

@Controller
public class ProductController {
    @Reference(version = "1.0.0" ,interfaceClass = FactoryService.class,check = true)
    FactoryService factoryService;
    @Autowired
    BookService bookService;
    @PostMapping("/add/product")
    public String addProduct(@RequestParam("file") MultipartFile file,String pname,String detail,int pre,int after) throws Exception{
        if(!file.isEmpty()){
            Encoder encoder= Base64.getEncoder();
            String image=encoder.encodeToString(file.getBytes());
            Product product=new Product(pname,detail,pre,after,0,image);
            factoryService.insertNewProduct(product);
        }
        return "/show/product";
    }
    @GetMapping(value = "/show/product")
    public String showProduct(Model model){
        List<Product> products=factoryService.getAllProduct();
        model.addAttribute("product",products);
        return "showProduct";
    }
    @PostMapping(value = "/find/product")
    public String findProduct(String s,Model model){
        List<Product> products=factoryService.findProduct(s);
        model.addAttribute("product",products);
        return "showProduct";
    }
    @GetMapping(value = "/detail/product/{pid}")
    public String detailProduct(@PathVariable("pid") int pid, Model model){
        Product product=factoryService.getProductByPid(pid);
        model.addAttribute("product",product);
        return "productDetail";
    }
    @GetMapping(value = "/show/image")
    public void getImage(@RequestParam("pid") int pid, HttpServletResponse response) throws Exception{
        Product product=factoryService.getProductByPid(pid);
        byte[] image=(byte[])product.getImg();
        String value=new String(image,"UTF-8");
        Base64.Decoder decoder= Base64.getDecoder();
        byte[] bytes=decoder.decode(value);
        for(int i=0;i<bytes.length;i++){
            if (bytes[i]<0){
                bytes[i]+=256;
            }
        }
        response.setContentType("image/jpeg");
        ServletOutputStream out =response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    }
    @PostMapping(value = "/book/info")
    public String bookInfo(int pid,boolean ifUse, HttpSession session,Model model){
        Product product=factoryService.getProductByPid(pid);
        User user=(User) session.getAttribute("user");
        int state=product.getState();
        int money=0;
        if (state==0){
            money=product.getPre();
            if(ifUse)money-=user.getPoint();
            model.addAttribute("ifUse",ifUse);
        }else money=product.getAfter()+product.getPre();
        model.addAttribute("pid",pid);
        model.addAttribute("pname",product.getPname());
        model.addAttribute("money",money);
        return "bookInfo";
    }
    @GetMapping(value = "/book/renew/{pid}/{bid}")
    public String bookRenew(@PathVariable("pid") int pid,@PathVariable("bid") int bid, Model model){
        Product product=factoryService.getProductByPid(pid);
        Book book=bookService.getBookById(bid);
        int money=0;
        money=product.getAfter();
        model.addAttribute("pid",pid);
        model.addAttribute("pname",product.getPname());
        model.addAttribute("money",money);
        model.addAttribute("address",book.getAddress());
        model.addAttribute("phone",book.getPhone());
        model.addAttribute("bid",book.getBid());
        model.addAttribute("msg","renew");
        boolean ifUse=false;
        model.addAttribute("ifUse",ifUse);
        return "bookInfo";
    }
    @GetMapping(value = "/update/product/{pid}/{state}")
    public String updateProduct(@PathVariable("pid")int pid,@PathVariable("state")int state) {
        Product product=factoryService.getProductByPid(pid);
        product.setState(state);
        factoryService.updateProductState(product);
        return "redirect:/show/product";
    }
}
