package org.netsharp.authorization;

import java.io.Serializable;

public class UserClient implements Serializable {

	/**
	 * 用户客户端信息
	 */
	private static final long serialVersionUID = 5798098687514465301L;
	private String ipAddress;// IP地址
	private String explorer;// 浏览器信息
	private String pcName;// 客户端主机名称。获取不到则为IP

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getExplorer() {
		return explorer;
	}

	public void setExplorer(String explorer) {
		this.explorer = explorer;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

}
