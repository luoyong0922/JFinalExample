package com.roy.model.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings({"serial", "unchecked"})
public abstract class BaseClassify<M extends BaseClassify<M>> extends Model<M> implements IBean {

	public M setId(java.lang.Long id) {
		set("id", id);
		return (M)this;
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public M setName(java.lang.String name) {
		set("name", name);
		return (M)this;
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public M setAliasName(java.lang.String aliasName) {
		set("alias_name", aliasName);
		return (M)this;
	}
	
	public java.lang.String getAliasName() {
		return getStr("alias_name");
	}

	public M setSeq(java.lang.Integer seq) {
		set("seq", seq);
		return (M)this;
	}
	
	public java.lang.Integer getSeq() {
		return getInt("seq");
	}

	public M setPid(java.lang.Long pid) {
		set("pid", pid);
		return (M)this;
	}
	
	public java.lang.Long getPid() {
		return getLong("pid");
	}

	public M setCreateTime(java.util.Date createTime) {
		set("create_time", createTime);
		return (M)this;
	}
	
	public java.util.Date getCreateTime() {
		return get("create_time");
	}

	public M setUpdateTime(java.util.Date updateTime) {
		set("update_time", updateTime);
		return (M)this;
	}
	
	public java.util.Date getUpdateTime() {
		return get("update_time");
	}

}
