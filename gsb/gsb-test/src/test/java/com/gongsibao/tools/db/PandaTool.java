//package com.gongsibao.db;
//
//import java.sql.Timestamp;
//import java.util.Date;
//
//import org.junit.Test;
//import org.netsharp.core.Column;
//import org.netsharp.core.Mtable;
//import org.netsharp.core.MtableManager;
//import org.netsharp.core.TableReference;
//import org.netsharp.panda.controls.ControlTypes;
//
//import com.gongsibao.ma.entity.AcquisitionDemand;
//import com.gongsibao.ma.entity.SellingDemand;
//
//import junit.framework.Assert;
//
//public class PandaTool {
//
//	Class<?> type = AcquisitionDemand.class;
//
//	@Test
//	public void datagrid() {
//
//		Mtable meta = MtableManager.getMtable(type);
//
//		for (Column column : meta.getColumns()) {
//			
//			TableReference tr = this.isRef(column.getColumnName());
//			
//			if(tr!=null) {
//				System.out.println("addColumn(datagrid, \"" + tr.getReferenceCode() + ".name\", \"" + tr.getReferenceName()+ "\", ControlTypes.TextBox, 80, false,true,null,null,null);");
//			}else {
//				System.out.println("addColumn(datagrid, \"" + column.getPropertyName() + "\", \"" + column.getHeader() + "\", ControlTypes.TextBox, 80, false,true,null,null,null);");
//			}
//		}
//	}
//
//	@Test
//	public void query() {
//
//		Mtable meta = MtableManager.getMtable(type);
//
//		for (Column column : meta.getColumns()) {
//
//			System.out.println("item = new PQueryItem();");
//			System.out.println("{");
//			System.out.println("    item.toNew();");
//			System.out.println("    item.setPropertyName(\"" + column.getPropertyName() + "\");");
//			System.out.println("    item.setHeader(\"" + column.getHeader() + "\");");
//			System.out.println("    item.setControlType(ControlTypes."+this.getControlType(column.getType())+");");
//			System.out.println("}");
//			System.out.println("queryProject.getQueryItems().add(item);	");
//			System.out.println("");
//		}
//	}
//
//	@Test
//	public void form() {
//		
//		Mtable meta = MtableManager.getMtable(type);
//
//		for (Column column : meta.getColumns()) {
//
//			System.out.println("addFormField(form, \""+column.getPropertyName()+"\", \""+column.getHeader()+"\", ControlTypes."+this.getControlType(column.getType())+", false, false);");
//		}
//	}
//	
//	@Test
//	public void ttttttttttt() {
//		
//		TableReference tr = this.isRef("pay_status_id");
//		
//		Assert.assertNotNull(tr);
//	}
//	
//	private TableReference isRef(String property) {
//		
//		Mtable meta = MtableManager.getMtable(type);
//		
//		for(TableReference tr : meta.getReferences().values()) {
//			if(tr.getForeignkey().equals(property)) {
//				return tr;
//			}
//		}
//		
//		return null;
//	}
//	
//	private ControlTypes getControlType(Class<?> type){
//		if(type.isEnum()) {
//			return ControlTypes.JavaEnumBox;
//		}
//		else if(type == Double.class || type == Integer.class || type == Short.class) {
//			return ControlTypes.NumberBox;
//		}
//		else if(type == Date.class || type == Timestamp.class ) {
//			return ControlTypes.DateBox;
//		}
//		else if(type == Boolean.class) {
//			return ControlTypes.CheckBox;
//		}
//		
//		return ControlTypes.TextBox;
//	}
//}
