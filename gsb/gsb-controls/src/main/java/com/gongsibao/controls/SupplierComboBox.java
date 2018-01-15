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

import com.gongsibao.entity.supplier.Supplier;



/**
 * 分配服务商
 * @author Administrator
 *
 */

public class SupplierComboBox implements IPropertyControl{

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
			enumbox.controlType = "SupplierComboBox";
			enumbox.editable = true;
		}
		List<Supplier> list = getSupplierList();
		for (Supplier item : list) {

			SelectOption option = new SelectOption();
			{
				option.optionValue = item.getId().toString();
				option.value = item.getName();
			}
			enumbox.getControls().add(option);
		}

		return enumbox;
	}
	
	private List<Supplier> getSupplierList(){
		
		Oql oql = new Oql();
		{
			oql.setType(Supplier.class);
			oql.setSelects("id,name");
			//oql.setFilter("disabled=1");
		}
		IPersister<Supplier> pm = PersisterFactory.create();
		return pm.queryList(oql);
	}
}