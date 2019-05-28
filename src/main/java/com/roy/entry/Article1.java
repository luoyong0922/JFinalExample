package com.roy.entry;

import com.jfinal.plugin.activerecord.Model;

import java.util.Date;

public class Article1 extends Model<Article1> {
	Long id;
	String title;
	String aliasTitle;
	String author;
	int type;
	Long classifyId;
	String sourceName;
	String sourceUrl;
	String summary;
	String keywords;
	String content;
	int isHot;
	String pic;
	Date createTime;
	Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAliasTitle() {
		return aliasTitle;
	}

	public void setAliasTitle(String aliasTitle) {
		this.aliasTitle = aliasTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(Long classifyId) {
		this.classifyId = classifyId;
	}

	public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsHot() {
		return isHot;
	}

	public void setIsHot(int isHot) {
		this.isHot = isHot;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Article1() {
	}

	@Override
	public String toString() {
		return "Article{" +
				"id=" + id +
				", title='" + title + '\'' +
				", aliasTitle='" + aliasTitle + '\'' +
				", author='" + author + '\'' +
				", type=" + type +
				", classifyId=" + classifyId +
				", sourceName='" + sourceName + '\'' +
				", sourceUrl='" + sourceUrl + '\'' +
				", summary='" + summary + '\'' +
				", keywords='" + keywords + '\'' +
				", content='" + content + '\'' +
				", isHot=" + isHot +
				", pic='" + pic + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				'}';
	}
}
