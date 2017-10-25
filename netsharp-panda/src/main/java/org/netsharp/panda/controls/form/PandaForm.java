package org.netsharp.panda.controls.form;

import java.util.Collection;

import org.netsharp.panda.controls.other.Div;
import org.netsharp.panda.controls.other.Fieldset;
import org.netsharp.util.StringManager;


public class PandaForm extends Form
{	
	
//	//是否明细（明细不渲染控件）
//	public boolean isDetail=false;
	
	public Object obj;
	
    private org.netsharp.panda.entity.PForm pForm;

    @Override
    public void initialize()
    {
        Div formContent = new Div(); 
        formContent.setClassName("formContent");
        Collection<FormFieldGroup> groups = FormFieldGroup.groupby(this.pForm);
        for (FormFieldGroup info : groups)
        {
            FormGroup formGroup = new FormGroup(); 
            { 
            	formGroup.pform = getPForm(); 
                formGroup.grouping = info;
                formGroup.obj = obj;
            }
        	
        	if(!StringManager.isNullOrEmpty(info.getGroupName())){

    			Fieldset fieldse = new Fieldset();{
    				
    				fieldse.title = info.getGroupName();
    				fieldse.getControls().add(formGroup);
    	            formContent.getControls().add(fieldse);   
    			}
        	}else{

                formContent.getControls().add(formGroup);   
        	}         
        }

        this.getControls().add(formContent);
        super.initialize();
    }

	public org.netsharp.panda.entity.PForm getPForm() {
		
		return pForm;
	}

	public void setPForm(org.netsharp.panda.entity.PForm pForm) {
		
		this.pForm = pForm;
	}
}