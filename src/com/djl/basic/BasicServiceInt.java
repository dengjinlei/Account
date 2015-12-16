package com.djl.basic;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("rawtypes")
public interface BasicServiceInt {
	
	//1、保存
	public void excuteSave(Object obj);
	
	//2、分页显示 hql语句示例：select * from Object where 1=1 and  name = ? and ... 
	public List queryListOfPage(String hql , Object []parms , int  currentPage ,int pageSize);
	
	//3、显示
	public List queryList(String hql , Object []parms );
	
	//4、得到条数
	public int queryCount(String hqlCount , Object []parms  );
	
	//5、通过id查找
	public Object queryById(Class clazz , Serializable id );
	
	//6、更新
	public void excuteUpdate(Object obj);
	
	//7、删除
	public void excuteDelete(Class clazz , Serializable id);

	
	
	
	
	
}
