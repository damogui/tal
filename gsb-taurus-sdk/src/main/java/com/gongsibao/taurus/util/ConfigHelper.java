package com.gongsibao.taurus.util;

public class ConfigHelper {

	public static final String DOMAIN = "http://www.telecredit.cn";
	
	public static final String APP_KEY = "gongsibao";
	
	public static final String APP_SIGN = "d10c5fa8d0d41a56fd1390ad214898ed";
	
	/**   
	 * @Title: getFullApiUrl
	 * @Description: TODO(获取完整URL)   
	 * @param: @param url
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public static String getFullApiUrl(String url){
		
		return ConfigHelper.DOMAIN + url;
	}
}
