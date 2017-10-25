package com.gongsibao.taurus;

public class TaurusException extends RuntimeException{

	/**   
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)   
	 */   
	private static final long serialVersionUID = -7601394355859770986L;

	private String errorCode;
	
	private String errmsg;
	
	public TaurusException(){
		super();
	}
	
	public TaurusException(String errorCode,String errmsg,String message){
		super(message);
		this.errorCode=errorCode;
	}
	
	public TaurusException(String message){
		super(message);
	}
	
	public TaurusException(Throwable throwable){
		super(throwable);
	}
	
	public TaurusException(String message,Throwable throwable){
		super(message,throwable);
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
}
