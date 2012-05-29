package trionesII.sql;

import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.io.Serializable;

import trionesII.util.*;


/**
* 可控指令集核心类（创建于 2003.03.05）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
略
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
class AbsOperator implements Cloneable,Serializable,Operator
{	
/**版本号（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
VERSION = "2003.03.18"
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
	
	public static final String VERSION = "2003.03.18 - Wu yongqing.";	

	
/**
* 还原操作（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于清除已发生的操作状态
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
	protected static final int SQL_DEFAULT = 0;

/**
* 附加条件表达式（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
附加条件表达式直接以SQL形式描述.
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
	private String _SQLCondition = "";
	
/**
* 附加条件关系符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
与指令集用其它方式产生的条件表达式之间的关联方式。
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
	private int _SQLConditionRelation = Syntax.CNDRELATION_AND;
	
/**
* 是否为复合类型（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当指令集中含有多个库表时，称指令集为复合指令集
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
	private boolean _IsMultiple = false;
	

/**
* 与指令集对应的数据库表名（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
数据库表名及字段名均用于生成数据库指令.
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
	private String _DBName;

	/**
	 * 取指令集对应的数据库表名（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
* @return 数据库表名.
* @see #setDBName(String)
*/	
	public String getDBName()
	{	return _DBName;
	}

	/**
	 * 设置实例对应的数据库表名（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的规则为数据库表名等于忽略路径的对象名
	当Operator为复合型时，本方法不影响列名中的表名信息.
	当Operator为简单型时，本方法自动替换列名中的表名信息.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//MyExample对应于库表user_list	
	MyExample user = new MyExample("wyq","wu yongqing");
	com.setDBName("user_list");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param dbname 数据库表名.
* @see #getDBName()
*/
	public void setDBName(String dbName)
	{	setOperatorDBName(dbName);
		
		if(!isMultiple()) setFieldsTableName(dbName);		
	}	
	

	
/**
* 设置Operator本身的数据库名称（创建于 2003.09.19）.
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
创建于 2003.09.19.
</pre>
</DL>
* @param dbname 数据库表名.
* @see #setDBName(String)
*/
	protected void setOperatorDBName(String dbName)
	{	_DBName = dbName;
	}
	
/**
* 设置属性对应的列名的表名（创建于 2003.09.17）.
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
创建于 2003.09.17.
</pre>
</DL>
* @param tblName 表名.
* @see #setOperatorDBName(String)
*/
	private void setFieldsTableName(String tblName)
	{	for(int i=0;i<_Fields.size();i++)
		{	fields(i).setTableName(tblName);
		}
	}
	
/**
* 与指令集对应的表别名（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
表别名及列别名均用于控制操作。
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
	private String _Name;
	

	
/**
* 取指令集名称（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
指令集名称用于控制操作
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
* @return 指令集名称.
* @see #setName(String)
*/
	public String getName()
	{	return _Name;
	}
	
/**
* 设置指令集名称（创建于 2003.03.05）.
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
* @param name 指令集名称.
* @see #getName()
*/
	public void setName(String name)
	{	_Name = name;
	}
	
/**
* 列数组（创建于 2003.03.05）.
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
	//protected FieldTag[] _Fields;
	private List _Fields = new ArrayList();
	
/**
* 列定位（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
简化编程
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
* @param i 列索引.
* @return 列.
*/
	protected FieldTag fields(int i)
	{	return (FieldTag)_Fields.get(i);
	}

	
/**
* 列个数（创建于 2003.03.05）.
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
* @return 列个数.
*/
	
	public int fieldCount()
	{	return _Fields.size();
	}
		
/**
* 指令集操作类型（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于记录当前的操作指令.
如Operator.SQL_SELECT等
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
	private int _OP = SQL_DEFAULT;
	
	
/**
* 构造方法（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
仅提供给子类用。本类必须继承后才可使用。
本类构建后必须按以下顺序使用方法：
setName(String)
setDBName(String)
addField(String,Class,Object)/addField(String,String,Class,Object)/addFields(FieldTag[])
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
	protected AbsOperator(){}
/**
* 增加列（创建于 2003.03.21）.
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
创建于 2003.03.21.
</pre>
</DL>
* @param name 列名.
* @param dbName 数据库字段名.
* @param type 列类型.
* @param val 列值.
	@return 列标识.
* @see #addField(FieldTag)
*/
	protected int addField(String name,String dbName,Class type,Object val)
	{	FieldTag lField = new FieldTag(name,dbName,type,val);
		lField.setTableName(_DBName);
		lField.setTableAlias(_Name); 
		_Fields.add(lField);
		
		return _Fields.size();//与列标识的值相等.
	}


/**
* 增加列（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param f 列实例.
* @see #addField(String,String,Class,Object)
	@return 列标识.
*/
	protected int addField(FieldTag f)
	{	_Fields.add(f);	
		return _Fields.size();//与列标识的值相等.
	}
	
 /**
* 删除列（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
0代表最后1列。
使用本方法可能影响原来的列标识顺序。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param fieldID 列标识.
	@see NoSuchElementException 不存在的列或所有的列均已清空.
* @see #removeField(String)
*/
	protected void removeField(int fieldID)
	{	if(fieldID==0) fieldID = _Fields.size();
	
		int li_index = getFieldIndex(fieldID);
		_Fields.remove(li_index);
	}
	
 /**
* 删除列（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
使用本方法可能影响原来的列标识顺序。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param fieldName 列名.
	@see NoSuchElementException 不存在的列或所有的列均已清空.
* @see #removeField(int)
*/
	protected void removeField(String fieldName)
	{	int li_index = getFieldIndex(fieldName);
		_Fields.remove(li_index);
	}

/**
* 增加列清单（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用本方法应注意列名的唯一性。
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
* @param f 列实例数组.
	@return 增加后的列的总数.
	@see #getFields()
*/
	protected int addFields(FieldTag[] f)
	{	_Fields.addAll(ArrayMethod.asList(f));
		return _Fields.size();
	}

	
