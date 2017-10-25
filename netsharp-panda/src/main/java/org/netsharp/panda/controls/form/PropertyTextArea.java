package org.netsharp.panda.controls.form;

import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.TextArea;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;

public class PropertyTextArea implements IPropertyControl {
	
	public Control create(PForm form, PFormField formField, FormGroup group) {
		
		TextArea textarea = new TextArea();
		{
			textarea.collected = true;
			textarea.setId(formField.getPropertyName());
			textarea.required = formField.isRequired();
		}
		
		if(formField.getHeight() == 0){
			formField.setHeight(60);
		}

		String style = "";
		if(formField.isFullColumn()){
			 style += "width:100%;";
		}else{
			
			style+="width:" + formField.getWidth() + "px;";
		}
		
		if (formField.getHeight() > 0) {

			style+="height:" + formField.getHeight() + "px;";
		}

		textarea.setStyle(style);
		if (formField.isReadonly() || form.isReadOnly()) {
			textarea.disabled = true;
		}
		
		if (formField.getTooltip()!=null) {
			textarea.placeholder =formField.getTooltip();
		}

		return textarea;
	}
}
