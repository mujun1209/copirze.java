package triones;

import java.util.*;
import java.io.*;


                       /* \\|///
                       \\  - -  //
                        (  @ @  )
**********************oOOo-(_)-oOOo************
*                                             *
*  Publish Date: 2004.06.11   Wu yongqing    *
*                                          *
*  We work because we wake               *
*                             Oooo        *
************************oooO**(   )*********
                       (   )   ) /
                        \ (   (_/
                         */
import triones.sql.*;
import triones.util.*;
import triones.frame.*;
import triones.xml.*;
import triones.bas.MyException;

/**
* 调试类（创建于 2003.01.05）.
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
当前版本信息：3.0（正式版） 2004.02.06 - Wu YongQing
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.02.28
</pre>
</DL>
*/
	public static String VERSION = "3.02（正式版） 2006.02.23 - Wu YongQing";

	
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
* 打印RecordSet（创建于 2005.04.17）.
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
创建于 2005.04.17.
</pre>
</DL>
* @param rs 记录集对象.
*/
	public static void println(RecordSet rs)
	{ 	if(IFDebug) 
		{ System.out.print("rowcount:" + rs.rowCount());
		  System.out.println("\n---------------------------------------------------");
		  for(int k=1;k<=rs.colCount();k++)
		    System.out.print(rs.getColName(k));
		  System.out.println("\n---------------------------------------------------");
		  for(int i=1;i<=rs.rowCount();i++)
		  { for(int j=1;j<=rs.colCount();j++)
		      System.out.print(rs.getItemValue(i,j));
		    System.out.println("\n--------------------------------------------------");
		  }
		}
	}
  

  
/**
* 打印记录（创建于 2005.04.17）.
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
创建于 2005.04.17.
</pre>
</DL>
* @param rc 记录对象.
*/
	public static void println(Record rc)
	{ 	if(IFDebug) 
		{ System.out.println("\n--------------------RECORD-----------------");
		  
		  for(int k=1;k<=rc.fieldCount();k++)
		    System.out.println(rc.getFieldName(k) + "=" + rc.getFieldValue(k));
		  
		  System.out.println("\n--------------------RECORD-----------------");
		
		}
	}


		
/**
* 生成Ezx快捷键中文编码（创建于 2006.09.04）.
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
创建于 2006.09.04.
</pre>
</DL>
*/
 	public static void buildEzxShortcut()
		throws FileNotFoundException,UnsupportedEncodingException,IOException
	{ 	String[] lComments = {"移动网点","移动仓储","司法矫正","保险代理","友邦保险"};

		FileOutputStream lFile = new FileOutputStream("e:\\EzxShortcut");

		for(int i=0;i<lComments.length;i++)
		{
			String lLine = lComments[i] + "=[";
			lFile.write(lLine.getBytes());
			lLine = toEzxShortcutLabel(lComments[i]);
			lFile.write(lLine.getBytes());
			lLine = "]\r\n";
			lFile.write(lLine.getBytes());			
		}
		
		lFile.close();		
	}	

	
/**
* EZX快捷键转码（创建于 2006.09.04）.
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
创建于 2006.09.04.
</pre>
</DL>
* @param name 快捷键中文名称.
* @return Ezx快捷键显示文本.
* @exception UnsupportedEncodingException 不支持的编码格式.
*/
	static String toEzxShortcutLabel(String name) 
		throws UnsupportedEncodingException 
	{
		String lLabel = StringMethod.encode(name,"utf-8","iso-8859-1");
		return StringMethod.encode(lLabel,"utf-8","gbk");
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
	{	try
		{	
			String a = "连接互联网";
			//TestAES.run("1234567890123456","吴勇庆");
			//println(StringMethod.encode(a,"utf-8","gbk"));
			buildEzxShortcut();
		
		}
		catch(Exception e)
		{	//println(e);
      		MyException.printStackTrace(e);
		}
		
	}
}

