package com.goxcheer.tomcat;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
*@Author: goxcheer
*@Date: 10:43 下午 2020/9/22
*@Email: 604721660@qq.com
*@motto: Rome was not built in a day
*@Description: Tomcat启动类
**/
public class MyTomcat {

    private int port = 8080;

    private ServerSocket server;

    /**
     * key->url, value->servlet
     */
    private Map<String,String> servletMap = new HashMap<>();

    private Properties webXml = new Properties();

    private void init() {
        //加载web.xml, 同时初始化ServletMapping对象
        try{
            String path = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(path + "web.properties");
            webXml.load(fis);

            String[] servletNames = null;
            String[] servletUrls = null;

            for (Object k : webXml.keySet()) {
                String key = k.toString();

                if ("servletNames".equalsIgnoreCase(key)) {
                    servletNames = webXml.getProperty(key).split(",");
                }

                if ("servletUrls".equalsIgnoreCase(key)) {
                   servletUrls = webXml.getProperty(key).split(",");
                }
            }

            if (servletNames.length != servletUrls.length) {
                throw new Exception("load web.xml error");
            }

            for (int i = 0 ; i < servletNames.length ; i++) {
                servletMap.put(servletUrls[i], servletNames[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start() {
        //初始化
        this.init();

        try{
            server = new ServerSocket(this.port);

            //等待用户请求
            while(true){
                Socket socketRequest = server.accept();
                //Http请求，数据就是一些字符串，可以参考浏览器报文
                doProcess(socketRequest);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void doProcess(Socket socketRequest) throws Exception {
        InputStream in = socketRequest.getInputStream();
        OutputStream out = socketRequest.getOutputStream();

        MyRequest request = new MyRequest(in);
        MyResponse response = new MyResponse(out);

        String servletName = servletMap.get(request.getUrl());

        if (servletName == null || servletName == "") {
            response.write("404 not found");
        }else{
            Servlet curServlet = (Servlet)Class.forName(servletName).newInstance();
            curServlet.service(request, response);
        }



        System.out.println("aaa");
        out.flush();
        out.close();

        in.close();
        socketRequest.close();
    }

    public static void main(String[] args) {
        MyTomcat tomcat = new MyTomcat();
        tomcat.start();
    }

}
