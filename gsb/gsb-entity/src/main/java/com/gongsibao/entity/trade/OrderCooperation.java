package com.gongsibao.entity.trade;

import org.netsharp.core.annotations.Column;
import org.netsharp.core.annotations.Table;

import com.gongsibao.entity.BaseEntity;

@Table(name="so_order_cooperation",header="璁㈠崟褰掑睘鍏ラ┗鍏徃")
public class OrderCooperation extends BaseEntity{

	

	/**   
	 * @Fields serialVersionUID : TODO(鐢ㄤ竴鍙ヨ瘽鎻忚堪杩欎釜鍙橀噺琛ㄧず浠�箞)   
	 */   
	private static final long serialVersionUID = -1407501547018300129L;

	@Column(name="order_id",header="璁㈠崟搴忓彿")
    private Integer orderId;
	
    @Column(name="cooperation_company_id",header="鍏ラ┗鍏徃")
    private Integer cooperationCompanyId;
    
    @Column(name="branch_id",header="鍒嗘敮鏈烘瀯id")
    private Integer branch_id;
    
    @Column(name="branch_name",header="鍒嗘敮鏈烘瀯鍚嶇О")
    private String branch_name;
    
    
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getCooperationCompanyId() {
		return cooperationCompanyId;
	}
	public void setCooperationCompanyId(Integer cooperationCompanyId) {
		this.cooperationCompanyId = cooperationCompanyId;
	}
	public Integer getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(Integer branch_id) {
		this.branch_id = branch_id;
	}
	public String getBranch_name() {
		return branch_name;
	}
	public void setBranch_name(String branch_name) {
		this.branch_name = branch_name;
	}
}
