package trionesII.sql;

import java.lang.reflect.*;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import trionesII.util.*;

/**可控制指令集（创建于 2003.03.18）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
可控制指令集提供了一套可根据需要灵活定制SQL指令集的模型。
开发人员只有实现构造过程，就可以获得对指令集的各项控制操作。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
略
</pre>
<DT><B>注意事项：</B><DD>
<pre>
略
</pre>
<DT><B>展望未来：</B><DD>
<pre>
1.增加排序的强化版	
2.增加对group的强化版
3.增加对条件关系的强化版
4.增加对XML的支持
</pre>
<DT><B>联系方式：</B><DD>
<pre>
email：wyq@triok.com
电  话:021-68672222-2013
</pre>
</DL>
*@author	吴勇庆
*@version 2003.03.18
*/
public class OperatorMethod
{	

	/**
	 * 隐藏符合指定特征的列（创建于 2003.03.22）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	特征模式请参见：	
	VALUE_NOT_VALID
	VALUE_IS_VALID
	VALUE_IS_NULL
	VALUE_NOT_NULL
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
	@param com 指令集.
* @param mode 特征模式.
*/
	public static void hideFields(Operator com,int mode)
	{	for(int i=1;i<=com.fieldCount();i++)
			if(isMatchedField(com.getFieldValue(i),mode))
				com.hideField(i);
	}

/**
	* 隐藏符合指定特征的条件列（创建于 2003.03.22）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	特征模式请参见：	
	VALUE_NOT_VALID
	VALUE_IS_VALID
	VALUE_IS_NULL
	VALUE_NOT_NULL
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
	@param com 指令集.
* @param mode 特征模式.
*/
	public static void hideCndFields(Operator com,int mode)
	{	for(int i=1;i<=com.fieldCount();i++)
			if(isMatchedField(com.getFieldValue(i),mode))
				com.hideCndField(i);
	}
	
	/**
	 * 显示符合指定特征的列（创建于 2003.03.22）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	特征模式请参见：	
	VALUE_NOT_VALID
	VALUE_IS_VALID
	VALUE_IS_NULL
	VALUE_NOT_NULL
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
	@param com 指令集.
* @param mode 特征模式.
*/
	public static void showFields(Operator com,int mode)
	{	for(int i=1;i<=com.fieldCount();i++)
			if(isMatchedField(com.getFieldValue(i),mode))
				com.showField(i);
	}
	
	/**
	 * 显示符合指定特征的条件列（创建于 2003.03.22）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	特征模式请参见：	
	VALUE_NOT_VALID
	VALUE_IS_VALID
	VALUE_IS_NULL
	VALUE_NOT_NULL
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
	@param com 指令集.
* @param mode 特征模式.
*/
	public static void showCndFields(Operator com,int mode)
	{	for(int i=1;i<=com.fieldCount();i++)
			if(isMatchedField(com.getFieldValue(i),mode))
				com.showCndField(i);
	}
	
	

	/**
	 * 显示指定的多个列（创建于 2003.03.09）.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	如果只有一个列，建议用showField(String)方法代替本方法。
	showFields("*")相当于showFields()
	showFields("")则忽略本次操作
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//以下程序相当于执行
	//com.showField("name");
	//com.showField("reg_dt");	
	com.showFields("name,reg_dt");		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	@param com 指令集.
* @param fieldNames 列名用','分隔,"*"代表所有的列，空字符串不起作用.
* @exception NoSuchElementException	不存在的列.
*/
	public static void showFields(Operator com,String fieldNames)		
	{	if(fieldNames.equals("")) return;
	
		if(fieldNames.equals("*")) 
			com.showFields();
		else
		{	String [] ls_FieldName = StringMethod.toChilds(fieldNames,Syntax.FIELD_APART);
			showFields(com,ls_FieldName);
		}
	}

	
/**
* 显示指定的多个列（创建于 2003.03.14）.
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
	@param com 指令集.
* @param fieldNames 列名数组.
* @exception NoSuchElementException	不存在的列.
*/
	public static void showFields(Operator com,String[] fieldNames)		
	{	for(int i=0;i<fieldNames.length;i++)
				com.showField(fieldNames[i]);
	}
	/**
	 * 隐藏指定的多个列（创建于 2003.03.09）.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	如果只有一个列，建议用hideField(String)方法代替本方法。
	hideFields("*")相当于hideFields()
	hideFields("")则忽略本次操作
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//以下程序相当于执行
	//com.hideField("name");
	//com.hideField("reg_dt");	
	com.hideFields("name,reg_dt");		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
		 @param com 指令集.
	 * @param fieldNames 列名用','分隔,"*"代表所有的列，空字符串不起作用.
	 * @exception NoSuchElementException	不存在的列.
	 */