/**
* 取列清单（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
导出所有的列实例.
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
* @return 列实例数组.
* @see #addFields(FieldTag[])
*/
	protected FieldTag[] getFields()
	{	FieldTag[] lFields = new FieldTag[_Fields.size()];
	
		_Fields.toArray(lFields);
		return lFields;
	}
	/**
	 * 设置SQL操作类型（创建于 2003.03.05）.
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
* @param op 生成SQL语句片断的类型.
* @param vals 用于存放参数的列表.
	@return SQL表达式.
*/
	public String prepareSQL(int op,List vals)
	{	String ls_sql = "";
	
		_OP = op;
		
		switch(_OP)
		{	case SQL_INSERT:
				ls_sql = prepareInsert(vals);break;
			case SQL_UPDATE:
				ls_sql = prepareUpdate(vals);break;
			case SQL_DELETE:
				ls_sql = prepareDelete();break;
			case SQL_SELECT:
				ls_sql = prepareSelect(_IsMultiple);break;
			case SQL_CONDITION:
				ls_sql = prepareCondition(_IsMultiple,vals);break;	
			case SQL_SORT:
				ls_sql = prepareSort();break;
			case SQL_GROUP:
				ls_sql = prepareGroup();break;
			case SQL_OBJECT:
				ls_sql = getDBName();break;
			default:break;
		}
		
		_OP = SQL_DEFAULT;//还原
		
		return ls_sql;
	}
	
	/**
	 * 生成预编译的INSERT子句（创建于 2003.03.05）.
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
* @param vals 用于存放与绑定参数对应的值数组.
	@return SQL表达式.
*/
	protected String prepareInsert(List vals)
	{	String ls_cols = "",ls_vals = "";
			
		for(int i=0;i<fieldCount();i++)
		{	if(!fields(i)._IsHidden)
			{	if(!ls_cols.equals("")) ls_cols += Syntax.FIELD_APART;
				ls_cols += fields(i)._DBName;
				if(!ls_vals.equals("")) ls_vals += Syntax.FIELD_APART;
				ls_vals += fields(i).BIND_VALUE;
				vals.add(fields(i).getValue());
			}
		}
									
		return Syntax.toINSERT(_DBName,ls_cols,ls_vals);
	}
	
	/**
	 * 生成预编译的UPDATE子句（创建于 2003.03.05）.
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
* @param vals 用于存放与绑定参数对应的值数组.
	@return SQL表达式.
*/
	protected String prepareUpdate(List vals)
	{	String ls_cols = "",ls_vals = "",ls_expressions = "";

		for(int i=0;i<fieldCount();i++)
		{	if(!fields(i)._IsHidden)
			{	if(!ls_expressions.equals("")) ls_expressions += Syntax.FIELD_APART;
				ls_expressions += fields(i)._DBName + " = " + fields(i).BIND_VALUE;
				
				vals.add(fields(i).getValue());
			}
		}
									
		return Syntax.toUPDATE(_DBName,ls_expressions);
	}

	/**
	 * 生成预编译的DELETE子句（创建于 2003.03.05）.
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
	@return SQL表达式.
*/
	protected String prepareDelete()
	{	return Syntax.toDELETE(_DBName);
	}
	

	/**
	 * 生成预编译的SELECT子句（创建于 2003.03.05）.
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
	@param isMultiple 是否为多表.
* @param vals 用于存放与绑定参数对应的值数组.
	@return SQL表达式.
*/
	protected String prepareSelect(boolean isMultiple)
	{	String ls_cols = "";
			
		for(int i=0;i<fieldCount();i++)
		{	if(!fields(i)._IsHidden)
			{	if(!ls_cols.equals("")) ls_cols += Syntax.FIELD_APART;
				ls_cols += fields(i).getCompute(isMultiple);
			}
		}
		return Syntax.toSELECT(_DBName,ls_cols);
	}
	
	/**
	 * 生成预编译的CONDITION子句（创建于 2003.03.05）.
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
	修改于 2003.03.15 - 纠正多值Field产生空指针的错误.
	</pre>
	</DL>
	@param isMultiple 是否为多表.
* @param vals 用于存放与绑定参数对应的值数组.
	@return SQL表达式.
*/
		protected String prepareCondition(boolean isMultiple,List vals)
	{	String ls_expression = "",ls_condition="";//预编译条件表达式
		boolean lb_hasCondition = false;
	  //一般条件列,如果为多表结构，则条件表达式由子对象生成.
		if(!_IsMultiple)
		{	for(int i=0;i<fieldCount();i++)
			{	if(!fields(i)._IsCndHidden)
				{	ls_condition = fields(i).getPreparedCondition(isMultiple);
					
					if(StringMethod.isValid(ls_condition))
					{	lb_hasCondition = true;
						if(StringMethod.isValid(ls_expression)) 
							ls_expression += Syntax.CNDRELATION_SYNTAX[_FieldCndRelation];
						
						ls_expression += ls_condition;
						
						for(int j=0;j<fields(i)._CndValCount;j++)//可能出现条件值的多次引用
							vals.add(fields(i).getValue());					
					}
				}
			}
			
			if(lb_hasCondition)
			{	ls_expression = "(" + ls_expression + ")";			
				lb_hasCondition = false;
			}
		}
		//附加条件列
		for(int i=0;i<_AppendCndFields.size();i++)
		{	FieldTag lField = (FieldTag)(_AppendCndFields.get(i));
			ls_condition = lField.getPreparedCondition(isMultiple);
			
			if(StringMethod.isValid(ls_condition))
			{	lb_hasCondition = true;
				if(StringMethod.isValid(ls_expression)) 
					ls_expression += Syntax.CNDRELATION_SYNTAX[lField._CndRelation];
					
				ls_expression += ls_condition;
				
				if(lField._IsArray) //附加条件为between、in、childsql等
					vals.addAll(lField.getValueList());
				else
					for(int j=0;j<lField._CndValCount;j++)
						vals.add(lField.getValue());	
			}
		}
		
		if(lb_hasCondition)
		{	ls_expression = "(" + ls_expression + ")";			
			lb_hasCondition = false;
		}
				
		//附加条件（参数格式与数据库相关）		
		if(StringMethod.isValid(_SQLCondition))
		{	if(StringMethod.isValid(ls_expression))
				ls_expression += Syntax.CNDRELATION_SYNTAX[_SQLConditionRelation];
				
			ls_expression += _SQLCondition;
				
			ls_expression = "(" + ls_expression + ")";
		}

		//附加条件对象（参数格式与数据库相关）		
		for(int i=0;i<_AppendCndOperators.size();i++)
		{	AbsOperator lAbsOperator = (AbsOperator)_AppendCndOperators.get(i);
			List la_appendVals = new ArrayList();
			ls_condition = lAbsOperator.prepareCondition(isMultiple,la_appendVals);

			if(StringMethod.isValid(ls_condition))
			{	lb_hasCondition = true;
				if(StringMethod.isValid(ls_expression))
					ls_expression += Syntax.CNDRELATION_SYNTAX[lAbsOperator._CndRelation];
					
				ls_expression += ls_condition;
				vals.addAll(la_appendVals);
			}			
		}
		
		if(lb_hasCondition)
		{	ls_expression = "(" + ls_expression + ")";			
			lb_hasCondition = false;
		}
		
		return (StringMethod.isValid(ls_expression)&&_OP==SQL_CONDITION)?Syntax.toWHERE(ls_expression):ls_expression;
	}
