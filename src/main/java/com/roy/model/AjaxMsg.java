package com.roy.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 消息类
 */
public class AjaxMsg {
    /**
     * 状态信息
     */
    private String state;
    /**
     * 提示消息
     */
    private String msg;
    /**
     * 消息对象集
     */
    private Map<String,Object> map = new HashMap<String,Object>();

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    /**
     * 向消息对象集中加入数据
     * <br/>建议value实现序列化
     * @param key
     *            键
     * @param value
     *            值
     */
    public void add(String key,Object value){
        this.map.put(key, value);
    }
}
