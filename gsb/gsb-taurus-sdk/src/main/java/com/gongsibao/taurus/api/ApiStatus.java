package com.gongsibao.taurus.api;

public final class ApiStatus {

	private static java.util.HashMap<String, String> Errors = new java.util.HashMap<String, String>();
	static
	{
		Errors.put("200", "成功");
		Errors.put("412", "参数传入有误");
		Errors.put("403", "禁止访问，权限不足");
		Errors.put("404", "访问资源过期");
		Errors.put("500", "服务器出现异常");
	}

	public static String GetStatus(String code)
	{
		String retValue = null;
		if ((retValue = Errors.get(code)) != null)
		{
			return retValue;
		}
		return "未知的错误码: " + code;
	}
		
}
