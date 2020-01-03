package com.sachin.commons.util;

import org.springframework.beans.BeanUtils;

public class EntityUtils<U, M> {
	
	private U u;
	private M m;
	
	public M copyProperties(U u, M m) {
		
		BeanUtils.copyProperties(u, m);
		return m;
		
	}

}
