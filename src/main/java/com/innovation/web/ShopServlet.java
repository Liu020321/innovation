package com.innovation.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.innovation.pojo.Shop;
import com.innovation.pojo.User;
import com.innovation.service.ShopService;
import com.innovation.service.impl.ShopServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/shop/*")
public class ShopServlet extends BaseServlet {
    private ShopService shopService = new ShopServiceImpl();

    public void shop(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");

        //1. 调用Service查询
        List<Shop> shops = shopService.selectShop();

        //2. 将集合转换为JSON数据   序列化
        String jsonString = JSON.toJSONString(shops);

        //3. 响应数据
        resp.getWriter().write(jsonString);

    }

    public void addShop(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");


        BufferedReader ur = req.getReader();
        String params = ur.readLine();

        JSONObject jsonObject=JSON.parseObject(params);

        // 获取用户输入的验证码
        String shopI = jsonObject.getString("shopId");

        int shopId=Integer.parseInt(shopI);

        System.out.println(shopId);

        //2. 调用service 注册
        boolean flag = shopService.shopCar(shopId);
        //3. 判断注册成功与否
        if(flag){
            //注册功能，跳转登陆页面
            resp.getWriter().write("success");
        }else {
            //注册失败，跳转到注册页面
            resp.getWriter().write("failed");
        }

    }

    //删除数据
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");


    }

    public void selectAllCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");

        //1. 调用Service查询
        List<Shop> shops = shopService.selectAllCount();

        //2. 将集合转换为JSON数据   序列化
        String jsonString = JSON.toJSONString(shops);

        //3. 响应数据
        resp.getWriter().write(jsonString);

    }

}
