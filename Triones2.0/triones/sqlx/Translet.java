package triones.sqlx;

import java.util.List;


/**
* Translet Interface(Created in 2004.09.20).
<DL>
<DT><B>Comment</B><DD>
<pre>
 Translet Interface is used for create the precompiled SQL and the setting IN parameter values.
</pre>
<DT><B>Usuage</B><DD>
<pre>
 Pass over.
</pre>
<DT><B>Explain</B><DD>
<pre>
 Pass over.
</pre>
<DT><B>Notice</B><DD>
<pre>
 Based on JDK1.3
</pre>
<DT><B>Future</B><DD>
<pre>
 Pass over.
</pre>
<DT><B>Contact</B><DD>
<pre>
 email：wyq@triok.com 
 phone：021-65804142
</pre>
</DL>
* @author	 Wu yongqing
* @version	2004.09.20
*/

/**
* 传输因子接口（创建于 2004.09.10）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
传输因子接口主要用于生成预编译的SQL语句和相应的绑定值。

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
 电  话:021-65804142
</pre>
</DL>
* @author	吴勇庆
* @version	2004.09.10
*/
public interface Translet
{		
	

/**
* To create a precompiled SQL statement of select operation(Created in 2004.09.17).
<P><DL>
<DT><B>Comment</B><DD>
<pre>
   Precompiled SQL statement means that it can be used for constructing
the Instance of PreparedStatement Object.

  The argument named "vals", which stores the setting IN parameter 
values, should ensure that the sequence of the elements is fit for the 
setting IN positions represented by symbol '?'.

</pre>
<DT><B>Example</B><DD>
<pre>
 Pass over.
</pre>
<DT><B>Log</B><DD>
<pre>
 Created in 2004.09.17.
</pre>
</DL>
* @param vals a List Object that stores the the setting IN parameter values.
* @return Precompiled SQL statement.
*/

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

	String prepareSelect(List vals);
  
/**
* To create a precompiled SQL statement of delete operation(Created in 2004.09.17).
<P><DL>
<DT><B>Comment</B><DD>
<pre>
   Precompiled SQL statement means that it can be used for constructing
the Instance of PreparedStatement Object.

  The argument named "vals", which stores the setting IN parameter 
values, should ensure that the sequence of the elements is fit for the 
setting IN positions represented by symbol '?'.

</pre>
<DT><B>Example</B><DD>
<pre>
 Pass over.
</pre>
<DT><B>Log</B><DD>
<pre>
 Created in 2004.09.17.
</pre>
</DL>
* @param vals a List Object that stores the the setting IN parameter values.
* @return Precompiled SQL statement.
*/

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

	String prepareDelete(List vals);
  
 /**
* To create a precompiled SQL statement of update operation(Created in 2004.09.17).
<P><DL>
<DT><B>Comment</B><DD>
<pre>
   Precompiled SQL statement means that it can be used for constructing
the Instance of PreparedStatement Object.

  The argument named "vals", which stores the setting IN parameter 
values, should ensure that the sequence of the elements is fit for the 
setting IN positions represented by symbol '?'.

</pre>
<DT><B>Example</B><DD>
<pre>
 Pass over.
</pre>
<DT><B>Log</B><DD>
<pre>
 Created in 2004.09.17.
</pre>
</DL>
* @param vals a List Object that stores the the setting IN parameter values.
* @return Precompiled SQL statement.
*/

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

	String prepareUpdate(List vals);
 
/**
* To create a precompiled SQL statement of insert operation(Created in 2004.09.17).
<P><DL>
<DT><B>Comment</B><DD>
<pre>
   Precompiled SQL statement means that it can be used for constructing
the Instance of PreparedStatement Object.

  The argument named "vals", which stores the setting IN parameter 
values, should ensure that the sequence of the elements is fit for the 
setting IN positions represented by symbol '?'.

</pre>
<DT><B>Example</B><DD>
<pre>
 Pass over.
</pre>
<DT><B>Log</B><DD>
<pre>
 Created in 2004.09.17.
</pre>
</DL>
* @param vals a List Object that stores the the setting IN parameter values.
* @return Precompiled SQL statement.
*/

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

	String prepareInsert(List vals);

  
/**
* 取存储对象名（创建于 2004.09.21）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
存储对象名即库表名.
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
* @return 存储对象名.
*/
  
  String getDBName();
  

 
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
  String prepareWhere(List vals);

}

