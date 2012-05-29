package triones.frame;

import triones.bas.HashStyle;
import triones.util.StringMethod;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;



/**
* 二维表结构原型（创建于 2003.10.11）.
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
* @version	2003.10.11
*/
public class WYQRecordTag extends HashStyle implements RecordTag,Cloneable,Serializable
{
	
/**
* 字段字段表（创建于 2003.10.11）.
<DL>
<DT><B>说明：</B><DD>
<pre>
已初始化，用于存放支持FieldTag接口的字段信息。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.11.
</pre>
</DL>
*/
	private List _Fields;
  

/**
* 构造方法（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @see #WYQRecordTag(String[])
*/
	WYQRecordTag()
	{	super();
		init();
	}
	



/**
* 构造方法（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @param name 记录名称.
* @param fieldNames 字段名称数组.
* @see #WYQRecordTag(String,String[],Class[])
*/
	public WYQRecordTag(String name,String[] fieldNames)
	{	super();
		init();		
		addFields(fieldNames,null);
    setName(name);
	}
  
/**
* 构造方法（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @param name 记录名称.
* @param fieldNames 字段名称数组.
* @param fieldTypes 字段类型数组.
* @see #WYQRecordTag(String,String[])
*/
	public WYQRecordTag(String name,String[] fieldNames,Class[] fieldTypes)
	{	super();
		init();		
		addFields(fieldNames,fieldTypes);
    setName(name);
	}

	
/**
* 初始化方法（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
*/
	private void init()
	{	_Fields = new ArrayList();
	}


	
/**
* 增加字段（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
* @param colName 字段名.
* @param colType 字段类型.
*/
	void addField(String colName,Class colType)
	{	if(colType == null) colType = FieldTag.ANY;
    _Fields.add(new WYQFieldTag(colName,colType));
	}
	
/**
* 增加字段（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
* @param fields 字段数组.
* @see #addField(FieldTag)
*/
	void addFields(String[] fieldNames,Class[] fieldTypes)
	{	boolean lb_AnyType;    
  
    lb_AnyType = (fieldTypes == null);
    for(int i = 0;i<fieldNames.length;i++)
    { if(lb_AnyType)
        addField(fieldNames[i],null);
      else
			  addField(fieldNames[i],fieldTypes[i]);
    }
	}
/**
* 取字段个数（创建于 2003.10.11）.
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
创建于 2003.10.11.
</pre>
</DL>
* @return 字段个数.
*/
	public int fieldCount()
	{	return _Fields.size();
	}
	

	
/**
* 取所有字段（创建于 2003.10.11）.
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
创建于 2003.10.11.
</pre>
</DL>
* @return 字段数组.
*/
  FieldTag[] fields()
	{	FieldTag[] lCols = new FieldTag[_Fields.size()];
		
		_Fields.toArray(lCols);
		
		return lCols;
	}
	
/**
* 取所有字段名称（创建于 2003.10.11）.
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
创建于 2003.10.11.
</pre>
</DL>
* @return 字段名称数组.
*/
	public String[] fieldNames()
	{	String[] ls_cols = new String[_Fields.size()];
		
		for(int i=0;i<_Fields.size();i++)
		{	ls_cols[i] = ((WYQFieldTag)(_Fields.get(i))).getName();
		}
		
		return ls_cols;
	}
/**
* 取所有字段的类型（创建于 2003.10.11）.
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
创建于 2003.10.11.
</pre>
</DL>
* @return 字段类型数组.
*/
	public Class[] fieldTypes()
	{	Class[] ls_cols = new Class[_Fields.size()];
		
		for(int i=0;i<_Fields.size();i++)
		{	ls_cols[i] = ((WYQFieldTag)(_Fields.get(i))).getType();
		}
		
		return ls_cols;
	}

	
/**
* 根据字段编号取字段（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段编号范围：1～colCount()
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.11.
</pre>
</DL>
* @param colID 字段标识.
* @return 字段.
*/
  FieldTag getFieldTag(int colID)
	{	return (FieldTag)(_Fields.get(fieldIndex(colID)));
	}
		

	
/**
* 字段编号对应的索引（创建于 2003.10.23）.
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
创建于 2003.10.23.
</pre>
</DL>
* @param colID 字段标识.
* @return 存放索引.
*/
	int fieldIndex(int colID)
	{	int li_index = colID - 1;
		
		if(li_index < 0 ) throw new IllegalArgumentException("Column doesn't exist - 字段不存在");
		
		return li_index;
	}
/**
* 取指定字段的名称（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段编号范围：1～colCount()
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.11.
</pre>
</DL>
* @param colID 字段标识.
* @return 字段名称.
*/
  public String getFieldName(int colID)
	{	return getFieldTag(colID).getName();
	}


/**
* 取指定字段编号的字段类型（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段编号范围：1～colCount()
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.11.
</pre>
</DL>
* @param colID 字段标识.
* @return 字段类型.
*/
	public Class getFieldType(int colID)
	{	return getFieldTag(colID).getType();
	}
  
/**
* 根据字段名取字段标识（创建于 2003.03.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段标识从1..fieldCount()
字段名对大小写不敏感.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.24.
</pre>
</DL>
* @param colName 字段名称.
* @return 字段标识，0代表字段标识不存在.
*/
	public int getFieldID(String colName)
	{	for(int i=1;i<=fieldCount();i++)
		{	if(colName.equalsIgnoreCase(getFieldName(i))) return i;
		}
		return 0;
	}
  
	
/**
* 取字段类型（创建于 2003.10.22）.
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
创建于 2003.10.22.
</pre>
</DL>
* @param colName 字段名.
* @return 字段类型.
*/
	public Class getFieldType(String colName)
	{	return getFieldType(getFieldID(colName));
	}


/**
* 取数据结构对应的库表名称（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如有多个库表组成，则表名之间用 KEY_APART 分隔。
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
* @return 库表.
* @see #setName(String)
*/
  public String getName()
	{	return (String)getAttribute("name");
	}
  

  
/**
* 设置记录名（创建于 2005.04.17）.
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
创建于 2005.04.17.
</pre>
</DL>
* @param name 记录名.
* @see #getName()
*/
  public void setName(String name)
  { setAttribute("name",name);
  }
  
/**
* 取字段属性（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @param fieldID 字段标识.
* @param property 字段属性.
* @return 字段属性值.
* @see #getFieldAttribute(String,String)
*/
	public Object getFieldAttribute(int fieldID,String property)
  { return getFieldTag(fieldID).getAttribute(property);
  }

/**
* 取字段属性（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
* @param fieldName 字段名称.
* @param property 字段属性.
* @return 字段属性值.
* @see #getFieldAttribute(int,String)
*/
  public Object getFieldAttribute(String fieldName,String property)
  { return getFieldAttribute(getFieldID(fieldName),property);    
  }
  
  
/**
* 设置字段属性（创建于 2005.04.06）.
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
创建于 2005.04.06.
</pre>
</DL>
* @param fieldID 字段标识.
* @param property 属性名.
* @param value 属性值.
* @see #setFieldAttribute(String,String,Object)
*/
  public void setFieldAttribute(int fieldID,String property,Object value)
  { getFieldTag(fieldID).setAttribute(property,value);
  }
/**
* 设置字段属性（创建于 2005.04.06）.
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
创建于 2005.04.06.
</pre>
</DL>
* @param fieldName 字段名.
* @param property 属性名.
* @param value 属性值.
* @see #setFieldAttribute(int,String,Object)
*/
  public void setFieldAttribute(String fieldName,String property,Object value)
  { setFieldAttribute(getFieldID(fieldName),property,value);
  }
}
