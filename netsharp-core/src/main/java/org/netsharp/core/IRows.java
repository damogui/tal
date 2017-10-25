package org.netsharp.core;

public interface IRows<T extends IRow> extends Iterable<T> {
	
	ITable<T> getTable();
	Class<?> getType();
	int size();
	IRow get(int index);
	IRow newLine();
	T newItem();
	boolean remove(IRow row);
}
