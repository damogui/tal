package org.netsharp.persistence.oql.languangeEngine.items;

import org.netsharp.persistence.oql.operation.select.OperationType;

public abstract class Operation extends LexerToken
{
    /// <summary>
    /// 操作名称
    /// </summary>
    public abstract String getValue();

    /// <summary>
    /// 操作符是否需要空格
    /// </summary>
    public abstract Boolean isSpace();

    /// <summary>
    /// 操作符
    /// </summary>
    //[Exclusive, SerializeIgnore]
    public OperationType getOperationType()
    {
    	return OperationType.Two;
    }

    //[Exclusive, SerializeIgnore]
    public String getLeftSplit() {
    	return " ";   }

    //[Exclusive, SerializeIgnore]
    public String getRightSplit() { 
    	return " ";}

    public Object execute()
    {
       return null;
    }

    public int getPriority()
    {
    	return 10; 
    }

    @Override
    public  String toString()
    {
        return this.getValue();
    }
}
