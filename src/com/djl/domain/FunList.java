package com.djl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 功能列表bean
 * @author djl
 *
 */
@Entity
@Table(name="funlist")
public class FunList extends BaseObject{
	 
	private static final long serialVersionUID = 4912450522801180077L;
	@Id
	@GeneratedValue(generator="funlistgenreator")
	@GenericGenerator(name="funlistgenreator" , strategy="increment")
	private int id;
	@Column(length=80 , nullable=false , unique = true)
	private String name;
	@Column(length=100)
	private String url;
	// 页面访问级别  0:超级管理员;1:管理员;2:主管;3:普通用户
	@Column(  columnDefinition="int default 3")
	private int level;
	//功能顺序
	@Column(columnDefinition="double(10 , 2) default '0.00' ")
	private double  position;
	//启用状态 0：启用；1：停用
	@Column  (columnDefinition="int default 0")
	private int status;
	@Column(length=120)
	private String context;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public double getPosition() {
		return position;
	}
	public void setPosition(double position) {
		this.position = position;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}

}
