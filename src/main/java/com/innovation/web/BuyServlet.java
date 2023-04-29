package com.innovation.web;

import com.alibaba.fastjson.JSON;
import com.innovation.pojo.User;
import com.innovation.pojo.shoppingcarts;
import com.innovation.pojo.things;
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
//
//        shoppingcarts sp = JSON.parseObject(params, shoppingcarts.class);

        String name1=req.getParameter("name");
        System.out.println(name1);

        String name=JSON.toJSONString(name1);


        System.out.println(name);

        //根据name确认购物车是否存在该项物品
        boolean b=userService.ifOk(name);

        System.out.println(b);


        if(b){//如果数据已经存在

            //数据已经存在，数量+1---执行修改操作
            boolean a=userService.ifUpdateOk(name);//修改数量数据+1
            if(a){
                resp.setContentType("text/json;charset=utf-8");
                resp.getWriter().write("success");
            }else{
                resp.setContentType("text/json;charset=utf-8");
                resp.getWriter().write("fail");
            }

        }else{//数据并不存在

            //数据不存在--新添加一条数据

            //根据name查找到相应数据
            float price=userService.getOnePrice(name);
            String type=userService.getOneType(name);


            shoppingcarts sc=new shoppingcarts();
            sc.setName(name);
            sc.setPrice(price);
            sc.setType(type);
            sc.setCount(1);

            boolean c=userService.addThings(sc);

            if(c){
                resp.setContentType("text/json;charset=utf-8");
                resp.getWriter().write("success");
            }else{
                resp.setContentType("text/json;charset=utf-8");
                resp.getWriter().write("fail");
            }

        }
    }

    //删除数据
    public void deleteCarts(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //System.out.println("来到buyServlet啦！");
        BufferedReader ur = req.getReader();
        String params = ur.readLine();

        String id1=req.getParameter("id");
        System.out.println(id1);
        //shoppingcarts ss=JSON.parseObject(params,shoppingcarts.class);
        int id=Integer.parseInt(id1);

        boolean b=userService.deleteThings(id);
        System.out.println(b);

        if(b){//true

            //响应数据
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("success");
        }else{

            //响应数据
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("false");
        }





    }

    public void deleteAll(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        boolean b=userService.deleteAll();

        if(b){
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("success");

        }else{
            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write("fail");
        }


    }

    public void getAllThings(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        List<things> list=userService.getAllThings();

        String jsonString=JSON.toJSONString(list);

        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void checkIt(HttpServletRequest req,HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        BufferedReader ur = req.getReader();
        String params = ur.readLine();

        String type=req.getParameter("type");//获取到类型

        System.out.println(type);

        List<things> list=userService.getType(type);

        //转成json类型
        String jsonString=JSON.toJSONString(list);

        //响应数据
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

}
