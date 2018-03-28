package com.gongsibao.entity.igirl.tm.baseinfo;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.igirl.tm.EntityWithSupplierInfo;

@Table(name = "ig_supplier_newinfo", header = "服务商最新资讯", orderBy = "create_time desc")
public class SupplierNewInfo extends EntityWithSupplierInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 998944471069115618L;

	@Column(name = "title", header = "标题")
	private String title;

	@Column(name = "memo", header = "说明", size = 512)
	private String memo;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}