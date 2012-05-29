package trionesII.sql;

import trionesII.util.ReflectMethod;
import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;
/**
* 类数据对象（创建于 2003.03.13）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
可根据Class生成的数据对象.
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
* @version	2003.03.13
*/
public class ClassDataObject extends AbstractDataObject implements DataObject
{
/**
* 列名数组（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private List _ColNames = new ArrayList();
/**
* 列类型数组（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private List _ColTypes = new ArrayList();
	
/**
* 列初始值（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private List _ColValues = new ArrayList();
/**
* 默认类、属性与数据库表、字段的对应规则（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	protected static Rule DEFAULT_RULE = new DefaultRule();
	
	
/**
* 构造类型（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private Class _Class;
	
	
/**
* 构造类的属性名列表（创建于 2003.03.13）.
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
创建于 2003.03.13.
</pre>
</DL>
*/
	private List _FieldNames = new ArrayList();
	
/**
* 构造方法（创建于 2003.03.13）.
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
* @param cls 类.
*/
	public ClassDataObject(Class cls)
	{	init(cls,null,DEFAULT_RULE);
	}
	
/**
* 构造方法（创建于 2003.03.13）.
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
* @param cls 类.
* @param rule 名称映射规则.
*/
	public ClassDataObject(Class cls,Rule rule)
	{	init(cls,null,rule);
	}
/**
* 构造方法（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
可通过对象提供列的初始值.
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
* @param obj 对象.
*/
	public ClassDataObject(Object obj)
	{	init(obj.getClass(),obj,DEFAULT_RULE);
	}
	

	
/**
* 构造方法（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
可通过对象提供列的初始值.
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
* @param obj 对象.
* @param rule 名称映射规则.
*/
	public ClassDataObject(Object obj,Rule rule)
	{	init(obj.getClass(),obj,rule);
	}

	
/**
* 初始化（创建于 2003.03.13）.
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
* @param cls 类.
* @param rule 名称映射规则.
*/
	void init(Class cls,Object obj,Rule rule)
	{	try
		{	_Class = cls;
			setTable(rule.toDBName(_Class.getName()));
		
			Field[] lFields = _Class.getFields();	
					
			for(int i=0;i<lFields.length;i++)
				if(isValidField(lFields[i]))
				{	String ls_fieldName = lFields[i].getName();
					Object lo_fieldValue = (obj==null?null:lFields[i].get(obj));
					_FieldNames.add(ls_fieldName);
					_ColNames.add(rule.toFieldDBName(ls_fieldName));
					_ColTypes.add(lFields[i].getType());
					_ColValues.add(lo_fieldValue);					
				}
		}
		catch(IllegalAccessException e)
		{	//will not happened
		}
	}
/**
* 字段个数（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识的取值范围：1..colCount()
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
* @return 字段个数.
*/
	public int colCount()
	{	return _ColNames.size();
	}
/**
* 取字段名（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识的取值范围：1..colCount()
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
* @param id 列标识.
* @return 字段名.
* @see #getColType(int)
*/	
	public String getColName(int id)
	{	return (String)(_ColNames.get(id - 1));
	}
/**
* 取字段类型（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识的取值范围：1..colCount()
本方法的返回值为null代表类型不确定，使用者可根据需要覆盖.
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
* @param id 列标识.
* @return 字段类型.
* @see #getColName(int)
*/
	public Class getColType(int id)
	{	return (Class)(_ColTypes.get(id - 1));
	}
/**
* 取字段初始值（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识的取值范围：1..colCount()
null代表没有初始值.
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
* @param id 列标识.
* @return 字段默认初始值..
*/
	public Object getColValue(int id)
	{	return _ColValues.get(id - 1);
	}
	

	
/**
* 取构造类或对象的类型（创建于 2003.03.13）.
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
* @return 构造类.
*/
	public Class getConstructor()
	{	return _Class;
	}
	

	
/**
* 取构造类中的属性名（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识的取值范围：1..colCount()
null代表没有初始值.
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
* @param id 列标识.
* @return 对应列的属性名.
*/
	public String getFieldName(int id)
	{	return (String)(_FieldNames.get(id - 1));
	}
/**
* 判断类的属性是否可作为数据库表字段（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	目前的规则是：当且仅当公共列，且为JDBC支持的类型
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
* @param f 列实例
* @return 是否为有效的列
*/
	protected static boolean isValidField(Field f)
	{	return ReflectMethod.isPublicField(f) && DBMS.isSupportedFieldType(f.getType());
	}
}


