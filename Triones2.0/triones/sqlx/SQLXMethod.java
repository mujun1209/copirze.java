package triones.sqlx;

import triones.Debug;
import triones.bas.None;
import triones.frame.Record;
import triones.frame.RecordSet;
import triones.frame.FrameMethod;
import triones.sql.Syntax;
import triones.util.StringMethod;

import java.util.List;

/**
* 用Translet生成SQL的函数库（创建于 2003.10.20）.
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
* @version	2003.10.20
*/
class SQLXMethod
{	
/**
* 无值（创建于 2003.10.21）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于判断字段是否赋值
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.21.
</pre>
</DL>
*/
	public static final Object NONE = new None();
	public static final char BIND_VALUE = '?';
	public static final char SIMULATED_VALUE = '$';
	public static final char VALUE = '@';
  public static final char COLUMN = '#';
  public static final char FIELD_APART = ',';


	/**
	 * 生成预编译的INSERT子句（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @parm com 记录.
	@param vals 用于存放与绑定参数对应的值数组.
	@return SQL表达式.
*/
	public static String toINSERT(Record com,List vals)
	{	 String ls_cols = "",ls_vals = "";
     Object lo_val;
			
		 for(int i=1;i<=com.fieldCount();i++)      
		 {	lo_val = com.getFieldValue(i);
        if(NONE.equals(lo_val))continue;
        
        if(!ls_cols.equals("")) ls_cols += FIELD_APART;
			  ls_cols += com.getFieldName(i);
			  if(!ls_vals.equals("")) ls_vals += FIELD_APART;
			  ls_vals += BIND_VALUE;
		 	  vals.add(lo_val);
		 }
									
		 return Syntax.toINSERT(com.getName(),ls_cols,ls_vals);
	}
	
	/**
	 * 生成预编译的DELETE子句（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @parm com 记录.
	@return SQL表达式.
*/
	public static String toDELETE(Record com)
	{	return Syntax.toDELETE(com.getName());
	}
  
/**
	 * 生成预编译的DELETE子句（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @parm com 记录.
	@return SQL表达式.
*/
	public static String toDELETE(RecordSet rs)
	{	return Syntax.toDELETE(rs.getName());
	}
	/**
	 * 生成预编译的UPDATE子句（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @parm com 记录.
* @param vals 用于存放与绑定参数对应的值数组.
	@return SQL表达式.
*/
	public static String toUPDATE(Record com,List vals)
	{	String ls_cols = "",ls_vals = "",ls_expressions = "";
    Object lo_val;

		for(int i=1;i<=com.fieldCount();i++)
		{	lo_val = com.getFieldValue(i);
      if(NONE.equals(lo_val))continue;
      
      if(!ls_expressions.equals("")) ls_expressions += FIELD_APART;
			ls_expressions += com.getFieldName(i) + " = " + BIND_VALUE;			
			vals.add(lo_val);
		}
									
		return Syntax.toUPDATE(com.getName(),ls_expressions);
	}

	/**
	 * 生成预编译的SELECT子句（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
	@param com 记录.
	@return SQL表达式.
*/
	public static String toSELECT(Record com)
	{	String ls_cols = "";
			
		for(int i=1;i<=com.fieldCount();i++)
		{ if(!ls_cols.equals("")) ls_cols += FIELD_APART;
			ls_cols += com.getFieldName(i);
		}
		return Syntax.toSELECT(com.getName(),ls_cols);
	}
	
