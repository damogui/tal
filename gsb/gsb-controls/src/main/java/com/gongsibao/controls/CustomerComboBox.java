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

import com.gongsibao.entity.crm.NCustomer;



/**
 * 客户
 * @author Administrator
 *
 */
public class CustomerComboBox implements IPropertyControl{

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
			enumbox.controlType = "CustomerComboBox";
			enumbox.editable = true;
		}
		List<NCustomer> list = getNCustomerList();
		for (NCustomer item : list) {

			SelectOption option = new SelectOption();
			{
				option.optionValue = item.getId().toString();
				option.value = item.getRealName();
			}
			enumbox.getControls().add(option);
		}

		return enumbox;
	}
	
	private List<NCustomer> getNCustomerList(){
		
		Oql oql = new Oql();
		{
			oql.setType(NCustomer.class);
			oql.setSelects("id,realName");
			//oql.setFilter("disabled=1");
		}
		IPersister<NCustomer> pm = PersisterFactory.create();
		return pm.queryList(oql);
	}
}
