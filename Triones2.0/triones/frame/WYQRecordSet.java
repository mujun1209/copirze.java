package triones.frame;

import triones.bas.HashStyle;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
* 二维表空间原型（创建于 2003.10.17）.
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
* @version	2003.10.17
*/
public class WYQRecordSet extends HashStyle implements RecordSet
{	
/**
* 行空间数组（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
*/
	private RecordSetTag _Tag;
/**
* 行空间数组（创建于 2003.10.16）.
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
创建于 2003.10.16.
</pre>
</DL>
*/
	
	private List _Rows;

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
* @see #WYQRecordSet(SheetTag)
*/
  WYQRecordSet()
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
* @param tag 表空间描述.
*/
	public WYQRecordSet(RecordSetTag tag)
	{	super();
		init(tag);
	}


/**
* 行记录数（创建于 2003.10.17）.
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
* @return 行记录数.
*/
	public int rowCount()
	{	return _Rows.size();
	}
	

	
/**
* 返回所有的行空间（创建于 2003.10.17）.
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
* @return 行空间.
*/
	Row[] rows()
	{	Row[] lRows = new WYQRow[_Rows.size()];
	
		_Rows.toArray(lRows);
		
		return lRows;
	}
	

	
/**
* 取单元值（创建于 2005.04.05）.
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
* @param row 行编号.
* @param colID 列编号.
* @return 单元值.
* @see #setItemValue(int,int,Object)
*/
	public Object getItemValue(int row,int colID)
	{	return getRow(row).getColValue(colID);
	}
/**
* 取单元属性值（创建于 2005.04.05）.
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
* @param row 行编号.
* @param colID 列编号.
* @param property 属性名.
* @return 属性值.
* @see #setItemAttribute(int,int,String,Object)
*/
  public Object getItemAttribute(int row,int colID,String property)
  { return getRow(row).getColAttribute(colID,property);
  }
/**
* 取单元属性值（创建于 2005.04.05）.
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
* @param row 行编号.
* @param colName 列名称.
* @param property 属性名.
* @return 属性值.
* @see #setItemAttribute(int,String,String,Object)
*/
  public Object getItemAttribute(int row,String colName,String property)
	{	return getItemAttribute(row,getColID(colName),property);
	}
/**
* 取单元值（创建于 2005.04.05）.
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
* @param row 行编号.
* @param colName 列名称.
* @return 单元值.
* @see #getItemValue(int,int)
* @see #setItemValue(int,String,Object)
*/
	public Object getItemValue(int row,String colName)
	{	return getItemValue(row,getColID(colName));
	}
  
	
/**
* 给单元赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
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
* @param row 行编号.
* @param colID 列编号.
* @param value 单元值.
* @return -1代表失败.
* @see #getItemValue(int,int)
*/
	public int setItemValue(int row,int colID,Object value)
	{	if(row < 1 && row > rowCount()) return -1;
    return getRow(row).setColValue(colID,value);
	}
  
/**
* 给单元属性赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
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
* @param row 行编号.
* @param colID 列编号.
* @param property 属性名.
* @param value 属性值.
* @return -1代表失败.
* @see #getItemAttribute(int,int,String)
*/
	public int setItemAttribute(int row,int colID,String property,Object value)
	{	if(row < 1 && row > rowCount()) return -1;
    return getRow(row).setColAttribute(colID,property,value);
	}
/**
* 给单元属性赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
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
* @param row 行编号.
* @param colName 列名称.
* @param property 属性名.
* @param value 属性值.
* @return -1代表失败.
* @see #getItemAttribute(int,String,String)
*/ 
  public int setItemAttribute(int row,String colName,String property,Object value)
  { return setItemAttribute(row,getColID(colName),property,value);
  }
/**
* 给单元赋值（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
值和列的数据类型的必须匹配
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
* @param row 行编号.
* @param colName 列名称.
* @param value 单元值.
* @return -1代表失败.
* @see #getItemValue(int,String)
* @see #setItemValue(int,int,Object)
*/
	public int setItemValue(int row,String colName,Object value)
  { return setItemValue(row,getColID(colName),value);
  }
	
/**
* 取指定行空间（创建于 2003.10.17）.
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
* @param row 行编号.
* @return 行空间.
*/
	Row getRow(int row)
	{	return (Row)(_Rows.get(rowIndex(row)));
	}
	

	
/**
* 行编号对应的索引值（创建于 2003.10.17）.
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
* @param row 行编号.
* @return 行索引.
*/
	private int rowIndex(int row)
	{	int li_index = row - 1;
	
		if(li_index < 0) throw new IllegalArgumentException("Row doesn't exist - 行编号不存在");
		
		return li_index;
	}
	

	
/**
* 新增行（创建于 2003.10.17）.
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
* @param row 指定行编号，如果为0，则代表最后1行.
	@return 实际插入的行编号.
* @see #deleteRow(int)
*/
	public int insertRow(int row)
	{	Row lRow = new WYQRow(colCount());
    	int li_row = row;
				
		if(li_row > 0 && li_row <= _Rows.size())
			_Rows.add(rowIndex(li_row),lRow);
		else
		{	_Rows.add(lRow);
			li_row = _Rows.size();
		}
		
		return li_row;
	}
	


	
/**
* 删除行（创建于 2003.10.17）.
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
* @param row 行编号.
* @see #insertRow(int)
*/
	public void deleteRow(int row)
	{	_Rows.remove(rowIndex(row));
	}
	

	
/**
* 清空所有行（创建于 2003.10.17）.
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
*/
	public void reset()
	{	_Rows.clear();
	}
	

	
/**
* 初始化（创建于 2003.10.18）.
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
	@param f 字段空间描述.
*/
	void init(RecordSetTag tag)
	{	_Tag = tag;	
		if(_Rows==null) _Rows = new ArrayList();
	}
/**
* 根据列名取列标识（创建于 2003.03.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识从1..colCount()
列名对大小写不敏感.
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
* @param colName 列名称.
* @return 列标识，0代表列标识不存在.
*/
	public int getColID(String colName)
	{	return _Tag.getColID(colName);
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
	public int colCount()
  { return _Tag.colCount();
  }
 /**
* 取列名称（创建于 2005.04.05）.
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
* @param colID 列标识.
* @return 列名称.
*/
	public String getColName(int colID)
  { return _Tag.getColName(colID);
  }
  
/**
* 取列类型（创建于 2005.04.05）.
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
* @param colName 列名称.
* @return 列类型.
* @see #getColType(int)
*/
  public Class getColType(String colName)
  { return _Tag.getColType(colName);
  }
/**
* 取列类型（创建于 2005.04.05）.
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
* @param colID 列标识.
* @return 列类型.
*/
  public Class getColType(int colID)
  { return _Tag.getColType(colID);    
  }
	
/**
* 取数据结构对应的库表（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如有多个库表组成，则表名之间用","分隔。
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
*/
	public String getName()
  { return _Tag.getName();
  }


}