/*
	protected String prepareCondition(boolean isMultiple,List vals)
	{	String ls_expression = "",ls_condition="";//预编译条件表达式
		boolean lb_hasCondition = false;
	  //一般条件列,如果为多表结构，则条件表达式由子对象生成.
		if(!_IsMultiple)
		{	for(int i=0;i<fieldCount();i++)
			{	if(!fields(i)._IsCndHidden)
				{	ls_condition = fields(i).getPreparedCondition(isMultiple);
					
					if(StringMethod.isValid(ls_condition))
					{	lb_hasCondition = true;
						if(StringMethod.isValid(ls_expression)) 
							ls_expression += Syntax.CNDRELATION_SYNTAX[_FieldCndRelation];
						
						ls_expression += ls_condition;
						
						for(int j=0;j<fields(i)._CndValCount;j++)//可能出现条件值的多次引用
							vals.add(fields(i).getValue());					
					}
				}
			}
			
			if(lb_hasCondition)
			{	ls_expression = "(" + ls_expression + ")";			
				lb_hasCondition = false;
			}
		}
		//附加条件列
		for(int i=0;i<_AppendConditions.size();i++)
		{	PreparedCondition lCondition = (PreparedCondition)(_AppendConditions.get(i));
			ls_condition = lCondition.getCndSyntax();
			
			if(StringMethod.isValid(ls_condition))
			{	lb_hasCondition = true;
				if(StringMethod.isValid(ls_expression)) 
					ls_expression += Syntax.CNDRELATION_SYNTAX[lCondition.getRelation()];
					
				ls_expression += ls_condition;
				
				//附加条件为between、in、childsql等
				vals.addAll(lCondition.getCndValueList());
			}
		}
		
		if(lb_hasCondition)
		{	ls_expression = "(" + ls_expression + ")";			
			lb_hasCondition = false;
		}
				
		//附加条件（参数格式与数据库相关）		
		if(StringMethod.isValid(_SQLCondition))
		{	if(StringMethod.isValid(ls_expression))
				ls_expression += Syntax.CNDRELATION_SYNTAX[_SQLConditionRelation];
				
			ls_expression += _SQLCondition;
				
			ls_expression = "(" + ls_expression + ")";
		}

		return (StringMethod.isValid(ls_expression)&&_OP==SQL_CONDITION)?Syntax.toWHERE(ls_expression):ls_expression;
	}
*/
	/**隐藏指定列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,被隐藏的列对应的数据库表字段名将不出现在非WHERE表达式中.	
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq";	
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//money不在非WHERE表达式出现
	com.hideField("money");
				
	//以下语句相当于执行
	//SELECT name,reg_dt FROM StreamExample
	//WHERE name="wyq" AND reg_dt IS NULL AND money = 1200
	RecordSet lRecord = SQLCA.select(com);		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@see NoSuchElementException 不存在的列.
	@see #hideField(int)
	@see #hideFields()
	*/
	public void hideField(String fieldName)		
	{	int li_index = getFieldIndex(fieldName);
		fields(li_index)._IsHidden = true;
	}

	
/**
* 隐藏指定列（创建于 2003.03.05）.
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
* @param fieldID 列标识.
	@see NoSuchElementException 不存在的列.
* @see #hideField(String)
	@see #hideFields()
*/
	public void hideField(int fieldID)
	{	int li_index = getFieldIndex(fieldID);
		fields(li_index)._IsHidden = true;
	}
 /**显示指定列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,不隐藏的列对应的数据库表字段名将出现在非WHERE表达式中.	
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq";	
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//money不在非WHERE表达式中出现
	com.hideField("money");
	
	//reg_dt不在非WHERE表达式中出现
	com.hideField("reg_dt");
	
	//reg_dt在非WHERE表达式中出现
	com.showField("reg_dt");
	
	//以下语句相当于执行
	//SELECT name,reg_dt FROM StreamExample
	//WHERE name="wyq" AND reg_dt IS NULL AND money = 1200
	RecordSet lRecord = SQLCA.select(com);		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@see NoSuchElementException 不存在的列.
	@see #showField(int)
	@see #showFields()
	*/
	public void showField(String fieldName)		
	{	int li_index = getFieldIndex(fieldName);
		fields(li_index)._IsHidden = false;
	}

	
/**
* 显示指定列（创建于 2003.03.05）.
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
* @param fieldID 列标识.
* @return description.
* @exception NoSuchElementException 不存在的列.
	@see #showField(String)
	@see #showFields()
*/
	public void showField(int fieldID)		
	{	int li_index = getFieldIndex(fieldID);
		fields(li_index)._IsHidden = false;
	}
	/**
	 *显示所有的列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本方法主要用于在hideField方法使用前先显示所有列.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//money、reg_dt不在非WHERE表达式中出现
	com.hideField("money");
	com.hideField("reg_dt");
	
	//reg_dt不在WHERE表达式中出现
	com.hideCndField("reg_dt");
	
	//name,money,reg_dt均在非WHERE表达式中出现
	com.showFields();
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND money = 1200		
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	 * @see #hideFields()
		 @see #showField(String)
	 */
	
	public void showFields()
	{	for(int i=0;i<fieldCount();i++)
			fields(i)._IsHidden = false;
	}
	
	/**
	 *隐藏所有的列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于在showField方法使用前先将所有列隐藏.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//name,money,reg_dt均不在非WHERE表达式中出现
	com.hideFields();
	
	//money、reg_dt在非WHERE表达式中出现
	com.showField("money");
	com.showField("reg_dt");
	
	//reg_dt不在WHERE表达式中出现
	com.hideCndField("reg_dt");	
	
	//以下语句相当于执行
	//SELECT reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND money = 1200		
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	 * @see #showFields()
		 @see #hideField(String)
	 */
	
	public void hideFields()
	{	for(int i=0;i<fieldCount();i++)
			fields(i)._IsHidden = true;
	}
	

	/**
	 * 隐藏作为条件的列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,被隐藏的列对应的数据库表字段名将不出现在WHERE表达式中.
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//reg_dt不在WHERE表达式中出现
	com.hideCndField("reg_dt");	
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND money = 1200		
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 * @exception NoSuchElementException 不存在的列.
		 @see #hideCndField(int)
		 @see #hideCndFields()
	 */	
	public void hideCndField(String fieldName)		
	{	int li_index = getFieldIndex(fieldName);
		fields(li_index)._IsCndHidden = true;
	}

	
/**
* 隐藏作为条件的列（创建于 2003.03.05）.
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
* @param fieldID 列标识.
* @exception NoSuchElementException 不存在的列.
	@see #hideCndField(String)
	@see #hideCndFields()
*/
	public void hideCndField(int fieldID)
	{	int li_index = getFieldIndex(fieldID);
		fields(li_index)._IsCndHidden = true;
	}	
	
	/**
	 * 显示作为条件的列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,不隐藏的列对应的数据库表字段名将出现在WHERE表达式中.
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//隐藏所有条件列
	com.hideCndFields;	
	
	//显示条件列name
	com.showCndField("name");
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1"
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 * @exception NoSuchElementException 不存在的列.
		 @see #showCndField(int)
		 @see #showCndFields()
	 */
	
	public void showCndField(String fieldName)		
	{	int li_index = getFieldIndex(fieldName);
		fields(li_index)._IsCndHidden = false;
	}
	

	
