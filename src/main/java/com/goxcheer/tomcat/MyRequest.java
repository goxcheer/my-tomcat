package com.goxcheer.tomcat;

import java.io.InputStream;

/**
*@Author: goxcheer
*@Date: 10:13 下午 2020/9/22
*@Email: 604721660@qq.com
*@motto: Rome was not built in a day
*@Description: Request请求类
**/
public class MyRequest {

    private String method;

    private String url;

    public MyRequest(InputStream in){
        try{
            String content = "";
            byte[] buff = new byte[1024];
            int len = 0;
            if ((len = in.read(buff)) > 0){
                content = new String(buff, 0, len);
            }
            String line = content.split("\\n")[0];
            String[] arr = line.split("\\s");

            this.method = arr[0];
            this.url = arr[1];
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getUrl(){
        return url;
    }

    public String getMethod() {
        return method;
    }
}
