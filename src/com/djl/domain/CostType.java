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
@Table(name="costtype")
public class CostType extends BaseObject{
	private static final long serialVersionUID = -3706472168793141911L;
	//标示为主键、 主键生成策略
	@Id
	@GeneratedValue(generator = "costtypeGenerator")     
	@GenericGenerator(name = "costtypeGenerator", strategy = "increment")  
	private int id;
	@Column(length=80,nullable=false,unique=true)
	private String name;
	@Column(length=240)
	private String context;
	//账目方向：1:收入 -1:支出
	@Column(columnDefinition = "int default  '-1' ")
	private int amtflag;
	
	//cascade 级联操作，配置在one的一方-merge=saveorupdate   /fetch=FetchType.LAZY
	@OneToMany(cascade=CascadeType.REMOVE , fetch=FetchType.LAZY , mappedBy="costType")
	private Set<CostInfo> costInfos = new HashSet<CostInfo>();

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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Set<CostInfo> getCostInfos() {
		return costInfos;
	}
	public void setCostInfos(Set<CostInfo> costInfos) {
		this.costInfos = costInfos;
	}
	public CostType() {
		
	}
	public CostType(int id , String name, String context) {
		this.id = id;
		this.name = name;
		this.context = context;
	}
	public long getAmtflag() {
		return amtflag;
	}
	public void setAmtflag(int amtflag) {
		this.amtflag = amtflag;
	}
	
}
