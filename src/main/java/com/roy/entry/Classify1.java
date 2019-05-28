package com.roy.entry;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

public class Classify1 extends Model<Classify1> {
	Long id;
	String name;
	String alias_name;
	int seq;
	long pid;
	Date create_time;
	Date update_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAliasName() {
		return alias_name;
	}

	public void setAliasName(String alias_name) {
		this.alias_name = alias_name;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public Date getCreateTime() {
		return create_time;
	}

	public void setCreateTime(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdateTime() {
		return update_time;
	}

	public void setUpdateTime(Date updateTime) {
		this.update_time = updateTime;
	}

	public Classify1() {
	}

	@Override
	public String toString() {
		return "Classify{" +
				"id=" + id +
				", name='" + name + '\'' +
				", alias_name='" + alias_name + '\'' +
				", seq=" + seq +
				", pid=" + pid +
				", create_time=" + create_time +
				", updateTime=" + update_time +
				'}';
	}
}
