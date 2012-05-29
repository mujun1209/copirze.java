package trionesII;

import java.util.*;

/**
* 调试类（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
本类主要提供用于trionesII内部调试的方法。
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
* @version	2003.03.04
*/
public class Debug
{	
/**
* 是否打开调试属性（创建于 2003.03.04）.
<DL>
<DT><B>说明：</B><DD>
<pre>
调试属性为true则程序运行时会将调用到的调试内容打印出来。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
*/
	public static boolean IFDebug = true;
	
/**
* trionesII的版本信息（创建于 2003.03.04）.
<DL>
<DT><B>说明：</B><DD>
<pre>
包含trionesII的版本号、发布日期、作者等信息。
当前版本信息：1.0（正式版） 2003.03.20 - Wu YongQing
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
*/
	public static String VERSION = "1.0（正式版） 2003.03.20 - Wu YongQing";

	
/**
* 打印对象信息（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
依赖于对象本身对toString()方法的实现。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param obj 对象实例.
*/
	public static void println(Object obj)
	{	if(IFDebug) System.out.println("<Debug>" + obj);
	}
/**
* 打印列表中的对象信息（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
依赖于对象本身对toString()方法的实现。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param objs 列表实例.
*/
	public static void println(List objs)
	{	if(IFDebug) System.out.println("<Debug begin>Number of List or Array is " + objs.size());
	
		for(int i=0;i<objs.size();i++)
			println(objs.get(i));
		
		if(IFDebug) System.out.println("<Debug end>");
	}
	
/**
* 打印数组中的对象信息（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
依赖于对象本身对toString()方法的实现。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param objs 对象数组.
*/
	public static void println(Object[] objs)
	{	println(Arrays.asList(objs));
	}
	

	static void println(double d)
	{	System.out.println("println(double)" + d);
	}
	static void println(String s)
	{	System.out.println("println(String)" + s);
	}
	static void println(char c)
	{	System.out.println("println(char)" + c);
	}
	static void println(long i)
	{ System.out.println("println(long)" + i);
	}
	static void println(boolean b)
	{ System.out.println("println(boolean)" + b);
	}


}

