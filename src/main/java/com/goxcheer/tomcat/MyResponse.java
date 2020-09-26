package com.goxcheer.tomcat;

import java.io.OutputStream;

/**
*@Author: goxcheer
*@Date: 10:13 下午 2020/9/22
*@Email: 604721660@qq.com
*@motto: Rome was not built in a day
*@Description: MyResponse响应类
**/
public class MyResponse {

    private OutputStream out;

    public MyResponse(OutputStream out) {
        this.out = out;
    }

    public void write(String s) throws Exception {
        //输出要遵守Http规范
        StringBuffer sb = new StringBuffer();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(s);
        out.write(sb.toString().getBytes());
    }
}
