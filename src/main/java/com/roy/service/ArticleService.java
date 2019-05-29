package com.roy.service;

import com.roy.model.Article;

import java.util.List;

public class ArticleService {
    /**
     * 根据cid文章信息
     * @param cid
     * @return
     */
    public List<Article> findListByCid(String cid)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from cms_article where 1=1 ");
        if (cid != null && !cid.equals(""))
        {
            stringBuffer.append("and classify_id = "+cid);
        }
        List<Article> list = Article.dao.find(stringBuffer.toString());
        return list;
    }

    public List<Article> findArticleByTitleAndAuthor(String title, String author){
       return Article.dao.find("select * from cms_article where title = ? and author = ?",title,author);
    }
    public List<Article> findArticleByTitleOrKeywords(String title, String keywords){
        return Article.dao.find("select * from cms_article where  title like ? or keywords like ?",title,keywords);
    }
}
