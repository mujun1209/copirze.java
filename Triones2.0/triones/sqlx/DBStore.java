package triones.sqlx;

import triones.Debug;
import triones.frame.Record;
import triones.frame.RecordSetTag;
import triones.frame.RecordSet;
import triones.frame.FrameMethod;
import triones.frame.Row;
import triones.frame.Store;
import triones.frame.WYQRecord;
import triones.frame.WYQRecordSet;
import triones.frame.WYQStore;
import triones.util.ResultSetMethod;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
* 数据存储对象控件（创建于 2005.04.12）.
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
* @version	2005.04.12
*/
public class DBStore extends WYQStore
{ 

/**
* 传输对象（创建于 2005.04.08）.
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
创建于 2005.04.08.
</pre>
</DL>
*/
	Transaction _SQLCA;
  

  
/**
* 设置传输对象（创建于 2005.04.08）.
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
创建于 2005.04.08.
</pre>
</DL>
* @param sqlca 传输对象.
* @see #getTransaction()
*/
  public void setTransaction(Transaction sqlca)
  { _SQLCA = sqlca;
  }
  

  
/**
* 取传输对象（创建于 2005.04.08）.
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
创建于 2005.04.08.
</pre>
</DL>
* @return 传输对象.
* @see #setTransaction(Transaction)
*/
  public Transaction getTransaction()
  { return _SQLCA;
  }
  
  
/**
* 构造方法（创建于 2005.04.08）.
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
创建于 2005.04.08.
</pre>
</DL>
* @param obj 记录对象说明.
*/
  public DBStore(RecordSetTag obj)
  { super(obj);
  }
/**
* 原始行生成记录（创建于 2005.04.07）.
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
* @param rs 记录集.
* @param row 行号.
* @return 记录.
*/
  public static Record rowBakToRecord(WYQRecordSet rs,int row)
  { Record lRecord = new WYQRecord(rs.getName(),FrameMethod.getColNames(rs));
    Row lRow = getRowBak(rs,row);
    
    if(lRow!=null) 
      for(int i=1;i<=lRecord.fieldCount();i++)
        lRecord.setFieldValue(i,lRow.getColValue(i));
      
    return lRecord;
  }
/**
* 将主缓存区数据更新到数据库（创建于 2003.05.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对已更新数据还原行状态为初始状态;
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.05.14.
</pre>
</DL>
* @return 发生更新的记录数.
* @exception SQLException 数据库错误.
*/
 int updatePrimaryBuffer()
	throws SQLException
 { int li_count = 0;		
   Record lRecord;
   RecordTranslet lTranslet;
		
		for(int i=1;i<=_PrimaryBuffer.rowCount();i++)
		{	switch(getRowStatus(_PrimaryBuffer,i))
			{	case ROW_STATUS_NEWMODIFIED:
          lRecord = FrameMethod.rowToRecord(_PrimaryBuffer,i);
          lTranslet = new RecordTranslet(lRecord);          
          li_count += _SQLCA.insert(lTranslet);
          setRowStatus(_PrimaryBuffer,i,ROW_STATUS_INITIAL);break;
				case ROW_STATUS_MODIFIED:
          lRecord = FrameMethod.rowToRecord(_PrimaryBuffer,i);
          lTranslet = new RecordTranslet(lRecord);
          lRecord = rowBakToRecord(_PrimaryBuffer,i);
          lTranslet.setWhereTranslet(new RecordTranslet(lRecord));          
					li_count += _SQLCA.update(lTranslet);
          setRowStatus(_PrimaryBuffer,i,ROW_STATUS_INITIAL);break;
			}
		}
		
		return li_count;
 }
/**
* 将删除缓存区数据更新到数据库（创建于 2003.05.14）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
对已更新数据还原行状态为初始状态;
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.05.14.
</pre>
</DL>
* @return 发生更新的记录数.
* @exception SQLException 数据库错误.
*/
  int updateDeletedBuffer()
	throws SQLException
 { int li_count = 0;		
   Record lRecord;
   RecordTranslet lTranslet;
   
   for(int i=_DeletedBuffer.rowCount();i>0;i--)
   {	switch(getRowStatus(_DeletedBuffer,i))
  	  {	case ROW_STATUS_INITIAL:
          lRecord = FrameMethod.rowToRecord(_DeletedBuffer,i);
          Debug.println("ROW_STATUS_INITIAL");
          Debug.println(lRecord);
          lTranslet = new RecordTranslet(lRecord);
          li_count += _SQLCA.delete(lTranslet);break;
  			case ROW_STATUS_MODIFIED:
          lRecord = rowBakToRecord(_DeletedBuffer,i);
          Debug.println("ROW_STATUS_MODIFIED");
          Debug.println(lRecord);
          lTranslet = new RecordTranslet(lRecord);
          li_count += _SQLCA.delete(lTranslet);break;
  		}			
  		_DeletedBuffer.deleteRow(i);
   }
  		
   return li_count;
 }

  
/**
* 将操作更新到数据库（创建于 2005.04.08）.
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
创建于 2005.04.08.
</pre>
</DL>
* @return 更新的记录个数.
* @exception SQLException 数据库错误.
*/
  public int update()
    throws SQLException
  { int li_count = updateDeletedBuffer();
    li_count += updatePrimaryBuffer();
    return li_count;
  }
  


  
/**
* 从数据库中读取数据（创建于 2005.04.08）.
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
创建于 2005.04.08.
</pre>
</DL>
* @return 记录个数.
* @exception SQLException 数据库错误.
*/
  public int retrieve()
    throws SQLException
  { Translet lTranslet = new RecordSetTranslet(_PrimaryBuffer);
    ResultSet lResultSet = _SQLCA.query(lTranslet);
    reset();
    int li_count = load(_PrimaryBuffer,lResultSet);
    _SQLCA.closeStatement();
    return li_count; 
  }
	

/**
* 导入数据集（创建于 2005.04.07）.
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
* @param buf 缓存区.
* @param rs 数据集.
* @return 导入行个数.
* @exception SQLException 数据库错误.
*/
  static int load(WYQRecordSet buf,ResultSet rs)
    throws SQLException
  { String[] ls_cols = FrameMethod.getColNames(buf);
    int[] li_cols = ResultSetMethod.findColIDs(rs,ls_cols);
    int li_row,li_rowCount = 0;
    
    while(rs.next())
    { li_rowCount ++;
      li_row = buf.insertRow(0);
      Object[] lo_vals = ResultSetMethod.currentRowToArray(rs,li_cols);
      FrameMethod.setItemsValues(buf,li_row,ls_cols,lo_vals);
      setRowStatus(buf,li_row,ROW_STATUS_INITIAL);
    }
    
    return li_rowCount;
  }
	

}
