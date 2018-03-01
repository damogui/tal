package com.gongsibao.utils.pcc;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.DataTable;
import org.netsharp.core.IRow;
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
}
