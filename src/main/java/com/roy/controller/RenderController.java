package com.roy.controller;

import com.jfinal.core.Controller;

public class RenderController extends Controller {

    /**
     * 在一个action中多次调用render方法只有最后一次有效。
     *
     * 除renderError方法以外，在调用render系列的方法后程序并不会立即返回，如果需要立即返回需要使用return语句。
     */
    public void renderView(){

        // 渲染名为test.html的视图，且视图类型为 JFinal Template
        // renderTemplate("test.html");

        // 生成二维码
        renderQrCode("Name:Roy LuoTel:15579857205Gender:Male",125,125);

        // 渲染名为test.html的视图，且视图类型为FreeMarker
        // renderFreeMarker("test.html");

        // 渲染名为test.html的视图，且视图类型为Velocity
        // renderVelocity("test.html");
    }
}
