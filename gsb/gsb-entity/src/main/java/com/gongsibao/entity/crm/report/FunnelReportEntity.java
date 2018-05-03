package com.gongsibao.entity.crm.report;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Reference;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.bd.Dict;

@Table(isView=true, name = "")
public class FunnelReportEntity extends BaseReportEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**   
	 * @Fields taskCount : TODO(全部商机数)   
	 */   
	private Integer taskCount = 0;
	
	/**   
	 * @Fields XCount : TODO(X类)   
	 */   
	private Integer XCount = 0;
	
	/**   
	 * @Fields SCount : TODO(S类)   
	 */   
	private Integer SCount = 0;
	
	/**   
	 * @Fields A0Count : TODO(A0类)   
	 */   
	private Integer A0Count = 0;
	
	/**   
	 * @Fields A1Count : TODO(A1类)   
	 */   
	private Integer A1Count = 0;
	
	/**   
	 * @Fields A2Count : TODO(A2类)   
	 */   
	private Integer A2Count = 0;
	
	/**   
	 * @Fields A3Count : TODO(A3类)   
	 */   
	private Integer A3Count = 0;
	
	/**   
	 * @Fields A4Count : TODO(A4类)   
	 */   
	private Integer A4Count = 0;
	
	/**   
	 * @Fields B1Count : TODO(B1类)   
	 */   
	private Integer B1Count = 0;
	
	/**   
	 * @Fields B2Count : TODO(B2类)   
	 */   
	private Integer B2Count = 0;
	
	/**   
	 * @Fields C1Count : TODO(C1类)   
	 */   
	private Integer C1Count = 0;
	
	/**   
	 * @Fields C2Count : TODO(C2类)   
	 */   
	private Integer C2Count = 0;
	
	/**   
	 * @Fields C3Count : TODO(C3类)   
	 */   
	private Integer C3Count = 0;
	
	/**   
	 * @Fields C4Count : TODO(C3类)   
	 */   
	private Integer C4Count = 0;
	
	/**   
	 * @Fields D1Count : TODO(D1类)   
	 */   
	private Integer D1Count = 0;
	
	/**   
	 * @Fields D2Count : TODO(D2类)   
	 */   
	private Integer D2Count = 0;

	@Reference(foreignKey = "sourceId", header = "商机来源")
	private Dict source;

	@Column(name = "source_id", header = "商机来源")
	private Integer sourceId;
	
	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public Integer getXCount() {
		return XCount;
	}

	public void setXCount(Integer xCount) {
		XCount = xCount;
	}

	public Integer getSCount() {
		return SCount;
	}

	public void setSCount(Integer sCount) {
		SCount = sCount;
	}

	public Integer getA0Count() {
		return A0Count;
	}

	public void setA0Count(Integer a0Count) {
		A0Count = a0Count;
	}

	public Integer getA1Count() {
		return A1Count;
	}

	public void setA1Count(Integer a1Count) {
		A1Count = a1Count;
	}

	public Integer getA2Count() {
		return A2Count;
	}

	public void setA2Count(Integer a2Count) {
		A2Count = a2Count;
	}

	public Integer getA3Count() {
		return A3Count;
	}

	public void setA3Count(Integer a3Count) {
		A3Count = a3Count;
	}

	public Integer getA4Count() {
		return A4Count;
	}

	public void setA4Count(Integer a4Count) {
		A4Count = a4Count;
	}

	public Integer getB1Count() {
		return B1Count;
	}

	public void setB1Count(Integer b1Count) {
		B1Count = b1Count;
	}

	public Integer getB2Count() {
		return B2Count;
	}

	public void setB2Count(Integer b2Count) {
		B2Count = b2Count;
	}

	public Integer getC1Count() {
		return C1Count;
	}

	public void setC1Count(Integer c1Count) {
		C1Count = c1Count;
	}

	public Integer getC2Count() {
		return C2Count;
	}

	public void setC2Count(Integer c2Count) {
		C2Count = c2Count;
	}

	public Integer getC3Count() {
		return C3Count;
	}

	public void setC3Count(Integer c3Count) {
		C3Count = c3Count;
	}
	
	
	public Integer getC4Count() {
		return C4Count;
	}

	public void setC4Count(Integer c4Count) {
		C4Count = c4Count;
	}

	public Integer getD1Count() {
		return D1Count;
	}

	public void setD1Count(Integer d1Count) {
		D1Count = d1Count;
	}

	public Integer getD2Count() {
		return D2Count;
	}

	public void setD2Count(Integer d2Count) {
		D2Count = d2Count;
	}

	public Dict getSource() {
		return source;
	}

	public void setSource(Dict source) {
		this.source = source;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
	
}
