package org.netsharp.core.property;

import org.netsharp.util.DES;
import org.netsharp.util.StringManager;

public class EncryptionProperty extends EntityProperty {

	@Override
	public Object dbField(Object owner) {
		try {
			String propertyValue = (String)field.get(owner);
			String dbValue=DES.encrypt(propertyValue, DES.getKey());
			
			return dbValue;
			
		} catch (Exception e) {

			logger.error(e);
		}

		return null;
	}

	@Override
	public void dbField(Object owner, Object value) {
		
		String dbValue = (String)value;
		try {
			String propertyValue = DES.decrypt(dbValue, DES.getKey());
			field.set(owner, propertyValue);
		} catch (Exception e) {
			logger.error( "数据库字段读取到实体字段("+owner.getClass().getName() + "." + field.getName()+"),"+dbValue+",错误："+StringManager.NewLine+e );
		}
	}
}