package com.example.reserve.controller;
import com.example.api.entity.Book;
import com.example.api.entity.Product;
import com.example.api.entity.User;
import com.example.api.service.BookService;
import com.example.api.service.FactoryService;
import com.example.api.service.PaymentService;
import com.example.api.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Reference(version = "1.0.0" ,interfaceClass = PaymentService.class,check = true)
    PaymentService paymentService;
    @Reference(version = "1.0.0" ,interfaceClass = FactoryService.class,check = true)
    FactoryService factoryService;
    @PostMapping(value = "/book/product")
    public String showBookDetail(int pid, String renew,boolean ifUse,int bid,String address, String phone, HttpSession session,int money,Model model)
    {
        User user= (User) session.getAttribute("user");
        Product product=factoryService.getProductByPid(pid);
        int balance=paymentService.getBalance(user.getUid());
        if(balance>=money){
            Date date = new Date();
            SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
            Book book=new Book(user.getUid(),pid,product.getState(),address,phone,dateFormat.format(date));
            if(renew.equals("no"))
            bookService.insertBook(book);
            else {
                book.setBid(bid);
                bookService.updateBook(book);
            }
            paymentService.updateBalance(user.getUid(),balance-money);
            if (ifUse){
                user.setPoint(0);
            }
            model.addAttribute("money","支付成功！本次消费获得积分"+money/100);
            user.setPoint(user.getPoint()+money/100);
            userService.updateUser(user);
            return "fail";
        }else {
            model.addAttribute("money","支付失败！余额不足！余额为"+balance);
            return "fail";
        }
    }
    @RequestMapping(value = "/show/book")
    public String showBook(Model model,HttpSession session){
        User user= (User) session.getAttribute("user");
        List<Book> books=bookService.getAllBook(user.getUid());
        model.addAttribute("book",books);
        return "showBook";
    }
    @RequestMapping(value = "/book/notice")
    public String bookNotice(Model model,HttpSession session){
        User user= (User) session.getAttribute("user");
        List<Book> books=null;
        List<Book> books1=null;
        if(user.getAuthority()==0) {
            books = bookService.getRenewProduct(user.getUid());
            books1=bookService.getReBook(user.getUid());
        }else {
            books = bookService.getBadBook();
            books1=bookService.getOkBook();
        }
        model.addAttribute("book",books);
        model.addAttribute("book1",books1);
        return "myNotice";
    }
    @RequestMapping(value = "/book/update/{bid}/{state}")
    public String bookUpdate(Model model,@PathVariable int bid,@PathVariable int state, HttpSession session) throws ParseException {
        Book book=bookService.getBookById(bid);
        User user=(User)session.getAttribute("user");
        Product product=factoryService.getProductByPid(book.getPid());
        book.setState(state);
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        if(state==3) {
            Date used = dateFormat.parse(book.getTime());
            long diff = date.getTime() - used.getTime();
            diff /= 1000;
            if (diff > 604800) {
                model.addAttribute("money", "申请退款失败！收货超过7天，不可申请");
                return "fail";
            }
        }
        book.setTime(dateFormat.format(date));
        bookService.updateBook(book);
        if(state==4){
            int uid=book.getUid();
            paymentService.updateBalance(uid,paymentService.getBalance(uid)+product.getPre()+product.getAfter());
            user.setPoint(user.getPoint()-product.getAfter()/100);
            userService.updateUser(user);
            model.addAttribute("money","操作成功！退款已返回该用户账户");
            return "fail";
        }
        if(state==2){
            model.addAttribute("money","操作成功！商品已发货");
            return "fail";
        }
        model.addAttribute("money","操作成功！已申请退款，等待商家处理");
        return "fail";
    }
}
