package com.roy.controller;

import com.jfinal.core.ActionKey;
import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.template.ext.directive.RenderDirective;
import com.roy.entry.User;
import com.roy.model.Article;
import com.roy.model.Classify;

import java.util.Date;
import java.util.List;

public class UserController extends Controller {
    @ActionKey("user/toUserIndex")
    public void toUserIndex() {
        // finalView = baseViewPath + viewPath + view
        // 注意：当view以 “/” 字符打头时表示绝对路径，baseViewPath 与 viewPath 将被忽略。
        render("user.html");
    }

    public void hello(){
        System.out.println(getPara(0));
        render("user.html");
    }
    // 不希望成为 action，仅供子类调用，或拦截器中调用
    // @NotAction
    public void addClassify() {
// 创建classify
//         Record classify = new Record().set("name", "科技").set("create_time", new Date());
        // boolean result = Db.save("cms_classify", classify);
        boolean result = new Article().set("name", "科技").set("create_time", new Date()).save();

// 删除id值为25的User
//         User.dao.deleteById(25);

// 查询id值为25的User将其name属性改为James并更新到数据库
//         User.dao.findById(25).set("name", "James").update();

// 查询id值为25的user, 且仅仅取name与age两个字段的值
//         User user = User.dao.findByIdLoadColumns(25, "name, age");

// 获取user的name属性
//         String userName = user.getStr("name");

// 获取user的age属性
//         Integer userAge = user.getInt("age");

// 查询所有年龄大于18岁的user
//         List<User> users = User.dao.find("select * from user where age>18");

// 分页查询年龄大于18的user,当前页号为1,每页10个user
//         Page<User> userPage = User.dao.paginate(1, 10, "select *", "from user where age > ?", 18);
        if(result){
            System.out.println("successs");
        }else{
            System.out.println("default");
        }
        render("user.html");
    }


}