/**
* 显示作为条件的列（创建于 2003.03.05）.
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
* @param fieldID 列标识.
* @exception NoSuchElementException 不存在的列.
	@see #showCndField(String)
	@see #showCndFields()
*/
	public void showCndField(int fieldID)		
	{	int li_index = getFieldIndex(fieldID);
		fields(li_index)._IsCndHidden = false;
	}
	/**
	 *显示所有作为条件的列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于在使用hideCndField方法之前，使所有条件列为显示。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//隐藏条件列name
	com.hideCndField("name");	
	
	//显示所有条件列
	com.showCndFields();
	
	//隐藏条件列money,reg_dt
	com.hideCndFields("money,reg_dt");	
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1"
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @see #hideCndFields()
		 @see #showCndField(int)
		 @see #showCndField(String)
	 */
	
	public void showCndFields()
	{	for(int i=0;i<fieldCount();i++)
			fields(i)._IsCndHidden = false;
	}
	
	/**
	 *隐藏所有条件列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于在showCndField方法使用前，隐藏所有条件列。
	</pre>
	<DT><B>示例：</B><DD>
		<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//隐藏条件列name
	com.hideCndFields();	

	//显示条件列money,reg_dt
	com.showCndFields("money,reg_dt");	
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE money= 1200 and reg_dt is NULL
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @see #showCndFields()
		 @see #hideCndField(String)
		 @see #hideCndField(int)
	 */
	
	public void hideCndFields()
	{	for(int i=0;i<fieldCount();i++)
			fields(i)._IsCndHidden = true;
	}

	
	/**
	 * 取所有的列名（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
* @return 列名数组.
*/
	
	public String[] getFieldNames()
	{	String[] ls_cols = new String[fieldCount()];
	
		for(int i=0;i<fieldCount();i++)
		{	ls_cols[i] = fields(i).getName(_IsMultiple);
		}
		
		return ls_cols;
	}

	/**
	* 判断列是否被隐藏（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 	 @return true代表已隐藏，反之为false.
	 * @exception NoSuchElementException 不存在的列.
	 * @see #showField(String)
		 @see #hideField(String)
		 @see #isHiddenField(int)
	*/
	public boolean isHiddenField(String fieldName)		
	{	int li_index = getFieldIndex(fieldName);		
		return fields(li_index)._IsHidden;
	}
	/**判断列是否被隐藏（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldID 列标识(注:不要与数据库表的字段名混淆).
	 	 @return true代表已隐藏，反之为false.
	 * @exception NoSuchElementException 不存在的列.
	 * @see #showField(String)
		 @see #hideField(String)
		 @see #isHiddenField(String)
	*/
	public boolean isHiddenField(int fieldID)		
	{	int li_index = getFieldIndex(fieldID);		
		return fields(li_index)._IsHidden;
	}
	
	/**
	* 判断指定的条件列是否被隐藏（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 	 @return true代表已隐藏，反之为false.
	 * @exception NoSuchElementException 不存在的列.
	 * @see #showCndField(String)
		 @see #hideCndField(String)
		 @see #isHiddenCndField(int)
	*/
	public boolean isHiddenCndField(String fieldName)		
	{	int li_index = getFieldIndex(fieldName);
		return fields(li_index)._IsCndHidden;
	}
	/**判断指定的条件列是否被隐藏（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 	 @return true代表已隐藏，反之为false.
	 * @exception NoSuchElementException 不存在的列.
	 * @see #showCndField(String)
		 @see #hideCndField(String)
		 @see #isHiddenCndField(String)
	*/
	public boolean isHiddenCndField(int fieldID)		
	{	int li_index = getFieldIndex(fieldID);
		return fields(li_index)._IsCndHidden;
	}
	/**设置与指定列对应的的数据库表字段名（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的列名等于数据库表字段名.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@param fieldDBName 对应的数据库表字段名.
	@see NoSuchElementException 不存在的列.
	@see #getFieldDBName(String)
	@see #setFieldCndExpression(String,String)
	*/
	protected void setFieldDBName(String fieldName,String fieldDBName)		
	{	int li_index = getFieldIndex(fieldName);
		fields(li_index)._DBName = fieldDBName;
	}
	
	/**取列对应的数据库表字段名（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@return 对应的数据库表字段名.
	@see NoSuchElementException 不存在的列.
	@see #setFieldDBName(String,String)
	*/
	protected String getFieldDBName(String fieldName)		
	{	return getFieldProperty(fieldName,FIELD_DBNAME);
	}
	
/**
	* 设置列的条件表达式（创建于 2003.03.22）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;条件表达式主要用于生成SQL语句时作为WHERE表达式的组成部分。AbsOperator为每个
	列提供了默认的条件表达式:
	
	# = @
	
	其中"#"代表数据库列名,"="是操作符,"@"代表列值的占位符.
	
	如果数据库列名为"Name",当前值为"wyq",则将来的生成条件是:
	
	Name = 'wyq'
	
	如果将表达式改为 # like @,则将来的生成条件是:
	
	Name like 'wyq'
	
	当然你也可以直接采用?代替@,但建议不要这样做.
	
	&nbsp;&nbsp;&nbsp;&nbsp;另外，还有一种占位符"$"，称为伪值，其含义即在生成条件时以	当前值的字符串形式
	插入该占位符所处的位置，例如设定表达式为
	# like '$%'
	
	则将来生成的条件是：
	
	Name like 'wyq%'
	
	&nbsp;&nbsp;&nbsp;&nbsp;值得注意的是，采用伪值占位符时必须保证对数据库SQL语句中的变量表示非常了解，
	否则将会产生意想不到的错误。
	
	当条件值为null时，以下条件表达式会做相应的自动修正：
	
	"# = @"     =>   "# IS NULL"
	"# != @"    =>   "# IS NOT NULL"
	"# <> @"    =>   "# IS NOT NULL"
	
	</pre>
	<DT><B>示例：</B><DD>
	<pre>	
	//假设列reg_dt当前的值为1997-01-08
	//以下程序将在转换SQL语句的WHERE表达式时生成
	//to_char(reg_dt,'yyyy-mm') = to_char(1997-01-08,'yyyy-mm')
	com.setFieldCndExpression("reg_dt","to_char(#,'yyyy-mm') = to_char(@,'yyyy-mm')");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.22.
	修改于 2003.03.18 - 增加当条件值为null时的自动修正功能.	
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@param cndExpression 条件表达式。
	@see NoSuchElementException 不存在的列。
	@see #getFieldCndExpression(String)
	@see #setFieldDBName(String,String)
*/
	public void setFieldCndExpression(String fieldName,String cndExpression)
	{	int li_index = getFieldIndex(fieldName);
		fields(li_index)._CndExpression = cndExpression;
	}
