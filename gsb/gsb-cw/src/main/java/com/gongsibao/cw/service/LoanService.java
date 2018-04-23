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
import com.gongsibao.cw.base.IFileService;
import com.gongsibao.cw.base.ILoanService;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;

@Service
public class LoanService extends PersistableService<Loan> implements ILoanService{
	
	ICostDetailService costDetailService = ServiceFactory.create(ICostDetailService.class);
	
	IFileService fileService = ServiceFactory.create(IFileService.class);
	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	
	public LoanService() {
		super();
		this.type = Loan.class;
	}

	@Override
	public Boolean saveLoan(Loan loan) {
		ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/cw/loan/form");
            ctx.setItem(loan);
            ctx.setState(loan.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
	}

	@Override
	public Loan getBillByFormId(Integer formId ,Boolean isSubset) {
		Oql oql = new Oql();
		oql.setType(Loan.class);
		oql.setSelects("loan.*,loan.setOfBooks.name,loan.u8Bank.code,loan.u8Department.code,loan.u8Department.personnelCode,loan.borrowerEmployee.name");
		oql.setFilter("id=?");
		oql.getParameters().add("id", formId, Types.INTEGER);
		Loan entity = this.queryFirst(oql);
		if(entity != null && isSubset){
			//entity.setCostDetailItem(costDetailService.getCostDetailItem(formId, FinanceDict.FormType.JKD.getValue()));
			//附件信息
			entity.setFiles(fileService.getByTabNameFormId("cw_loan", formId));
			//审核信息
			entity.setAuditItem(auditRecordService.getAuditRecordList(formId, FinanceDict.FormType.JKD.getValue()));
		}
		return entity;
	}
	@Override
	public Boolean updateStatus(AuditRecord auditRecord){
		int status = 0;
		if(auditRecord.getStatus().getValue().intValue() != FinanceDict.AuditDetailStatus.AGREE.getValue().intValue()){
			status = FinanceDict.AuditStatus.Status_6.getValue();
		}else{
			status = FinanceDict.AuditStatus.Status_5.getValue();
		}
		String sql = "UPDATE cw_loan SET status ="+ status +" , bank_id = "+auditRecord.getBankId()+"  WHERE id = "+auditRecord.getFormId();
		Boolean bool =  this.pm.executeNonQuery(sql, null) > 0;
		if(bool){
			auditRecordService.saveFinance(auditRecord);
		}
		return bool;
	}

	@Override
	public List<Loan> getLoanByUserId(Integer userId) {
		Oql oql = new Oql();
		oql.setType(Loan.class);
		oql.setSelects("loan.*");
		oql.setFilter("creatorId=? and status=? and arrearsAmount<>?");
		oql.getParameters().add("creatorId", userId, Types.INTEGER);
		oql.getParameters().add("status", FinanceDict.AuditStatus.Status_5.getValue(), Types.INTEGER);
		oql.getParameters().add("arrearsAmount", 0, Types.INTEGER);
		return this.queryList(oql);
	}
	
}
