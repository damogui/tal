package com.gongsibao.taurus.api;

public class ApiResult {

	private static java.util.HashMap<String, String> Errors = new java.util.HashMap<String, String>();
	static
	{
		Errors.put("-1", "获取数据失败");
		Errors.put("1", "成功获取数据_有数据");
		Errors.put("2", "获取数据中");
		Errors.put("0", "成功获取_没数据");
	}
	public static String GetResult(String code)
	{
		String retValue = null;
		if ((retValue = Errors.get(code)) != null)
		{
			return retValue;
		}
		return "未知的错误码: " + code;
	}
}
