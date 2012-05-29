package triones.bas;

import triones.Debug;
import triones.bas.Factory;
import triones.util.ReflectMethod;
import triones.util.ArrayMethod;
import triones.util.DataTypeMethod;
import triones.util.StringMethod;
import triones.sql.DBMS;

import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;


/**
* 默认遗传基因类（创建于 2003.03.07）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
主要用于按默认规则描述支持遗传基因的类的数据结构和构造方法.

规则是提取该类的公共属性，且该属性的类型必须是JDBC支持的.

该类必须支持无参数的构造方法。
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
* @version	2003.03.07
*/


public class ClassFactory implements Factory
{	
/**
* 遗传基因类（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	Class _Class;
	
/**
* 遗传基因类属性（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	Field[] _Fields; 
	
/**
* 遗传基因类属性名（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	String[] _FieldNames;
/**
* 制造基因的属性名是否以类名为前缀（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	boolean _ClassNameAsPrefix = false;
/**
* 忽略属性名大小写（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	boolean _IgnoreFieldNameCase = false;
/**
* 忽略基本类型属性值为null的赋值（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	boolean _IgnoreBaseTypeWhenValueIsNull = true;


/**
* 设定是否严格检查字段类型（创建于 2006.02.27）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果option为true则基本类型字值为null时抛出异常
如果option为false则基本类型字值为null时忽略赋值
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2006.02.27.
</pre>
</DL>
* @param option 是否严格判断字段类型.
*/
	void constrainFieldType(boolean option)
	{ 	_IgnoreBaseTypeWhenValueIsNull = !option;
	}
/**
* 忽略属性名的大小写（创建于 2003.04.02）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法将影响newInstance(String[],Object[])和getFieldIDs(String[])方法的作用。
当参数为true时，外部提供的fieldNames将以大小写不敏感的方式匹配与之相关的列标识.
默认为大小写敏感.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.04.02.
</pre>
</DL>
* @param option true代表忽略属性名的大小写，false代表不忽略属性名的大小写.
*/
	void ignoreFieldNameCase(boolean option)
	{	_IgnoreFieldNameCase = option;
	}
/**
* 是否将类名作为属性名的前缀（创建于 2003.03.09）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果需要类名作为前缀则以如下形式作为属性名称：

类名_属性名

类名中不含所在包名称.
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
* @param option true代表以类名作为前缀，false代表不以类名为前缀.
*/
	void classNameAsPrefix(boolean option)
	{	_ClassNameAsPrefix = option;
		_FieldNames = getFieldNames();
	}
/**
* 基因类名（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	String _ClassName;

	
/**
* 构造方法（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
类必须具备下列条件：

有公共属性，且属性类型为JDBC支持的类型

默认构造的模式为不将类名作为前缀，且忽略属性名的大小写
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param cls 类.
*/
	public ClassFactory(Class cls)
	{	init(cls,false,true);		
	}
	

	
/**
* 构造方法（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
类必须具备下列条件：

有公共属性，且属性类型为JDBC支持的类型。

当以类名为前缀时，不包含类名全路径，详见classNameAsPrefix(boolean)
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param cls 类.
* @param classNameAsPrefix 是否将类名作为前缀.
	@param ignoreFieldNameCase 是否忽略属性名的大小写.
*/
	public ClassFactory(Class cls,boolean classNameAsPrefix,boolean ignoreFieldNameCase)
	{	init(cls,classNameAsPrefix,ignoreFieldNameCase);
	}
	

	
/**
* 初始化用于构造（创建于 2003.03.11）.
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
创建于 2003.03.11.
</pre>
</DL>
* @param cls 类.
* @param classNameAsPrefix 是否将类名作为前缀.
	@param ignoreFieldNameCase 是否忽略属性名的大小写.
*/
	private void init(Class cls,boolean classNameAsPrefix,boolean ignoreFieldNameCase)
	{	_Class = cls;
		List lFieldList = new ArrayList();
		Field[] lFields = _Class.getFields();
		
		_ClassName = ReflectMethod.getClassName(_Class,true);
		
		for(int i=0;i<lFields.length;i++)
		{ if(isValidField(lFields[i]))
			{	lFields[i].setAccessible(true);
				lFieldList.add(lFields[i]);
			}
		}
		
		_Fields = new Field[lFieldList.size()];
		lFieldList.toArray(_Fields);
		
		_FieldNames = getFieldNames();
		classNameAsPrefix(classNameAsPrefix);
		ignoreFieldNameCase(ignoreFieldNameCase);
	}

	
/**
* 根据属性名和属性值构造遗传基因类的实例（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
属性名如果不存在，则被忽略。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性名称数组.
* @param vals 属性值数组.
* @return 遗传基因类实例.
* @exception InstantiationException 对象实例缺少默认的构造方法.
* @exception IllegalAccessException 对象属性不允许赋值，例如final变量.
*/
	public Object newInstance(String[] fields,Object[] vals)
		throws InstantiationException,IllegalAccessException
	{	Object lObj,lo_val;	
		int li_index;

		lObj = _Class.newInstance();
		
		for(int i=0;i<_Fields.length;i++)
		{	if(_IgnoreFieldNameCase)
				li_index = ArrayMethod.findItemIgnoreCase(fields,getFieldName(_Fields[i]));
			else
				li_index = ArrayMethod.findItem(fields,getFieldName(_Fields[i]));
		
			if(li_index >= 0)
			{	Class lClass = _Fields[i].getType();
				lo_val = DataTypeMethod.convert(vals[li_index],lClass);
				//_Fields[i].setAccessible(true);				
				if(_IgnoreBaseTypeWhenValueIsNull&&lo_val==null&&DataTypeMethod.isBaseType(lClass)) continue;
/**test**/Debug.println(_Fields[i].getType() + " " + getFieldName(_Fields[i]) + " = " + lo_val);				
				_Fields[i].set(lObj,lo_val);				
			}
		}
		
		return lObj;
	}
	
/**
* 构造类实例（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性列表.
* @param vals 属性值列表.
* @return 对象实例.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
	public Object newInstance(int[] fields,Object[] vals)
		throws InstantiationException,IllegalAccessException
	{	Object lObj,lo_val;	
		int li_index;

		lObj = _Class.newInstance();
		
		for(int i=0;i<fields.length;i++)
		{	if(fields[i] > 0)
			{	lo_val = DataTypeMethod.convert(vals[i],_Fields[fields[i] - 1].getType());
				//_Fields[i].setAccessible(true);
				_Fields[fields[i] - 1].set(lObj,lo_val);				
			}
		}
		
		return lObj;
	}


	
/**
* 取属性名列表（创建于 2003.03.11）.
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
创建于 2003.03.11.
</pre>
</DL>
* @return 属性名列表.
*/
	public String[] getFieldNames()
	{	String[] ls_fieldNames = new String[_Fields.length];
		
		for(int i=0;i<_Fields.length;i++)
		{	ls_fieldNames[i] = getFieldName(_Fields[i]);
		}
		
		return ls_fieldNames;
	}
	

	
/**
* 根据属性名返回属性标识（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回元素值 0 代表属性名不存在.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param fieldNames 属性名.
* @return 属性标识列表.
	@see #getFieldID(String)
*/
	public int[] getFieldIDs(String[] fieldNames)
	{	int[] li_fields = new int[fieldNames.length];
	
		for(int i=0;i<fieldNames.length;i++)
		{	li_fields[i] = getFieldID(fieldNames[i]);
		}
		
		return li_fields;
	}


	
/**
* 查找列名（创建于 2003.03.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回值 0 代表属性名不存在.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.11.
</pre>
</DL>
* @param fieldNames 属性名.
* @return 属性标识.
* @see #getFieldIDs(String[])
*/
	public int getFieldID(String fieldNames)
	{	int li_index;
	
		if(_IgnoreFieldNameCase)
			li_index = ArrayMethod.findItemIgnoreCase(_FieldNames,fieldNames);
		else
			li_index = ArrayMethod.findItem(_FieldNames,fieldNames);
		
		return li_index + 1;
	}
/**
* 按规则获取属性名（创建于 2003.03.09）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果需要类名作为前缀则以如下形式作为属性名称：

类名_属性名

属性名称如果超过DBMS.NAMING_LENGTH,则被截断到该长度.

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
* @param f 属性.
* @return 属性名称.
*/
	String getFieldName(Field f)
	{	String ls_name = f.getName();
		
		return StringMethod.truncate(_ClassNameAsPrefix?_ClassName + "_" + ls_name:ls_name,DBMS.NAMING_LENGTH);
	}


	/**
	 * 判断列是否符合默认遗传基因类的规则（创建于 2002.03.09）.
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
	创建于 2002.03.09.
	</pre>
	</DL>
* @param f 列实例
* @return 是否为有效的列
*/
	private static boolean isValidField(Field f)
	{	return ReflectMethod.isPublicField(f) && DBMS.isSupportedFieldType(f.getType());
	}

}

