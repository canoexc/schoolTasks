package com.example.factory.mapper;

import com.example.api.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FactoryMapper {
    //全部
    @Select("select * from product")
    public List<Product> getAllProduct();
    //预售
    @Select("select * from product where state=0")
    public List<Product> getPreProduct();
    //发售
    @Select("select * from product where state=1")
    public List<Product> getNowProduct();
    //预售结束
    @Select("select * from product where state=2")
    public List<Product> getMissProduct();
    //通过pid获取
    @Select("select * from product where pid=#{pid}")
    public Product getProductByPid(int pid);
    //通过uid获取
    @Select("select * from product a,book b where a.pid=b.pid and b.uid=#{uid}")
    public List<Product> getProductByUid(int uid);
    //添加新商品
    @Options(useGeneratedKeys = true,keyProperty = "pid")
    @Insert("insert into product(pname,detail,pre,after,state,img)"+
            " values(#{pname},#{detail},#{pre},#{after},#{state},#{img})")
    public int insertNewProduct(Product product);
    //更新商品状态
    @Update("update product set state=#{state} where pid=#{pid}")
    public int updateProduct(Product product);
    @Select("select * from product where pname like #{s}")
    public List<Product> findProduct(String s);
}
