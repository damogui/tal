package com.gongsibao.trade.web.dto;

import java.util.List;

import com.gongsibao.entity.trade.OrderProdTraceFile;

public class TraceFileDTO {
	
	/**   
	 * @Fields alreadyUploadCount : TODO(已上传数量)   
	 */   
	private Integer alreadyUploadCount = 0;
	
	/**   
	 * @Fields notUploadCount : TODO(未上传数量)   
	 */   
	private Integer notUploadCount = 0;
	
	/**   
	 * @Fields notUploadFileNames : TODO(未上传文件名称)   
	 */   
	private String notUploadFileNames = "";

	/**   
	 * @Fields fileList : TODO(已上传文件集合)   
	 */   
	private List<OrderProdTraceFile> fileList;

	public List<OrderProdTraceFile> getFileList() {
		return fileList;
	}

	public void setFileList(List<OrderProdTraceFile> fileList) {
		this.fileList = fileList;
	}

	public Integer getAlreadyUploadCount() {
		return alreadyUploadCount;
	}

	public void setAlreadyUploadCount(Integer alreadyUploadCount) {
		this.alreadyUploadCount = alreadyUploadCount;
	}

	public Integer getNotUploadCount() {
		return notUploadCount;
	}

	public void setNotUploadCount(Integer notUploadCount) {
		this.notUploadCount = notUploadCount;
	}

	public String getNotUploadFileNames() {
		return notUploadFileNames;
	}

	public void setNotUploadFileNames(String notUploadFileNames) {
		this.notUploadFileNames = notUploadFileNames;
	}
}
