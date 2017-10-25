package org.netsharp.panda.rest;

import java.util.List;

public class PandaInvoking extends RestInvoking {
	
	private String methodName;
	private List<PandaParameter> parameters;

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public List<PandaParameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<PandaParameter> parameters) {
		this.parameters = parameters;
	}
}
