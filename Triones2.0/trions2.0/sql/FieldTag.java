package trionesII.sql;

import java.lang.reflect.Field;
import java.util.List;
import java.sql.Types;
import trionesII.util.*;
import java.io.Serializable;

/**
* 列对象（创建于 2003.03.05）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
列对象用于构建库表或视图结构。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
略
</pre>
<DT><B>名词解释：</B><DD>
<pre>
略
</pre>
<DT><B>注意事项：</B><DD>
<pre>
基于JDK1.3
</pre>
<DT><B>展望未来：</B><DD>
<pre>
略
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wyq@triok.com 
 电  话:021-68672222-2013
</pre>
</DL>
* @author	吴勇庆
* @version	2003.03.05
*/
class FieldTag implements Cloneable, Serializable// extends Properties
{	
/**
* 表名（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _TableName = "";
/**
* 表别名（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
与表名对应
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _TableAlias = "";
	
/**
* 列名（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _Name;
	
/**
* 数据库字段名（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _DBName;	
	
/**
* 查询时的列表达式（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _Compute;
	
/**
* 排序时的列表达式（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _SortCompute;
	
/**
* 分组时的列表达式（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _GroupCompute;

/**
* 条件表达式（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	String _CndExpression;

/**
* 条件中绑定值的个数（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	int _CndValCount;

/**
* 绑定值（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	private	Object _Value = null;
	
/**
* 绑定值类型（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	private Class _ValueType = null;
	/**域对应的数据库类型*/
	//public int sqlType;
	
/**
* 是否在执行语句中被隐藏（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	boolean _IsHidden;
/**
* 是否在条件语句中被隐藏（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	boolean _IsCndHidden;	

/**
* 作为附加条件域时的关系符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
默认为AND
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	int _CndRelation = Syntax.CNDRELATION_AND;
	
/**
* 数据域的值是否有多个（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
如果为多个值，则以List形式存放.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	boolean _IsArray = false;
	
		
/**
* 预编译语句中的绑定值占位符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	final static char BIND_VALUE = '?';
	
/**
* 列占位符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	final static char COLUMN = '#';
	
/**
* 值占位符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	final static char VALUE = '@';
	
/**
* 伪值占位符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
伪值即值的字符串形式
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	final static char SIMULATED_VALUE = '$';
	
/**
* 默认条件表达式（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	final static String CNDEXPRESSION = COLUMN + " = " + VALUE;
/**
* 查询表达式（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/	
	final static String COMPUTE = "" + COLUMN;


/**
* 构造方法（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
提供方法和数据结构的调用
可用于List转换到数组时作为默认的数组类型
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
*/
	FieldTag()
	{
	}

	

	
/**
* 构造方法（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param name 列名.
* @param dbName 列名对应的数据库字段名.
* @param type 列的数据类型.
* @param value 列的值.
*/
	FieldTag(String name,String dbName,Class type,Object value)
	{	init(name,dbName,type,value);
	}
	
/**
* 初始化方法（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
初始化列的各项属性.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param name 列名.
* @param dbName 列名对应的数据库字段名.
* @param type 列的数据类型.
* @param value 列的值.
*/
	void init(String name,String dbName,Class type,Object value)
	{	_Name = name;
		_ValueType = type;
		_Value = value;
		_DBName = dbName;		
		//sqlType = getSQLType(f.getType());
		_Compute = COMPUTE;
		_SortCompute = COMPUTE;
		_GroupCompute = COMPUTE;		
		_CndExpression = CNDEXPRESSION;		
		_IsHidden = false;
		_IsCndHidden = false;
		
	}

/**
* 取可作为预编译SQL语句片段的列条件表达式（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
修改于 2003.03.18 - 增加当条件值为null时的自动修正功能.	
</pre>
</DL>
* @param fullName 数据库字段名中是否包含库表名.
* @return 条件表达式.
*/
	String getPreparedCondition(boolean fullName)
	{	String ls_dbname = getDBName(fullName);
		String ls_express;

		//修改于 2003.03.18.
		ls_express = _CndExpression;
		
		if(_Value==null)
		{	if(ls_express.equals("# != @")||ls_express.equals("# <> @"))
				ls_express = COLUMN + " IS NOT NULL";
			else if(ls_express.equals("# = @"))
				ls_express = COLUMN + " IS NULL";
		}
	
		ls_express = StringMethod.replaceAll(ls_express,COLUMN,ls_dbname);
		
		//ls_express = StringMethod.replaceAll(_CndExpression,COLUMN,ls_dbname);
		ls_express = StringMethod.replaceAll(ls_express,SIMULATED_VALUE,"" + _Value);
		ls_express = StringMethod.replaceAll(ls_express,VALUE,BIND_VALUE);
		_CndValCount = StringMethod.charCount(ls_express,BIND_VALUE);
		
		return ls_express;
	}
	
	
/**
* 取可作为预编译SQL语句片段的列查询表达式（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param fullName 数据库字段名中是否包含库表名.
* @return 查询表达式.
*/
	String getCompute(boolean fullName)
	{	String ls_dbname = getDBName(fullName);
		String ls_name = getName(fullName);
		String ls_compute = _Compute.equals(COMPUTE)?ls_dbname:StringMethod.replaceAll(_Compute,COLUMN,ls_dbname);	
		
		if(!ls_compute.equals(ls_name))
			ls_compute = ls_compute + " AS " + ls_name;
			
		return ls_compute;
	}

	
/**
* 取可作为预编译SQL语句片段的列分组表达式（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param fullName 数据库字段名中是否包含库表名.
* @return 分组表达式.
*/
	String getGroupCompute(boolean fullName)
	{	String ls_dbname = getDBName(fullName);
		return _GroupCompute.equals(COMPUTE)?ls_dbname:StringMethod.replaceAll(_GroupCompute,COLUMN,ls_dbname);
	}
	
/**
* 取可作为预编译SQL语句片段的列排序表达式（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param fullName 数据库字段名中是否包含库表名.
* @return 排序表达式.
*/
	String getSortCompute(boolean fullName)
	{	String ls_dbname = getDBName(fullName);
		return _SortCompute.equals(COMPUTE)?ls_dbname:StringMethod.replaceAll(_SortCompute,COLUMN,ls_dbname);
	}
	
/**
* 取与列名对应的数据库字段名（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param fullName 数据库字段名中是否包含库表名.
* @return 分组表达式.
*/
	String getDBName(boolean fullName)
	{	return fullName? _TableName + "." + _DBName:_DBName;
	}
	
/**
* 取列名（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param fullName 列名中是否以表别名为前缀.
* @return 列名.
*/
	String getName(boolean fullName)
	{	return (fullName? _TableAlias + "_" + _Name:_Name);
		//return StringMethod.truncate(ls_name,DBMS.NAMING_LENGTH);
	}
	

	
/**
* 设置库表名（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param tblName 库表名.
*/
	void setTableName(String tblName)
	{	_TableName = tblName;
	}
/**
* 设置表别名（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param alias 表别名.
*/
	void setTableAlias(String alias)
	{	_TableAlias = alias; 
	}
	

	
/**
* 设置列值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param val 列值.
	@exception ClassCastException 值类型与列预定义的类型不匹配.
* @see #getValue()
*/
	void setValue(Object val)
	{	if(val!=null)
		{	if(_ValueType==null)
				_ValueType = val.getClass();
			else if(!_ValueType.equals(val.getClass()))
				throw new ClassCastException("val's class is mismatched with " + _Name);
		}
		
		_IsArray = false;
		_Value = val;
	}
/**
* 设置列值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
针对多个列值时使用.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @param val 列值列表.
	@exception ClassCastException 值类型与列预定义的类型不匹配.
* @see #getValue()
*/
	void setValueList(List val)
	{	for(int i=0;i<val.size();i++)
		{	if(_ValueType==null)
				_ValueType = (val.get(i)).getClass(); 
			else if(!_ValueType.equals((val.get(i)).getClass()))
				throw new ClassCastException("val[" + i + "]'s class is mismatched with " + _Name);				
		}			
		_IsArray = true;
		_Value = val;
	}
/**
* 取列值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
针对多个列值时使用.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @return 列值列表.
* @see #setValueList(List)
*/
	List getValueList()
	{	return (List) _Value;
	}
/**
* 取列值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.13.
</pre>
</DL>
* @return 列值.
* @see #setValue(Object)
*/
	Object getValue()
	{	return _Value;
	}
	/**取类型在数据库中对应的类型*/
	//public static int getSQLType(Class objType)
	//{	String[] COLTYPE = {"java.lang.String",
	//											"boolean",
	//											"[B",//byte[]
	//											"byte",												
	//											"short",												
	//											"int",
	//											"long",
	//											"float",
	//											"double",
	//											"jaba.math.BigDecimal",
	//											"java.sql.Date",
	//											"java.sql.Time",
	//											"java.sql.Timestamp"
	//											};
	//	String ls_objType = objType.getName();
	//	int li_objType = StrListMethod.findItem(COLTYPE,ls_objType);
	//	switch(li_objType)
	//	{	case 0:return Types.VARCHAR;
	//		case 1:return Types.BIT;
	//		case 2:return Types.VARBINARY;				
	//		case 3:return Types.TINYINT;
	//		case 4:return Types.SMALLINT;
	//		case 5:return Types.INTEGER;
	//		case 6:return Types.BIGINT;
	//		case 7:return Types.REAL;
	//		case 8:return Types.DOUBLE;
	//		case 9:return Types.NUMERIC;
	//		case 10:return Types.DATE;
	//		case 11:return Types.TIME;
	//		case 12:return Types.TIMESTAMP;
	//		default:Debug.println("unknow type:" + ls_objType);
	//						return Types.VARCHAR;
	//	}		
	//}
	
}

