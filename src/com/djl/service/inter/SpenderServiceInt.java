package com.djl.service.inter;

import java.util.List;

import com.djl.basic.BasicServiceInt;
import com.djl.domain.Spender;

public interface SpenderServiceInt extends BasicServiceInt {
		public List<Spender> list(Object []parms) ;
//	public Spender find(String id );
	public String checkUnique(String name);
}
