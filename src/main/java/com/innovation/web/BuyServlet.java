package com.innovation.web;

import com.alibaba.fastjson.JSON;
import com.innovation.pojo.User;
import com.innovation.pojo.shoppingcarts;
import com.innovation.service.UserService;
import com.innovation.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/buy/*")
public class BuyServlet extends BaseServlet{
    private UserService userService=new UserServiceImpl();

    public void view(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //BufferedReader ur = req.getReader();
        //String params = ur.readLine();

        //shoppingcarts sc = JSON.parseObject(params, shoppingcarts.class);

        List<shoppingcarts> scSelect = userService.selectAllThings();//遍历购物车数据库

        String jsonString=JSON.toJSONString(scSelect);//转成json数据

        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);



//        if (scSelect != null) {
//            HttpSession session = req.getSession();
//            session.setAttribute("sc",sc);
//            resp.getWriter().write("success");
//
//        } else {
//            req.setAttribute("select_msg", "查询失败，请重试");
//            req.getRequestDispatcher("/ViewShoppingCarts.html").forward(req, resp);
//        }

    }

    public void addCarts(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        BufferedReader ur = req.getReader();
        String params = ur.readLine();

        shoppingcarts ss = JSON.parseObject(params, shoppingcarts.class);

        //shoppingcarts sc=new shoppingcarts();
        boolean b=userService.addThings(ss);

    }

    //删除数据
    public void deleteCarts(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        BufferedReader ur = req.getReader();
        String params = ur.readLine();

        shoppingcarts ss=JSON.parseObject(params,shoppingcarts.class);
        System.out.println(ss);

        boolean b=userService.deleteThings(ss.getCount());

        String result=JSON.toJSONString(b);

        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(result);

    }

    public void deleteAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        boolean b=userService.deleteAll();

        String result=JSON.toJSONString(b);

        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(result);
    }
}
