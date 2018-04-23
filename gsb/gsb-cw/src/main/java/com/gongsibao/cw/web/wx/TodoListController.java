package com.gongsibao.cw.web.wx;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.panda.annotation.Authorization;
import org.netsharp.panda.commerce.EasyuiDatagridResult;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.IBillAuditDTOService;
import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.IFileService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.cw.base.IU8BankService;
import com.gongsibao.entity.bd.File;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.cw.dto.BillAuditDTO;
import com.gongsibao.entity.u8.U8Bank;

/**
 * 企业微信号  我的待办
 * @author Administrator
 *
 */
public class TodoListController {

	
	IBillAuditDTOService billAuditDTOService =  ServiceFactory.create(IBillAuditDTOService.class);
	
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	
	IExpenseService expenseService  = ServiceFactory.create(IExpenseService.class);
	//u8科目
	IU8BankService u8BankService = ServiceFactory.create(IU8BankService.class);
	
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	
	IFileService fileService = ServiceFactory.create(IFileService.class);
	/**
	 * 我的待办
	 * @param employeeId
	 * @param searchKeyWord
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@Authorization(is = false)
	public Object query(Integer employeeId, String searchKeyWord, Integer pageIndex, Integer pageSize) {
		return doQuery(employeeId,searchKeyWord,pageIndex,pageSize);
	}
	/**
	 * 借款订单详情
	 * @param id
	 * @return
	 */
	@Authorization(is = false)
	public Object getLoanById(Integer formId){
		return loanService.getBillByFormId(formId,false);
	}
	/**
	 * 报销单详情
	 * @param formId
	 * @return
	 */
	@Authorization(is = false)
	public Object getExpenseById(Integer formId){
		return expenseService.getBillByFormId(formId);
	}
	
	/**
	 * 审核记录
	 * @param formId
	 * @param formType
	 * @return
	 */
	@Authorization(is = false)
	public List<AuditRecord> getAuditByFormId(Integer formId,Integer formType){
		return auditRecordService.getAuditRecordList(formId, formType);
	}
	/**
	 * 附近信息
	 * @param tabName
	 * @param formId
	 * @return
	 */
	@Authorization(is = false)
	public List<File> getByTabNameFormId(String tabName, Integer formId){
		return fileService.getByTabNameFormId(tabName, formId);
	}
	
	/**
	 * 获取付款科目
	* @Title: getU8BankList  
	* @Description: TODO(这里用一句话描述这个方法的作用)  
	* @param @param setOfBooksId
	* @param @return    参数  
	* @return List<U8Bank>    返回类型  
	* @throws
	 */
	@Authorization(is = false)
	public List<U8Bank> getU8BankList(Integer setOfBooksId ){
		return u8BankService.getU8BankList(setOfBooksId);
	}
	
	/**   
	 * @Title: doQuery   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param ownerId
	 * @param: @param searchKeyWord
	 * @param: @param pageIndex
	 * @param: @param pageSize
	 * @param: @return      
	 * @return: Object      
	 * @throws   
	 */
	private Object doQuery(Integer employeeId, String searchKeyWord, Integer pageIndex, Integer pageSize){
		
		Paging paging = new Paging();
		{
			paging.setPageNo(pageIndex);
			paging.setPageSize(pageSize);
		}
		String filter = " a.audit_user_id ="+ employeeId +"  AND  a.status = " +FinanceDict.AuditDetailStatus.WAIT.getValue() +" ";
		if(StringUtils.isNotEmpty(searchKeyWord)){
			filter +=" AND t.code like '%"+searchKeyWord+"%'";
		}
		Oql oql = new Oql();
		{
			oql.setFilter(filter);
			oql.setPaging(paging);
		}
		List<BillAuditDTO> billDTOList = billAuditDTOService.queryList(oql);
		EasyuiDatagridResult result = new EasyuiDatagridResult();
		{
			result.setRows(billDTOList);
			if (oql.getPaging() != null) {

				result.setTotal(oql.getPaging().getTotalCount());
			}
		}
		return result;
	}
	
	/**
	 * 保存审核信息
	* @Title: saveAudit  
	* @Description: TODO
	* @param @param auditRecord
	* @param @return    参数  
	* @return Boolean    返回类型  
	* @throws
	 */
	@Authorization(is = false)
	public Boolean saveAudit(AuditRecord auditRecord){
		Integer fromType = auditRecord.getFormType().getValue();
		Integer status = auditRecord.getStatus().getValue();
		//获取待审核记录
		AuditRecord auditTemp = auditRecordService.getAuditRecordByParam(auditRecord.getFormId(), fromType, auditRecord.getApplyUserId(),status );
		auditRecord.setId(auditTemp.getId());
		auditRecord.setFormType(auditRecord.getFormType());
		return auditRecordService.saveAudit(auditRecord);
	}
}
