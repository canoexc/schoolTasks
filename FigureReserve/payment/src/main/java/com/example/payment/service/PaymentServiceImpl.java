package com.example.payment.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.example.api.service.PaymentService;
import com.example.payment.mapper.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(version = "1.0.0" ,interfaceClass = PaymentService.class)
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentMapper paymentMapper;
    @Override
    public int getBalance(int uid) {
        return paymentMapper.getBalance(uid);
    }

    @Override
    public int updateBalance(int uid, int balance) {
        paymentMapper.updateBalance(uid,balance);
        return 0;
    }

    @Override
    public int insertAccount(int uid) {
        return 0;
    }

    @Override
    public String getPwd(int uid) {
        return paymentMapper.getPwd(uid);
    }
}
