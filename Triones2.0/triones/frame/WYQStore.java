package triones.frame;

import triones.bas.HashStyle;
import triones.util.MyMethod;
import triones.util.ArrayMethod;

import java.util.List;

/**
* 原型数据存储对象（创建于 2003.10.11）.
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
public class WYQStore extends HashStyle implements Store
{ 
/**
* 行记录列表（创建于 2003.10.11）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于存放实现Row接口的对象信息.
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
* @see #_DeletedBuffer
*/
	protected WYQRecordSet _PrimaryBuffer;
	
	
/**
* 被删除的行记录列表（创建于 2003.10.11）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于存放实现Row接口的对象信息.
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
* @see #_PrimaryBuffer
*/
	protected WYQRecordSet _DeletedBuffer;
	
	
/**
* 数据对象（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
*/
	protected RecordSetTag _DataObject;
	

	
/**
* 构造方法（创建于 2003.10.17）.
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
* @param obj 数据对象.
*/
	public WYQStore(RecordSetTag obj)
	{	super();
		init(obj);
	}


	
/**
* 初始化（创建于 2003.10.17）.
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
* @param obj 数据对象.
*/
	private void init(RecordSetTag obj)
	{	_DataObject = obj;
		
		_PrimaryBuffer = new WYQRecordSet(_DataObject);
		_DeletedBuffer = new WYQRecordSet(_DataObject);
	}
/**
* 当前行的个数（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不包括当前被删除的行记录.
在数据存储对象中，记录以行的形式存放.
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
* @return 行个数.
	@see #deletedCount()
*/
	public int rowCount()
	{	return _PrimaryBuffer.rowCount();
	}
	

	
/**
* 当前被删除行的个数（创建于 2003.10.11）.
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
* @return 被删除的行个数.
* @see #rowCount()
*/
	public int deletedCount()
	{	return _DeletedBuffer.rowCount();
	}
	

	


/**
* 插入新行（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
行号的取值范围:1..rowCount().
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
* @param row 要插入的行编号，0代表最后一行.
	@return 实际插入的行编号.
*/

  public int insertRow(int row)
	{ int li_row = _PrimaryBuffer.insertRow(row);
		setRowStatus(_PrimaryBuffer,li_row,ROW_STATUS_NEW);		
		return li_row;
	}

	
/**
* 删除行（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
行号的取值范围:1..rowCount().
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
* @param row 要删除的行编号，0代表最后一行.
*/
	public void deleteRow(int row)
	{	int li_row = _DeletedBuffer.insertRow(0);
		rowCopy(_PrimaryBuffer,row,_DeletedBuffer,li_row);
    setRowBak(_DeletedBuffer,li_row,getRowBak(_PrimaryBuffer,row));
    setRowStatus(_DeletedBuffer,li_row,getRowStatus(_PrimaryBuffer,row));
		_PrimaryBuffer.deleteRow(row);
	}
	

  
/**
* 行复制（创建于 2005.04.07）.
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
创建于 2005.04.07.
</pre>
</DL>
* @param source 来源数据表.
* @param sourceRow 来源行.
* @param target 目的数据表.
* @param targetRow 目的行.
*/
  void rowCopy(RecordSet source,int sourceRow,RecordSet target,int targetRow)
  { for(int i=1;i<=source.colCount();i++)
      target.setItemValue(targetRow,i,source.getItemValue(sourceRow,i));
  }
	
/**
* 取记录值（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
记录位置通过行和字段标明。
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
* @param row 行编号.
* @param colID 列编号.
* @return 记录值.
* @see #getItemValue(int,String)
*/
	public Object getItemValue(int row,int colID)
	{ return _PrimaryBuffer.getItemValue(row,colID);
	}
	
	
/**
* 取记录值（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
记录位置通过行和字段标明。
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
* @param row 行编号.
* @param colName 字段名称.
* @return 记录值.
* @see #getItemValue(int,int)
*/
  public Object getItemValue(int row,String colName)
	{ return getItemValue(row,_DataObject.getColID(colName));
	}
	

	
/**
* 设置记录值（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
记录位置通过行和字段标明。
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
* @param row 行编号.
* @param colID 字段编号.
* @param colVal 记录值.
* @return -1代表失败.
* @see #setItemValue(int,String,Object)
*/
	public int setItemValue(int row,int colID,Object colVal)
	{ if(row < 1 && row > rowCount()) return -1;
    
    int li_newStatus;
		int li_oldStatus = getRowStatus(_PrimaryBuffer,row);	
		
		switch(li_oldStatus)
		{	case ROW_STATUS_INITIAL:
			case ROW_STATUS_MODIFIED:li_newStatus = ROW_STATUS_MODIFIED;break;
			case ROW_STATUS_NEW:
			case ROW_STATUS_NEWMODIFIED:li_newStatus = ROW_STATUS_NEWMODIFIED;break;
			default: throw new IllegalStateException("RowStatus[" + row + "] error: " + li_oldStatus);
		}
		
		if(li_oldStatus==ROW_STATUS_INITIAL)
		{	Row lRow = getRow(_PrimaryBuffer,row);
			setRowBak(_PrimaryBuffer,row,(Row)MyMethod.deepClone(lRow));
		}
		
		if(_PrimaryBuffer.setItemValue(row,colID,colVal)<0) return -1;
		
		setRowStatus(_PrimaryBuffer,row,li_newStatus);
    
    return 1;
	}
	
	
