package com.djl.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="costInfo")
public class CostInfo extends BaseObject {

	private static final long serialVersionUID = 6148582256560704419L;
	//花费信息id
	@Id 
	@GeneratedValue(generator="costInfoGenerator")
	@GenericGenerator(name="costInfoGenerator" , strategy="uuid")
	@Column(length=32)
	private String id ;
	//花费人spid
	@Transient	   //表示非数据库字段
	private String spid;
	@ManyToOne
	//name:本表字段名称，referencedColumnName：对应外键表字段名称
	@JoinColumn(name="spid" , referencedColumnName="id")
	private Spender spender;
	//花费时间
	@Column(length=10 , columnDefinition="DATE")
	private Date spendTime;
	
	//花费种类ctid
	@Transient	   //表示非数据库字段
	private int ctid;
	@ManyToOne
	@JoinColumn(name="ctid" ,   referencedColumnName="id" )
	private  CostType costType;
	
	//记账人acid
	@Transient	   //表示非数据库字段
	private String acid;
	@ManyToOne
	@JoinColumn(name="acid" ,  referencedColumnName="id" )
	private Spender accounter;
	
	//备注
	@Column(length=240)
	private String comment;
	
	//金额
	@Column(columnDefinition="double(10 , 2) default '0.00' ")
	private double amt;
	
	
	//为list查询优化使用
	@Transient //账单人名称
	private String  spname;
	@Transient //记账人名称
	private String acname;
	@Transient //账单种类名称
	private String ctname;
	
	//金额方向：1:收入 -1:支出 作为list列表显示使用
	@Transient
	private int amtflag;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Spender getSpender() {
		return spender;
	}
	public void setSpender(Spender spender) {
		this.spender = spender;
	}
	public Date getSpendTime() {
		return spendTime;
	}
	public void setSpendTime(Date spendTime) {
		this.spendTime = spendTime;
	}

	public Spender getAccounter() {
		return accounter;
	}
	public void setAccounter(Spender accounter) {
		this.accounter = accounter;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public CostType getCostType() {
		return costType;
	}
	public void setCostType(CostType costType) {
		this.costType = costType;
	}
	
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public int getCtid() {
		return ctid;
	}
	public void setCtid(int ctid) {
		this.ctid = ctid;
	}
	public String getAcid() {
		return acid;
	}
	public void setAcid(String acid) {
		this.acid = acid;
	}
	public String getSpname() {
		return spname;
	}
	public void setSpname(String spname) {
		this.spname = spname;
	}
	public String getAcname() {
		return acname;
	}
	public void setAcname(String acname) {
		this.acname = acname;
	}
	public String getCtname() {
		return ctname;
	}
	public void setCtname(String ctname) {
		this.ctname = ctname;
	}
	public double getAmt() {
		return amt;
	}
	public void setAmt(double amt) {
		this.amt = amt;
	}
	public int getAmtflag() {
		return amtflag;
	}
	public void setAmtflag(int amtflag) {
		this.amtflag = amtflag;
	}
	
	public CostInfo() {
	}
	public CostInfo( Spender spender, Date spendTime,
			 CostType costType,  Spender accounter,
			String comment, double amt ) {
		this.spender = spender;
		this.spendTime = spendTime;
		this.costType = costType;
		this.accounter = accounter;
		this.comment = comment;
		this.amt = amt;
	}

	
}
