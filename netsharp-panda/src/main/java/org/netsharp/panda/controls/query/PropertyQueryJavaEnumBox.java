package org.netsharp.panda.controls.query;

import java.util.Map;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.core.PandaException;
import org.netsharp.panda.entity.PQueryItem;
import org.netsharp.util.JavaEnumManager;

public class PropertyQueryJavaEnumBox implements IPropertyQueryControl {


	@Override
	public Control create(PQueryItem queryItem, Mtable meta) {
		
		Column column = meta.findProperty(queryItem.getPropertyName());
		Combobox  control= new Combobox();
		{
			control.collected = true;
			control.required = queryItem.isRequired();
			control.setId(queryItem.getPropertyName().replace(".", "_"));
			control.controlType = "JavaEnumBoxQueryItem";
			control.setStyle("width:"+queryItem.getWidth()+"px;");
			control.multiple=true;
			control.editable = false;
			control.getInnerValues().put("query", "1");
		}
		
		Class<?> type = column.getType();
		if (!type.isEnum()) {
			String message = queryItem.getPropertyName() + "字段配置成枚举，但是类型为:" + type.getName();
			throw new PandaException(message);
		}
		Map<String, String> map = JavaEnumManager.enumAsMapList2(type);
		control.getControls().add( new SelectOption("-1", "不限",true) );
		for (String key : map.keySet()) {
			String display = map.get(key);

			SelectOption option = new SelectOption();
			{
				option.optionValue = key.trim();
				option.value = display.trim();
			}
			control.getControls().add(option);
		}
		return control;
	}
}
