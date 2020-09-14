package com.example.reserve.controller;
import com.example.api.entity.Account;
import com.example.api.entity.User;
import com.example.api.service.BookService;
import com.example.api.service.FactoryService;
import com.example.api.service.PaymentService;
import com.example.api.service.UserService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;

    @Reference(version = "1.0.0" ,interfaceClass = FactoryService.class,check = true)
    FactoryService factoryService;
    @Reference(version = "1.0.0" ,interfaceClass = PaymentService.class,check = true)
    PaymentService paymentService;

    @PostMapping(value = "user/check")
    public String check(String username, String pwd, HttpSession session, Model model){
        List<User> users=userService.checkUserLogin(username,pwd);
        if(users!=null){
            session.setAttribute("user",users.get(0));
            return "redirect:/show/product";
        } else{
            model.addAttribute("msg","用户名或密码错误");
            return "index";
        }
    }
    @PostMapping(value = "user/signin")
    public String signIn(String username,String password,Model model){
        List<User> users=userService.checkUsernameUni(username);
        if(users.size()!=0){
            model.addAttribute("msg","用户名已被注册！");
            return "signup";
        }else {
            User user=new User(username,password,0,0);
            userService.insertUser(user);
            List<User> users1=userService.checkUserLogin(username, password);
            Account account=new Account(users1.get(0).getUid(),"123456",5000);
            model.addAttribute("msg","注册成功，请重新登录！");
            return "index";
        }
    }
    @RequestMapping(value = "user/profile")
    public String profile(Model model,HttpSession session){
        User user=(User)session.getAttribute("user");
        int balance=paymentService.getBalance(user.getUid());
        model.addAttribute("balance",balance);
        return "profile";
    }
}
