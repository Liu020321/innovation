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
import java.io.UnsupportedEncodingException;
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

    public void selectByVoice(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        BufferedReader ur = req.getReader();

        String data=req.getReader().readLine();
        System.out.println(data);

        //分割字符串
        String[] data1=data.split(":|\"|}");

        int count=data1.length;

        String data2=data1[count-1];
        System.out.println(data2);






//        System.out.println(params);
//
//        JSONObject jsonObject=JSON.parseObject(params);


//        String information = jsonObject.getString("voiceText");

        //String information=req.getParameter("voice");
        //String information=req.getParameter("voiceText");

//        System.out.println("医疗");

        List<Shop> list=shopService.selectByVoice(data2);
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i).getShopName()+"  "+list.get(i).getShopType());
        }

        String jsonString=JSON.toJSONString(list);
        resp.getWriter().write(jsonString);
    }

    public void deleteOne(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");

        String id1=req.getParameter("id");
        int id=Integer.parseInt(id1);

        System.out.println(id);

        boolean b=shopService.deleteOne(id);

        if(b){
            resp.getWriter().write("success");
        }else{
            resp.getWriter().write("fail");
        }
    }

}
