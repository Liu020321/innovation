package com.innovation.web;

import com.innovation.service.UserService;
import com.innovation.service.impl.UserServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@WebServlet("/buy/*")
public class BuyServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();

    public void checkShoppingCarts(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");



    }
}
