package com.roy.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.paragetter.Para;
import com.jfinal.json.JFinalJson;
import com.roy.model.AjaxMsg;
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
        render("/view/cms/classifyList.html");
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
        AjaxMsg ajaxMsg = new AjaxMsg();
        ajaxMsg.setState("fail");
        ajaxMsg.setMsg("信息不正确！");
        Long id = getParaToLong(0);
        if(id != null) {
            Classify classify = Classify.dao.findById(id);
            if(classify != null) {
                // 查询子标题
                List<Article> articles= Article.dao.find("select * from cms_article where classify_id = ?",id);
                if (articles.size() > 0) {
                    ajaxMsg.setMsg("请先删除该目录下的文章！");
                }else{
                    boolean result = Classify.dao.deleteById(id);
                    if(result){
                        ajaxMsg.setState("success");
                        ajaxMsg.setMsg("删除成功");
                    }else {
                        ajaxMsg.setState("fail");
                        ajaxMsg.setMsg("删除失败");
                    }
                }
            }
        }
        renderJson(ajaxMsg);
    }

    public void searchClassifyByName() throws UnsupportedEncodingException {
        // 转码
        String name = URLDecoder.decode(getPara(0).trim(),"UTF-8");
        List<Classify> classifies = Classify.dao.find("select * from cms_classify where name=?",name);
        System.out.println("search classify:"+classifies);
        render("/view/common/layout.html");
    }

    public void modifyClassifyById(@Para("classify")Classify classify){
        if(classify != null && classify.getId() != null) {
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
     * 跳转添加目录页面
     */
    public void toAddClassify(){
        render("/view/cms/classifyEdit.html");
    }

    /**
     * 弹出编辑页面
     */
    public void toEditClassify(){
        Classify classify = Classify.dao.findById(getParaToLong(0));
        setAttr("classify", classify).render("/view/cms/classifyEdit.html");
    }

    /**
     * 获取treegrid 初始化数据
     */
    public void classifyList(){

        Classify classify = new Classify();
        classify.setId(0L);
        List<Classify> classifies = this.tree(classify);
        System.out.println(classifies);
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
                    tmp_classify.setCreateTime(classify_parent.getCreateTime());
                    tmp_classify.setUpdateTime(classify_parent.getUpdateTime());

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

    /**
     * 新增或更新栏目信息
     */
    public void saveOrUpdate() {
        AjaxMsg ajaxMsg = new AjaxMsg();
        boolean res = false;
        Classify classify = getModel(Classify.class,"");
        if (classify != null && classify.getId() != null && !classify.getId().equals("")){//更新
            classify.setUpdateTime(new Date());
            res = classify.update();
        }else if(classify != null &&( classify.getId() == null || classify.getId().equals("")) ) {
            int size = classifyService.findClassifyByNameAndPid( classify.getName(), classify.getPid()).size();
            if(size > 0){
                res = true;
                ajaxMsg.setMsg("该栏目已存在");
            }else {
                classify.setCreateTime(new Date());
                res = classify.save();
            }
        }
        if(res){
            ajaxMsg.setState("success");
            if(ajaxMsg.getMsg() == null || ajaxMsg.getMsg().equals("")) {
                ajaxMsg.setMsg("保存成功");
            }
        }else {
            ajaxMsg.setState("fail");
            ajaxMsg.setMsg("保存失败");
        }
        renderJson(ajaxMsg);
    }
}
