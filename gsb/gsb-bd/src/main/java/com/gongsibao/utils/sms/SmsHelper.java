package com.gongsibao.utils.sms;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.netsharp.util.JsonManage;
import org.netsharp.util.StringManager;

@SuppressWarnings("deprecation")
public class SmsHelper {

	private static Log log = LogFactory.getLog(SmsHelper.class);

	private static String smsApiUrl = "http://192.168.16.52:2102/api/Send";

	private static Registry<ConnectionSocketFactory> socketFactoryRegistry = null;
	static {
		try {
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			X509HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER;
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
			socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create().register("http", PlainConnectionSocketFactory.getSocketFactory()).register("https", sslsf).build();
		} catch (Exception e) {
			log.error(e);
		}
	}
	private static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
	private static CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).setDefaultCookieStore(new BasicCookieStore()).build();

	/**   
	 * @Title: send   
	 * @Description: TODO(发送短信)   
	 * @param: @param mobile
	 * @param: @param content
	 * @param: @return      
	 * @return: SmsResponse      
	 * @throws   
	 */
	public static SmsResponse send(String mobile, String content) {

		String charset = "utf-8";
		String result = null;
		HttpPost post = new HttpPost(smsApiUrl);
		post.addHeader("accept", "application/json");
		post.addHeader("content-type", "application/json");

		SmsRequest request = new SmsRequest();
		request.setAppId(2);
		request.setMobilePhone(mobile);
		request.setContent(content);
		String body = JsonManage.serialize2(request);
		post.setEntity(new StringEntity(body,charset));
		SmsResponse smsResponse = new SmsResponse();
		try {
			CloseableHttpResponse response = client.execute(post);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, charset);
			if (!StringManager.isNullOrEmpty(result)) {
				smsResponse = (SmsResponse) JsonManage.deSerialize2(SmsResponse.class, result);
			}
			log.debug("httppost respose=" + result);
		} catch (IOException e) {
			e.printStackTrace();
			smsResponse.setIsSuccessful(false);
			smsResponse.setSmsId(-1);
			smsResponse.setStatusCode(-1);
			smsResponse.setStatusMessage(e.getMessage());
		} finally {
			if (null != post) {
				post.releaseConnection();
			}
		}
		return smsResponse;
	}

	/**   
	 * @Title: send   
	 * @Description: TODO(批量发送)   
	 * @param: @param mobiles
	 * @param: @param content      
	 * @return: void      
	 * @throws   
	 */
	public static void send(String[] mobiles, String content) {

		for (String mobile : mobiles) {

			send(mobile, content);
		}
	}

}
