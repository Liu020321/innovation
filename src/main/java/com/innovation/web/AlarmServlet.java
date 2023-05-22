package com.innovation.web;

import com.alibaba.fastjson.JSON;
import com.innovation.pojo.Down;
import com.innovation.pojo.Shop;
import com.innovation.service.AlarmService;
import com.innovation.service.impl.AlarmServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/alarm/*")
public class AlarmServlet extends BaseServlet{
    private AlarmService alarmService=new AlarmServiceImpl();

    public void getOneCount(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");

        //1. 调用Service查询
        Down count= alarmService.getOneCount(1);

        //System.out.println(count.getCount());

        //2. 将集合转换为JSON数据   序列化
        String jsonString = JSON.toJSONString(count);

        //3. 响应数据
        resp.getWriter().write(jsonString);
    }
}
