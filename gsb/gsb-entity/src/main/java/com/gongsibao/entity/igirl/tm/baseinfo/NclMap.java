package com.gongsibao.entity.igirl.tm.baseinfo;


import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;
import org.netsharp.entity.Entity;


@Table(name="ig_base_ncl_map",header="尼斯映射")
public class NclMap extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="ncl_batch_id",header="尼斯期间ID")
	private Integer nclBeachId = -1;

	@Reference(foreignKey="nclBeachId",header="尼斯期间")
	private NclBatch nclBatch;

	@Column(name="nclone_id",header="商标大类ID")
	private Integer nclOneId = -1;

	@Reference(foreignKey="nclOneId",header="商标大类")
	private NCLOne nclOne;


	@Column(name="ncltwo_content",size = 8192,header="商标小类")
	private String nclTwoContent;


	public Integer getNclBeachId() {
		return nclBeachId;
	}


	public void setNclBeachId(Integer nclBeachId) {
		this.nclBeachId = nclBeachId;
	}


	public NclBatch getNclBatch() {
		return nclBatch;
	}


	public void setNclBatch(NclBatch nclBatch) {
		this.nclBatch = nclBatch;
	}


	public Integer getNclOneId() {
		return nclOneId;
	}


	public void setNclOneId(Integer nclOneId) {
		this.nclOneId = nclOneId;
	}


	public NCLOne getNclOne() {
		return nclOne;
	}


	public void setNclOne(NCLOne nclOne) {
		this.nclOne = nclOne;
	}


	public String getNclTwoContent() {
		return nclTwoContent;
	}


	public void setNclTwoContent(String nclTwoContent) {
		this.nclTwoContent = nclTwoContent;
	}




}
