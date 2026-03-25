package com.irvings.controller;

import com.irvings.user.AuthenticationService;
import com.irvings.user.CustomerAccount;
import com.irvings.user.StaffAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private AuthenticationService authService;

    // 注册新用户
    @PostMapping("/register")
    public boolean register(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        return authService.register(email, password);
    }

    // 登录并获取会话ID
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");
        String sessionId = authService.createSession(email, password);
        if (sessionId == null) throw new IllegalArgumentException("账号或密码错误");
        return sessionId;
    }

    // 校验会话是否有效
    @GetMapping("/validate")
    public boolean validateSession(@RequestParam String sessionId) {
        return authService.validateSession(sessionId);
    }

    // 登出
    @PostMapping("/logout")
    public void logout(@RequestParam String sessionId) {
        authService.logout(sessionId);
    }

    // 创建顾客账号
    @PostMapping("/customer")
    public CustomerAccount createCustomer(@RequestBody Map<String, String> request) {
        String fullName = request.get("fullName");
        String email = request.get("email");
        String phone = request.get("phone");
        return new CustomerAccount(fullName, email, phone);
    }

    // 创建员工账号
    @PostMapping("/staff")
    public StaffAccount createStaff(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String email = request.get("email");
        String role = request.get("role");
        return new StaffAccount(name, email, role);
    }
}
