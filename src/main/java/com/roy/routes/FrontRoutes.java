package com.roy.routes;

import com.jfinal.config.Routes;

public class FrontRoutes extends Routes {
    public void config() {
        // 超类中的方法也被映射为 action,这里配置只对 FrontRoutes 下的路由有效，建议这样配置以提升性能
        setMappingSuperClass(true);
        setBaseViewPath("/view/front");
        // add("/", IndexController.class);
        // add("/blog", BlogController.class);
    }
}