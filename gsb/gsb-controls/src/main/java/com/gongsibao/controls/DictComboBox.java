package com.gongsibao.controls;

import java.util.ArrayList;
import java.util.List;

import org.netsharp.base.IReferenceFilterBuilder;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.Oql;
import org.netsharp.core.TableRelation;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.ControlTypes;
import org.netsharp.panda.controls.form.FormGroup;
import org.netsharp.panda.controls.form.IPropertyControl;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;
import org.netsharp.util.ReflectManager;
import org.netsharp.util.StringManager;

import com.gongsibao.entity.bd.Dict;

/**   
 * @ClassName:  DicComboBox   
 * @Description:TODO 字典下拉控件
 * @author: 韩伟
 * @date:   2017年12月3日 下午5:58:40   
 *     
 * @Copyright: 2017 www.yikuaxiu.com Inc. All rights reserved. 
 */
public class DictComboBox implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {

		String[] paths = formField.getPropertyName().split("\\.");
		ArrayList<String> ss = new ArrayList<String>();
		for (String p : paths) {
			ss.add(p);
		}
		ss.remove(ss.size() - 1);

		Mtable meta = MtableManager.getMtable(form.getEntityId());
		TableRelation relation = meta.findRelation(StringManager.join(".", ss));
		Combobox control = new Combobox();
		{
	        control.collected = true;
	        control.setId(formField.getPropertyName().replaceAll("\\.", "_"));
			control.setName(formField.getPropertyName().replaceAll("\\.", "_"));
	        control.required = formField.isRequired();
	        control.controlType = ControlTypes.CUSTOM.getName();
	        control.customControlType = this.getClass().getName();
	        control.defaultValue = formField.getDefaultValue();
	        control.valueField = "id";
	        control.textField = "name";
	        control.innerValues.put("foreignId", relation.getForeignProperty());
	        control.innerValues.put("foreignName", formField.getPropertyName());
	        control.innerValues.put("foreignkey", relation.getForeignProperty());
	        
			if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
				
				control.onSelect = "function(record){"+formField.getTroikaTrigger()+"}";
			}
	        if (formField.isFullColumn())
	        {
	        	control.setStyle("width:100%;");
	        }else{
	        	
	        	control.width = formField.getWidth();
			}
		}

		List<String> filterList = new ArrayList<String>();
		if(!StringManager.isNullOrEmpty(formField.getTroikaCodition())){

			IReferenceFilterBuilder filterBuilder = (IReferenceFilterBuilder)ReflectManager.newInstance(formField.getTroikaCodition());
			String builderFilter = filterBuilder.builderFilter();
			if(!StringManager.isNullOrEmpty(builderFilter)){
			
				filterList.add(builderFilter);
			}
		}
		
		String refFilter = formField.getRefFilter();
		filterList.add(refFilter);
		
		String filter = StringManager.join(" and ",filterList);
		List<Dict> dictList = queryDicList(filter);
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
