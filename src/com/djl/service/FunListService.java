package com.djl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.djl.basic.BasicService;
import com.djl.domain.FunList;
import com.djl.domain.Page;

@Service
public class FunListService extends BasicService  {

	public List<FunList> listOfPage(int pageNo){
		
		String hql = "from FunList  order by position" ; 
		List<FunList> list = queryListOfPage(hql, null, pageNo, new Page().getSize());
		return list;
	}
	
	public int getTotalCount(){
		String hql = "select count(id) from FunList ";
		int totolCount = queryCount(hql, null);	
		return totolCount;
	}
		

}
