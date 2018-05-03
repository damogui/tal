package com.gongsibao.cw.service;

import java.sql.Types;

import org.netsharp.action.ActionContext;
import org.netsharp.action.ActionManager;
import org.netsharp.communication.Service;
import org.netsharp.communication.ServiceFactory;
import org.netsharp.core.Oql;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IAllocationService;
import com.gongsibao.cw.base.IAuditRecordService;
import com.gongsibao.cw.base.ICostDetailService;
import com.gongsibao.cw.base.IFileService;
import com.gongsibao.entity.cw.Allocation;
import com.gongsibao.entity.cw.AuditRecord;
import com.gongsibao.entity.cw.Loan;
import com.gongsibao.entity.cw.dict.FinanceDict;

@Service
public class AllocationService extends PersistableService<Allocation> implements IAllocationService {

    ICostDetailService costDetailService = ServiceFactory.create(ICostDetailService.class);
	
	IFileService fileService = ServiceFactory.create(IFileService.class);
	//审核记录服务
	IAuditRecordService auditRecordService = ServiceFactory.create(IAuditRecordService.class);
	
	public AllocationService() {
		super();
		this.type = Allocation.class;
	}

	@Override
	public Boolean saveAllocation(Allocation allocation) {
		ActionContext ctx = new ActionContext();
        {
            ctx.setPath("gsb/cw/allocation/form");
            ctx.setItem(allocation);
            ctx.setState(allocation.getEntityState());
        }
        ActionManager action = new ActionManager();
        action.execute(ctx);
        return true;
	}

	@Override
	public Allocation getBillByFormId(Integer formId) {
		Oql oql = new Oql();
		oql.setType(Allocation.class);
		oql.setSelects("allocation.*,allocation.setOfBooks.name");
		oql.setFilter("id=?");
		oql.getParameters().add("id", formId, Types.INTEGER);
		Allocation entity = this.queryFirst(oql);
		if(entity != null){
			entity.setCostDetailItem(costDetailService.getCostDetailItem(formId, FinanceDict.FormType.DBD.getValue()));
			//附件信息
			entity.setFiles(fileService.getByTabNameFormId("cw_allocation", formId));
			//审核信息
			entity.setAuditItem(auditRecordService.getAuditRecordList(formId, FinanceDict.FormType.DBD.getValue()));
		}
		return entity;
	}

	@Override
	public Boolean updateStatus(AuditRecord auditRecord){
		String sql = "UPDATE cw_allocation SET status ="+FinanceDict.AuditStatus.Status_5.getValue() +"   WHERE id = "+auditRecord.getFormId();
		Boolean bool =  this.pm.executeNonQuery(sql, null) > 0;
		if(bool){
			auditRecordService.saveFinance(auditRecord);
		}
		return bool;
	}
	

}
