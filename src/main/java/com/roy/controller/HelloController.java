package com.roy.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;

public class HelloController extends Controller{
    public void index() {
        renderText("Welcome To JFinal World.");
    }
    // Action可以有返回值，返回值可在拦截器中通过invocation.getReturnValue() 获取到，以便进行render控制。
    public String test() {
        return "index.html";
    }
}
