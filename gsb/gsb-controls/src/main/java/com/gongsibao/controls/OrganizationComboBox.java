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

import com.gongsibao.entity.uc.Organization;

public class OrganizationComboBox implements IPropertyControl{

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
			enumbox.controlType = "OrganizationComboBox";
			enumbox.editable = true;
		}
		List<Organization> list = getOrganizationList();
		for (Organization org : list) {

			SelectOption option = new SelectOption();
			{
				option.optionValue = org.getId().toString();
				option.value = org.getShortName();
			}
			enumbox.getControls().add(option);
		}

		return enumbox;
	}
	
	private List<Organization> getOrganizationList(){
		
		Oql oql = new Oql();
		{
			oql.setType(Organization.class);
			oql.setSelects("id,shortName");
			oql.setFilter("enabled=1");
		}
		IPersister<Organization> pm = PersisterFactory.create();
		return pm.queryList(oql);
	}
}
