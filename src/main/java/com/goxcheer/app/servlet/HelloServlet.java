package com.goxcheer.app.servlet;

import com.goxcheer.tomcat.MyRequest;
import com.goxcheer.tomcat.MyResponse;
import com.goxcheer.tomcat.Servlet;

public class HelloServlet extends Servlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws Exception {
        System.out.println("execute HelloServlet doPost......");
        response.write(request.getUrl());
    }
}
