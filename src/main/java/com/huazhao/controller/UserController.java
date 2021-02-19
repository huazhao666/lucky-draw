package com.huazhao.controller;

import com.huazhao.exception.AppException;
import com.huazhao.model.User;
import com.huazhao.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with Intellij IDEA
 * Description:
 * User : 花朝
 * Date : 2021-02-02
 * Time : 12:07
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Object register(User user , MultipartFile headFile){
        //头像保存到本地
        if(headFile != null){
            String head = userService.saveHead(headFile);
            user.setHead(head);
        }
        userService.register(user);
        return null;
    }
    @PostMapping("/login")
    public Object login(@RequestBody User user, HttpServletRequest req){ //username,password
        //根据账号查用户
        User exist = userService.queryUsername(user.getUsername());//这个exist是数据库里面得
        if(exist == null){
            throw new AppException("LOG001","用户不存在");
        }
        //用户存在，校验密码；
        if(!user.getPassword().equals(exist.getPassword())){ //这个user是用户登陆时设置的；
            throw new AppException("LOG002","账号或密码错误");
        }
        //校验通过
        HttpSession session = req.getSession();
        session.setAttribute("user",exist);
        return null;//登录成功；
    }
    @GetMapping("/logout")
    public Object logout(HttpSession session){
        session.removeAttribute("username");
        return null;
    }

}
