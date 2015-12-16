package com.djl.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.djl.domain.CostType;

@Service 
@Transactional(rollbackFor = RuntimeException.class)
public class CostTypeService {
	//注入sessionFactory
	@Resource SessionFactory sessionFactory;
	
	public void save (CostType costType){
		//persist 为持久化的意思，和save功能相同
		sessionFactory.getCurrentSession().persist(costType);
		
	}
	
	public void update(CostType costType){
		//merge为saveOrupdate功能
		sessionFactory.getCurrentSession().merge(costType);
	}
	
	@SuppressWarnings("unchecked")
	//查询不需要事务
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<CostType> list(){
		String hql =" from CostType";
		return sessionFactory.getCurrentSession().createQuery(hql).list();
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public CostType find(int id ){
		String hql = "from CostType where id = "+id;
		return (CostType) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}
	
	public void delete(int id ){
		CostType ct = this.find(id);
		if(ct!=null){
			sessionFactory.getCurrentSession().delete(ct);
		}
	}
}