	public static void hideFields(Operator com,String fieldNames)		
	{	if(fieldNames.equals("")) return;
	
		if(fieldNames.equals("*")) 
			com.hideFields();
		else
		{	String [] ls_FieldNames = StringMethod.toChilds(fieldNames,Syntax.FIELD_APART);
		
			hideFields(com,ls_FieldNames);
		}
	}

	
/**
* 隐藏指定的多个列（创建于 2003.03.14）.
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
	@param com 指令集.
* @param fieldNames 列名数组.
* @exception NoSuchElementException	不存在的列.
*/
	public static void hideFields(Operator com,String[] fieldNames)		
	{	for(int i=0;i<fieldNames.length;i++)
				com.hideField(fieldNames[i]);
	}
	/**
	 * 获取匹配条件的列表达式清单（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	返回的清单以","分隔.
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
	@param com 指令集.
* @param rule 匹配条件.
* @return 符合条件的列名清单.
* @see #VALUE_NOT_VALID
	@see #VALUE_IS_VALID
*/

	public static String getFieldSyntax(Operator com,int rule)
	{	String ls_cols = "";
	
		for(int i=1;i<=com.fieldCount();i++)
		{	if(isMatchedField(com.getFieldValue(i),rule))
				ls_cols = StringMethod.addChild(ls_cols,com.getFieldName(i),Syntax.FIELD_APART);
		}
		
		return ls_cols;
	}
	/**
	 * 获取所有的列表达式清单（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	返回的清单以","分隔.
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
	@param com 指令集.
* @return 列名清单.
*/
	
	public static String getFieldSyntax(Operator com)
	{	String ls_cols = "";
	
		for(int i=1;i<=com.fieldCount();i++)
		{	ls_cols = StringMethod.addChild(ls_cols,com.getFieldName(i),Syntax.FIELD_APART);
		}
		
		return ls_cols;
	}
	/**列值不为null,且字符串不为""（创建于 2003.03.31）.*/
	public static final int VALUE_NOT_VALID = -1;
	/**列值为null,或者字符串为""（创建于 2003.03.31）.*/
	public static final int VALUE_IS_VALID = 1;
	/**列值为null（创建于 2003.03.31）.*/
	public static final int VALUE_IS_NULL = 2;
	/**列值不为null（创建于 2003.03.31）.*/
	public static final int VALUE_NOT_NULL = -2;
	/**字符串为"".*/
	//public static final int VALUE_IS_EMPTY = 3;
	/**字符串不为""*/
	//public static final int VALUE_NOT_EMPTY = -3;
	
	/**
	 * 判断域是否匹配要求（创建于 2003.03.31）.
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
	创建于 2003.03.31.
	</pre>
	</DL>
	@param com 指令集.
* @param f 域实例.
* @param rule 匹配规则.
* @return 是否匹配.
*/
	
