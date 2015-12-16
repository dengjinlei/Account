package com.djl.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.djl.basic.BasicService;
import com.djl.domain.Page;

@Service @Transactional(rollbackFor=RuntimeException.class)
public class CostInfoService extends BasicService{
	
	 
 
	//需要发出多条查询语句，效率低，基于V1.0进行优化
/*	@Transactional (propagation=Propagation.NOT_SUPPORTED)
	public List<CostInfo> list(){
		
		String hql = "from CostInfo order by spendTime desc";
		@SuppressWarnings("unchecked")
		List<CostInfo> list = sessionFactory.getCurrentSession().createQuery(hql).list();
		return list;  --- 
	}
	
	@Transactional (propagation=Propagation.NOT_SUPPORTED)
	public Page list(Page page , Map<Integer, Object> map){
		
		
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(CostInfo.class);
		if(map!=null&&map.size()!=0){
			if(map.get(0)!=null){
				criteria.add(Restrictions.eq("spendTime", (Date)map.get(0))); 
			}
			if(map.get(1)!=null){
				//进行关联条件查询 inner join 使用createAlias不生成新的criteria
				criteria = criteria.createAlias("costType", "ct");
				criteria.add(Restrictions.eq("ct.amtflag", ((Number)map.get(1)).intValue()));
			}
		}
		 
		 //获得总条数
		 int totalcount =((Integer) criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
		 page.setTotalCount(totalcount);
		 //获得总页数
		 int totalPage = (totalcount %page.getCount()==0) ?  totalcount /page.getCount():( totalcount /page.getCount()+1);
		page.setTotalPage(totalPage);
		
		 criteria.setProjection(null);
		 criteria.addOrder(Order.desc("spendTime")).setFirstResult(page.getFirstRow()).setMaxResults(page.getCount()) ; 
		 
		 @SuppressWarnings("unchecked")
		 //如果在createAlias()之后，又使用了setProjection(如查询总记录数)，这样得到的查询结果集中，每个对象并不是Parent类型，
		 //而是一个对象数组(Object[])，里边有一个Parent对象和一个Child对象，需要根据object的类名去判断是哪个对象。
		 //如果还要恢复默认的结果集状态，需调用
		 //criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		List<CostInfo> list = criteria.setResultTransformer(Criteria.ROOT_ENTITY).list();
		
		page.setList(list);
		 
		return page;
	}
	*/
	
	@Transactional (propagation=Propagation.NOT_SUPPORTED)
	public Page list(Page page , Map map){
		//为参数设定脚标
		int i =0;
		Object []parms= new Object[2];
		StringBuffer hql = new StringBuffer();
		StringBuffer hqlCount = new StringBuffer();
		hql.append("select id , spendTime , spender.name , costType.name , accounter.name , comment , amt , costType.amtflag  from CostInfo  ");
		hqlCount.append("select count(id) from CostInfo  ");
		if(map!=null&&map.size()!=0){
			hql.append(" where 1= 1 ");
			hqlCount.append(" where 1= 1 ");
			if(map.get("spendTime")!=null){
				hql.append(" and spendTime = ? ");
				hqlCount.append(" and spendTime = ? ");
				parms[i]=map.get("spendTime");
				i++;
			}
			if(map.get("amtFlag")!=null){
				hql.append(" and costType.amtflag = ? ");
				hqlCount.append(" and costType.amtflag = ? ");
				parms[i]=map.get("amtFlag");
				i++;
			}
		}
		hql.append(" order by spendTime desc ");
		
		int totalCount = this.queryCount(hqlCount.toString(), parms);
		page.setTotalCount(totalCount);
		//获得总页数
		 int totalPage = (totalCount %page.getSize()==0) ?  totalCount /page.getSize():( totalCount /page.getSize()+1);
		page.setTotalPage(totalPage);
		List list = this.queryListOfPage(hql.toString(), parms, page.getCurrentPage(), page.getSize());
		page.setList(list);
		return page;
	}
	
	@Transactional (propagation=Propagation.NOT_SUPPORTED)
	public List<Object[]> list(){
		
		String hql = "select id , spendTime , spender.name , costType.name , accounter.name , comment , amt , costType.amtflag  from CostInfo order by spendTime desc";
		@SuppressWarnings("unchecked")
		List<Object[]> list = this.queryList(hql, null);
		return list;
	}
	
	
	/**
	 * 在Hibernate2.0之前版本list.get(0)返回的是Integer类型. 
	 * 但是在Hibernate3.0以后版本list.get(0)返回的是Long类型. 
	 * Integer属于不可更改类型，而且Long和Integer没有任何继承关系，所有不能互相转换
	 * 但可以通过Number解决 
	 * 注：java.lang.Number是Integer,Long的父类. 
	 */
//	@Transactional (propagation=Propagation.NOT_SUPPORTED)
/*	public Integer getCount(){
		String hql = "select count(*) as c  from CostInfo ";
		@SuppressWarnings("unchecked")
		List<Integer> list= sessionFactory.getCurrentSession().createQuery(hql).list();
		Number totalCount  =(Number)list.get(0);
		return  totalCount.intValue();
		
	}*/
	

	
}
