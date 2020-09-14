package com.example.reserve.mapper;

import com.example.api.entity.Book;
import com.example.api.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BookMapper {
    @Options(useGeneratedKeys = true,keyProperty = "bid")
    @Insert("insert into books(uid,pid,state,address,phone,time) "+
            "values (#{uid},#{pid},#{state},#{address},#{phone},#{time})")
    public int insertBook(Book book);
    @Update("update books set state=#{state},address=#{address},phone=#{phone},time=#{time} where bid=#{bid}")
    public int updateBook(Book book);
    @Select("select b.* from product a,books b where a.pid=b.pid and a.state=1 and b.state=0 and b.uid=#{uid}")
    public List<Book> getRenewProduct(int uid);
    @Select("select * from books where uid=#{uid}")
    public List<Book> getAllBook(int uid);
    @Select("select * from books where uid=#{uid} and state=0")
    public List<Book> getPreBook(int uid);
    @Select("select * from books where state=1")
    public List<Book> getOkBook();
    @Select("select * from books where bid=#{bid}")
    public Book getBookById(int bid);
    @Select("select * from books where state=3")
    public List<Book> getBadBook();
    @Select("select * from books where state=4 and uid=#{uid}")
    public List<Book> getReBook(int uid);
}
