package com.roy.controller;

import com.jfinal.core.Controller;
import com.roy.model.AjaxMsg;
import com.roy.model.Article;
import com.roy.service.ArticleService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleController extends Controller {
    private static ArticleService articleService = new ArticleService();

    public void index(){
        render("/view/cms/articleList.html");
    }
    /**
     * 根据类别id查询文章信息
     */
    public void listArticleByCid(){
        List<Article> list = articleService.findListByCid(get("cid"));
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", list.size());
        result.put("data", list);
        renderJson(result);
    }

    /**
     * 根据文章id查询文章信息
     */
    public void listArticleById(){
        Article article = Article.dao.findById(getParaToLong(0));
        renderJson(article);
    }

    /**
     * 弹出编辑页面
     */
    public void editArticle(){
        Article article = Article.dao.findById(getParaToLong(0));
        setAttr("article", article).render("/view/cms/aritcleEdit.html");
    }

    /**
     * 跳转添加文章页面
     */
    public void toAddArticle(){
        render("/view/cms/aritcleEdit.html");
    }

    /**
     * 根据 文章id 删除文章
     */
    public void deleteArticleById(){
        AjaxMsg ajaxMsg = new AjaxMsg();
        ajaxMsg.setState("fail");
        ajaxMsg.setMsg("信息不正确！");
        Long id = getParaToLong(0);
        if(id != null) {
            Article article = Article.dao.findById(id);
            if(article != null) {
                boolean result = Article.dao.deleteById(id);
                if(result){
                    ajaxMsg.setState("success");
                    ajaxMsg.setMsg("删除成功");
                }else {
                    ajaxMsg.setState("fail");
                    ajaxMsg.setMsg("删除失败");
                }
            }
        }
        renderJson(ajaxMsg);
    }

    /**
     * 根据标题或关键字模糊搜索文章信息
     * @throws UnsupportedEncodingException
     */
    public void searchArticleByTitle() throws UnsupportedEncodingException {
        // 转码  %E6%96%87  -->  文
        String title = URLDecoder.decode(getPara("title").trim(),"UTF-8");
        String condition = "%"+title+"%";
        List<Article> articles = articleService.findArticleByTitleOrKeywords(condition , condition );
        System.out.println("search article:"+articles);
        Map<String,Object> result = new HashMap<String, Object>();
        result.put("code", 0);
        result.put("msg", "");
        result.put("count", articles.size());
        result.put("data", articles);
        renderJson(result);
    }

    // public void addArticle(@Para("article")Article article){
    //     if(article != null) {
    //         int size = Article.dao.find("select count(*) from cms_article where title = ? and author = ?", article.getTitle(), article.getAuthor()).size();
    //         if(size > 0){
    //             System.out.println("该文章已存在");
    //         }else {
    //             article.setCreateTime(new Date());
    //             boolean result = article.save();
    //             System.out.println("add article:" + result);
    //         }
    //     }
    //     render("/view/common/layout.html");
    // }
    //
    // public void modifyArticleById() {
    //     boolean result = false;
    //     AjaxMsg ajaxMsg = new AjaxMsg();
    //     Article article = getModel(Article.class,"");
    //     if (article != null) {
    //         article.setUpdateTime(new Date());
    //         result = article.update();
    //         System.out.println("modify article:" + result);
    //     }
    //     if (result) {
    //         ajaxMsg.setState("success");
    //         ajaxMsg.setMsg("修改成功");
    //     } else {
    //         ajaxMsg.setState("fail");
    //         ajaxMsg.setMsg("修改失败");
    //     }
    //     renderJson(ajaxMsg);
    // }

    /**
     * 新增或更新文章信息
     */
    public void saveOrUpdate() {
        AjaxMsg ajaxMsg = new AjaxMsg();
        boolean res = false;
        Article article = getModel(Article.class,"");
        if (article != null && article.getId() != null && !article.getId().equals("")){//文章更新
            article.setUpdateTime(new Date());
            res = article.update();
        }else if(article != null &&( article.getId() == null || article.getId().equals("")) ) {
            int size = articleService.findArticleByTitleAndAuthor( article.getTitle(), article.getAuthor()).size();
            if(size > 0){
                res = true;
                ajaxMsg.setMsg("该文章已存在");
            }else {
                article.setCreateTime(new Date());
                res = article.save();
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
