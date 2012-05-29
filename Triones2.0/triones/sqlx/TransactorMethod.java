package triones.sqlx;

import triones.sql.Syntax;
import triones.util.ArrayMethod;
import triones.util.StringMethod;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;


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
public class TransactorMethod
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
	public static void hideFields(Transactor com,int mode)
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
	public static void hideCndFields(Transactor com,int mode)
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
	public static void showFields(Transactor com,int mode)
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
	public static void showCndFields(Transactor com,int mode)
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
	public static void showFields(Transactor com,String fieldNames)		
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
	public static void showFields(Transactor com,String[] fieldNames)		
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

	public static void hideFields(Transactor com,String fieldNames)		
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
	public static void hideFields(Transactor com,String[] fieldNames)		
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

	public static String getFieldSyntax(Transactor com,int rule)
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
	
	public static String getFieldSyntax(Transactor com)
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
	public static void showCndFields(Transactor com,String fieldNames)		
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
	public static void showCndFields(Transactor com,String[] fieldNames)		
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

	public static void hideCndFields(Transactor com,String fieldNames)
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
	public static void hideCndFields(Transactor com,String[] fieldNames)		
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
	public static void groupByFields(Transactor com,String[] fieldNames)		
	{	for(int i=0;i<fieldNames.length;i++)
			com.groupByField(fieldNames[i]);
	}

	/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param com 传输因子.
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(Transactor,int,String,String,Object)
*/
	public static boolean appendCndFieldOP(Transactor com,int cndRelation,String fieldName,String op,long val)		
	{	return appendCndFieldOP(com,cndRelation,fieldName,op,new Long(val));
	}
	

	/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param com 传输因子.
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(Transactor,int,String,String,Object)
*/
	public static boolean appendCndFieldOP(Transactor com,int cndRelation,String fieldName,String op,double val)		
	{	return appendCndFieldOP(com,cndRelation,fieldName,op,new Double(val));
	}
		/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param com 传输因子.
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(Transactor,int,String,String,Object)

*/
	public static boolean appendCndFieldOP(Transactor com,int cndRelation,String fieldName,String op,char val)		
	{	return appendCndFieldOP(com,cndRelation,fieldName,op,val + "");
	}

	/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param com 传输因子.
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(Transactor,int,String,String,Object)

