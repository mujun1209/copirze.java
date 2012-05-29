package triones.frame;

import triones.bas.HashStyle;
import triones.bas.None;
import triones.util.ArrayMethod;
import triones.util.DataTypeMethod;

import java.util.List;
import java.util.ArrayList;

/**
* 行空间原型（创建于 2003.10.11）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
针对二维表中以行为记录的特点，设计本类。
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
public class WYQRow extends HashStyle implements Row
{	


/**
* 字段值（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
*/
	private List _Values;
/**
* 字段属性（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
*/
  private List _Attributes;


/**
* 字段编号对应的值索引（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
* @param colID 字段编号.
* @return 值索引.
*/
	private int colIndex(int colID)
	{	return colID - 1;
	}

/**
* 构造方法（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
*/
	WYQRow()
	{	super();
	}
  
/**
* 构造方法（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
* @param colNum 字段个数.
*/
	WYQRow(int colNum)
	{	super();
    init(colNum);
	}
  
/**
* 字段个数（创建于 2003.10.16）.
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
* @return 字段个数.
*/
	public int colCount()
	{	return _Values.size();
	}

/**
* 取指定列的类型（创建于 2003.10.11）.
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
* @param colID 字段编号.
* @return 字段类型.
*/
	public Class getColType(int colID)
	{	return getColValue(colID).getClass();
	}
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
* @param colID 字段编号.
* @return 字段当前的值.
*/
	public Object getColValue(int colID)
	{	return _Values.get(colIndex(colID));
	}

	
/**
* 设置字段当前的值（创建于 2003.10.11）.
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
* @param colID 字段编号.
* @param value 字段值.
* @return -1代表失败.
*/
	public int setColValue(int colID,Object value)
	{	if(colID < 1 || colID > colCount()) return -1;
    _Values.set(colIndex(colID),value);
    return 1;
	}

/**
* 所有字段的值（创建于 2003.10.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
顺序与columns()相同
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.18.
</pre>
</DL>
* @return 值数组.
*/
	
	Object[] vals()
	{	return _Values.toArray();
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
* @param colNum 列个数.
*/
  
  void init(int colNum)
  { _Values = new ArrayList();
    _Attributes = new ArrayList();
    
    for(int i = 0;i<colNum;i++)
    { _Values.add(NONE);
      _Attributes.add(new HashStyle());
    }
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
* @param colID 字段标识.
* @param property 字段属性名.
* @return 字段属性值.
* @see #setColAttribute(int,String,Object)
*/
	public Object getColAttribute(int colID,String property)
  { return ((HashStyle)_Attributes.get(colIndex(colID))).getAttribute(property);
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
* @param colID 字段标识.
* @param property 字段属性名.
* @param value 字段属性值.
* @see #getColAttribute(int,String)
*/
  public int setColAttribute(int colID,String property,Object value)
  { if(colID < 1 || colID > colCount()) return -1;
    ((HashStyle)_Attributes.get(colIndex(colID))).setAttribute(property,value);
    return 1;
  }
}

