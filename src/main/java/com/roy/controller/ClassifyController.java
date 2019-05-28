package com.roy.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.json.JFinalJson;
import com.roy.model.Article;
import com.roy.model.Classify;
import com.roy.model.Ztree;
import com.roy.service.ClassifyService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClassifyController extends Controller {

    private static ClassifyService classifyService = new ClassifyService();

    public void index(){
        render("/view/cms/articleList.html");
    }

    public void addClassify(){
        Classify classify = getModel(Classify.class,"");
        if(classify != null) {
            int size = Classify.dao.find("select count(*) from cms_classify where pid = ? and name = ?", classify.getPid(), classify.getName()).size();
            if(size > 0){
                System.out.println("该目录已存在");
            }else {
                classify.setCreateTime(new Date());
                boolean result = classify.save();
                System.out.println("add classify:" + result);
            }
        }
        render("/view/common/layout.html");
    }

    public void deleteClassifyById(){
        Long id = getParaToLong(0);
        if(id != null) {
            Classify classify = Classify.dao.findById(id);
            if(classify != null) {
                // 查询子标题
                List<Classify> classifies= Classify.dao.find("select count(id) from cms_classify where pid = ?",id);
                if (classifies.size() > 0) {
                    System.out.println("请先删除子标题！");
                }else{
                    boolean result = Classify.dao.deleteById(id);
                    System.out.println("delete classify:" + result);
                }
            }
        }
        render("/view/common/layout.html");
    }

    public void searchClassifyByName() throws UnsupportedEncodingException {
        // 转码
        String name = URLDecoder.decode(getPara(0).trim(),"UTF-8");
        List<Classify> classifies = Classify.dao.find("select * from cms_classify where name=?",name);
        System.out.println("search classify:"+classifies);
        render("/view/common/layout.html");
    }

    public void modifyClassifyById(@Para("classify")Classify classify){
        if(classify != null) {
            classify.setUpdateTime(new Date());
            boolean result = classify.update();
            System.out.println("modify classify:" + result);
        }
        render("/view/common/layout.html");
    }
    public void searchClassifyById() {
        List<Classify> classifies = Classify.dao.find("select * from cms_classify where id = ?",get("cid"));
        Classify classify = null;
        if(classifies != null && classifies.size() > 0){
            classify = classifies.get(0);
        }
        renderJson(JFinalJson.getJson().toJson(classify));
    }
    /**
     * 获取treegrid数据
     */
    public void classifyList(){

        Classify classify = new Classify();
        classify.setId(0L);
        List<Classify> classifies = this.tree(classify);
        renderJson(JFinalJson.getJson().toJson(classifies));
    }

    /**
     * 使用递归查询树形菜单
     * @param classify
     * @return
     */
    List<Classify> root = new ArrayList<Classify>();
    private List<Classify> tree(Classify classify){

        Classify tmp_classify;
        List<Classify> classify_childs = new ArrayList<Classify>();
        List<Classify> classify_parents= classifyService.findChildList(classify);
        if (classify_parents != null && classify_parents.size()>0){
            for (Classify classify_parent:classify_parents){
                if (classify_parent != null){
                    tmp_classify = new Classify();
                    tmp_classify.setId(classify_parent.getId());
                    tmp_classify.setName(classify_parent.getName());
                    tmp_classify.setPid(classify_parent.getPid());

                    if (tmp_classify.getPid() == 0){//根目录
                        //递归调用之前先添加到全局list
                        root.add(tmp_classify);
                        //递归调用
                        this.tree(tmp_classify);
                    } else{
                        classify_childs.add(tmp_classify);
                        //递归调用
                        this.tree(tmp_classify);
                    }
                }
            }

        }
        classify.setChildren(classify_childs);
        return root;
    }


    /**
     * 文章列表左侧树形菜单
     */
    public void article_ztree_list()
    {
        List<Classify> list = classifyService.findList(new Classify());
        Ztree ztree = null;
        List<Ztree>  ztreelist = new ArrayList<>();
        for (Classify classify:list)
        {
            ztree = new Ztree();
            ztree.setId(classify.getId().toString());
            ztree.setName(classify.getName());
            ztree.setpId(classify.getPid().toString());
            ztreelist.add(ztree);
        }
        renderJson(JFinalJson.getJson().toJson(ztreelist));
    }
}
