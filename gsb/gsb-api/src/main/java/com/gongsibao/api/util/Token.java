package com.gongsibao.api.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.netsharp.core.BusinessException;
import org.netsharp.util.EncrypUtil;

public class Token {

	/**
	 * 生成Token
	 * @param srcData
	 * @return
	 */
	public static String create(Map<String, Object> srcData) {

		if (null == srcData) {

			throw new BusinessException("传入参数为空");
		}
		List<Map.Entry<String, Object>> list = new ArrayList<Map.Entry<String, Object>>(srcData.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Object>>() {

			public int compare(Entry<String, Object> o1, Entry<String, Object> o2) {
				return o1.getKey().compareTo(o2.getKey());
			}
		});

		StringBuffer srcSb = new StringBuffer();
		for (Map.Entry<String, Object> srcAtom : list) {
			srcSb.append(String.valueOf(srcAtom.getValue()));
		}
		System.out.println("身份验证加密前字符串：" + srcSb.toString());
		String token = EncrypUtil.md5(srcSb.toString());
		return token;
	}
	
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 */
//	public static User getUser(String token){
//	
//		return null;
//	}
}
