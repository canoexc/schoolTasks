package com.example.api.service;

import com.example.api.entity.Book;
import com.example.api.entity.Product;

import java.util.List;

public interface BookService {
    public int insertBook(Book book);
    public int updateBook(Book book);
    //我的消息（待补款）
    public List<Book> getRenewProduct(int uid);
    //历史订单（全部）
    public List<Book> getAllBook(int uid);
    //未开补款 0=0
    public List<Book> getPreBook(int uid);
    //待发货 1
    public List<Book> getOkBook();
    //待退款 3
    public List<Book> getBadBook();
    //
    public Book getBookById(int bid);
    //退款通知
    public List<Book> getReBook(int uid);
}
