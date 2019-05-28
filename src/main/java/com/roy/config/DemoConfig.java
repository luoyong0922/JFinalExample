package com.roy.config;

import com.jfinal.config.*;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.redis.RedisPlugin;
import com.jfinal.render.ViewType;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;
import com.roy.controller.HelloController;
import com.roy.model.Article;
import com.roy.model.Classify;
import com.roy.routes.AdminRoutes;
import com.roy.routes.FrontRoutes;

public class DemoConfig extends JFinalConfig {

    public static void main(String[] args) {
        UndertowServer.start(DemoConfig.class, 80, true);
    }

    public void configConstant(Constants me) {
         // 此方法用来配置JFinal常量值，如开发模式常量devMode的配置，如下代码配置了JFinal运行在开发模式
        // 在开发模式下，JFinal会对每次请求输出报告，如输出本次请求的URL、Controller、Method以及请求所携带的参数。
        // me.setDevMode(true);
        // 第一次使用use加载的配置将成为主配置，可以通过PropKit.get(...)直接取值
        PropKit.use("a_little_config.txt");
        me.setDevMode(PropKit.getBoolean("devMode"));
        me.setViewType(ViewType.JFINAL_TEMPLATE);
    }

    public void configRoute(Routes me) {
        // 如果要将控制器超类中的 public 方法映射为 action 配置成 true，一般不用配置。 该功能属于性能优化，拥有大量路由的大型项目可加快启动速度。该配置如果配置在 "子Routes" 中，将只对该 "子Routes" 有效
        me.setMappingSuperClass(false);
        me.add(new FrontRoutes());  // 前端路由
        me.add(new AdminRoutes());  // 后端路由
        me.add("/hello", HelloController.class);
    }

    public void configEngine(Engine me) {
        me.setDevMode(true);
        // 向模板引擎中添加了两个定义了 template function 的模板文件
        me.addSharedFunction("/view/common/layout.html");
        me.addSharedFunction("/view/admin/common/layout.html");
    }
    public void configPlugin(Plugins me) {
// 非第一次使用 use加载的配置，也可以先得到一个Prop对象，再通过该对象来获取值
        Prop p = PropKit.use("db_config.txt");
        DruidPlugin dp = new DruidPlugin(p.get("jdbcUrl"), p.get("userName"), p.get("password"));
        me.add(dp);

        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        // 必须添加映射，否则操作数据库时会报空指针
        // arp.addMapping("t_user", User.class);
        arp.addMapping("cms_article",Article.class);
        arp.addMapping("cms_classify",Classify.class);
        me.add(arp);

        // 非第一次使用use加载的配置，需要通过每次使用use来指定配置文件名再来取值
        // String redisHost = PropKit.use("redis_config.txt").get("host");
        // int redisPort = PropKit.use("redis_config.txt").getInt("port");
        // RedisPlugin rp = new RedisPlugin("myRedis", redisHost, redisPort);
        // me.add(rp);

        // 如上代码所示，PropKit可同时加载多个配置文件，第一个被加载的配置文件可以使用PorpKit.get(…)方法直接操作，非第一个被加载的配置文件则需要使用PropKit.use(…).get(…)来操作。

        // PropKit 的使用并不限于在 YourJFinalConfig 中，可以在项目的任何地方使用。此外PropKit.use(…)方法在加载配置文件内容以后会将数据缓存在内存之中，可以通过PropKit.useless(…)将缓存的内容进行清除。

    }
    public void configInterceptor(Interceptors me) {
        // 此方法用来配置JFinal的全局拦截器，全局拦截器将拦截所有 action 请求，除非使用@Clear在Controller中清除
        // me.add(new AuthInterceptor());
        // JFinal 的 Interceptor 非常类似于 Struts2，但使用起来更方便，Interceptor 配置粒度分为 Global、Inject、Class、Method四个层次，其中以上代码配置粒度为全局
    }
    public void configHandler(Handlers me) {
        // 此方法用来配置JFinal的Handler，Handler可以接管所有web请求，并对应用拥有完全的控制权，可以很方便地实现更高层的功能性扩展。
        // me.add(new ResourceHandler());
        //配置项目的上下文，页面中用${ctx}表示
        me.add(new ContextPathHandler("ctx"));
    }

    // 系统启动完成后回调
    public void onStart() {
        System.out.println("Project is starting!");
    }

    // 系统关闭之前回调
    public void onStop() {
        System.out.println("Project is stopped!");
    }

}