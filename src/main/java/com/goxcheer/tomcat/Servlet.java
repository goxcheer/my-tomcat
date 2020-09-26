package com.goxcheer.tomcat;
/**
*@Author: goxcheer
*@Date: 10:11 下午 2020/9/22
*@Email: 604721660@qq.com
*@motto: Rome was not built in a day
*@Description: servlet抽象类
**/
public abstract class Servlet {

    public abstract void doGet(MyRequest request, MyResponse response) throws Exception;

    public abstract void doPost(MyRequest request, MyResponse response) throws Exception;

    public void service(MyRequest request, MyResponse response) throws Exception {
        if ("GET".equalsIgnoreCase(request.getMethod())){
            this.doGet(request, response);
        }else{
            this.doPost(request, response);
        }
    }
}
