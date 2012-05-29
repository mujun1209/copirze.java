package triones.frame;

import triones.bas.Factory;
import triones.util.DataTypeMethod;
import triones.util.ResultSetMethod;
import triones.util.StringMethod;
import triones.xml.XMLMethod;
import triones.sql.DBMS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
* 记录集静态方法库（创建于 2005.04.07）.
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
* @version	2005.04.07
*/
public class FrameMethod
{ 

  
/**
* 取所有的字段名（创建于 2005.04.07）.
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
* @return 字段名数组.
*/
  public static String[] getColNames(RecordSet rs)
  { String[] ls_cols = new String[rs.colCount()];  
  
    for(int i=0;i<ls_cols.length;i++)
      ls_cols[i] = rs.getColName(i+1);
      
    return ls_cols;
  }
  
/**
* 取所有的字段类型（创建于 2005.04.07）.
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
* @return 字段类型数组.
*/
  public static Class[] getColTypes(RecordSet rs)
  { Class[] ls_cols = new Class[rs.colCount()];  
  
    for(int i=0;i<ls_cols.length;i++)
      ls_cols[i] = rs.getColType(i+1);
      
    return ls_cols;
  }
  
/**
* 设置指定行的值（创建于 2005.04.07）.
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
* @param cols 字段名列表.
* @param vals 值列表.
*/
  public static void setItemsValues(RecordSet rs,int row,String[] cols,Object[] vals)
  { for(int i=0;i<cols.length;i++)
      rs.setItemValue(row,cols[i],vals[i]);
  }
/**
* 取指定行的值（创建于 2005.04.07）.
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
* @param cols 字段名列表.
* @return 值列表.
*/
  public static Object[] getItemsValues(RecordSet rs,int row,String[] cols)
  { Object[] lo_vals = new Object[cols.length];
    for(int i=0;i<cols.length;i++)
      lo_vals[i] = rs.getItemValue(row,cols[i]);
    return lo_vals;
  }
  
/**
* 设置指定行的单元属性值（创建于 2005.04.07）.
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
* @param cols 字段名列表.
* @param property 属性名.
* @param vals 属性值列表.
*/
  public static void setItemsAttribute(RecordSet rs,int row,String[] cols,String property,Object[] vals)
  { for(int i=0;i<cols.length;i++)
      rs.setItemAttribute(row,cols[i],property,vals[i]);
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
* @param record 记录集.
* @param result 数据集.
* @return 导入行个数.
* @exception SQLException 数据库错误.
* @see #load(RecordSet,ResultSet,int,int)
*/
  public static int load(RecordSet record,ResultSet result)
    throws SQLException
  { String[] ls_cols = getColNames(record);
    int[] li_cols = ResultSetMethod.findColIDs(result,ls_cols);
    int li_row,li_rowCount = 0;
    
    while(result.next())
    { li_rowCount ++;
      li_row = record.insertRow(0);
      Object[] lo_vals = ResultSetMethod.currentRowToArray(result,li_cols);
      setItemsValues(record,li_row,ls_cols,lo_vals);
    }
    
    return li_rowCount;
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
* @param record 记录集.
* @param result 数据集.
* @param beginIndex 数据集当前行之后的的第几行，≥1.	
* @param rows 取记录个数，≥1.
* @return 导入行个数.
* @exception SQLException 数据库错误.
* @see #load(RecordSet,ResultSet)
*/
  public static int load(RecordSet record,ResultSet result,int beginIndex,int rows)
      throws SQLException
  { String[] ls_cols = getColNames(record);
    int[] li_cols = ResultSetMethod.findColIDs(result,ls_cols);
    int li_max,li_min,li_row,li_rowCount = 0;
    
    li_min = Math.min(beginIndex,beginIndex + rows - 1);
		li_max = Math.max(beginIndex,beginIndex + rows - 1);
    
    while(result.next())
    { li_rowCount ++;    
      if(li_rowCount < li_min) continue;
			if(li_rowCount > li_max) break;
      
      li_row = record.insertRow(0);
      Object[] lo_vals = ResultSetMethod.currentRowToArray(result,li_cols);
      setItemsValues(record,li_row,ls_cols,lo_vals);
    }
    
    return li_rowCount;    
  }
  

  
/**
* 行生成记录（创建于 2005.04.07）.
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
  public static Record rowToRecord(RecordSet rs,int row)
  { Record lRecord = new WYQRecord(rs.getName(),getColNames(rs));
    String ls_property = "expression";
  
    for(int i=1;i<=lRecord.fieldCount();i++)
    { lRecord.setFieldValue(i,rs.getItemValue(row,i));
      lRecord.setFieldAttribute(i,ls_property,rs.getItemAttribute(row,i,ls_property));
    }
      
    return lRecord;
  }
  

  
  
/**
* 生成XML格式文本（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param rs 记录集.
* @param bootTag 根标签.
* @param rowTag 行标签.
* @param colAsProperty true代表字段以属性方式生成,false代表字段以子标签方式生成.
* @return XML文本.
*/
  public static String toXML(RecordSet rs,String bootTag,String rowTag,boolean colAsProperty)
  { String ls_xml = "";
  
    for(int i=1;i<=rs.rowCount();i++)
      ls_xml += rowToXML(rs,i,rowTag,colAsProperty);
    
    if(StringMethod.isValid(ls_xml))
        ls_xml = "<" + bootTag + ">" + ls_xml + "</" + bootTag + ">";
    else
        ls_xml = "<" + bootTag + "/>";    
    
    return ls_xml;
  }
/**
* 将行记录转换成XML格式（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param rs 记录集.
* @param row 行号.
* @param rowTag 行标签.
* @param colAsProperty true代表字段以属性方式生成,false代表字段以子标签方式生成.
* @return XML文本.
*/
  public static String rowToXML(RecordSet rs,int row,String rowTag,boolean colAsProperty)
  { String ls_xml="",ls_col;
    Object lo_val;
  
    for(int i=1;i<=rs.colCount();i++)
      ls_xml += colToXML(rs,row,i,colAsProperty);
    
    if(colAsProperty)
    { ls_xml = "<" + rowTag + ls_xml + " />"; 
    }
    else
    { if(StringMethod.isValid(ls_xml))
        ls_xml = "<" + rowTag + ">" + ls_xml + "</" + rowTag + ">";
      else
        ls_xml = "<" + rowTag + "/>";      
    }
  
    return ls_xml;
  }
  

  
/**
* 生成XML格式文本（创建于 2005.04.09）.
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
创建于 2005.04.09.
</pre>
</DL>
* @param rs 记录集.
* @param row 行号.
* @param col 字段标识.
* @param colAsProperty true字段作为属性,false字段作为标签.
* @return XML文本.
*/
  public static String colToXML(RecordSet rs,int row,int col,boolean colAsProperty)
  { String ls_xml,ls_col;
    Object lo_val;
  
    lo_val = rs.getItemValue(row,col);
    ls_col = rs.getColName(col);
    
    if(colAsProperty) 
    { if(lo_val==null)
        ls_xml = " " + ls_col + "=" + '"' + '"';
      else
        ls_xml = ls_col + "=" + '"' + lo_val + '"';
    }
    else
    { if(lo_val==null)
        ls_xml = "<" + ls_col + "/>";
      else
        ls_xml = "<" + ls_col + ">" + lo_val + "</" + ls_col + ">";
    }
    return ls_xml;
  }
  

  
/**
* 导入XML文本（创建于 2005.04.10）.
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
* @param rs 记录集.
* @param xml XML文本.
* @param rowTag 行标签.
* @param colTag 列标签,null代表列值为行的属性值.
* @return 导入记录的个数.
*/
  public static int loadXML(RecordSet rs,String xml,String rowTag,String colTag)
  { return loadXML(rs,xml,rowTag,colTag,rs.rowCount() + 1);
  }
  

  
/**
* 导入XML文本（创建于 2005.04.16）.
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
* @param rs 记录集.
* @param xml XML文本.
* @param rowTag 行标签.
* @param colTag 列标签,null代表列值为行的属性值.
* @param beginRow 开始行.
* @return 导入记录的个数.
*/
  public static int loadXML(RecordSet rs,String xml,String rowTag,String colTag,int beginRow)
  { int li_row,li_count = rs.rowCount();
    String[] ls_rows = XMLMethod.getElementsByTag(xml,rowTag);
    String[] ls_cols = getColNames(rs);
    Class[] ls_types = getColTypes(rs);
    String[] ls_vals;
    Object[] lo_vals;

    for(int i=0;i<ls_rows.length;i++)
    { li_row = beginRow + i;
      if(li_row > li_count) li_row = rs.insertRow(0);   
      
      if(!StringMethod.isValid(colTag)) ls_vals = XMLMethod.elementGetAttributes(ls_rows[i],ls_cols);
      else 
      { 
        String[] ls_expressions = XMLMethod.getElementsAttributeByTags(ls_rows[i],ls_cols,colTag);
        setItemsAttribute(rs,li_row,ls_cols,colTag,ls_expressions);
        ls_vals = XMLMethod.getElementsContentByTags(ls_rows[i],ls_cols);
      }
      lo_vals = DataTypeMethod.convert(ls_vals,ls_types,Row.NONE);    
      setItemsValues(rs,li_row,ls_cols,lo_vals);
    }
    
    return ls_rows.length;
  }


  
/**
* 删除所有的记录（创建于 2005.04.16）.
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
* @param rs 数据集.
* @return 删除的记录个数.
*/
  public static int deleteAll(Sheet rs)
  { int li_count = rs.rowCount();
    for(int i=1;i<=li_count;i++)
      rs.deleteRow(i);
    return li_count;
  }
  

  
/**
* 还原所有的行（创建于 2005.04.16）.
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
* @param rs 记录集.
* @return 还原的行记录数.
*/
  public static int refreshAll(Store rs)
  { int li_count = rs.rowCount();
    for(int i=1;i<=li_count;i++)
      rs.refreshRow(i);
    return li_count;
  }
  
/**
* 自动转换列名调整（创建于 2003.01.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果名称长度超过命名长度，则取到命名长度为止.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.24.
</pre>
</DL>
* @param colName 原始列名.
	@return 调整后的列名.
* @see #toList(Class,boolean)
*/
	private static String ruleColName(String colName)
	{	return (colName.length() > DBMS.NAMING_LENGTH?colName.substring(0,DBMS.NAMING_LENGTH):colName);
	}


/**
* 将记录集数据按设定的基因工厂创建基因对象列表（创建于 2003.01.27）.
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
创建于 2003.01.27.
</pre>
</DL>
	@param rs 记录集.
	@param f 基因工厂.
* @return 基因对象列表.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
	public static List toList(RecordSet rs,Factory f)
		throws IllegalAccessException,InstantiationException
	{	List lList = new ArrayList(rs.rowCount());
		String[] ls_cols = getColNames(rs);
    
		for(int i=1;i<=rs.rowCount();i++)
		{	lList.add(f.newInstance(ls_cols,getItemsValues(rs,i,ls_cols)));
		}	
		
		return lList;
	}
	

	
/**
* 按设定的基因工厂创建基因对象列表（创建于 2007.09.05）.
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
创建于 2007.09.05.
</pre>
</DL>
* @param xml XML格式字符串.
* @param f 基因工厂.
* @param rowTag XML行标签.
* @param colTag XML列标签,null代表列作为行节点的属性而非列节点.
* @param beginRow 开始行,>=1.
* @return 基因工厂对应的对象列表.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/
	public static List toList(String xml,Factory f,String rowTag,String colTag,int beginRow)
		throws IllegalAccessException,InstantiationException
	{
		String[] ls_cols = f.getFieldNames();
		
    	WYQRecordSet lRecordSet = new WYQRecordSet(new WYQRecordSetTag("",ls_cols));
		
		loadXML(lRecordSet,xml,rowTag,colTag,beginRow);
		
		return toList(lRecordSet,f);
	
	}
	
/**
* 将指定行数据按设定的基因工厂创建基因对象（创建于 2003.01.27）.
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
创建于 2003.01.27.
</pre>
</DL>
	@param rs 记录集.
	@param row 行号.
	@param f 基因工厂.
* @return 基因对象.
* @exception IllegalAccessException 对应类的属性值必须初始化.
* @exception InstantiationException 对应类不存在，或者是一个抽象类，或者构造函数全都有参数.
*/	
	public static Object toObject(RecordSet rs,Factory f,int row)
		throws IllegalAccessException,InstantiationException
	{	String[] ls_cols = getColNames(rs);
    	return f.newInstance(ls_cols,getItemsValues(rs,row,ls_cols));
	}

}
