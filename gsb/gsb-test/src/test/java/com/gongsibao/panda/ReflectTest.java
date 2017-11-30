package com.gongsibao.panda;

import java.lang.reflect.Field;
import java.util.Map;

import org.junit.Test;
import org.netsharp.core.annotations.Subs;
import org.netsharp.panda.controls.input.SelectOption;
import org.netsharp.panda.core.PandaException;
import org.netsharp.util.JavaEnumManager;
import org.netsharp.util.ReflectManager;

public class ReflectTest {

	@Test
	public void run() throws ClassNotFoundException {

		
		Class<?> type = Class.forName("org.netsharp.organization.entity.Employee");
		String enumFieldName = "postType";
		Field field = ReflectManager.getField(type, "postTyes");
		
		Subs subs = field.getAnnotation(Subs.class);
		String foreignKey = subs.foreignKey();
		System.out.println(foreignKey);
		
		Class<?> subType = subs.subType();
		
		//获取明细表的字段
		Field enumField = ReflectManager.getField(subType, enumFieldName);

		System.out.println(enumField.getType().getName());
		
		if (!enumField.getType().isEnum()) {
			String message = "字段配置成枚举，但是类型为:" + enumField.getName();
			throw new PandaException(message);
		}
		System.out.println(subs.subType().getName());
		Map<String, String> map = JavaEnumManager.enumAsMapList2(enumField.getType());
		
		for (String key : map.keySet()) {
			
			String display = map.get(key);
			SelectOption option = new SelectOption();
			{
				option.optionValue = key;
				option.value = display;
			}

			System.out.println(key+"："+display);
		}
		
		
		
		
		
		
		
		
//		Map<String, String> enumAsMapList = JavaEnumManager.enumAsMapList2(AggregateType.class);
//		String json = JsonManage.serialize2(enumAsMapList);
//		System.out.print(json);
		// PPart part = new PPart();
		// Field fields[] = part.getClass().getDeclaredFields();
		// String[] name = new String[fields.length];
		// Object[] value = new Object[fields.length];
		//
		// try {
		// Field.setAccessible(fields, true);
		// for (int i = 0; i < name.length; i++) {
		// name[i] = fields[i].getName();
		// System.out.println(name[i] + "-> ");
		// value[i] = fields[i].get(part);
		// System.out.println(value[i]);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// Annotation tableAnnotation = PPart.class.getAnnotation(Table.class);

		// Long
		// BigDecimal
		// Boolean
		// BigInteger
		// Date
		// String
		// ArrayList

		// 判断是否持久化实体
		// System.out.println(tableAnnotation!=null);

		// for (Annotation a : annotations) {
		//
		// boolean isComposite =
		// Table.class.equals(a.annotationType().getClass());
		// System.out.println(isComposite);
		// System.out.println(a.annotationType());
		// }

		// Field[] fields = ReflectManager.getDeclaredFields(PPart.class);
		// for (Field field : fields) {

		// Annotation referenceAnnotation =
		// field.getAnnotation(Reference.class);
		// Annotation compositeOneAnnotation =
		// field.getAnnotation(CompositeOne.class);
		//
		// if(referenceAnnotation != null || compositeOneAnnotation != null){
		// System.out.println(field.getName());
		// }
		// System.out.println(field.getName()+"："+field.getModifiers());
		// System.out.println(field.getType().getName());
		// System.out.println(field.getType());
		// System.out.println(field.getGenericType());
		// System.out.println("--------------------------------------");
		// System.out.println(field.getName());
		// }
	}
}
