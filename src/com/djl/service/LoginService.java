package com.djl.service;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.djl.basic.BasicService;
import com.djl.domain.FunList;
import com.djl.domain.Spender;

@Service
public class LoginService extends BasicService{
	
	
	
//	public boolean check(Spender user){
//		String hql = "from Spender where name = ? and password = ?";
//		Query q = getSessionFactory().getCurrentSession().createQuery(hql);
//		q.setString(0, user.getName());
//		q.setString(1, user.getPassword());
//		Spender s  = (Spender) q.uniqueResult();
//		if(s!=null){
//			return true;
//		}else{
//			return false;
//		}
		
//	}
	public Spender getUser(String username){
		String hql = "from Spender where name =?";
		Query q = getSessionFactory().getCurrentSession().createQuery(hql);
		q.setString(0, username);
		Spender s  = (Spender) q.uniqueResult();
		return s;
	}
	
	public List<FunList> showList(int level){
		
		String hql = "from FunList where status = ? ";
		//系统管理员，只能看到功能管理
		if(level==1){
			hql+=" and level = ?";
		//企业管理员，除功能管理都可以看到
		}else if(level ==2){
			hql+=" and level >=?";
		//普通用户不能看到功能和用户管理
		}else {
			hql+=" and level = ? ";
		}
		hql +=" order by position" ;
		Object []obj = new Object[2];
		obj[0]=0;
		obj[1]=level;
		List<FunList> list = queryList(hql, obj) ;
		return list;
	}
	
}
