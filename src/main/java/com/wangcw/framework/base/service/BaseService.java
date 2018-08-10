package com.wangcw.framework.base.service;

import org.springframework.stereotype.Service;

import java.util.List;

/**
  *@description   All Service extends BaseService
  *@author        王财文
  *@version       1.0 2018-3-27
  */


@Service
public class BaseService {
	
	/**
	 * 返回唯一结果
	 * @return
	 */
	public <T> T uniqueResult(List list){
		return ((list==null||list.size()==0)?null:(T)list.get(0));
	}
	
	/**
	 * 判断字符串是否不为空或null
	 * @param str 字符串对象
	 * @return 是否不为空
	 */
	public boolean isNotNullAndEmpty(Object str){
		return str!=null&&(!str.equals(""));
	}
}
