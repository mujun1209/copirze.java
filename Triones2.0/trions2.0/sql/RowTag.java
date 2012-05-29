package trionesII.sql;


/**
* 行记录类（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
针对二维表中以行为记录的特点，设计本类。
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
* @version	2003.03.12
*/
class RowTag
{	
/**
* 行初始化状态（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
行状态的值可由使用者自行定义，但必须保留0作为默认的初始状态值。
默认的行状态值为0。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
*/
	public static final int INITIAL = 0;

	
/**
* 行状态（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
*/
	private int _Status;
	
/**
* 行指令集（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于生成与行记录相关的SQL指令集。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
*/
	Operator _Operator;
	

	
/**
* 构造方法（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param status 行状态.
* @param com 行指令集.
* @see #RowTag(Operator)
*/
	public RowTag(int status,Operator com)
	{	_Status = status;
		_Operator = com;
	}
/**
* 构造方法（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用默认行状态。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param com 行指令集.
* @see #RowTag(int,Operator)
*/
	public RowTag(Operator com)
	{	_Status = INITIAL;
		_Operator = com;
	}
	

	
/**
* 取行状态（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @return 行状态.
* @see #setStatus(int)
*/
	public int getStatus()
	{	return _Status;
	}
	

	
/**
* 设置行状态（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param status 行状态.
* @see #getStatus()
*/
	public void setStatus(int status)
	{	_Status = status;
	}
	

	
/**
* 取SQL指令集（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @return 指令集.
*/
	public Operator getOperator()
	{	return _Operator;
	}

}

