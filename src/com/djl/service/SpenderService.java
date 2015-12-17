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
	public List<Spender> list(int level) {
		String hql ="from Spender  ";
		//系统管理员，只能看到自己
		if(level==1){
			hql+="where level = ?";
		//企业管理员，可以看到自己和普通用户
		}else if(level ==2){
			hql+="where level >=?";
		//普通用户只能看到自己
		}else {
			hql+="where level = ? ";
		}
		Object [] obj ={level};
		return this.queryList(hql,obj );
	}
	
	
	/**
	 * 检查用户名是否重复
	 * @param name 用户名
	 * @return yes:用户名可用 ；no:用户名不可用
	 * @author  dengjinlei
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public String checkUnique(String name){
		String msg ="";
		String hql = "from Spender where name= ? ";
		@SuppressWarnings("unchecked")
		List<Spender> sps = getSessionFactory().getCurrentSession().createQuery(hql).setString(0, name).list();
		if(sps.size()==0){
			msg="yes";
		}else
				msg="no";
		//清除session，防止再次验证时查询缓存
		//※※※※日后如果需要把登录信息放入session，可能也会被清除！！！！！！！
		//已经证实无影响
		getSessionFactory().getCurrentSession().clear();
		return msg;
	}
}
