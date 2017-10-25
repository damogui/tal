package org.netsharp.panda.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.convertor.impl.DateConvertor;
import org.netsharp.core.property.IProperty;
import org.netsharp.entity.IPersistable;
import org.netsharp.panda.commerce.EasyuiDatagridResult;
import org.netsharp.panda.entity.PDatagrid;
import org.netsharp.panda.entity.PDatagridColumn;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.StopWatch;

public class DatagridResultJson {
	
    private EasyuiDatagridResult result;
    private PDatagrid pdatagrid;
    
    public DatagridResultJson(EasyuiDatagridResult result,PDatagrid pdatagrid){
    	
    	this.result=result;
    	this.pdatagrid=pdatagrid;
    }
    
    public Object parse(){
    	
    	StopWatch sw=new StopWatch();
    	sw.start();
    	
    	Mtable meta = MtableManager.getMtable(pdatagrid.getEntityId());
    	Column keyColumn = meta.getKeyColumn();
    	IProperty keyProperty = keyColumn.getProperty();
    	
    	DateConvertor convertor=new DateConvertor();
    	
    	HashMap<String,Object> maps=new HashMap<String,Object>();
    	
    	List<Object> rows=new ArrayList<Object>();
    	
    	for(Object obj : result.getRows()){
    		
    		IPersistable p = (IPersistable)obj;
    		
    		HashMap<String,Object> item=new HashMap<String,Object>();
    		
    		//
    		Object key = keyProperty.field(p);
    		item.put(keyColumn.getPropertyName(), key);
    		
    		for(PDatagridColumn column : this.pdatagrid.getColumns()){
    			
    			String propertyName = column.getPropertyName();
//    			boolean isPermission = PermissionManager.isFieldGetway(pdatagrid.getEntityId(), propertyName);
//    			if(!isPermission){
//    				continue;
//    			}
    			
    			Object value= p.get(propertyName);
    			if(value == null){
    				
    				value = null;
    			}
    			else if(value instanceof Date){
    				
    				value = convertor.toString(value);
    			}
    			else if(value.getClass().isEnum()){
    				
    				value = JavaEnumManager.getText((Enum<?>)value);
    			}
    			item.put(column.getColumnName(), value);
    		}
    		
    		rows.add(item);
    	}
    	
    	maps.put("total", this.result.getTotal());
    	maps.put("rows", rows);
    	if(this.result.getFooter() != null && this.result.getFooter().size() > 0){

        	maps.put("footer", this.result.getFooter());
    	}
    	sw.stop();
    	
    	System.out.println("序列化耗时："+sw.getEclipsed());
    	
    	return maps;
    }
}
