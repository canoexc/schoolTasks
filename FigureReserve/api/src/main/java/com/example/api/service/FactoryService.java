package com.example.api.service;

import com.example.api.entity.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface FactoryService {
    //全部
    public List<Product> getAllProduct();
    //预售
    public List<Product> getPreProduct();
    //发售
    public List<Product> getNowProduct();
    //预售结束
    public List<Product> getMissProduct();
    //通过pid获取
    public Product getProductByPid(int pid);
    //通过uid获取
    public List<Product> getProductByUid(int uid);
    //添加新商品
    public int insertNewProduct(Product product);
    //更新商品状态
    public int updateProductState(Product product);
    //图片
    public void getImage(int pid, HttpServletResponse response) throws IOException;
    //模糊查找
    public List<Product> findProduct(String s);
}