/**
* 设置列的条件表达式（创建于 2003.03.22）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;条件表达式主要用于生成SQL语句时作为WHERE表达式的组成部分。AbsOperator为每个
	列提供了默认的条件表达式:
	
	# = @
	
	其中"#"代表数据库列名,"="是操作符,"@"代表列值的占位符.
	
	如果数据库列名为"Name",当前值为"wyq",则将来的生成条件是:
	
	Name = 'wyq'
	
	如果将表达式改为 # like @,则将来的生成条件是:
	
	Name like 'wyq'
	
	当然你也可以直接采用?代替@,但建议不要这样做.
	
	&nbsp;&nbsp;&nbsp;&nbsp;另外，还有一种占位符"$"，称为伪值，其含义即在生成条件时以	当前值的字符串形式
	插入该占位符所处的位置，例如设定表达式为
	# like '$%'
	
	则将来生成的条件是：
	
	Name like 'wyq%'
	
	&nbsp;&nbsp;&nbsp;&nbsp;值得注意的是，采用伪值占位符时必须保证对数据库SQL语句中的变量表示非常了解，
	否则将会产生意想不到的错误。
	
	当条件值为null时，以下条件表达式会做相应的自动修正：
	
	"# = @"     =>   "# IS NULL"
	"# != @"    =>   "# IS NOT NULL"
	"# <> @"    =>   "# IS NOT NULL"
	
	</pre>
	<DT><B>示例：</B><DD>
	<pre>	
	//假设列reg_dt当前的值为1997-01-08
	//以下程序将在转换SQL语句的WHERE表达式时生成
	//to_char(reg_dt,'yyyy-mm') = to_char(1997-01-08,'yyyy-mm')
	com.setFieldCndExpression("reg_dt","to_char(#,'yyyy-mm') = to_char(@,'yyyy-mm')");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.22.
	修改于 2003.03.18 - 增加当条件值为null时的自动修正功能.	
	</pre>
	</DL>
	@param fieldID 列标识(注:不要与数据库表的字段名混淆).
	@param cndExpression 条件表达式。
	@see NoSuchElementException 不存在的列。
	@see #getFieldCndExpression(String)
*/
	public void setFieldCndExpression(int fieldID,String cndExpression)
	{	int li_index = getFieldIndex(fieldID);
		fields(li_index)._CndExpression = cndExpression;
	}
	/**
	* 取列的数据库表字段当前的条件表达式（创建于 2003.03.22）.
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
	创建于 2003.03.22.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@return 条件表达式。
	@see NoSuchElementException 不存在的列。
	@see #setFieldCndExpression(String,String)
	*/
	public String getFieldCndExpression(String fieldName)		
	{	int li_index = getFieldIndex(fieldName);
		return fields(li_index)._CndExpression;
	}
	/**
	* 取列的数据库表字段当前的条件表达式（创建于 2003.03.22）.
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
	创建于 2003.03.22.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@return 条件表达式。
	@see NoSuchElementException 不存在的列。
	@see #setFieldCndExpression(int,String)
	*/
	public String getFieldCndExpression(int fieldID)		
	{	int li_index = getFieldIndex(fieldID);
		return fields(li_index)._CndExpression;
	}


	/**
	 * 根据列名取索引号（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@param FieldName 列名.
	@return 索引号.
*/
	protected int indexOfField(String fieldName)
	{	for(int i=0;i<fieldCount();i++)
		{	if(fields(i).getName(_IsMultiple).equalsIgnoreCase(fieldName)) return i;
		}
		
		return -1;
	}

	/**
	 * 根据列名取列标识（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@param fieldName 列名
	@return 列标识，0代表不存在的列名.
*/
	public int getFieldID(String fieldName)		
	{	return indexOfField(fieldName) + 1;
	}
	

	
/**
* 根据列名取索引号（创建于 2003.03.05）.
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
* @param fieldName 列名.
* @return 索引号.
* @see #getFieldIndex(int)
*/
	private int getFieldIndex(String fieldName)		
	{	int li_index = indexOfField(fieldName);
		if(li_index < 0) throw new NoSuchElementException(fieldName);
		return li_index;
	}
	
/**
* 根据列标识取索引号（创建于 2003.03.05）.
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
* @param fieldID 列标识.
* @return 索引号.
* @see #getFieldIndex(String)
*/
	private int getFieldIndex(int fieldID)		
	{	int li_index = fieldID - 1;
		if(li_index < 0 || li_index >= fieldCount()) throw new NoSuchElementException("ID = " + fieldID);
		return li_index;
	}


/**
* 指令集原始条件列之间的关系符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
指令集原始条件列之间的关系符是统一设定的
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
	private int _FieldCndRelation = Syntax.CNDRELATION_AND;

	/**
	 * 设置指令集原始条件列间的关系符（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	关系符的定义请参见：
	Syntax.CNDRELATION_AND
	Syntax.CNDRELATION_OR
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param rela 关系常量.
* @see Syntax#CNDRELATION_AND
	@see Syntax#CNDRELATION_OR
	@see #getFieldCndRelation()
*/
	protected void setFieldCndRelation(int cndRelation)
	{	Syntax.verifyCndRelation(cndRelation);		
					
		_FieldCndRelation = cndRelation;
	}	
	

	
	/**
	 * 取指令集原始条件列之间的关系符（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
* @return 指令集的条件列之间的关系符.
* @see Syntax#CNDRELATION_AND
	@see Syntax#CNDRELATION_OR
	@see #setFieldCndRelation(int)
*/
	protected int getFieldCndRelation()
	{	return _FieldCndRelation;
	}
	
	
/**
* 排序设置（创建于 2003.03.05）.
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
	private String _SortExpression = "";
	
	/**
	 * 生成排序语句（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@return SQL表达式.
*/
	protected String prepareSort()
	{	return (StringMethod.isValid(_SortExpression)&&_OP==SQL_SORT)?Syntax.toORDERBY(_SortExpression):_SortExpression;
	}
	
	/**
	 * 设置列的排序模式（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	可以多次使用，按调用的先后顺序设定排序的优先级。
	
	mode的值请参见：
	Syntax.SORT_ASC
	Syntax.SORT_DESC
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param fieldName 需要排序的列名.
* @param mode 排序模式.
* @exception NoSuchElementException 不存在的列.
* @see #clearSort()
	@see Syntax#SORT_ASC
	@see Syntax#SORT_DESC
*/
	public void sortByField(String fieldName,int mode)		
	{	int i = getFieldIndex(fieldName);
		
		Syntax.verifySort(mode);
						
		_SortExpression = StringMethod.addChild(_SortExpression,
														fields(i).getSortCompute(_IsMultiple) + " " + Syntax.SORT_SYNTAX[mode],Syntax.FIELD_APART);
	}

	
	/**
	 * 清除指令集的排序设置（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@see #sortByField(String,int)		
*/
	public void clearSort()
	{	_SortExpression = "";
	}
	

/**
* 分组设置（创建于 2003.03.05）.
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
	private String _GroupExpression = "";

	/**
	 * 生成GROUP BY语句（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@return SQL表达式.	
*/
	protected String prepareGroup()
	{	return (StringMethod.isValid(_GroupExpression)&&_OP==SQL_GROUP)?Syntax.toGROUPBY(_GroupExpression):_GroupExpression;
	}

	/**
	 * 设置列的分组模式（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	可以多次使用，按调用的先后顺序设定排序的优先级。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param fieldName 需要分组的列名.
* @exception NoSuchElementException 不存在的列.
* @see #clearGroup()
*/
	public void groupByField(String fieldName)		
	{	int i = getFieldIndex(fieldName);
	
		_GroupExpression = StringMethod.addChild(_GroupExpression,
																	fields(i).getGroupCompute(_IsMultiple),Syntax.FIELD_APART);

	}

	/**
	 * 清除指令集的分组设置（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
* @see #groupByField(String)
*/
	public void clearGroup()
	{	_GroupExpression = "";
	}
	
