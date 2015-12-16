package com.djl.basic;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = RuntimeException.class)
public abstract class BasicService implements BasicServiceInt {

	@Resource
	private SessionFactory sessionFactory;
 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void excuteSave(Object obj) {
		sessionFactory.getCurrentSession().persist(obj);

	}

	private Query setParms(Object[] parms, Query query) {
		if(parms!=null&&parms.length!=0){
			for(int i =0;i<parms.length;i++){
					//为避免parms中存在空，此处必须判断
					if(parms[i]!=null)
						query.setParameter(i, parms[i]);
			}
		}
		return query;
	}
	
	@Override
	public List queryListOfPage(String hql, Object[] parms, int currentPage, int pageSize) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query = setParms(parms, query);
		List list = query.setFirstResult(currentPage*pageSize).setMaxResults(pageSize).list();
		
		return list;
	}

	@Override
	public List queryList(String hql, Object[] parms) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query = setParms(parms, query);
		List list = query.list();
		return list;
	}


	@Override
	public int queryCount(String hqlCount, Object[] parms) {
		Query query = sessionFactory.getCurrentSession().createQuery(hqlCount);
		query = setParms(parms, query);
		String s= query.list().get(0).toString();
		return Integer.parseInt(s);
	}

	@Override
	public Object queryById(Class clazz , Serializable id) {
		return sessionFactory.getCurrentSession().get(clazz, id);
	}

	@Override
	public void excuteUpdate(Object obj) {
		sessionFactory.getCurrentSession().merge(obj);

	}

	@Override
	public void excuteDelete(Class clazz , Serializable id) {
		Object obj = this.queryById(clazz , id);
		if(obj !=null){
			sessionFactory.getCurrentSession().delete(obj);
		}

	}

}
