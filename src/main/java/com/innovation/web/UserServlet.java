package com.innovation.web.Servlet;

import com.innovation.mapper.UserMapper;
import com.innovation.pojo.User;
import com.innovation.service.UserService;
import com.innovation.service.impl.UserServiceImpl;

import java.util.List;

public class UserServlet {
        private static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {

        List<User> users=userService.selectAll();
        System.out.println(users);
    }
}
