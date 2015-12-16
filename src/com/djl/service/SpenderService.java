package com.djl.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.djl.basic.BasicService;
import com.djl.domain.Spender;
import com.djl.service.inter.SpenderServiceInt;

@Service
public class SpenderService extends BasicService implements SpenderServiceInt{
	

	@SuppressWarnings("unchecked")
//	查询不需要事务
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public List<Spender> list(Object []parms) {
		String hql ="from Spender ";
		return this.queryList(hql, null);
	}
	
	
	//检查用户名是否重复
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String checkUnique(String name){
		String msg ="";
		String hql = "from Spender where name= ? ";
		@SuppressWarnings("unchecked")
		List<Spender> sps = getSessionFactory().getCurrentSession().createQuery(hql).setString(0, name).list();
		if(sps.size()==0){
			msg="用户名可以使用";
		}else
				msg="用户名重复";
		//清除session，防止再次验证时查询缓存
		//※※※※日后如果需要把登录信息放入session，可能也会被清除！！！！！！！
		//已经证实无影响
		getSessionFactory().getCurrentSession().clear();
		return msg;
	}
}
