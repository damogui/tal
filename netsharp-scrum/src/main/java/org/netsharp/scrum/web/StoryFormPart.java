/**  
 * @Title: SupportFormPart.java 
 * @Package org.netsharp.prj.web
 * @Description: TODO
 * @author hanwei
 * @date 2015-6-5 下午2:05:52 
 * @version V1.0  
 */

package org.netsharp.scrum.web;

import org.netsharp.core.DataTable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Row;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.FormPart;
import org.netsharp.scrum.entity.Story;
import org.netsharp.util.StringManager;

/*支持表单controller*/
public class StoryFormPart extends FormPart {

	@Override
	public IPersistable newInstance(Object par) {

		Story entity = (Story) super.newInstance(par);
		entity.setCode(this.getCode(MtableManager.getMtable(Story.class.getName()).getTableName()));

		return entity;
	}

	private String getCode(String tableName) {
		String sqlString = "select max(id) as c from " + tableName;
		DataTable table = this.getService().executeTable(sqlString, null);
		Object code = null;
		for (Row row : table) {
			code = row.get("c");
		}
		if (code == null) {
			code = 0;
		}
		Long id = Long.valueOf(code.toString());
		code = id + 1;
		String voucherCode = StringManager.padLeft(code.toString(), 6, '0');

		sqlString = " select id from " + tableName + " where code ='" + voucherCode + "'";
		table = this.getService().executeTable(sqlString, null);
		if (table.size() != 0) {
			Integer c = Integer.parseInt(voucherCode) + 1;
			voucherCode = StringManager.padLeft(c.toString(), 6, '0');
		}

		return voucherCode;
	}

}