/**
* 行状态形式（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
*/
	protected static String[] ROW_STATUS = {"INITIAL","MODIFIED","NEW","NEWMODIFIED"};
	
	
/**
* 初始状态（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
*/
	protected static final int ROW_STATUS_INITIAL = 0;
	
/**
* 已改状态（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
*/
	protected static final int ROW_STATUS_MODIFIED = 1;
	
/**
* 空行状态（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
*/
	protected static final int ROW_STATUS_NEW = 2;
	
/**
* 新增状态（创建于 2003.10.17）.
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
创建于 2003.10.17.
</pre>
</DL>
*/
	protected static final int ROW_STATUS_NEWMODIFIED = 3;
	
/**
* 取行状态（创建于 2003.10.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
状态值含义如下：

INITIAL			初始的
MODIFIED		已改的
NEW					空的
NEWMODIFIED	新增的
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
* @param buffer 表空间.
* @param row 行编号.
* @return 行状态.
* @see #setRowStatus(WYQRecordSet,int,int)
*/
	protected static int getRowStatus(WYQRecordSet buffer,int row)
	{	return ArrayMethod.findItem(ROW_STATUS,"" + getRow(buffer,row).getAttribute("status"));
	}
	

	
/**
* 设置行状态（创建于 2003.10.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
状态值含义如下：

INITIAL			初始的
MODIFIED		已改的
NEW					空的
NEWMODIFIED	新增的
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
* @param buffer 表空间.
* @param row 行编号.
* @param status 行状态.
* @see #getRowStatus(WYQRecordSet,int)
*/
	protected static void setRowStatus(WYQRecordSet buffer,int row,int status)
	{	getRow(buffer,row).setAttribute("status",ROW_STATUS[status]);		
	}
	

	
/**
* 设定行备份（创建于 2003.10.17）.
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
* @param buffer 表空间.
* @param row 行编号.
* @param Row 用于备份的行空间.
* @see #getRowBak(WYQRecordSet,int)
*/
	protected static void setRowBak(WYQRecordSet buffer,int row,Row Row)
	{	getRow(buffer,row).setAttribute("bak",Row);
	}
	

	
/**
* 取行备份（创建于 2003.10.17）.
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
* @param buffer 表空间.
* @param row 行编号.
* @return 备份的行空间.
* @see #setRowBak(WYQRecordSet,int,Row)
*/
	protected static Row getRowBak(WYQRecordSet buffer,int row)
	{	return (Row)(getRow(buffer,row).getAttribute("bak"));
	}
	

	
/**
* 还原行（创建于 2003.10.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
true:如果行记录为修改行，则恢复原始值；
false:如果行记录为新增行，则将其设为原始行；
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
	@return true代表成功，false代表失败。
*/
	public boolean refreshRow(int row)
	{ boolean lb_modified = false;
	  if(getRowStatus(_PrimaryBuffer,row)==ROW_STATUS_MODIFIED)
		{	Row lRow = getRowBak(_PrimaryBuffer,row);
      if(lRow!=null)
      { for(int i=1;i<=lRow.colCount();i++)
          _PrimaryBuffer.setItemValue(row,i,lRow.getColValue(i));
      }
      
			lb_modified = true;
		}
		
    setRowStatus(_PrimaryBuffer,row,ROW_STATUS_INITIAL);
    
		return lb_modified;			
	}

	
/**
* 清除行（创建于 2003.10.17）.
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
*/
	public void discardRow(int row)
	{	_PrimaryBuffer.deleteRow(row);
	}
	

	
/**
* 取表空间指定行空间（创建于 2003.10.17）.
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
* @param buffer 表空间.
* @param row 行编号.
* @return 行空间.
*/
	protected static Row getRow(WYQRecordSet buffer,int row)
	{	return buffer.getRow(row);
	} 
	
/**
* 设置记录值（创建于 2003.10.11）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
记录位置通过行和字段标明。
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
* @param row 行编号.
* @param colName 字段名称.
* @param colVal 记录值.
* @return -1代表失败.
* @see #setItemValue(int,int,Object)
*/
	public int setItemValue(int row,String colName,Object colVal)
	{ return setItemValue(row,_DataObject.getColID(colName),colVal);
	}
	

	
/**
* 清空所有的行（创建于 2003.10.18）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法不代表删除操作.
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
	public void reset()
	{	_PrimaryBuffer.reset();
		_DeletedBuffer.reset();
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
  { return _DataObject.getColType(colName);
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
  { return _DataObject.getColType(colID);    
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
  { return _DataObject.colCount();
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
  { return _DataObject.getName();
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
  { return _DataObject.getColName(colID);
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
	{	return _DataObject.getColID(colName);
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
  { return _PrimaryBuffer.setItemAttribute(row,colID,property,value);
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
  { return _PrimaryBuffer.getItemAttribute(row,colID,property);
  }
  
/* 取单元值（创建于 2005.04.05）.
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
*/
  public Object getItemAttribute(int row,String colName,String property)
  { return _PrimaryBuffer.getItemAttribute(row,colName,property);
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
*/
	public int setItemAttribute(int row,String colName,String property,Object value)
  { return _PrimaryBuffer.setItemAttribute(row,colName,property,value);
  }
}

