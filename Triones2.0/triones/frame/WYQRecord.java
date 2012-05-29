package triones.frame;

import triones.bas.None;

import java.util.ArrayList;
import java.util.List;

/**
* 记录接口（创建于 2005.04.06）.
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
 电 话：021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2005.04.06
*/
public class WYQRecord extends WYQRecordTag implements Record
{ 

/**
* 值列表（创建于 2005.04.06）.
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
创建于 2005.04.06.
</pre>
</DL>
*/
	private List _Values;
/**
* 取字段当前的值（创建于 2003.10.11）.
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
* @param fieldID 字段编号≥1.
* @return 字段文本形式的值.
* @see #getFieldValue(String)
*/
	public Object getFieldValue(int fieldID)
  {	return _Values.get(fieldIndex(fieldID));
	}


  
/**
* 给字段赋值（创建于 2003.10.16）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段值需与字段类型匹配.
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
* @param fieldID 字段编号≥1.
* @param value 字段值.
* @return -1代表失败.
* @see #setFieldValue(String,Object)
*/
	public int setFieldValue(int fieldID,Object value)
  {	if(fieldID < 1 || fieldID > fieldCount()) return -1;
    _Values.set(fieldIndex(fieldID),value);
    return 1;
	}


/**
* 取字段的值（创建于 2005.04.05）.
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
* @return 字段值.
* @see #getFieldValue(int)
*/
  public Object getFieldValue(String fieldName)
  { return getFieldValue(getFieldID(fieldName));
  }

  
/**
* 给字段赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字段值需与字段类型匹配.
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
* @param value 字段值.
* @see #setFieldValue(int,Object)
* @return -1代表失败.
*/
  public int setFieldValue(String fieldName,Object value)
  { return setFieldValue(getFieldID(fieldName),value);
	}
  
/**
* 初始化（创建于 2005.04.05）.
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
* @param vals 初始值数组.
*/  
  void init(Object[] vals)
  { int li_count = fieldCount();
  
    _Values = new ArrayList();    
    for(int i = 0;i<li_count;i++)
      _Values.add(NONE);
    
    if(vals!=null)
    { if(vals.length < li_count) li_count = vals.length;
      for(int i = 1;i<=li_count;i++)
        setFieldValue(i,vals[i-1]);
    }
  }
  

  
/**
* 构造方法（创建于 2005.04.06）.
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
*/
  WYQRecord()
  { super();
  }

  
/**
* 构造方法（创建于 2005.04.06）.
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
* @param name 记录名称.
* @param fieldNames 字段名称数组.
* @see #WYQRecord(String,String[],Object[])
* @see #WYQRecord(String,String[],Class[])
* @see #WYQRecord(String,String[],Class[],Object[])
*/
  public WYQRecord(String name,String[] fieldNames)
  { super(name,fieldNames);
    init(null);
  }
  
/**
* 构造方法（创建于 2005.04.06）.
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
* @param name 记录名称.
* @param fieldNames 字段名称数组.
* @param fieldValues 字段值数组.
* @see #WYQRecord(String,String[])
* @see #WYQRecord(String,String[],Class[])
* @see #WYQRecord(String,String[],Class[],Object[])
*/
  public WYQRecord(String name,String[] fieldNames,Object[] fieldValues)
  { super(name,fieldNames);
    init(fieldValues);
  }
/**
* 构造方法（创建于 2005.04.06）.
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
* @param name 记录名称.
* @param fieldNames 字段名称数组.
* @param fieldTypes 字段类型数组.
* @see #WYQRecord(String,String[])
* @see #WYQRecord(String,String[],Object[])
* @see #WYQRecord(String,String[],Class[],Object[])
*/
  public WYQRecord(String name,String[] fieldNames,Class[] fieldTypes)
  { super(name,fieldNames,fieldTypes);
    init(null);
  }
/**
* 构造方法（创建于 2005.04.06）.
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
* @param name 记录名称.
* @param fieldNames 字段名称数组.
* @param fieldTypes 字段类型数组.
* @param fieldValues 字段值数组.
* @see #WYQRecord(String,String[])
* @see #WYQRecord(String,String[],Class[])
* @see #WYQRecord(String,String[],Object[])
*/
  public WYQRecord(String name,String[] fieldNames,Class[] fieldTypes,Object[] fieldValues)
  { super(name,fieldNames,fieldTypes);
    init(fieldValues);
  }
}
