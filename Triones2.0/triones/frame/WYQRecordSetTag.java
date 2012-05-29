package triones.frame;

import triones.bas.HashStyle;
import triones.util.DataTypeMethod;
import triones.util.FileMethod;
import triones.util.StringMethod;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
public class WYQRecordSetTag extends HashStyle implements RecordSetTag,Cloneable,Serializable
{
	
/**
* 字段列表（创建于 2003.10.11）.
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
	private List _Columns;
  
/**
* 主键字段（创建于 2005.04.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
记录的唯一标识,多个字段之间用 KEY_APART 分隔
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
*/
	private String _PrimaryKey;
 
/**
* 只读字段（创建于 2005.04.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
记录的唯一标识,多个字段之间用 KEY_APART 分隔
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
*/
  private String _ReadOnlyKey;
	
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
* @see #WYQRecordSetTag(FieldTag[])
*/
	WYQRecordSetTag()
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
* @param name 记录集名称.
* @param colNames 列名称数组.
* @see #WYQRecordSetTag(String,String[],Class[])
*/
	public WYQRecordSetTag(String name,String[] colNames)
	{	super();
		init();		
		addCols(colNames,null);
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
* @param name 记录集名称.
* @param colNames 列名称数组.
* @param colTypes 列类型数组.
* @see #WYQRecordSetTag(String,String[])
*/
	public WYQRecordSetTag(String name,String[] colNames,Class[] colTypes)
	{	super();
		init();		
		addCols(colNames,colTypes);
    setName(name);
	}



  
/**
* 构造方法（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param name 记录集名称.
* @param view 视图.
*/
  public WYQRecordSetTag(String name,Map view)
  { super();
    init();
    load(view);
    setName(name);
  }
  
  
/**
* 构造方法（创建于 2005.04.16）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
文件名（不包括扩展名部分）将默认作为记录集名称
文件格式参见java.util.Properties.load(File)
</pre>
<DT><B>示例</B><DD>
<pre>
如文件"E:\吴勇庆\t_user_info.properties"中的内容：

USER_CODE = String
USER_NAME = String
USER_PWD = String
USER_AGE = Number
USER_DATE = Datetime

执行以下程序:

...

RecordSetTag lRst = new WYQRecordSetTag(new File("E:\\吴勇庆\\t_user_info.properties"));
System.out.println(lRst.getName());      
for(int i=1;i<=lRst.colCount();i++)
  System.out.println(lRst.getColName(i) + ":" + lRst.getColType(i));
  
...

结果如下：
-------------------------------------------
t_user_info
USER_AGE:class java.math.BigDecimal
USER_CODE:class java.lang.String
USER_PWD:class java.lang.String
USER_DATE:class java.sql.Timestamp
USER_NAME:class java.lang.String


</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.16.
</pre>
</DL>
* @param cfg 配置文件.
* @exception IOException 文件读写错误.
*/
	public WYQRecordSetTag(File cfg)
    throws IOException
  { super();
    init();
    Properties lp = new Properties();
		FileInputStream lFIStream = new FileInputStream(cfg);
    lp.load(lFIStream);
    lFIStream.close();
    load(lp);
    setName(FileMethod.getNameIgnoreExtension(cfg));
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
	{	_Columns = new ArrayList();
	}


  
/**
* 导入视图信息（创建于 2005.04.16）.
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
* @param view 视图定义.
*/
  private void load(Map view)
  { String[] ls_colNames = (String[])(view.keySet().toArray(new String[0]));
    String[] ls_colTypes = (String[])(view.values().toArray(new String[0])); 
    Class[] lc_colTypes = DataTypeMethod.toClasses(ls_colTypes);
    addCols(ls_colNames,lc_colTypes);
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
* @param colName 列名.
* @param colType 字段类型.
*/
	void addCol(String colName,Class colType)
	{	if(colType == null) colType = FieldTag.ANY;
    _Columns.add(new WYQFieldTag(colName,colType));
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
* @param cols 字段数组.
* @see #addCol(FieldTag)
*/
	void addCols(String[] colNames,Class[] colTypes)
	{	boolean lb_AnyType;    
  
    lb_AnyType = (colTypes == null);
    for(int i = 0;i<colNames.length;i++)
    { if(lb_AnyType)
        addCol(colNames[i],null);
      else
			  addCol(colNames[i],colTypes[i]);
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
	public int colCount()
	{	return _Columns.size();
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
  FieldTag[] cols()
	{	FieldTag[] lCols = new FieldTag[_Columns.size()];
		
		_Columns.toArray(lCols);
		
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
	public String[] colNames()
	{	String[] ls_cols = new String[_Columns.size()];
		
		for(int i=0;i<_Columns.size();i++)
		{	ls_cols[i] = ((WYQFieldTag)(_Columns.get(i))).getName();
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
	Class[] colTypes()
	{	Class[] ls_cols = new Class[_Columns.size()];
		
		for(int i=0;i<_Columns.size();i++)
		{	ls_cols[i] = ((WYQFieldTag)(_Columns.get(i))).getType();
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
* @param colID 列标识.
* @return 字段.
*/
  FieldTag getColTag(int colID)
	{	return (FieldTag)(_Columns.get(colIndex(colID)));
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
* @param colID 列标识.
* @return 存放索引.
*/
	private int colIndex(int colID)
	{	int li_index = colID - 1;
		
		if(li_index < 0 ) throw new IllegalArgumentException("Column doesn't exist - 字段不存在");
		
		return li_index;
	}
/**
* 取指定列的名称（创建于 2003.10.11）.
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
* @param colID 列标识.
* @return 列名称.
*/
  public String getColName(int colID)
	{	return getColTag(colID).getName();
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
* @param colID 列标识.
* @return 字段类型.
*/
	public Class getColType(int colID)
	{	return getColTag(colID).getType();
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
	{	for(int i=1;i<=colCount();i++)
		{	if(colName.equalsIgnoreCase(getColName(i))) return i;
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
* @param colName 列名.
* @return 字段类型.
*/
	public Class getColType(String colName)
	{	return getColType(getColID(colName));
	}
/**
* 取数据结构的关键字（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
关键字用于指明与记录唯一性相关的字段。
在执行UPDATE、DELETE操作时将根据这些字段进行条件匹配。
关键字如有多个字段组成，则字段名之间用","分隔。
空字符串代表没有字段.
*代表所有字段.
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
* @return 关键字.
*/
  public String getPrimaryKey()
  { return _PrimaryKey;
  }
  
/**
* 取数据结构中的只读字段（创建于 2003.03.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
只读字段在INSERT、UPDATE操作中将被忽略。
如有多个只读字段，则字段名之间用 KEY_APART 分隔。
空字符串代表没有字段.
*代表所有字段.
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
* @return 只读字段.
*/	  
  public String getReadOnlyKey()
  { return _ReadOnlyKey;    
  }

/**
* 设置关键字段（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
多个字段用 KEY_APART 分隔
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
* @param key 列名.
*/
  public void setPrimaryKey(String key)
  { _PrimaryKey = key;
  }
  
/**
* 设置只读字段（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
多个字段用 KEY_APART 分隔
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
* @param key 列名.
*/
  public void setReadOnlyKey(String key)
  { _ReadOnlyKey = key;
  }

/**
* 取记录集名称（创建于 2003.03.13）.
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
* @return 记录集名称.
* @see #setName(String)
*/
  public String getName()
	{	return (String)getAttribute("name");
	}
  

  
/**
* 设置记录集名称（创建于 2005.04.17）.
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
* @param name 记录集名称.
* @see #getName()
*/
  public void setName(String name)
  { setAttribute("name",name);
  }
 
/**
* KEY分隔符（创建于 2005.04.05）.
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
创建于 2005.04.05.
</pre>
</DL>
*/
	public final static char KEY_APART = ',';
  

  
/**
* 判断列是否属于只读字段（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列名对大小写不敏感.
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
* @param colName 列名.
* @return true代表是只读字段.
* @see #isReadOnlyCol(int)
*/
  public boolean isReadOnlyCol(String colName)
  { return StringMethod.isChild(_ReadOnlyKey,colName,KEY_APART);
  }
  
/**
* 判断列是否属于只读字段（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识从1..colCount()
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
* @return true代表是只读字段.
* @see #isReadOnlyCol(String)
*/
  public boolean isReadOnlyCol(int colID)
  { return isReadOnlyCol(getColName(colID));
  }

  
/**
* 判断列是否属于关键字段（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列名对大小写不敏感.
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
* @param colName 列名.
* @return true代表是关键字段.
* @see #isPrimaryCol(int)
*/
  public boolean isPrimaryCol(String colName)
  { return StringMethod.isChild(_PrimaryKey,colName,KEY_APART);
  }
 
/**
* 判断列是否属于关键字段（创建于 2005.04.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
列标识从1..colCount()
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
* @return true代表是关键字段.
* @see #isPrimaryCol(String)
*/
  public boolean isPrimaryCol(int colID)
  { return isPrimaryCol(getColName(colID));
  } 
}
