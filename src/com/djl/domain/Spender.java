package com.djl.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="spender")
public class Spender extends BaseObject {

	private static final long serialVersionUID = 8616691615946641575L;
	@Id
	@GeneratedValue(generator="spenderGenerator")
	@GenericGenerator(name="spenderGenerator" , strategy="uuid" )
	@Column(length=32)
	private String id;
	
	@Column(length=80,nullable=false,unique=true)
	private String name ;
	@Column(length=280)
	private String password;
	
	//用户级别0:超级管理员;1:管理员;2:主管;3:普通用户
	@Column(columnDefinition = "int default  '3' ")
	private int level;
	//cascade 级联操作，配置在one的一方-merge=saveorupdate
	//mappedBy="spender":指定外键，可以不生成中间表；fetch=FetchType.EAGER：关闭延时加载
	@OneToMany(cascade=CascadeType.REMOVE , mappedBy="spender", fetch=FetchType.LAZY)	
	private Set<CostInfo> spenders = new HashSet<CostInfo>();
	@OneToMany(cascade=CascadeType.REMOVE ,  mappedBy="accounter", fetch=FetchType.LAZY)	
	private Set<CostInfo> accounters = new HashSet<CostInfo>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public Spender() {}
	
	public Spender(String id , String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
	}
	public Set<CostInfo> getSpenders() {
		return spenders;
	}
	public void setSpenders(Set<CostInfo> spenders) {
		this.spenders = spenders;
	}
	public Set<CostInfo> getAccounters() {
		return accounters;
	}
	public void setAccounters(Set<CostInfo> accounters) {
		this.accounters = accounters;
	}
	
	
	
	

}