/**
* 列对应的数据库表字段名（创建于 2003.03.05）.
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
	protected static final int FIELD_DBNAME = 0;
	
/**
* 列对应的查询表达式（创建于 2003.03.05）.
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
	protected static final int FIELD_COMPUTE = 1;
/**
* 列对应的排序表达式（创建于 2003.03.05）.
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
	protected static final int FIELD_SORTCOMPUTE = 2;
/**
* 列对应的分组表达式（创建于 2003.03.05）.
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
	protected static final int FIELD_GROUPCOMPUTE = 3;
/**
* 列对应的条件表达式（创建于 2003.03.05）.
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
	protected static final int FIELD_CNDEXPRESSION = 4;
/**
* 列对应的数据库表字段名全称（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
含库表名
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
	protected static final int FIELD_FULLDBNAME = 5;

	
	/**
	 * 设置指定列的扩展属性（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>	
	&nbsp;&nbsp;&nbsp;&nbsp;在给属性FIELD_DBNAME设值时与setFieldDBName(String,String)不同,	前者
	不会影响其它属性的变化.扩展属性参见:FIELD_XXX
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example的类对应于StreamExample表，name,reg_dt,money对应于该表的列	
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject是AbsOperator的子类
	SQLObject com = new SQLObject(example);
	
	//设置列money的查询表达式
	com.setFieldProperty("money",com.FIELD_COMPUTE,"sum(#)");
	
	//隐藏除money外的其它列.
	com.hideFields();
	com.showField("money");
	
	//以下语句相当于执行
	//SELECT sum(money) FROM StreamExample
	//WHERE name="wyq1" AND reg_dt IS NULL AND money = 1200
	RecordSet lRecord = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@param property 扩展属性。
	@param value 扩展属性值.
	@see NoSuchElementException 不存在的列.
	@see #getFieldProperty(String,int)

*/
	protected void setFieldProperty(String fieldName,int property,String value)		
	{	int li_index = getFieldIndex(fieldName);
		switch(property)
		{	case FIELD_DBNAME:				
				fields(li_index)._DBName = value;break;
			case FIELD_SORTCOMPUTE:
				fields(li_index)._SortCompute = value;break;
			case FIELD_GROUPCOMPUTE:
				fields(li_index)._GroupCompute = value;break;
			case FIELD_CNDEXPRESSION:
				fields(li_index)._CndExpression = value;break;
			case FIELD_COMPUTE:
				fields(li_index)._Compute = value;break;
			default:throw new NoSuchElementException("FieldProperty: " + property);
		}		
	}

 /**
	 * 取列的扩展属性值（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	扩展属性参见:FIELD_XXX
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@param property 扩展属性.
	@return 扩展属性值.
	@see NoSuchElementException 不存在的列.
	@see #setFieldProperty(String,int,String)
*/
	protected String getFieldProperty(String fieldName,int property)
		
	{	int li_index = getFieldIndex(fieldName);
		switch(property)
		{	case FIELD_DBNAME:				
				return fields(li_index)._DBName;
			case FIELD_SORTCOMPUTE:
				return fields(li_index)._SortCompute;
			case FIELD_GROUPCOMPUTE:
				return fields(li_index)._GroupCompute;
			case FIELD_CNDEXPRESSION:
				return fields(li_index)._CndExpression;
			case FIELD_COMPUTE:
				return fields(li_index)._Compute;
			case FIELD_FULLDBNAME:
				return fields(li_index).getDBName(true);
			default:throw new NoSuchElementException("FieldProperty: " + property);
		}				
	}
	
/**
* 设置附加条件表达式（创建于 2003.03.26）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
	附加条件表达式直接采用SQL语义描述，并将出现在WHERE表达式的最后面。	
	本方法不对附加条件的有效性进行验证。
	多次使用本方法，只保留最后一次的设置。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.26.
</pre>
</DL>
* @param cndRelation 附加条件与其它条件表达式之间的关系.
* @param condition 附加条件表达式，""或null代表清空原来的附加条件.
*/
	protected void setSQLCondition(int cndRelation,String condition)
	{ Syntax.verifyCndRelation(cndRelation);
		_SQLCondition = condition;
		_SQLConditionRelation = cndRelation;
	}
/**
* 清除附加条件（创建于 2003.03.28）.
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
创建于 2003.03.28.
</pre>
</DL>
*/
	protected void clearSQLCondition()
	{	_SQLCondition = "";
	}

		
/**
* 附加条件列数组（创建于 2003.03.05）.
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
	private List _AppendCndFields = new ArrayList();
	//private List _AppendConditions = new ArrayList();
	/**
	 * 附加条件表达式（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	cndExpression的定义方式参见setFieldCndExpression(String,String)
	cndRelation：Syntax.CNDRELATION_AND 或者 Syntax.CNDRELATION_OR
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param cndRelation 与已有条件之间的关系符.
* @param fieldName 作为条件的列名.
* @param cndExpression 条件表达式.
* @param val 作为条件表达式中出现的值.
* @exception NoSuchElementException 不存在的列.
* @see #appendCndField(int,String,String,List)
*/
	public void appendCndField(int cndRelation,String fieldName,String cndExpression,Object val)
	{	int li_index = getFieldIndex(fieldName);
		String ls_condition;

		Syntax.verifyCndRelation(cndRelation);

		FieldTag lField = (FieldTag)MyMethod.deepClone(fields(li_index));
		lField.setValue(val);
		lField._CndExpression = cndExpression;	
		lField._IsHidden = false;
		lField._IsCndHidden = false;
		lField._CndRelation = cndRelation;
		_AppendCndFields.add(lField);
	}
	/*
	public void appendCndField(int cndRelation,String fieldName,String cndExpression,Object val)
	{	int li_index = getFieldIndex(fieldName);
		String ls_condition;

		Syntax.verifyCndRelation(cndRelation);

		FieldTag lField = (FieldTag)MyMethod.deepClone(fields(li_index));
		lField.setValue(val);
		lField._CndExpression = cndExpression;	
		
		PreparedCondition lCondition = new PreparedCondition(cndRelation,lField.getPreparedCondition(isMultiple()));		
		//可能出现条件值的多次引用
		for(int j=0;j<lField._CndValCount;j++)
				lCondition.addCndValue(val);
				
		_AppendConditions.add(lCondition);
	}
	*/
