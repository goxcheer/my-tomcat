package com.goxcheer.app.servlet;

import com.goxcheer.tomcat.MyRequest;
import com.goxcheer.tomcat.MyResponse;
import com.goxcheer.tomcat.Servlet;

public class UserServlet extends Servlet {
    @Override
    public void doGet(MyRequest request, MyResponse response) throws Exception {
        this.doPost(request, response);
    }

    @Override
    public void doPost(MyRequest request, MyResponse response) throws Exception {
        response.write("login success");
    }
}
