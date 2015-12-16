package com.djl.service;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.djl.basic.BasicService;
import com.djl.domain.FunList;
import com.djl.domain.Spender;

@Service
public class LoginService extends BasicService{
	
	
	
	public boolean check(Spender user){
		String hql = "from Spender where name = ? and password = ?";
		Query q = getSessionFactory().getCurrentSession().createQuery(hql);
		q.setString(0, user.getName());
		q.setString(1, user.getPassword());
		Spender s  = (Spender) q.uniqueResult();
		if(s!=null){
			return true;
		}else{
			return false;
		}
		
	}
	public Spender getUser(Spender user){
		String hql = "from Spender where name =?";
		Query q = getSessionFactory().getCurrentSession().createQuery(hql);
		q.setString(0, user.getName());
		Spender s  = (Spender) q.uniqueResult();
		return s;
	}
	
	public List<FunList> showList(int level){
		
		String hql = "from FunList where status = ? and level >=? order by position" ;
		Object []obj = new Object[2];
		obj[0]=0;
		obj[1]=level;
		List<FunList> list = queryList(hql, obj) ;
		return list;
	}
	
}
