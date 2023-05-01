package com.innovation.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.innovation.pojo.User;
import com.innovation.service.UserService;
import com.innovation.service.impl.UserServiceImpl;
import com.innovation.util.CheckCodeUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

   public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // 生成验证码
       ServletOutputStream os = resp.getOutputStream();
       String checkCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);


       // 存入Session
       HttpSession session = req.getSession();
       session.setAttribute("checkCodeGen",checkCode);

   }
    public void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/json;charset=utf-8");

        BufferedReader ur = req.getReader();
        String params = ur.readLine();

        User user = JSON.parseObject(params, User.class);

        JSONObject jsonObject=JSON.parseObject(params);


        // 获取用户输入的验证码
        String checkCode = jsonObject.getString("checkCode");

        // 程序生成的验证码，从Session获取
        HttpSession session = req.getSession();
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");

        // 比对
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){

            resp.getWriter().write("checkCode_error");

            // 不允许注册
            return;
        }



        //2. 调用service 注册
        boolean flag = userService.register(user);
        //3. 判断注册成功与否
        if(flag){
            //注册功能，跳转登陆页面
            resp.getWriter().write("success");
        }else {
            //注册失败，跳转到注册页面

            req.setAttribute("register_msg","用户名已存在");
            req.getRequestDispatcher("/register.jsp").forward(req,resp);
        }

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
            HttpSession session = req.getSession();
            session.setAttribute("user",user);
            resp.getWriter().write("success");

        } else {
            req.setAttribute("login_msg", "账号或密码错误，请重试");
            req.getRequestDispatcher("/index.html").forward(req, resp);
        }


    }


}
