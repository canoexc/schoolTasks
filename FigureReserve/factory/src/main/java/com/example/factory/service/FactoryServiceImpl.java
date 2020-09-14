package com.example.factory.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.api.entity.Product;
import com.example.api.service.FactoryService;
import com.example.factory.mapper.FactoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.io.UnsupportedEncodingException;
import java.util.List;
@Component
@Service(version = "1.0.0" ,interfaceClass = FactoryService.class,timeout = 1200000)
public class FactoryServiceImpl implements FactoryService {
    @Autowired
    FactoryMapper factoryMapper;
    @Override
    public List<Product> getAllProduct() {
        List<Product> products=factoryMapper.getAllProduct();
        return products;
    }

    @Override
    public List<Product> getPreProduct() {
        List<Product> products=factoryMapper.getPreProduct();
        return products;
    }

    @Override
    public List<Product> getNowProduct() {
        List<Product> products=factoryMapper.getNowProduct();
        return products;
    }

    @Override
    public List<Product> getMissProduct() {
        List<Product> products=factoryMapper.getMissProduct();
        return products;
    }

    @Override
    public Product getProductByPid(int pid) {
        Product product=factoryMapper.getProductByPid(pid);
        return product;
    }

    @Override
    public List<Product> getProductByUid(int uid) {
        List<Product> products=factoryMapper.getProductByUid(uid);
        return products;
    }

    @Override
    public int insertNewProduct(Product product) {
        factoryMapper.insertNewProduct(product);
        return 0;
    }

    @Override
    public int updateProductState(Product product) {
        factoryMapper.updateProduct(product);
        return 0;
    }

    @Override
    public void getImage(int pid, HttpServletResponse response) throws IOException {
        Product product=factoryMapper.getProductByPid(pid);
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

    @Override
    public List<Product> findProduct(String s) {
        s="%"+s+"%";
        return factoryMapper.findProduct(s);
    }
}
