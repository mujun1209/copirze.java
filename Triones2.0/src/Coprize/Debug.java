package Coprize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2011.12.14   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */


/**
* 调试类（创建于 2003.01.05）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
本类主要提供用于Coprize内部调试的方法。
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
* @version	2003.01.05
*/
public class Debug
{	
/**
* 是否打开调试属性（创建于 2003.01.05）.
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
创建于 2003.01.05
</pre>
</DL>
*/
	public static boolean IFDebug = true;
	
/**
* triones的版本信息（创建于 2003.01.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
包含triones的版本号、发布日期、作者等信息。
当前版本信息：0.01（正式版） 2011.12.14 - Wu YongQing
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2011.12.14
</pre>
</DL>
*/
	public static String VERSION = "0.01（正式版） 2011.12.14 - Wu YongQing";

	
/**
* 打印对象信息（创建于 2003.01.05）.
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
创建于 2003.01.05
</pre>
</DL>
* @param obj 对象实例.
*/
	public static void println(Object obj)
	{	if(IFDebug) System.out.println("<DebugObject>" + obj + "</DebugObject>");
	}
/**
* 打印列表中的对象信息（创建于 2003.01.05）.
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
创建于 2003.01.05
</pre>
</DL>
* @param objs 列表实例.
*/
	public static void println(List objs)
	{	if(IFDebug) System.out.println("<DebugList>Number of List or Array is " + objs.size());
	
		for(int i=0;i<objs.size();i++)
			println(objs.get(i));
		
		if(IFDebug) System.out.println("</DebugList>");
	}
	
/**
* 打印数组中的对象信息（创建于 2003.01.05）.
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
创建于 2003.01.05
</pre>
</DL>
* @param objs 对象数组.
*/
	public static void println(Object[] objs)
	{	println(Arrays.asList(objs));
	}


/**
* 打印整数（创建于 2004.06.11）.
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
创建于 2004.06.11.
</pre>
</DL>
* @param i 整型.
*/
	public static void println(long i)
	{	if(IFDebug) System.out.println("<Debuglong>" + i + "</Debuglong>");
	}


/**
* 测试用主程序（创建于 2004.06.24）.
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
创建于 2004.06.24.
</pre>
</DL>
* @param args 参数数组.
*/
	public static void main(String[] args)
	{	
		List list = new ArrayList();
		
			list.add("11");
			list.add(0,"22");
			list.add(1,"33");
			list.add(1,"44");
			//list.add(-2,"55");

		
		System.out.println(5>>1);
		Iterator<String> it = list.iterator();
	}
}

