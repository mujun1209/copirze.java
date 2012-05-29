package triones.bas;


/**
* 不存在（创建于 2003.10.18）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
null代表空值,本对象代表值不存在.
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
* @version	2003.10.18
*/
public class None
{	


/**
* 是否相等（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
* @param val 用于比较的对象.
* @return true代表是值不存在的,false代表是有值的.
*/
	public boolean equals(Object val)
	{	return getClass().equals(val.getClass());
	}


	
/**
* 生成字符串（创建于 2003.10.18）.
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
创建于 2003.10.18.
</pre>
</DL>
* @return 字符串.
*/
	public String toString()
	{	return "None";
	}

}

