package org.netsharp.wx.qy.chat;

import org.netsharp.wx.qy.Response;

public class ChatGetResponse extends Response {
	
	private Chat chat_info;

	public Chat getChat_info() {
		return chat_info;
	}

	public void setChat_info(Chat chat_info) {
		this.chat_info = chat_info;
	}
}
