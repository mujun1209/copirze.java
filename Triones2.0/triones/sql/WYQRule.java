package triones.sql;


/**
* 默认的SQL映射规则（创建于 2003.03.06）.
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
* @version	2003.03.06
*/
public class WYQRule implements Rule
{		
/**
* 将指令集名转换为数据库表名（创建于 2003.03.06）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库表名一般指表或视图名，也可指存储过程、函数名等。

数据库表名的生成规则是：忽略包名的类名
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
* @param name 对象的类名.
* @return 数据库表名.	 
 	@see #toFieldDBName(String)
*/
	public String toDBName(String name)
	{ int li_pos;
		String ls_name = name;

		li_pos = ls_name.lastIndexOf(".");
		
		if(li_pos >=0 )
			ls_name = ls_name.substring(li_pos + 1);
			
		return ls_name;
	}

	
/**
* 将指令集中的列名转换为数据库表字段名（创建于 2003.03.06）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
数据库表字段名一般指表或视图的列名，也可用于存储过程的参数名等。

数据库表字段名的生成规则是：等于列名
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.06.
</pre>
</DL>
* @param fieldName 对象的属性名.
* @return 数据库对象属性名.
 	@see #toDBName(String)
*/
	public String toFieldDBName(String fieldName)
	{	return fieldName;
	}


}