*/
	public static boolean appendCndFieldOP(Transactor com,int cndRelation,String fieldName,String op,boolean val)		
	{	return appendCndFieldOP(com,cndRelation,fieldName,op,new Boolean(val));
	}


	
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
	@param not 以否定形式出现.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(Transactor,int,String,Object,Object,boolean)
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,long beginVal,long endVal,boolean not)		
	{	appendCndFieldBetween(com,cndRelation,fieldName,new Long(beginVal),new Long(endVal),not);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(Transactor,int,String,Object,Object)
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,long beginVal,long endVal)
	{	appendCndFieldBetween(com,cndRelation,fieldName,beginVal,endVal,false);		
	}
		/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
	@param not 以否定形式出现.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(Transactor,int,String,Object,Object,boolean)
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,double beginVal,double endVal,boolean not)
	{	appendCndFieldBetween(com,cndRelation,fieldName,new Double(beginVal),new Double(endVal),not);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(Transactor,int,String,Object,Object)
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,double beginVal,double endVal)
	{	appendCndFieldBetween(com,cndRelation,fieldName,beginVal,endVal,false);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
	@param not 以否定形式出现.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(Transactor,int,String,Object,Object,boolean)
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,char beginVal,char endVal,boolean not)
	{	appendCndFieldBetween(com,cndRelation,fieldName,new Character(beginVal),new Character(endVal),not);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.	
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(Transactor,int,String,Object,Object)
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,char beginVal,char endVal)
	{	appendCndFieldBetween(com,cndRelation,fieldName,beginVal,endVal,false);		
	}
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,Object beginVal,Object endVal)
	{appendCndFieldBetween(com,cndRelation,fieldName,beginVal,endVal,false);
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param not 是否采用否定语义.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
*/
	public static void appendCndFieldBetween(Transactor com,int cndRelation,String fieldName,Object beginVal,Object endVal,boolean not)
	{	List lList = new ArrayList();
		lList.add(beginVal);
		lList.add(endVal);
		
		String ls_condition = "(" + DBField.COLUMN + (not?" NOT ":" ") + "BETWEEN " + DBField.VALUE + " AND " + DBField.VALUE + ")";
		
		com.appendCndField(cndRelation,fieldName,ls_condition,lList);
		
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
* @param com 传输因子.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @param fieldName 条件列名.
* @param val 枚举值数组.
* @exception NoSuchElementException 不存在的列.
*/
	public static void appendCndFieldIn(Transactor com,int cndRelation,String fieldName,List val)
	{	appendCndFieldIn(com,cndRelation,fieldName,val,false);
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
* @param com 传输因子.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @param fieldName 条件列名.
* @param val 枚举值数组.
	@param not 是否采用否定语义.
* @exception NoSuchElementException 不存在的列.
*/
	public static void appendCndFieldIn(Transactor com,int cndRelation,String fieldName,List val,boolean not)
	{	String ls_enum = "";
		
		for(int i=0;i<val.size();i++)
			ls_enum = StringMethod.addChild(ls_enum,DBField.VALUE,',');
		
		String ls_condition = "(" + DBField.COLUMN + (not?" NOT ":" ") + "IN (" + ls_enum + "))";
		
		com.appendCndField(cndRelation,fieldName,ls_condition,val);
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param subSelect 子查询SQL语义对象.
	@param not 是否采用否定语义.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(Transactor,int,String,List,boolean)
	@see #appendCndFieldIn(Transactor,int,String,Transactor)
*/
	public static void appendCndFieldIn(Transactor com,int cndRelation,String fieldName,Transactor subSelect,boolean not)
	{	List lList = new ArrayList();
		String ls_preparedSQL = subSelect.prepareSelect(lList);		
		
		String ls_condition = "(" + DBField.COLUMN + (not?" NOT ":" ") + "IN (" + ls_preparedSQL + "))";
		
		com.appendCndField(cndRelation,fieldName,ls_condition,lList);
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
* @param com 传输因子.
* @param fieldName 条件列名.
* @param subSelect 子查询SQL语义对象.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(Transactor,int,String,Transactor,boolean)
*/
	public static void appendCndFieldIn(Transactor com,int cndRelation,String fieldName,Transactor subSelect)
	{	appendCndFieldIn(com,cndRelation,fieldName,subSelect,false);
	}

/**
* 构造简单条件表达式的操作符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
目前支持的操纵符如下：
">",">=","<>","!=","=","<","<=","like","not like"
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
	public final static String[] OP = {">",">=","<>","!=","=","<","<=","like","not like"};
	

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

//SQLObject是TransactorMethod的子类
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
* @param com 传输因子.
	@param cndRelation 与已有条件表达式之间的关系.	
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
*/
	
	public static boolean appendCndFieldOP(Transactor com,int cndRelation,String fieldName,String op,Object val)		
	{	String ls_condition;
	
		if(ArrayMethod.findItemIgnoreCase(OP,op) < 0)
			throw new UnsupportedOperationException(op + " is unsupported in appendCndFieldOP(String,String,Object)!");
				
		if(val==null&&!(op.equals("=")||op.equals("!=")||op.equals("<>"))) return false;
		
		if(op.indexOf("like") >=0 && (val + "").indexOf("%") < 0)
			ls_condition = DBField.COLUMN + " " + op + " '%" + DBField.SIMULATED_VALUE + "%'";
		else
			ls_condition = DBField.COLUMN + " " + op + " " + DBField.VALUE;
		
		com.appendCndField(cndRelation,fieldName,ls_condition,val);
		
		return true;
	}
  
	
	/**
	* 设置对象所有属性的条件表达式（创建于 2007.09.03）.
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
	创建于 2007.09.03.
	</pre>
	</DL>
	* @param com 传输因子.
	* @param expression 条件表达式.
	* @see #setFieldsCndExpression(Transactor,String[],String)
	* @see #setFieldsCndExpression(Transactor,String,String)
	*/	
	public static void setFieldsCndExpression(Transactor com,String expression)
	{
		for(int i=1;i<=com.fieldCount();i++)
			com.setFieldCndExpression(i,expression);
	}
	/**
	* 设置对象多个属性的条件表达式（创建于 2007.09.03）.
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
	创建于 2007.09.03.
	</pre>
	</DL>
	* @param com 传输因子.
	* @param fieldNames 列名数组.
	* @param expression 条件表达式.
	* @see #setFieldsCndExpression(Transactor,String)
	* @see #setFieldsCndExpression(Transactor,String,String)
	*/
	public static void setFieldsCndExpression(Transactor com,String fieldNames[],String expression)
	{
		for(int i=0;i<fieldNames.length;i++)
			com.setFieldCndExpression(fieldNames[i],expression);
	}
	/**
	* 设置对象多个属性的条件表达式（创建于 2007.09.03）.
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
	创建于 2007.09.03.
	</pre>
	</DL>
	* @param com 传输因子.
	* @param fieldNames 列名表达式,各属性之间用","分隔,"*"代表所有属性.
	* @param expression 条件表达式.
	* @see #setFieldsCndExpression(Transactor,String)
	* @see #setFieldsCndExpression(Transactor,String,String)
	*/
	public static void setFieldsCndExpression(Transactor com,String fieldNames,String expression)
	{
		if(fieldNames.equals("")) return;
	
		if(fieldNames.equals("*")) 
			setFieldsCndExpression(com,expression);
		else
		{	String [] ls_FieldName = StringMethod.toChilds(fieldNames,Syntax.FIELD_APART);
			setFieldsCndExpression(com,ls_FieldName,expression);
		}
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
}

