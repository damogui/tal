package com.gongsibao.taurus.api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.gongsibao.taurus.TaurusException;
import com.gongsibao.taurus.WebClient;
import com.gongsibao.taurus.message.ResponseMessage;
import com.gongsibao.taurus.message.response.EntShareholderResponseMessage;
import com.gongsibao.taurus.util.ConfigHelper;
import com.gongsibao.taurus.util.MD5Util;
import com.gongsibao.taurus.util.StringManager;
import com.gongsibao.taurus.util.json.JacksonObjectMapper;

public abstract class AbstractApi<T extends ResponseMessage<?>> {

	// private static Log logger = LogFactory.getLog(AbstractApi.class);
	protected String companyName;

	/**
	 * @Fields currentPage : TODO(当前页)
	 */
	private Integer currentPage = 0;

	/**
	 * @Fields pageSize : TODO(页面大小)
	 */
	private Integer pageSize = 10;

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public abstract Class<?> getResponseType();

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	SimpleDateFormat shortFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	protected List<String> getParameters() {
		
		String nowTime = shortFormate.format(new Date());
		List<String> parameters = new ArrayList<String>();
		parameters.add("companyName=" + this.getCompanyName());
		parameters.add("appKey=" + ConfigHelper.APP_KEY);
		parameters.add("currentTime=" + nowTime);
		
		int pageIndex = this.getCurrentPage()>0?this.getCurrentPage()-1:this.getCurrentPage();
		parameters.add("currentPage=" + pageIndex);
		parameters.add("pageSize=" + this.getPageSize());

		String origin = nowTime + ConfigHelper.APP_KEY + ConfigHelper.APP_SIGN + this.getCompanyName() + pageIndex + this.getPageSize();
		String token = MD5Util.MD5Encode(origin, "UTF-8");
		parameters.add("token=" + token);
		return parameters;
	}

	protected abstract String getUrl();

	public T getResponse() {

		T t = doResponse();
		return t;
	}

	protected T doResponse() {

		List<String> parameters = this.getParameters();
		setExtendParameter(parameters);
		String json = StringManager.join("&", parameters);
		return this.executeHttpPost(json);
	};

	/**
	 * @Title: setParameter
	 * @Description: TODO(增加扩展参数，子类重写)
	 * @param: @param parameters
	 * @return: void
	 * @throws
	 */
	protected void setExtendParameter(List<String> parameters) {

	}

	protected T executeHttpPost(String json) {

		WebClient client = new WebClient();

		try {
			return this.doExecuteHttpPost(client, json);
		} catch (TaurusException te) {

			throw te;
		}
	}

	private T doExecuteHttpPost(WebClient client, String json) {

		String url = this.getUrl();
		String fullUrl = ConfigHelper.getFullApiUrl(url);
		json = client.uploadString(fullUrl, json);
		System.out.println("请求结果："+json);
		int startIndex = 8;
		int endIndex = json.indexOf(",\"seqNum\"");
		String cutJson = json.substring(startIndex, endIndex);
		T response = this.deserialize(cutJson);
		return response;
	}

	@SuppressWarnings("unchecked")
	public T deserialize(String json) throws TaurusException {

		if (json == null || json.trim().equals("") || json.trim().equals("\"\"")) {

			return null;
		}
		
		//接口返回不规范，特殊处理。hw 2017-10-25
		Class<?> responseTypeClass = getResponseType();
		if(responseTypeClass.equals(EntShareholderResponseMessage.class)){
			
			json = json.replaceAll("\"paidIn\":\"-\"", "\"paidIn\":[]");
			json = json.replaceAll("\"subcribe\":\"-\"", "\"subcribe\":[]");
		}

		// System.out.println(json);
		T response = null;

		ObjectMapper mapper = new JacksonObjectMapper();
		try {
			response = (T) mapper.readValue(json, getResponseType());
			if (response.getResult() == -1) {

				System.err.println(json);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return response;
	}

	public String serialize(Object obj) throws TaurusException {

		try {

			ObjectMapper mapper = new JacksonObjectMapper();
			String json = mapper.writeValueAsString(obj);
			return json;
		} catch (Exception ex) {

			throw new TaurusException(ex);
		}
	}
}
