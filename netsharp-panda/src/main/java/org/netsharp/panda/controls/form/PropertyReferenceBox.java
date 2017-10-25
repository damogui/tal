package org.netsharp.panda.controls.form;

import java.util.ArrayList;

import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableRelation;
import org.netsharp.panda.controls.Control;
import org.netsharp.panda.controls.input.ReferenceBox;
import org.netsharp.panda.entity.PForm;
import org.netsharp.panda.entity.PFormField;
import org.netsharp.panda.entity.PReference;
import org.netsharp.util.StringManager;

public class PropertyReferenceBox implements IPropertyControl {
	
	public Control create(PForm form, PFormField formField, FormGroup group) {
		
		String[] paths = formField.getPropertyName().split("\\.");
		ArrayList<String> ss = new ArrayList<String>();
		for (String p : paths) {
			ss.add(p);
		}
		ss.remove(ss.size() - 1);

		Mtable meta = MtableManager.getMtable(form.getEntityId());
		TableRelation relation = meta.findRelation(StringManager.join(".", ss));
		ReferenceBox referenceBox = new ReferenceBox();
		{
			PReference pReference = formField.getReference();
			
			referenceBox.collected = true;
			referenceBox.foreignId = relation.getForeignProperty();
			referenceBox.foreignName = formField.getPropertyName();
			referenceBox.foreignkey = relation.getForeignProperty();
			referenceBox.setId(formField.getPropertyName().replaceAll("\\.", "_"));
			referenceBox.setName(formField.getPropertyName().replaceAll("\\.", "_"));
			referenceBox.reference = pReference;
			referenceBox.url = "/panda"+formField.getReference().getPopupUrl();
			referenceBox.required = formField.isRequired();
			referenceBox.idField = relation.getToTable().getKeyColumn().getPropertyName();
			referenceBox.isPopup = formField.getReference().isPopup();
			referenceBox.windowTitle = "选择"+formField.getHeader();
			
			//改变事件
			if(!StringManager.isNullOrEmpty(formField.getTroikaTrigger())){
				
				referenceBox.onChange = "function(newValue, oldValue){"+formField.getTroikaTrigger()+"}";
			}

			if(!StringManager.isNullOrEmpty(formField.getRefFilter())){
				referenceBox.filter = formField.getRefFilter().replaceAll("'", "----").replace("=", "____");//RestReferenceService.processRequest
			}
			
	        if (formField.isFullColumn())
	        {
	        	referenceBox.setStyle("width:100%;");
	        }else{
	        	
	        	referenceBox.width = formField.getWidth();
			}
			
			if(!referenceBox.isPopup){
				
				if(pReference.getWidth() != null && pReference.getWidth()>0L){
					
					referenceBox.windowWidth = Integer.parseInt(pReference.getWidth().toString()); 
				}
				
				if(pReference.getHeight() != null && pReference.getHeight() > 0L){
					
					referenceBox.windowHeight = Integer.parseInt(pReference.getHeight().toString()); 
				}
				//解决手动输入的值，在下拉表格中不存在，保存时报错。
				referenceBox.onHidePanel="function(){var grid = $(this).combogrid('grid');var row = grid.datagrid('getSelected');if(row==null){$(this).combogrid('clear');}}";
			}
		}

		return referenceBox;
	}
}
