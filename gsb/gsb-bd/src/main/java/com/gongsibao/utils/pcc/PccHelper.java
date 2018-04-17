package com.gongsibao.utils.pcc;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
import org.netsharp.core.QueryParameters;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.Dict;

public class PccHelper {

	public static List<PccDTO> getFullPcc(List<Integer> pccIdList) {

		String ids = StringManager.join(",", pccIdList);
		StringBuilder builder = new StringBuilder();
		{
			builder.append("SELECT pkid,name,pid FROM bd_dict WHERE ");
			builder.append("pkid IN (" + ids + ") ");
			builder.append("OR pkid IN (SELECT pid FROM bd_dict WHERE pkid IN (" + ids + ")) ");
			builder.append("OR pkid IN (SELECT pid FROM bd_dict WHERE pkid IN ( SELECT pid FROM bd_dict WHERE pkid IN (" + ids + ")))");
		}
		IPersister<Dict> pm = PersisterFactory.create();
		DataTable dataTable = pm.executeTable(builder.toString(), null);
		PccDTO dto = null;
		List<PccDTO> dtoList = new ArrayList<PccDTO>();
		for (IRow row : dataTable) {

			Integer pkid = row.getInteger("pkid");
			String name = row.getString("name");
			Integer pid = row.getInteger("pid");
			dto = new PccDTO();
			{
				dto.setId(pkid);
				dto.setParentId(pid);
				dto.setName(name);
			}
			dtoList.add(dto);
		}

		// 处理上下级关系
		dtoList = PccHelper.listToTree(dtoList);
		return dtoList;
	}

	public static List<PccDTO> listToTree(List<PccDTO> list) {

		List<PccDTO> tops = new ArrayList<PccDTO>();

		for (int i = 0; i < list.size(); i++) {
			PccDTO t = list.get(i);
			if (t.getParentId() == null || t.getParentId().equals(0)) {
				tops.add(t);
			}
		}

		for (PccDTO top : tops) {
			list.remove(top);
		}

		for (PccDTO top : tops) {
			listToTree(top, list);
		}

		return tops;
	}

	public static void listToTree(PccDTO t, List<PccDTO> list) {

		List<PccDTO> children = new ArrayList<PccDTO>();
		for (PccDTO x : list) {
			if (t.getId().equals(x.getParentId())) {
				children.add(x);
			}
		}

		if (t.getItems() == null) {
			t.setItems(new ArrayList<PccDTO>());
		}

		for (PccDTO child : children) {
			
			t.getItems().add(child);
			list.remove(child);
		}

		for (PccDTO child : children) {
			listToTree(child, list);
		}
	}
	
	public static String getFullName(Integer cityId){
		
		String sql = "SELECT GROUP_CONCAT(T3.NAME SEPARATOR '-') AS cityName "
				+ "FROM ( SELECT T2.pkid, T2.`name` FROM ( SELECT @r AS _id , ( SELECT @r := pid FROM `bd_dict` WHERE pkid = _id ) AS parent_id, @l := @l + 1 AS lvl "
				+ "FROM ( SELECT @r := ?, @l := 0 ) vars, `bd_dict` h WHERE @r <> 0 ) T1 JOIN `bd_dict` T2 ON T1._id = T2.pkid ORDER BY T1.lvl DESC ) T3;";
		QueryParameters qps = new QueryParameters();
		qps.add("cityId", cityId, Types.INTEGER);
		IPersister<Dict> pm = PersisterFactory.create();
		DataTable dataTable = pm.executeTable(sql, qps);
		String cityName = "";
		for (IRow row : dataTable) {

			cityName = row.getString("cityName");
		}
		
		return cityName;
	}
}
