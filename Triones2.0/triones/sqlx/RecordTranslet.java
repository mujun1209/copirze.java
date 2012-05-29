package triones.sqlx;

import triones.frame.Record;
import triones.sql.Syntax;

import java.util.List;

/**
* 数据传输因子（创建于 2005.04.04）.
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
* @version	2005.04.04
*/
class RecordTranslet implements Translet
{ 
  
  
/**
* 条件记录对象（创建于 2005.04.04）.
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
创建于 2005.04.04.
</pre>
</DL>
*/
	Record _Record;
  
/**
* 字段条件关系模式（创建于 2005.04.06）.
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
  int _CndRelation = 0;
  

  
/**
* 设置字段条件关系模式（创建于 2005.04.06）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
 0  代表 AND
 1  代表 OR
>1  代表 自定义
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
* @param mode 关系模式.
*/
  public void setCndRelation(int mode)
  { _CndRelation = mode;
  }
  
/**
* 构造方法（创建于 2005.04.04）.
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
创建于 2005.04.04.
</pre>
</DL>
* @param data 数据对象.
*/
  public RecordTranslet(Record data)
  { _Record = data;
  }
  

/**
* 取传输因子名称（创建于 2005.04.04）.
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
创建于 2005.04.04.
</pre>
</DL>
* @return 传输因子名称.
*/
  public String getDBName()
  { return _Record.getName();
  }
  
/**
* 取预编译条件语句（创建于 2004.09.21）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
 预编译条件语句是指包含WHERE关键字的预编译SQL语句,不包括ORDER BY和GROUP BY语句;
  
 参数vals中存放与预编译条件语句中的占位符'?'对应顺序的绑定值.

</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.09.21.
</pre>
</DL>
* @param vals 与预编译条件语句中绑定值占位符对应的参数列表.
* @return 预编译条件语句.
*/
   public String prepareWhere(List vals)
   { return toWHERE(vals);
   }

/**
* 生成INSERT操作的预编译SQL语句（创建于 2004.09.10）.  
<P><DL>
<DT><B>说明：</B><DD>
<pre>
 预编译SQL语句是指符合SQL语法的，可用于PreparedStatement对象构造的表达式;
  
 参数vals中存放与预编译SQL语句中的占位符'?'对应顺序的绑定值.

</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.09.10.
</pre>
</DL>
* @param vals 与预编译SQL语句中绑定值占位符对应的参数列表.
* @return 预编译SQL语句.
*/
   public String prepareInsert(List vals)
   { return SQLXMethod.toINSERT(_Record,vals);
   }
/**
* 生成UPDATE操作的预编译SQL语句（创建于 2004.09.10）.  
<P><DL>
<DT><B>说明：</B><DD>
<pre>
 预编译SQL语句是指符合SQL语法的，可用于PreparedStatement对象构造的表达式;
  
 参数vals中存放与预编译SQL语句中的占位符'?'对应顺序的绑定值.

</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.09.10.
</pre>
</DL>
* @param vals 与预编译SQL语句中绑定值占位符对应的参数列表.
* @return 预编译SQL语句.
*/

	public String prepareUpdate(List vals)
  { String ls_sql = SQLXMethod.toUPDATE(_Record,vals);
    if(_Where!=null) ls_sql += toWHERE(vals);
    return ls_sql;
  }
  
/**
* 生成DELETE操作的预编译SQL语句（创建于 2004.09.10）.  
<P><DL>
<DT><B>说明：</B><DD>
<pre>
 预编译SQL语句是指符合SQL语法的，可用于PreparedStatement对象构造的表达式;
  
 参数vals中存放与预编译SQL语句中的占位符'?'对应顺序的绑定值.

</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.09.10.
</pre>
</DL>
* @param vals 与预编译SQL语句中绑定值占位符对应的参数列表.
* @return 预编译SQL语句.
*/
	public String prepareDelete(List vals)
  { return SQLXMethod.toDELETE(_Record) + toWHERE(vals);
  }
  
/**
* 生成SELECT操作的预编译SQL语句（创建于 2004.09.10）.  
<P><DL>
<DT><B>说明：</B><DD>
<pre>
 预编译SQL语句是指符合SQL语法的，可用于PreparedStatement对象构造的表达式;
  
 参数vals中存放与预编译SQL语句中的占位符'?'对应顺序的绑定值.

</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2004.09.10.
</pre>
</DL>
* @param vals 与预编译SQL语句中绑定值占位符对应的参数列表.
* @return 预编译SQL语句.
*/
	public String prepareSelect(List vals)
  { return SQLXMethod.toSELECT(_Record) + toWHERE(vals);
  }
  
  
/**
* WHERE因子（创建于 2005.04.08）.
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
	Translet _Where = null;
    
/**
* 设置WHERE因子（创建于 2005.04.08）.
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
* @param com 条件因子.
*/
  public void setWhereTranslet(Translet com)
  { _Where = com;
  }
    
/**
* Short concise description（创建于 2005.04.08）.
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
* @param vals 与预编译WHERE语句中绑定值占位符对应的参数列表.
* @return WHERE语句.
*/
  String toWHERE(List vals)
  { if(_Where==null)
      return SQLXMethod.toWHERE(_Record,vals,_CndRelation);
    else
      return _Where.prepareWhere(vals);    
  }
}