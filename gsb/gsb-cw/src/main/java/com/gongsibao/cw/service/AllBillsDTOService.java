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

import com.gongsibao.cw.base.IAllBillsDTOService;
import com.gongsibao.entity.cw.dict.FinanceDict;
import com.gongsibao.entity.cw.dto.BillAuditDTO;

@Service
public class AllBillsDTOService extends PersistableService<BillAuditDTO>  implements IAllBillsDTOService {

	public AllBillsDTOService() {
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
			dto.setCode(row.getString("code"));
			dto.setCreateTime(row.getDate("createTime"));
			dto.setCreator(row.getString("creator"));
			dto.setFormId(row.getInteger("formId"));
			String formType = row.getString("formType");
			if(formType!=null && !"".equals(formType)){
				dto.setFormTypeValue(Integer.parseInt(formType));
				dto.setFormType(FinanceDict.FormType.getItem(Integer.parseInt(formType)));
			}
			dto.setStatus(FinanceDict.AuditStatus.getItem(row.getInteger("status")));
			dto.setMemoto(row.getString("memoto"));
			reslis.add(dto);
		}

		return reslis;
	}
	
	protected StringBuffer getsql(int type, String strWhere, int startIndex, int pageSize) {
		StringBuffer sql = new StringBuffer();

		if (type == 0) {
			sql.append("SELECT t.id AS formId, t.formType AS formType, t.code AS code, t.amount AS amount, ");
			sql.append("t.creator AS creator, t.create_time AS createTime, t.memoto AS memoto,t.status AS status ");
		} else {
			sql.append("SELECT COUNT(t.id) 'rcount' ");
		}
		sql.append("FROM (");
		sql.append("SELECT id, 3 AS formType, code, amount, creator , create_time, memoto, status FROM cw_loan ");
		sql.append("UNION SELECT id, 4 AS formType, code, amount, creator , create_time, memoto, status FROM cw_expense ");
		sql.append("UNION SELECT id, 5 AS formType, code, amount, creator , create_time, memoto, status FROM cw_payment ) AS t ");
		sql.append("WHERE  1 = 1 ");
		
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
