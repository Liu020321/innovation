package com.innovation.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.System;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.InputStream;

@WebServlet("/video/*")
public class VideoServlet extends BaseServlet {


    public StringBuilder GetPSV(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Socket socket = null;
        StringBuilder sb = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            String host = addr.getHostName();
            //String ip=addr.getHostAddress().toString(); //获取本机ip
            //log.info("调用远程接口:host=>"+ip+",port=>"+12345);

            // 初始化套接字，设置访问服务的主机和进程端口号，HOST是访问python进程的主机名称，可以是IP地址或者域名，PORT是python进程绑定的端口号
            socket = new Socket(host, 12345);

            // 获取输出流对象
            OutputStream os = socket.getOutputStream();
            PrintStream out = new PrintStream(os);
            // 发送内容
            out.print("star");
            // 告诉服务进程，内容发送完毕，可以开始处理
            out.print("over");

            // 获取服务进程的输入流
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String tmp = null;
            sb = new StringBuilder();
            // 读取内容
            while ((tmp = br.readLine()) != null)
                sb.append(tmp).append('\n');
//            classNews = JSONArray.parseObject(sb.toString(),ClassNews.class);
//            System.out.println(classNews.getBase64code());
//            System.out.println(classNews.getName());
            System.out.println(sb);
            //将算法返回的结果再次返回给Vue前端
            return sb;
            // 解析结果
            //JSONArray res = JSON.parseArray(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return sb;
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
            }
            System.out.println("远程接口调用结束.");
        }
    }

}

