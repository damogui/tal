package com.gongsibao.controls;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.core.Mtable;
import org.netsharp.core.Oql;
import org.netsharp.core.TableRelation;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.controls.query.IPropertyQueryControl;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.Dict;

public class PropertyQueryDictComboBox implements IPropertyQueryControl {

	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {

		String[] paths = queryItem.getPropertyName().split("\\.");
		ArrayList<String> ss = new ArrayList<String>();
		for (String p : paths) {
			ss.add(p);
		}
		ss.remove(ss.size() - 1);

		TableRelation relation = meta.findRelation(StringManager.join(".", ss));
		Combobox control = new Combobox();
		{
			control.setId(queryItem.getPropertyName().replaceAll("\\.", "_"));
			control.setName(queryItem.getPropertyName().replaceAll("\\.", "_"));
			control.required = queryItem.isRequired();
			control.controlType = ControlTypes.CUSTOM.getName();
			control.setStyle("width:"+queryItem.getWidth()+"px;");
			control.customControlType = "org.netsharp.controls.DictComboBoxQueryItem";
			control.multiple = false;
			control.editable = false;
			control.valueField = "id";
			control.textField = "name";
			control.innerValues.put("foreignId", relation.getForeignProperty());
			control.innerValues.put("foreignName", queryItem.getPropertyName());
			control.innerValues.put("foreignkey", relation.getForeignProperty());
			control.getInnerValues().put("query", "1");
		}

		String refFilter = queryItem.getRefFilter();
		List<Dict> dictList = queryDicList(refFilter);

		control.getControls().add(new SelectOption("-1", "不限", true));
		for (Dict dict : dictList) {

			SelectOption option = new SelectOption();
			{
				option.optionValue = dict.getId().toString();
				option.value = dict.getName();
			}
			control.getControls().add(option);
		}
		return control;
	}

	public List<Dict> queryDicList(String refFilter) {

		Oql oql = new Oql();
		{
			oql.setType(Dict.class);
			oql.setSelects("id,name");
			oql.setFilter(refFilter + " and enabled=1");
			oql.setOrderby(" sort ");
		}

		IPersister<Dict> pm = PersisterFactory.create();
		return pm.queryList(oql);
	}
}
