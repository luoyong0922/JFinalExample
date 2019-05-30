package com.roy.routes;

import com.jfinal.config.Routes;
import com.roy.controller.ArticleController;
import com.roy.controller.ClassifyController;
import com.roy.controller.UserController;

public class AdminRoutes extends Routes {

    @Override
    public void config() {

        setBaseViewPath("/view/cms");
        // addInterceptor(new AdminInterceptor());
        // add("/admin",AdminController.class);
        //  第三个参数viewPath是指该Controller返回的视图的相对路径(该参数具体细节将在Controller相关章节中给出)。当viewPath未指定时默认值为controllerKey。
        add("/front",UserController.class);
        add("/classify",ClassifyController.class,"/");
        add("/article",ArticleController.class,"/");
    }
}
