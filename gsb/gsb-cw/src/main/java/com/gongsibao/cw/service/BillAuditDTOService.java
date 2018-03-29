package com.gongsibao.cw.service;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.persistence.session.SessionManager;
import org.netsharp.service.PersistableService;

import com.gongsibao.cw.base.IBillAuditDTOService;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.cw.dto.BillAuditDTO;

@Service
public class BillAuditDTOService  extends PersistableService<BillAuditDTO> implements IBillAuditDTOService {

	public BillAuditDTOService() {
		super();
		this.type = BillAuditDTO.class;
	}
	
	@Override
	public List<BillAuditDTO> queryList(Oql oql) {

		String filterString = oql.getFilter();
		Paging paging = oql.getPaging();
		int startIndex = paging == null ? 0 : (paging.getPageNo() - 1) * paging.getPageSize();
		int pageSize = paging == null ? 0 : paging.getPageSize();
		// 查询sql
		StringBuffer sql = getsql(0, filterString, startIndex, pageSize);

		if (pageSize > 0) {
			// 获取总条数sql
			StringBuffer rcountsql = getsql(1, filterString, startIndex, pageSize);
			// 获取总条数
			paging.setTotalCount(getqueryListCount(rcountsql.toString()));
			oql.setPaging(paging);
		}

		DataTable dataTable = this.pm.executeTable(sql.toString(), null);
		List<BillAuditDTO> reslis = new ArrayList<>();
		for (IRow row : dataTable) {
			BillAuditDTO dto = new BillAuditDTO();
			dto.setAmount(row.getInteger("amount"));
			dto.setAuditUserId(row.getInteger("auditUserId"));
			dto.setCode(row.getString("code"));
			dto.setCreateTime(row.getDate("createTime"));
			dto.setCreator(row.getString("creator"));
			dto.setFormId(row.getInteger("formId"));
			dto.setFormType(FinanceDict.FormType.getItem(row.getInteger("formType")));
			dto.setMemoto(row.getString("memoto"));
			reslis.add(dto);
		}

		return reslis;
	}

	// type：0查询sql 1获取页码sql
	protected StringBuffer getsql(int type, String strWhere, int startIndex, int pageSize) {
		SessionManager.getUserId();
		StringBuffer sql = new StringBuffer();

		if (type == 0) {
			sql.append("SELECT a.id,a.form_id AS formId,a.form_type AS formType,a.audit_user_id AS auditUserId,t.code AS code, t.amount AS amount, ");
			sql.append(" t.code AS code, t.creator AS  creator, t.create_time AS createTime, t.memoto AS memoto, a.content AS content ");
		} else {
			sql.append("SELECT COUNT(a.id) 'rcount' ");
		}
		sql.append("FROM cw_audit_record AS a ");
		sql.append("LEFT JOIN ( SELECT id,code,amount,creator,create_time,memoto FROM cw_loan ");
		sql.append("UNION SELECT id,code,amount,creator,create_time,memoto FROM cw_expense ");
		sql.append("UNION SELECT id,code,amount,creator,create_time,memoto FROM cw_payment ) AS t ");
		sql.append("ON t.id = a.form_id ");
		sql.append("WHERE  a.audit_user_id =  " + SessionManager.getUserId()+" ");
		//拼接前台传入参数
		if(strWhere != null && !"".equals(strWhere) ){
			sql.append("AND   " + strWhere);
		}
		// 分页时，不用排序分组
		if (type == 0) {
			sql.append("ORDER BY createTime DESC ");
			if (pageSize > 0)
				sql.append("LIMIT " + startIndex + ", " + pageSize + " ");
		}

		return sql;
	}

	// 获取查询的总条数
	protected int getqueryListCount(String sql) {
		int count = 0;
		Object rcount = this.pm.executeScalar(sql.toString(), null);
		count = Integer.parseInt(rcount.toString());
		return count;
	}

}
