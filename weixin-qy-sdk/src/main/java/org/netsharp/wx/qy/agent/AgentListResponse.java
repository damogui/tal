package org.netsharp.wx.qy.agent;

import java.util.List;

import org.netsharp.wx.qy.Response;

public class AgentListResponse extends Response {
	
	private List<Agent> agentlist;

	public List<Agent> getAgentlist() {
		return agentlist;
	}

	public void setAgentlist(List<Agent> agentlist) {
		this.agentlist = agentlist;
	}
	
}
