package triones.sql;

import triones.sqlx.DBStore;
import triones.sqlx.Transaction;
import triones.frame.RecordSetTag;
import triones.frame.FrameMethod;

import java.sql.SQLException;

/**
* XML数据处理对象（创建于 2005.04.16）.
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
* @version	2005.04.16
*/
public class XMLProcessor
{ 
  
/**
* 数据存储对象（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
*/
	DBStore _DataStore;
 

  
/**
* Short concise description（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param dataobject 数据对象.
*/
  public XMLProcessor(RecordSetTag dataobject)
  { _DataStore = new DBStore(dataobject);
  }
  
 
/**
* 行标签（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
*/
	public static String TAG_ROW = "RECORD";
/**
* 列标签（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
*/  
	public static String TAG_COL = "COLUMN";
  
/**
* 根目录标签（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
*/
  public static String TAG_BOOT = "RECORDSET";

  
/**
* 数据传输（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param sqlca 数据传输对象.
*/
  Transaction _SQLCA;
/**
* 设置数据传输对象（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param sqlca 数据传输对象.
*/
  public void setTransaction(Transaction sqlca)
  { _SQLCA = sqlca;
    _DataStore.setTransaction(_SQLCA);
  }
  

  
/**
* 新增数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param xml XML文本.
* @return 新增的记录个数.
* @exception SQLException 数据库错误.
*/
  public int insert(String xml)
    throws SQLException
  { loadXML(xml);
    return _DataStore.update();
  }
  

  
/**
* 导入XML（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param xml XML文本.
* @return 导入行数.
*/
  int loadXML(String xml)
  { return FrameMethod.loadXML(_DataStore,xml,TAG_ROW,TAG_COL,1);
  }
  

  
/**
* 删除数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param xml XML文本.
* @return 删除记录数.
* @exception SQLException 数据库错误.
*/
  public int delete(String xml)
    throws SQLException
  { loadXML(xml);
    FrameMethod.refreshAll(_DataStore);
    FrameMethod.deleteAll(_DataStore);
    return _DataStore.update();
  }


  
/**
* 更改数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param oldXML 被修改的数据.
* @param newXML 修改后的数据.
* @return 修改的记录数.
* @exception SQLException 数据库错误.
*/
  public int update(String oldXML,String newXML)
    throws SQLException
  { loadXML(oldXML);
    FrameMethod.refreshAll(_DataStore);
    loadXML(newXML);
    return _DataStore.update();
  }
  

  
/**
* 查询数据（创建于 2005.04.16）.
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
创建于 2005.04.16.
</pre>
</DL>
* @param xml XML条件文本.
* @return XML结果文本.
* @exception SQLException 数据库错误.
*/
  public String select(String xml)
    throws SQLException
  { loadXML(xml);
    _DataStore.retrieve();
    return FrameMethod.toXML(_DataStore,TAG_BOOT,TAG_ROW,false);
  }

}
