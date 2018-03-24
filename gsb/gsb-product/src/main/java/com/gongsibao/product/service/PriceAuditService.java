package com.gongsibao.product.service;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.Oql;
import org.netsharp.core.Paging;
import org.netsharp.service.PersistableService;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.product.ProdPriceAuditRow;
import com.gongsibao.entity.taurus.UserConsumptionView;
import com.gongsibao.product.base.IPriceAuditService;

@Service
public class PriceAuditService extends PersistableService<ProdPriceAuditRow> implements IPriceAuditService {

    public PriceAuditService(){
        super();
        this.type=ProdPriceAuditRow.class;
    }
    
    @Override
	public List<ProdPriceAuditRow> queryList(Oql oql){
		
		String filterString = oql.getFilter();
		HashMap<String, String> mapFilters = new HashMap<String, String>();
		try {
			mapFilters = getMapFilters(filterString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Paging paging = oql.getPaging();
		int startIndex = (paging.getPageNo() - 1) * paging.getPageSize();

		// 查询sql
		StringBuffer stringBuffer = getsql(0, mapFilters, startIndex, paging.getPageSize());
		// 获取总条数sql
		StringBuffer rcountsql = getsql(1, mapFilters, startIndex, paging.getPageSize());

		// 获取总条数
		paging.setTotalCount(getqueryListCount(rcountsql.toString()));
		oql.setPaging(paging);
		DataTable dataTable = this.pm.executeTable(stringBuffer.toString(), null);
		List<ProdPriceAuditRow> list = new ArrayList<ProdPriceAuditRow>();
		ProdPriceAuditRow prodPriceAuditRow = new ProdPriceAuditRow();
		for (IRow row : dataTable) {
			prodPriceAuditRow = new ProdPriceAuditRow();
			prodPriceAuditRow.setProductName(row.getString("name"));
			prodPriceAuditRow.setStatusId(row.getInteger("auditStatusId"));
			prodPriceAuditRow.setStatusType(row.getInteger("auditStatusType"));
			list.add(prodPriceAuditRow);
		}
		return list;
	}
	
	
	// type：0查询sql 1获取页码sql
	protected StringBuffer getsql(int type, Map<String, String> mapFilters, int startIndex, int pageSize) {
		StringBuffer stringBuffer = new StringBuffer();

		if (type == 0) {
			stringBuffer.append(" SELECT b.pkid as pkid, b.organization_id as organizationId, b.product_id as productId, p.name as name, b.audit_status_id as auditStatusId,b.audit_status_type as auditStatusType  ");
			stringBuffer.append(" FROM prod_price_audit b JOIN prod_price a ON a.price_audit_id = b.pkid JOIN prod_product p ON p.pkid = b.product_id  where 1=1  ");
			stringBuffer.append("LIMIT " + startIndex + ", " + pageSize + " ");
		} else {
			stringBuffer.append(" SELECT count(b.pkid)  FROM prod_price_audit b JOIN prod_price a ON a.price_audit_id = b.pkid JOIN prod_product p ON p.pkid = b.product_id  where 1=1");
		}
		return stringBuffer;
	}
	
	
	// 根据前段传过来的参数，得到Map
	protected HashMap<String, String> getMapFilters(String filter) throws UnsupportedEncodingException {

		HashMap<String, String> map = new HashMap<String, String>();

		if (!StringManager.isNullOrEmpty(filter)) {

			filter = filter.replace("%", "|");
			filter = URLDecoder.decode(filter, "UTF-8");
			filter = filter.replace("|", "%");
			String[] ss = filter.split(" AND ");
			if (ss.length > 0) {

				for (String s : ss) {
					String splitString = "=";
					if (s.contains("LIKE")) {
						splitString = "LIKE";
					}
					if (s.contains("in")) {
						splitString = "in";
					}
					if (s.contains(">=")) {
						splitString = ">=";
					}
					if (s.contains("<=")) {
						splitString = "<=";
					}
					String[] a = s.split(splitString);
					String mapKeyString = a[0].trim();
					String mapValString = a[1].trim();
					// 下单时间
					if (mapKeyString.equals("addTime") && splitString.equals(">=")) {
						mapKeyString = "startAddTime";
					}
					if (mapKeyString.equals("addTime") && splitString.equals("<=")) {
						mapKeyString = "endAddTime";
					}
					// 订单回款时间
					if (mapKeyString.equals("payTime") && splitString.equals(">=")) {
						mapKeyString = "startPayTime";
					}
					if (mapKeyString.equals("payTime") && splitString.equals("<=")) {
						mapKeyString = "endPayTime";
					}
					map.put(mapKeyString, mapValString);
				}
				return map;
			}
		}
		return map;
	}
	
	// 获取查询的总条数
	protected int getqueryListCount(String sql) {
		int count = 0;
		Object rcount = this.pm.executeScalar(sql.toString(), null);
		count = Integer.parseInt(rcount.toString());
		return count;
	}
    
    
}