package com.goxcheer;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public Map<String,String> methodA() {
        Map<String,String> params = new HashMap<>();
        //方式1
        String res = methodB();
        params.put("res", res);

        //方式2
        methodC(params);

        return params;

    }

    private void methodC(Map<String, String> params) {
        params.put("res", "hello");
    }

    private String methodB() {
        return "hello";
    }


}