	private static boolean isMatchedField(Object val,int rule)
	{ switch(rule)
		{case VALUE_NOT_VALID	:	return (val==null) || !StringMethod.isValid(val + "");
		 case VALUE_IS_VALID	:	return (val!=null) && StringMethod.isValid(val + "");
		 case VALUE_NOT_NULL	:	return (val!=null);
		 case VALUE_IS_NULL		: return (val==null);
		 //case VALUE_NOT_EMPTY	: return (f.getValue()==null||!(f.getValue().toString().equals("")));
		 //case VALUE_IS_EMPTY	: return (f.getValue().toString().equals(""));
		 default :return true;
		}		
	}
	/**
	 * 显示指定的多个条件列.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	如果只有一个列，建议用showCndField(String)方法代替本方法。
	showCndFields("*")相当于showCndFields().
	showCndFields("")则忽略本次操作.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//以下程序相当于执行
	//com.showCndField("name");
	//com.showCndField("reg_dt");	
	com.showCndFields("name,reg_dt");		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	@param com 指令集.
* @param fieldNames 列名用“,”分隔,“*”代表所有的对象域，空字符串不起作用.
* @exception NoSuchElementException	不存在的列名.
*/
	public static void showCndFields(Operator com,String fieldNames)		
	{	if(fieldNames.equals("")) return;
	
		if(fieldNames.equals("*")) 
			com.showCndFields();
		else
		{	String [] ls_FieldName = StringMethod.toChilds(fieldNames,Syntax.FIELD_APART);
		
			showCndFields(com,ls_FieldName);
		}
	}
	

	
/**
* 显示指定的多个条件列（创建于 2003.03.14）.
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
	@param com 指令集.
* @param fieldNames 列名数组.
* @exception NoSuchElementException	不存在的列名.
*/
	public static void showCndFields(Operator com,String[] fieldNames)		
	{	for(int i=0;i<fieldNames.length;i++)
				com.showCndField(fieldNames[i]);
	}
	/**
	 * 隐藏指定的多个条件列（创建于 2003.03.09）.
<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	如果只有一个列，建议用hideCndField(String)方法代替本方法。
	hideCndFields("*")相当于hideCndFields()
	hideCndFields("")则忽略本次操作
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//以下程序相当于执行
	//com.hideCndField("name");
	//com.hideCndField("reg_dt");	
	com.hideCndFields("name,reg_dt");		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
		 @param com 指令集.
	 * @param fieldNames 对象列名用“,”分隔,“*”代表所有的对象域，空字符串不起作用.
	 * @exception NoSuchElementException	不存在的对象列名.
	 */

	public static void hideCndFields(Operator com,String fieldNames)
	{	if(fieldNames.equals("")) return;
	
		if(fieldNames.equals("*")) 
			com.hideCndFields();
		else
		{	String [] ls_FieldName = StringMethod.toChilds(fieldNames,Syntax.FIELD_APART);
		
			hideCndFields(com,ls_FieldName);
		}
	}
	
	
/**
* 隐藏指定的多个条件列（创建于 2003.03.14）.
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
  @param com 指令集.
* @param fieldNames 对象列名数组.
* @exception NoSuchElementException	不存在的对象列名.
*/
	public static void hideCndFields(Operator com,String[] fieldNames)		
	{	for(int i=0;i<fieldNames.length;i++)
			com.hideCndField(fieldNames[i]);
	}
	

	

	
/**
* 设置指定的多个分组列（创建于 2003.03.14）.
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
	@param com 指令集.
* @param fieldNames 分组列名列表,列名之间用','分隔,"*"代表所有的域.
* @exception NoSuchElementException 不存在的域.
*/
	public static void groupByFields(Operator com,String[] fieldNames)		
	{	for(int i=0;i<fieldNames.length;i++)
			com.groupByField(fieldNames[i]);
	}


				/**********计划开发的**********************/
/*
	void appendCndFieldExists(String fieldName,OperatorMethod child,String childFieldName)
	{
	}
	*/
/**条件域之间的关系表达式（创建于 2003.04.09）.*/
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
* @see package.class
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
* @param fieldNames 列名用“,”分隔,“*”代表所有的对象域.
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
		{	String [] ls_FieldName = StringMethod.toChilds(fieldNames,Syntax.FIELD_APART);
		
			setFieldsCndExpression(ls_FieldName,cndExpression);
		}
	}
	*/
/**
* 设置对象的多个属性的条件表达式（创建于 2003.03.14）.
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

