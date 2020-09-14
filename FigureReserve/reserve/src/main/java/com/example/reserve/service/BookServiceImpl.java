package com.example.reserve.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.api.entity.Book;
import com.example.api.entity.Product;
import com.example.api.service.BookService;
import com.example.reserve.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
@Component
@Service(version = "1.0.0" ,interfaceClass = BookService.class)
public class BookServiceImpl implements BookService {
    @Autowired
    BookMapper bookMapper;
    @Override
    public int insertBook(Book book) {
        return bookMapper.insertBook(book);
    }

    @Override
    public int updateBook(Book book) {
        return bookMapper.updateBook(book);
    }

    @Override
    public List<Book> getRenewProduct(int uid) {
        return bookMapper.getRenewProduct(uid);
    }

    @Override
    public List<Book> getAllBook(int uid) {
        return bookMapper.getAllBook(uid);
    }

    @Override
    public List<Book> getPreBook(int uid) {
        return bookMapper.getPreBook(uid);
    }

    @Override
    public List<Book> getOkBook() {
        return bookMapper.getOkBook();
    }

    @Override
    public List<Book> getBadBook() {
        return bookMapper.getBadBook();
    }

    @Override
    public Book getBookById(int bid) {
        return bookMapper.getBookById(bid);
    }

    @Override
    public List<Book> getReBook(int uid) {
        return bookMapper.getReBook(uid);
    }
}
