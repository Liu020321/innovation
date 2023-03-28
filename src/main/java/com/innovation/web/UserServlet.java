package com.innovation.web;

import com.alibaba.fastjson.JSON;
import com.innovation.pojo.User;
import com.innovation.service.UserService;
import com.innovation.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    private  UserService userService = new UserServiceImpl();


    /**
     * 登录方法
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String userName=req.getParameter("username");
        String password=req.getParameter("password");
        Integer status=Integer.parseInt(req.getParameter("status"));
        String remember = req.getParameter("remember");

        User user = userService.login(userName, password, status);

        if(user!=null){
            if("1".equals(remember)){
                Cookie c_userName= new Cookie("userName",userName);
                Cookie c_password=new Cookie("password",password);

                c_userName.setMaxAge(60*60*24*7);
                c_password.setMaxAge(60*60*24*7);

                resp.addCookie(c_userName);
                resp.addCookie(c_password);

                HttpSession session = req.getSession();
                session.setAttribute("user",user);

                req.getRequestDispatcher("/index.html").forward(req,resp);
//                String contextPath=req.getContextPath();
//                resp.sendRedirect(contextPath+"/user/selectAll");
            }
            else {
                req.setAttribute("login_msg","账号或密码错误，请重试");
                req.getRequestDispatcher("/login.html").forward(req,resp);
            }
        }


    }


}
