package triones.app;


/**
* <img src="new.gif" width="28" height="11" border="0">计数器接口（创建于 2002.12.13）.
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
* @version	2002.12.13
*/
public interface Counter
{	
/**
* 计数器累加（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @return 累加后的计数值.
*/
	public long next();


/**
* 当前计数器的值（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
*/
	public long current();


/**
* 计数器清0（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
*/
	public void reset();
}

