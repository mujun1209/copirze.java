package triones.bas;

/**<img src="new.gif" width="28" height="11" border="0">调用了未实现的方法的异常（创建于2002.11.21）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
如果被调用的方法还未实现或对某些参数不支持时抛出本异常。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
无
</pre>
<DT><B>名词解释：</B><DD>
<pre>
无
</pre>
<DT><B>注意事项：</B><DD>
<pre>
无
</pre>
<DT><B>展望未来：</B><DD>
<pre>
无
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wyq@wondersgroup.com 
 电  话:021-64950118-2346
</pre>
</DL>
*@author	吴勇庆
*@version 2002.11.21
*/
public class NotImplementedException extends java.lang.RuntimeException
{	
/**
* 构造方法.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
无
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
</DL>
*/
	public NotImplementedException()
	{	super();
	}
/**
* 构造方法.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
设定字符串格式的提示信息。
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
</DL>
* @param s 提示信息.
*/
  public NotImplementedException(String s)
  { super(s);
  }

}