/**
* 附加条件指令集（创建于 2003.03.05）.
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
	private List _AppendCndOperators = new ArrayList();
	
		
/**
* 附加条件关系符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
当指令集本身作为其它指令集的附加条件指令集时，与其它附加条件指令集之间的关系符.
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
	private int _CndRelation = Syntax.CNDRELATION_AND;


/**
* 附加条件指令集（创建于 2003.03.26）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
在AbsOperator创建条件时，按以下顺序进行:
((((原始条件域)附加条件域)附加条件表达式)附加条件对象...)
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.26.
</pre>
</DL>
* @param com 作为附加条件的指令集.
* @param cndRelation 与其它附加条件指令集的关系符.
*/
	protected void appendCndOperator(int cndRelation,AbsOperator com)
	{	if(!_IsMultiple && !com.getDBName().equals(getDBName())) 
		{	throw new IllegalArgumentException("附加条件所在库表与原库表不匹配!");
			//_IsMultiple = true;
		}
		
		Syntax.verifyCndRelation(cndRelation);
		
		com._CndRelation = cndRelation;
		
		_AppendCndOperators.add(com);
	}
	
	/*
	public void appendCndOperator(int cndRelation,AbsOperator com)
	{	if(!isMultiple() && !com.getDBName().equals(getDBName())) 
		{	throw new IllegalArgumentException("附加条件所在库表与原库表不匹配!");
			//_IsMultiple = true;
		}
		
		Syntax.verifyCndRelation(cndRelation);
		
		List lVals = new ArrayList();
		String ls_condition = com.prepareCondition(isMultiple(),lVals);
		
		PreparedCondition lCondition = new PreparedCondition(cndRelation,ls_condition,lVals);		
		_AppendConditions.add(lCondition);
	}
	*/
	/**
	 * 附加条件表达式（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	cndExpression的定义方式参见setFieldCndExpression(String,String)
	cndRelation：Syntax.CNDRELATION_AND 或者 Syntax.CNDRELATION_OR
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param cndRelation 与已有附加条件列之间的关系符.
* @param fieldName 作为条件的列名.
* @param cndExpression 条件表达式.
* @param vals 作为条件表达式中出现的值数组.
* @exception NoSuchElementException 不存在的列.
* @see #appendCndField(int,String,String,Object)
*/
	public void appendCndField(int cndRelation,String fieldName,String cndExpression,List val)
	{	int li_index = getFieldIndex(fieldName);
		String ls_condition;
	
		Syntax.verifyCndRelation(cndRelation);
	
		FieldTag lField = (FieldTag)MyMethod.deepClone(fields(li_index));
		lField.setValueList(val);
		lField._CndExpression = cndExpression;	
		lField._IsHidden = false;
		lField._IsCndHidden = false;
		lField._CndRelation = cndRelation;
		_AppendCndFields.add(lField);		
	}
	
	/*
	public void appendCndField(int cndRelation,String fieldName,String cndExpression,List vals)
	{	int li_index = getFieldIndex(fieldName);
		String ls_condition;
	
		Syntax.verifyCndRelation(cndRelation);
	
		FieldTag lField = (FieldTag)MyMethod.deepClone(fields(li_index));
		lField.setValueList(vals);
		lField._CndExpression = cndExpression;	
		
		PreparedCondition lCondition = new PreparedCondition(cndRelation,lField.getPreparedCondition(isMultiple()),vals);		
		_AppendConditions.add(lCondition);
	}
	*/
		/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
	参数 cndRelation：Syntax.CNDRELATION_AND 或者 Syntax.CNDRELATION_OR
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object,boolean)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,Object beginVal,Object endVal)
	{	appendCndFieldBetween(cndRelation,fieldName,beginVal,endVal,false);
	}

	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 [NOT] BETWEEN ... AND ...
	参数 not：true代表采用否定语义，false（默认值）代表不采用否定语义
	参数 cndRelation：Syntax.CNDRELATION_AND 或者 Syntax.CNDRELATION_OR，默认值为getFieldCndRelation()
	
	appendCndFieldBetween(fieldName,beginVal,endVal[,not][,cndRelation])
	
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param not 是否采用否定语义.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,Object beginVal,Object endVal,boolean not)
	{	List lList = new ArrayList();
		lList.add(beginVal);
		lList.add(endVal);
		
		String ls_condition = "(" + FieldTag.COLUMN + (not?" NOT ":" ") + "BETWEEN " + FieldTag.VALUE + " AND " + FieldTag.VALUE + ")";
		
		appendCndField(cndRelation,fieldName,ls_condition,lList);
		
	}

/**
 * 附加枚举查询的条件列（创建于 2003.03.28）.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 [NOT] IN (element1,element2,...)
	参数 not：true代表采用否定语义，false（默认值）代表不采用否定语义
	参数 cndRelation：Syntax.CNDRELATION_AND 或者 Syntax.CNDRELATION_OR，默认值为getFieldCndRelation()

	appendCndFieldIn(fieldName,val[,not][,cndRelation])
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @param fieldName 条件列名.
* @param val 枚举值数组.
	@param not 是否采用否定语义.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(int,String,Operator,boolean)
	@see #appendCndFieldIn(int,String,List)
*/
	public void appendCndFieldIn(int cndRelation,String fieldName,List val,boolean not)
	{	String ls_enum = "";
		
		for(int i=0;i<val.size();i++)
			ls_enum = StringMethod.addChild(ls_enum,FieldTag.VALUE,',');
		
		String ls_condition = "(" + FieldTag.COLUMN + (not?" NOT ":" ") + "IN (" + ls_enum + "))";
		
		appendCndField(cndRelation,fieldName,ls_condition,val);
	}

/**
 * 附加枚举查询的条件列（创建于 2003.03.28）.
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
	创建于 2003.03.28.
	</pre>
	</DL>
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @param fieldName 条件列名.
* @param val 枚举值数组.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(int,String,List,boolean)
*/
	public void appendCndFieldIn(int cndRelation,String fieldName,List val)
	{	appendCndFieldIn(cndRelation,fieldName,val,false);
	}


/**
 * 附加以子查询为枚举值的条件列（创建于 2003.03.28）.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 [NOT] IN (SELECT ...)
	
	参数 not：true代表采用否定语义，false（默认值）代表不采用否定语义
	参数 cndRelation：Syntax.CNDRELATION_AND 或者 Syntax.CNDRELATION_OR，默认值为getFieldCndRelation()
	
	appendCndFieldIn(fieldName,subSelect[,not][,cndRelation])
	
	要求：必须保证subSelect中只有一个列可用，且该列将与fieldName相关。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	修改于 2003.09.05 - IN的前面保留一个空格.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param subSelect 子查询SQL语义对象.
	@param not 是否采用否定语义.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(int,String,List,boolean)
	@see #appendCndFieldIn(int,String,Operator)
*/
	public void appendCndFieldIn(int cndRelation,String fieldName,Operator subSelect,boolean not)
	{	List lList = new ArrayList();
		String ls_preparedSQL = subSelect.prepareSQL(SQL_SELECT,lList);		
		ls_preparedSQL += subSelect.prepareSQL(SQL_CONDITION,lList);		
		
		String ls_condition = "(" + FieldTag.COLUMN + (not?" NOT ":" ") + "IN (" + ls_preparedSQL + "))";
		
		appendCndField(cndRelation,fieldName,ls_condition,lList);
	}
	
