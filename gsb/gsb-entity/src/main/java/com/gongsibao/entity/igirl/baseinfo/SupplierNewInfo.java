package com.gongsibao.entity.igirl.baseinfo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Exclusive;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;

import com.gongsibao.entity.igirl.EntityWithSupplierInfo;
import com.gongsibao.entity.supplier.Supplier;

import java.util.ArrayList;
import java.util.List;

@Table(name = "ig_supplier_newinfo", header = "服务商最新资讯", orderBy = "create_time desc")
public class SupplierNewInfo extends EntityWithSupplierInfo {
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