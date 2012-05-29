package triones.util;

import javax.servlet.ServletRequest;
import java.lang.reflect.*;
import java.util.NoSuchElementException;

/**ServletRequest函数库（创建于 2003.03.05）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供对ServletRequest操作的静态方法.
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
 电  话:021-68672222-2346
</pre>
</DL>
*@author	吴勇庆
*@version 2003.03.05
*/
class ServletRequestMethod
{	

	/**
	 * 从请求对象中获取指定参数的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @param objType 参数类型.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static Object getObject(ServletRequest request,String name,Class objType)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return DataTypeMethod.convert(ls_val,objType);
	}
	

	/**
	 * 从请求对象中获取指定参数的int类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static int getInt(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return StringMethod.toInt(ls_val);
	}
	/**
	 * 从请求对象中获取指定参数的String类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static String getString(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return ls_val;
	}
	/**
	 * 从请求对象中获取指定参数的double类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static double getDouble(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return StringMethod.toDouble(ls_val);
	}
	/**
	 * 从请求对象中获取指定参数的float类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static float getFloat(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return StringMethod.toFloat(ls_val);
	}
		/**
	 * 从请求对象中获取指定参数的long类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static long getLong(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return StringMethod.toLong(ls_val);
	}
		/**
	 * 从请求对象中获取指定参数的short类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static short getShort(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return StringMethod.toShort(ls_val);
	}
		/**
	 * 从请求对象中获取指定参数的byte类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static byte getByte(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return StringMethod.toByte(ls_val);
	}
		/**
	 * 从请求对象中获取指定参数的char类型的值（创建于 2003.03.05）.
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
	创建于 2003.03.05.
	</pre>
	</DL>
* @param request 请求对象.
* @param name 参数名称.
* @return 参数值.
  @throws NoSuchElementException 参数名称不存在.
*/
	public static char getChar(ServletRequest request,String name)
	{ String ls_val = request.getParameter(name);
		if(ls_val==null) throw new NoSuchElementException("Invalid Element: " + name);
		return StringMethod.toChar(ls_val);
	}

}

