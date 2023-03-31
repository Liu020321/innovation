package com.innovation.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.innovation.pojo.User;
import com.innovation.service.UserService;
import com.innovation.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    public void lll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("llll");
    }

    /**
     * 登录方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");

        BufferedReader ur = req.getReader();
        String params = ur.readLine();

        User user = JSON.parseObject(params, User.class);

        User userLogin = userService.login(user.getUserName(), user.getPassword());


        if (userLogin != null) {
            resp.getWriter().write("success");

        } else {
            req.setAttribute("login_msg", "账号或密码错误，请重试");
            req.getRequestDispatcher("/index.html").forward(req, resp);
        }


    }


}