/**
 * 附加以子查询为枚举值的条件列（创建于 2003.03.28）.
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
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param subSelect 子查询SQL语义对象.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(int,String,Operator,boolean)
*/
	public void appendCndFieldIn(int cndRelation,String fieldName,Operator subSelect)
	{	appendCndFieldIn(cndRelation,fieldName,subSelect,false);
	}

	/**
	 * 附加条件表达式（创建于 2003.03.23）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	条件表达式将以 fieldName op val 的形式出现。
	如果val为null，且op不为"=","<>","!="，则附加条件表达式将被过滤，返回false，成功返回true。
	目前op支持以下几种逻辑操作：
	<>、!=、	>、	>=、	<、	<=、	=、	like、not like
	其中like代表模糊匹配，如果值中不包含"%"，则自动在条件值前后加入"%"
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example的类对应于StreamExample表，name,reg_dt,money对应于该表的列	
example.name = "wyq1";
example.money = 1200;
example.reg_dt = null;

//SQLObject是OperatorMethod的子类
SQLObject com = new SQLObject(example);

//重新设置name的判断条件
com.hideCndFields();
com.appendCndFieldOP("name","like","wu");

//以下语句相当于执行
//SELECT name,money,reg_dt FROM StreamExample
//WHERE name like '%wu%'
RecordSet lRecord = SQLCA.select(com);

	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.23.
	修改于 2003.03.19 - 允许当op为"=","<>","!="时，val可以为null.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.	
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndField(int,String,String,Object)
*/
	
	public boolean appendCndFieldOP(int cndRelation,String fieldName,String op,Object val)		
	{	String ls_condition;
	
		if(ArrayMethod.findItemIgnoreCase(OP,op) < 0)
			throw new UnsupportedOperationException(op + " is unsupported in appendCndFieldOP(String,String,Object)!");
				
		if(val==null&&!(op.equals("=")||op.equals("!=")||op.equals("<>"))) return false;
		
		if(op.indexOf("like") >=0 && (val + "").indexOf("%") < 0)
			ls_condition = FieldTag.COLUMN + " " + op + " '%" + FieldTag.SIMULATED_VALUE + "%'";
		else
			ls_condition = FieldTag.COLUMN + " " + op + " " + FieldTag.VALUE;
		
		appendCndField(cndRelation,fieldName,ls_condition,val);
		
		return true;
	}

	/**
	 * 指令集是否为复合类型（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;它与普通Operator最大的区别在于列名的格式不同。普通类型的列
	名为<field>，而在复合类型中，格式为<Class>_<field>，复合类型的Operator主要出现在多表查询。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.03.
	</pre>
	</DL>
* @return true代表是复合类型，反之为false.
	@see #setMultiple(boolean)
*/
	protected boolean isMultiple()
	{	return _IsMultiple;
	}
	
/**
* 设定指令集为复合类型（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认为false
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
* @param option true代表是复合类型，反之为false.
* @see #isMultiple()
*/
	protected void setMultiple(boolean option)
	{	_IsMultiple = option;
	}
	

	
/**
* 取列的值（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param fieldID 列标识.
* @return 列值.
	@see NoSuchElementException 不存在的列.
* @see #getFieldValue(String)
*/
	public Object getFieldValue(int fieldID)
	{	int li_index = getFieldIndex(fieldID);
		return fields(li_index).getValue();
	}
	
/**
* 取列的名称（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param fieldID 列标识.
* @return 列名.
	@see NoSuchElementException 不存在的列.
*/
	public String getFieldName(int fieldID)
	{	int li_index = getFieldIndex(fieldID);
		return fields(li_index).getName(isMultiple());
	}
	

	
/**
* 取列的值（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param fieldName 列名(注:不要与数据库表的字段名混淆).
* @return 列值.
	@see NoSuchElementException 不存在的列.
* @see #getFieldValue(int)
*/
	public Object getFieldValue(String fieldName)
	{	int li_index = getFieldIndex(fieldName);
		return fields(li_index).getValue();
	}
	

	
/**
* 设置列的值（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param fieldID 列标识.
* @param val 列值.
	@see NoSuchElementException 不存在的列.
* @see #setFieldValue(String,Object)
*/
	public void setFieldValue(int fieldID,Object val)
	{	int li_index = getFieldIndex(fieldID);
	
		fields(li_index).setValue(val);
	}
/**
* 设置列的值（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param fieldName 列名(注:不要与数据库表的字段名混淆)..
* @param val 列值.
	@see NoSuchElementException 不存在的列.
* @see #setFieldValue(int,Object)
*/
	public void setFieldValue(String fieldName,Object val)
	{	int li_index = getFieldIndex(fieldName);
	
		fields(li_index).setValue(val);
	}
	
	
	
				/**********计划开发的**********************/
/*
	void appendCndFieldExpressionExists(String fieldName,AbsOperator child,String childFieldName)
	{
	}
	*/
/**条件域之间的关系表达式（创建于 2003.03.09）.*/
	//private String _FieldCndRelationExpression;
/**not Finished
	 * 根据关系表达式设置条件域之间的关系组合.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	& 代表"and"关系。
	|	代表"or"关系。
	()用来指定关系表达式之间的优先级。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	</DL>
* @param syntax description.
* @return description.

*/
	/*void setCndFieldsRelationSyntax(String syntax)
	{
	}*/
		
/***************************不要了的**********************/
	/**
	 * 设置对象所有属性的条件表达式（创建于 2003.03.22）.
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
	创建于 2003.03.22.
	</pre>
	</DL>
* @param cndExpression 条件表达式.
* @see #setFieldCndExpression(String,String)
* @see #setFieldsCndExpression(String,String)
*/
/*	public void setFieldsCndExpression(String cndExpression)
	{	for(int i=0;i<fieldCount();i++)
			fields(i)._CndExpression = cndExpression;
	}
*/

	/**
	 * 设置对象的多个属性的条件表达式（创建于 2003.03.22）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	fieldNames 为"*"时相当于执行groupByFields()
	fieldNames 为""时被忽略
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.22.
	</pre>
	</DL>
* @param fieldNames 列名用“,”分隔,“*”代表所有的列.
* @param cndExpression 条件表达式.
* @exception NoSuchElementException	不存在的列名.
* @see #setFieldCndExpression(String,String)
* @see #setFieldsCndExpression(String)
* @see #setFieldsCndExpression(String[],String)
*/
/*	public void setFieldsCndExpression(String fieldNames,String cndExpression)
		
	{	if(fieldNames.equals("")) return;
	
		if(fieldNames.equals("*")) 
			setFieldsCndExpression(cndExpression);
		else
		{	String [] ls_FieldName = StringMethod.toArray(fieldNames,Syntax.FIELD_APART);
		
			setFieldsCndExpression(ls_FieldName,cndExpression);
		}
	}
	*/
/**
* <img src="new.gif" width="28" height="11" border="0">设置对象的多个属性的条件表达式（创建于 2003.03.14）.
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
创建于 2003.03.14.
</pre>
</DL>
* @param fieldNames 列名数组.
* @param cndExpression 条件表达式.
* @exception NoSuchElementException	不存在的列名.
* @see #setFieldCndExpression(String,String)
* @see #setFieldsCndExpression(String)
* @see #setFieldsCndExpression(String,String)
*/
	/*public void setFieldsCndExpression(String[] fieldNames,String cndExpression)
		
	{	for(int i=0;i<fieldNames.length;i++)
			setFieldCndExpression(fieldNames[i],cndExpression);
	}*/
}

