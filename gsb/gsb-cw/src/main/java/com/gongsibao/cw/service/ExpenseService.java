package com.gongsibao.cw.service;

import java.sql.Types;
import java.util.List;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.cw.base.IExpenseService;
import com.gongsibao.cw.base.IFileService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.cw.base.ISubsidyRecordService;
import com.gongsibao.cw.base.ITripRecordService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Expense;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;

@Service
public class ExpenseService extends PersistableService<Expense> implements IExpenseService {

     ICostDetailService costDetailService = ServiceFactory.create(ICostDetailService.class);
	
	IFileService fileService = ServiceFactory.create(IFileService.class);
	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	//行程明细
	ITripRecordService tripRecordService = ServiceFactory.create(ITripRecordService.class);
	//补助明细
	ISubsidyRecordService subsidyRecordService= ServiceFactory.create(ISubsidyRecordService.class);
	//借款服务
	ILoanService loanService = ServiceFactory.create(ILoanService.class);
	
	public ExpenseService() {
		super();
		this.type = Expense.class;
	}

	@Override
	public Boolean saveExpense(Expense entity) {
		ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/cw/expense/form");
            ctx.setItem(entity);
            ctx.setState(entity.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
	}

	@Override
	public Expense getBillByFormId(Integer formId) {
		Oql oql = new Oql();
		oql.setType(Expense.class);
		oql.setSelects("expense.*,expense.setOfBooks.name");
		oql.setFilter("id=?");
		oql.getParameters().add("id", formId, Types.INTEGER);
		Expense entity = this.queryFirst(oql);
		if(entity != null){
			entity.setCostDetailItem(costDetailService.getCostDetailItem(formId, FinanceDict.FormType.BXD.getValue()));
			//附件信息
			entity.setFiles(fileService.getByTabNameFormId("cw_expense", formId));
			//审核信息
			entity.setAuditItem(auditRecordService.getAuditRecordList(formId, FinanceDict.FormType.BXD.getValue()));
			//行程明细
			entity.setTripItem(tripRecordService.getTripItem(formId));
			//补助明细
			entity.setSubsidyItem(subsidyRecordService.getSubsidyItem(formId));
		}
		return entity;
	}

	@Override
	public Boolean updateStatus(AuditRecord auditRecord){
		//但需要冲正借款 报销金额 大于0   冲正所有借款 
		String sql = "UPDATE cw_expense SET status ="+FinanceDict.AuditStatus.Status_5.getValue() +"   WHERE id = "+auditRecord.getFormId();
		Boolean bool =  this.pm.executeNonQuery(sql, null) > 0;
		if(bool){
			auditRecordService.saveFinance(auditRecord);
			//冲正借款金额
			correctLoan(auditRecord.getFormId());
		}
		return bool;
	}
	/**
	 * 冲正借款
	* @Title: correctLoan  
	* @Description: TODO
	* @param @param formId    参数  
	* @return void    返回类型  
	* @throws
	 */
	private void correctLoan(Integer formId){
		Oql oql = new Oql();
		oql.setType(Expense.class);
		oql.setSelects("expense.*");
		oql.setFilter("id=?");
		oql.getParameters().add("id", formId, Types.INTEGER);
		Expense entity = this.queryFirst(oql);
		if(entity != null && entity.getIsOffset()){
			Integer expenseAmount = entity.getTotalAmount();
			List<Loan> loanList =  loanService.getLoanByUserId(entity.getCreatorId());
			for(Loan loan : loanList){
				expenseAmount = expenseAmount - loan.getAmount();
				loan.toPersist();
				if(expenseAmount>=0){  //报销金额大于借款  金额   证明可以冲正所有借款 
					loan.setRepaymentAmount(loan.getAmount()); //还款金额
					loan.setArrearsAmount(0);  //未还金额
				}else{  
					//公式 还款金额=借款金额 + （报销金额-借款金额） 如：报销金额5000  借款金额6000  还款金额为：5000
					Integer tempAmount = loan.getAmount()+expenseAmount;
					loan.setRepaymentAmount(tempAmount); //还款金额
					loan.setArrearsAmount(loan.getAmount()-tempAmount);  //未还金额
				}
				loanService.save(loan);
			}
		}
	}

}
