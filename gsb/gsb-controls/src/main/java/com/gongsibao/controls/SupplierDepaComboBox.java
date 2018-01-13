package com.gongsibao.controls;

import java.util.List;

import org.netsharp.core.Oql;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.form.FormGroup;
import org.netsharp.panda.controls.form.IPropertyControl;
import org.netsharp.panda.controls.input.Combobox;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.persistence.IPersister;
import org.netsharp.persistence.PersisterFactory;

import com.gongsibao.entity.supplier.SupplierDepartment;

/**
 * 分配服务商部门
 * @author Administrator
 *
 */
public class SupplierDepaComboBox implements IPropertyControl{

	@Override
	public Control create(PForm form, PFormField formField, FormGroup group) {
		
		Combobox enumbox = new Combobox();
		{
			if (formField.isFullColumn()) {
				
				enumbox.style = "width:100%;";
			} else if (formField.getWidth() >= 100) {
				
				enumbox.width = 180;
			}
			enumbox.collected = true;
			enumbox.id = formField.getPropertyName();
			enumbox.required = formField.isRequired();
			enumbox.controlType = "SupplierDepaComboBox";
			enumbox.editable = true;
		}
		List<SupplierDepartment> list = getSupplierDepartmentList();
		for (SupplierDepartment org : list) {

			SelectOption option = new SelectOption();
			{
				option.optionValue = org.getId().toString();
				option.value = org.getName();
			}
			enumbox.getControls().add(option);
		}

		return enumbox;
	}
	
	private List<SupplierDepartment> getSupplierDepartmentList(){
		
		Oql oql = new Oql();
		{
			oql.setType(SupplierDepartment.class);
			oql.setSelects("id,name");
			//oql.setFilter("disabled=1");
		}
		IPersister<SupplierDepartment> pm = PersisterFactory.create();
		return pm.queryList(oql);
	}
}
