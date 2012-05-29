package trionesII.util;

/**
* 8种基本数据类型函数库（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
主要收集与JAVA基本变量类型有关的公共函数。
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
public class DataTypeMethod
{	

	
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:String
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
* @param val 值.
* @return 默认对象值.
*/
	public static String toObject(char val)	
	{	return val + "";
	}
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:Boolean
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
* @param val 值.
* @return 默认对象值.
*/
	public static Boolean toObject(boolean val)
	{	return new Boolean(val);
	}
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:Integer
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
* @param val 值.
* @return 默认对象值.
*/
	public static Integer toObject(int val)
	{	return new Integer(val);
	}
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:Short
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
* @param val 值.
* @return 默认对象值.
*/
	public static Short toObject(short val)
	{	return new Short(val);
	}
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:Long
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
* @param val 值.
* @return 默认对象值.
*/
	public static Long toObject(long val)
	{	return new Long(val);
	}
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:Byte
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
* @param val 值.
* @return 默认对象值.
*/
	public static Byte toObject(byte val)
	{	return new Byte(val);
	}
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:Double
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
* @param val 值.
* @return 默认对象值.
*/
	public static Double toObject(double val)
	{	return new Double(val);
	}
/**
* 将值转换成默认的对象类型（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
默认对象类型为:Float
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
* @param val 值.
* @return 默认对象值.
*/
	public static Float toObject(float val)
	{	return new Float(val);
	}


	
/**
* 根据8种基本元素类型将对象转型（创建于 2003.01.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
使用时需保证对象可被转型！
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.24.
</pre>
</DL>
* @param val 原始对象.
* @param colType 转型的基本类型.
* @return 转型后的对象.
*/
	public static Object convert(Object val,Class colType)
	{	if(val!=null)
		{	if(colType.equals(double.class)||colType.equals(Double.class)) return new Double(val.toString());
			else if(colType.equals(int.class)||colType.equals(Integer.class)) return new Integer(val.toString());		
			else if(colType.equals(float.class)||colType.equals(Float.class)) return new Float(val.toString());
			else if(colType.equals(long.class)||colType.equals(Long.class)) return new Long(val.toString());
			else if(colType.equals(short.class)||colType.equals(Short.class)) return new Short(val.toString());						
			else if(colType.equals(char.class)||colType.equals(Character.class)) return new Character(val.toString().charAt(0));
			else if(colType.equals(boolean.class)||colType.equals(Boolean.class)) return new Boolean(val.toString());
			else if(colType.equals(byte.class)||colType.equals(Byte.class)) return new Byte(val.toString());
			//else if(colType.equals(java.util.Date.class)) return new Date(
		}
			
		return val;		
	}
}

