package com.gongsibao.panda.platform.cw;

import org.junit.Before;
import org.netsharp.meta.base.ResourceCreationBase;
import org.netsharp.resourcenode.entity.ResourceNode;

import com.gongsibao.cw.base.IAllBillsDTOService;
import com.gongsibao.cw.base.IAllocationService;
import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.IBillAuditDTOService;
import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.cw.base.ICostTypeService;
import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.IFinanceBillDTOService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.cw.base.IPaymentService;
import com.gongsibao.cw.base.ISubsidyRecordService;
import com.gongsibao.cw.base.ITripRecordService;
import com.gongsibao.entity.cw.Allocation;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.CostDetail;
import com.gongsibao.entity.cw.CostType;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.Payment;
import com.gongsibao.entity.cw.SubsidyRecord;
import com.gongsibao.entity.cw.TripRecord;
import com.gongsibao.entity.cw.dto.BillAuditDTO;

/**
 * 财务报销单
*    
* 项目名称：gsb-test   
* 类名称：ResourceTest   
* 类描述：   
* 创建人：angang
* 创建时间：2018年3月20日 上午11:27:34   
* @version
 */
public class ResourceTest extends ResourceCreationBase {

	public static String resourcePrefix = "GSB_CW";

	@Before
	public void setup() {

		parentNodeName = "财务报销";
		parentNodeCode = ResourceTest.resourcePrefix;
		pluginName = "财务报销";
		seq = 8;
		entityClass = ResourceNode.class;
	}

	@Override
	protected void createResourceNodeVouchers(ResourceNode node) {

		String prefix = ResourceTest.resourcePrefix;
		ResourceNode node1 = null;
		node1 = this.createResourceNodeCategory("财务审批", prefix + "_Manage", node.getId());
		{
			
			
			this.createResourceNodeVoucher(BillAuditDTO.class.getName(), "我的待办", node1.getCode() + "_Todo_Bills", IBillAuditDTOService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(BillAuditDTO.class.getName(), "我的已办", node1.getCode() + "_Done_Bills", IBillAuditDTOService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(BillAuditDTO.class.getName(), "抄送我的", node1.getCode() + "_CC_Bills", IBillAuditDTOService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(BillAuditDTO.class.getName(), "财务办理", node1.getCode() + "_Finance_Bills", IFinanceBillDTOService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(BillAuditDTO.class.getName(), "单据查询", node1.getCode() + "_All_Bills", IAllBillsDTOService.class.getName(), node1.getId());
			
			
			this.createResourceNodeVoucher(Loan.class.getName(), "借款单", node1.getCode() + "_Loan_Bill", ILoanService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Expense.class.getName(), "报销单", node1.getCode() + "_Expense_Bill", IExpenseService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Payment.class.getName(), "付款单", node1.getCode() + "_Payment_Bill", IPaymentService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(Allocation.class.getName(), "调拨单", node1.getCode() + "_Allocation_Bill", IAllocationService.class.getName(), node1.getId());
			//	this.createResourceNodeVoucher(Attachment.class.getName(), "上传附件",  node1.getCode() + "Upload_File", IAttachmentService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(CostDetail.class.getName(), "费用明细", node1.getCode() + "_Cost_Detail", ICostDetailService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(AuditRecord.class.getName(), "审批记录",  node1.getCode() + "_Audit_Record", IAuditRecordService.class.getName(), node1.getId());
			this.createResourceNodeVoucher(TripRecord.class.getName(), "行程明细",  node1.getCode() + "_Trip_Record", ITripRecordService.class.getName(), node1.getId());
			
			this.createResourceNodeVoucher(SubsidyRecord.class.getName(), "补助明细",  node1.getCode() + "_Subsidy_Record", ISubsidyRecordService.class.getName(), node1.getId());
			
			
			this.createResourceNodeVoucher(CostType.class.getName(), "费用类型",  node1.getCode() + "_Cost_Type", ICostTypeService.class.getName(), node1.getId());
		}
		
	}
}
