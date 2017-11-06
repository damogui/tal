package com.gongsibao.taurus.api;

public class ApiFactory {

	@SuppressWarnings("unchecked")
	public static <T> T create(Class<?> type){
		
		T api = null;
		try {
			api = (T)type.newInstance();
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		return api;
	}
}
