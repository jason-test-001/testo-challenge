package com.testo.controller;


import com.testo.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * login controller
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Subject subject = SecurityUtils.getSubject();
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, md5Password);
        try {
            subject.login(usernamePasswordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "username or password incorrect！";
        } catch (AuthorizationException e) {
            e.printStackTrace();
            return "has no permission";
        }
        return "login success";
    }

}

