package org.netsharp.authorization;

import java.util.List;

import org.netsharp.application.Application;
import org.netsharp.core.Column;
import org.netsharp.core.Mtable;
import org.netsharp.core.MtableManager;
import org.netsharp.core.TableRelation;
import org.netsharp.organization.entity.Operation;
import org.netsharp.organization.entity.OperationType;
import org.netsharp.resourcenode.entity.ResourceNode;
import org.netsharp.util.StringManager;

public class PermissionManager {

	private static Boolean isNeedPermission = null;// 是否需要权限控制
	private static Boolean isNeedPermission(){
		if(isNeedPermission==null){
			String value = Application.getConfig("org.netsharp.panda.ispermission");
			if(StringManager.isNullOrEmpty(value)){
				isNeedPermission = true;
			}else{
				isNeedPermission = Boolean.valueOf(value);
			}
		}
		return isNeedPermission;
	}

	public static boolean isPermission(ResourceNode node, Integer operationId, OperationType ot1, OperationType ot2) {

		if (!isNeedPermission()) {
			return true;
		}

		if (operationId != null) {
			return PermissionManager.isPermission(operationId);
		}
		
		if (node == null){
			return true;
		}
		else if (ot1 != null && ot2 != null) {
			return PermissionManager.isPermission(node, ot1, ot2);
		} else if (ot1 != null) {
			return PermissionManager.isPermission(node, ot1);
		} else {
			return true;
		}
	}

	public static boolean isPermission(ResourceNode node, OperationType ot) {

		UserPermission up = UserPermissionManager.getUserPermission();
		for (Operation operation : up.getOperations()) {
			if(operation.getResourceNodeId()==null){
				continue;
			}
			
			if (operation.getOperationTypeId().equals(ot.getId()) && operation.getResourceNodeId().equals(node.getId())) {
				return true;
			}
		}
		return false;
	}

	public static boolean isPermission(ResourceNode node, OperationType ot1, OperationType ot2) {

		boolean t1 = PermissionManager.isPermission(node, ot1);
		boolean t2 = PermissionManager.isPermission(node, ot2);

		return t1 | t2;
	}

	public static boolean isPermission(Object operationId) {

		UserPermission up = UserPermissionManager.getUserPermission();
		for (Operation operation : up.getOperations()) {
			if (operation.getId().equals(operationId)) {
				return true;
			}
		}
		return false;
	}

	// 是否有某个字段权限
	// pathName可能为多级属性，如entityId:org.netsharp.test.SalesOrder,propertyName:customer.phoneNumber,实际上控制的是客户的手机号权限
	public static boolean isFieldGetway(String entityId, String propertyName) {

		if (!isNeedPermission()) {
			return true;
		}
		
		if(StringManager.isNullOrEmpty(entityId)){
			return true;
		}

		UserPermission up = UserPermissionManager.getUserPermission();
		
		Mtable meta = MtableManager.getMtable(entityId);
		Column column = meta.findProperty(propertyName);
		if (column == null) {
			return false;
		}

		if (propertyName.contains(".")) {
			int index = propertyName.lastIndexOf(".");
			String path = propertyName.substring(index);
			TableRelation relation = meta.findRelation(path);
			if (relation == null) {
				return true;
			}
			entityId = relation.getToEntityId();
		}

		List<String> fields = up.getFieldGeteways().get(entityId);
		if (fields == null) {
			return true;
		}

		for (String field : fields) {
			if (StringManager.equals(field, column.getPropertyName())) {
				return true;
			}
		}

		return false;
	}
}
