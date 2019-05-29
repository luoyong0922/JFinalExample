package com.roy.service;

import com.roy.model.Classify;

import java.util.List;

public class ClassifyService {
    /**
     * 根据id获取子菜单
     * @param classify
     * @return
     */
    public List<Classify> findChildList(Classify classify)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from cms_classify where 1=1 ");
        if (classify.getId() != null)
        {
            stringBuffer.append("and pid = "+classify.getId());
        }
        List<Classify> list = Classify.dao.find(stringBuffer.toString());
        return list;
    }

    /**
     * 根据id获取信息
     * @param classify
     * @return
     */
    public List<Classify> findList(Classify classify)
    {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from cms_classify where 1=1 ");
        if (classify.getPid() != null && !classify.getPid().equals(""))
        {
            stringBuffer.append("and pid = "+classify.getPid());
        }

        List<Classify> list = Classify.dao.find(stringBuffer.toString());
        return list;
    }

    public List<Classify> findClassifyByNameAndPid(String name, Long pid) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select * from cms_classify where 1=1 ");
        if (name != null && !name.equals(""))
        {
            stringBuffer.append("and name = "+ "'" + name + "'");
        }
        if (pid != null)
        {
            stringBuffer.append(" and pid = "+pid);
        }
        List<Classify> list = Classify.dao.find(stringBuffer.toString());
        return list;
    }
}
