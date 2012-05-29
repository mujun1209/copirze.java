package triones.sqlx;

import triones.Debug;
import triones.frame.RecordSet;
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
class RecordSetTranslet implements Translet
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
	RecordSet _RecordSet;
  
/**
* 行条件关系模式（创建于 2005.04.06）.
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
  int _RowRelation = 1;
  
/**
* 列条件关系模式（创建于 2005.04.06）.
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
  int _ColRelation = 0;
  
/**
* 设置行条件关系模式（创建于 2005.04.06）.
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
  public void setRowRelation(int mode)
  { _RowRelation = mode;
  }
/**
* 设置列条件关系模式（创建于 2005.04.06）.
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
  public void setColRelation(int mode)
  { _ColRelation = mode;
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
  public RecordSetTranslet(RecordSet data)
  { _RecordSet = data;
    Debug.println("constructor:" + _RecordSet.rowCount());
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
  { return _RecordSet.getName();
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
   { return SQLXMethod.toWHERE(_RecordSet,vals,_RowRelation,_ColRelation);
   }

/**
* 生成INSERT操作的预编译SQL语句（创建于 2004.09.10）.  
<P><DL>
<DT><B>说明：</B><DD>
<pre>
本方法仅用于编译
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
   { return "This method is used only for compiling - 本方法仅用于编译";
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
  { return "This method is used only for compiling - 本方法仅用于编译";
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
  { return SQLXMethod.toDELETE(_RecordSet) + SQLXMethod.toWHERE(_RecordSet,vals,_RowRelation,_ColRelation);
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
  { return SQLXMethod.toSELECT(_RecordSet) + SQLXMethod.toWHERE(_RecordSet,vals,_RowRelation,_ColRelation);
  }
}