	/**
	 * 生成预编译的SELECT子句（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
	@param rs 记录集.
	@return SQL表达式.
*/
	public static String toSELECT(RecordSet rs)
	{	String ls_cols = "";
			
		for(int i=1;i<=rs.colCount();i++)
		{ if(!ls_cols.equals("")) ls_cols += FIELD_APART;
			ls_cols += rs.getColName(i);
		}
		return Syntax.toSELECT(rs.getName(),ls_cols);
	}
	/**
	 * 生成预编译的CONDITION子句（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
	@param com 记录.
* @param vals 用于存放与绑定参数对应的值数组.
  @param cndRelation 字段条件的关联方式
	@return SQL表达式.
*/
	public static String toCondition(Record com,List vals,int cndRelation)
	{	String ls_condition = "",ls_expression,ls_name;
    Object lo_val;
    int li_valCount;
  
    for(int i=1;i<=com.fieldCount();i++)
		{	lo_val = com.getFieldValue(i);
      if(NONE.equals(lo_val)) continue;
      
      ls_expression = getFieldExpression(com,i);      
      
  		if(lo_val==null)
  		{	if(ls_expression.equals("# != @")||ls_expression.equals("# <> @"))
  				ls_expression = COLUMN + " IS NOT NULL";
  			else if(ls_expression.equals("# = @"))
  				ls_expression = COLUMN + " IS NULL";
  		}

      ls_name = com.getFieldName(i);
  		ls_expression = StringMethod.replaceAll(ls_expression,COLUMN,ls_name);
  		ls_expression = StringMethod.replaceAll(ls_expression,SIMULATED_VALUE,"" + lo_val);
  		ls_expression = StringMethod.replaceAll(ls_expression,VALUE,BIND_VALUE);
      Debug.println(ls_name + ":" + ls_expression);
      if(!ls_condition.equals("")) ls_condition += Syntax.CNDRELATION_SYNTAX[cndRelation];
      ls_condition += ls_expression;
      
  		li_valCount = StringMethod.charCount(ls_expression,BIND_VALUE);
      for(int j=0;j<li_valCount;j++) vals.add(lo_val); 		
		}
    
    return ls_condition;
	}
	
/**
* 取可作为预编译SQL语句片段的列条件表达式（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
	&nbsp;&nbsp;&nbsp;&nbsp;条件表达式主要用于生成SQL语句时作为WHERE表达式的组成部分。AbsOperator为每个
	列提供了默认的条件表达式:
	
	# = @
	
	其中"#"代表数据库列名,"="是操作符,"@"代表列值的占位符.
	
	如果数据库列名为"Name",当前值为"wyq",则将来的生成条件是:
	
	Name = 'wyq'
	
	如果将表达式改为 # like @,则将来的生成条件是:
	
	Name like 'wyq'
	
	当然你也可以直接采用?代替@,但建议不要这样做.
	
	&nbsp;&nbsp;&nbsp;&nbsp;另外，还有一种占位符"$"，称为伪值，其含义即在生成条件时以	当前值的字符串形式
	插入该占位符所处的位置，例如设定表达式为
  
	# like '$%'	则将来生成的条件是：	Name like 'wyq%'
  
  假设列reg_dt当前的值为1997-01-08
  表达式为 to_char(#,'yyyy-mm') = to_char(@,'yyyy-mm')
	转换SQL语句的WHERE表达式时生成
	to_char(reg_dt,'yyyy-mm') = to_char(1997-01-08,'yyyy-mm')
		
	&nbsp;&nbsp;&nbsp;&nbsp;值得注意的是，采用伪值占位符时必须保证对数据库SQL语句中的变量表示非常了解，
	否则将会产生意想不到的错误。
	
	当条件值为null时，以下条件表达式会做相应的自动修正：
	
	"# = @"     =>   "# IS NULL"
	"# != @"    =>   "# IS NOT NULL"
	"# <> @"    =>   "# IS NOT NULL"
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
修改于 2003.03.18 - 增加当条件值为null时的自动修正功能.	
</pre>
</DL>
* @param com 记录对象.
  @param fieldID 字段标识.
* @return 条件表达式.
*/
	static String getFieldExpression(Record com,int fieldID)
	{	String ls_expression = (String)com.getFieldAttribute(fieldID,"expression");
    if(!StringMethod.isValid(ls_expression)) ls_expression = "# = @";
		return ls_expression;
	}
    
/**
* 生成WHERE子句（创建于 2005.04.06）.
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
* @param com 记录对象.
* @param vals 存放WHERE子句对应的值.
* @param cndRelation 字段关系.
* @return WHERE子句.
*/
  public static String toWHERE(Record com,List vals,int cndRelation)
  { return Syntax.toWHERE(SQLXMethod.toCondition(com,vals,cndRelation));
  }
  

  
/**
* 生成条件（创建于 2005.04.07）.
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
* @param vals 用于存放与绑定参数对应的值数组..
* @param rowRelation 行条件之间的关系.
* @param colRelation 列条件之间的关系.
* @return 条件表达式.
* @see #toCondition(Record,List,int)
*/
  public static String toCondition(RecordSet rs,List vals,int rowRelation,int colRelation)
  { String ls_cnd,ls_condition = "";
    int li_rowRelation = rowRelation;

    for(int i=1;i<=rs.rowCount();i++)
    { ls_cnd = toCondition(FrameMethod.rowToRecord(rs,i),vals,colRelation);
      if(!StringMethod.isValid(ls_cnd))//如果为空行则将关系符置为字段关系符 
      { li_rowRelation = colRelation;
      }
      else
      { if(StringMethod.isValid(ls_condition)) ls_condition += Syntax.CNDRELATION_SYNTAX[li_rowRelation];
        ls_condition += "(" + ls_cnd + ")";
        li_rowRelation = rowRelation;
      }
    }

    return ls_condition;      
  }
  
/**
* 生成WHERE子句（创建于 2005.04.06）.
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
* @param rs 记录集.
* @param vals 存放WHERE子句对应的值.
* @param rowRelation 行条件关系.
* @param rowRelation 列条件关系.
* @return WHERE子句.
* @see #toWHERE(Record,List,int)
*/
  public static String toWHERE(RecordSet rs,List vals,int rowRelation,int colRelation)
  { return Syntax.toWHERE(SQLXMethod.toCondition(rs,vals,rowRelation,colRelation));
  }
  

}


