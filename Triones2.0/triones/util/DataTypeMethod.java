package triones.util;

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
* @param type 转型的基本类型.
* @return 转型后的对象.
*/
	public static Object convert(Object val,Class type)
	{	if(val!=null)
		{	if(type.equals(double.class)||type.equals(Double.class)) return new Double(val.toString());
			else if(type.equals(int.class)||type.equals(Integer.class)) return new Integer(val.toString());		
			else if(type.equals(float.class)||type.equals(Float.class)) return new Float(val.toString());
			else if(type.equals(long.class)||type.equals(Long.class)) return new Long(val.toString());
			else if(type.equals(short.class)||type.equals(Short.class)) return new Short(val.toString());						
			else if(type.equals(char.class)||type.equals(Character.class)) return new Character(val.toString().charAt(0));
			else if(type.equals(boolean.class)||type.equals(Boolean.class)) return new Boolean(val.toString());
			else if(type.equals(byte.class)||type.equals(Byte.class)) return new Byte(val.toString());
			//else if(type.equals(java.util.Date.class)) return new Date(
		}
			
		return val;		
	}
  

  
/**
* 判断值是否为0（创建于 2005.05.30）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
判断值类型为数字类型且值为0
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.05.30.
</pre>
</DL>
* @param val 对象值.
* @return true代表值等于0.
*/
  public static boolean isZero(Object val)
  { if(val.getClass()==String.class) return false;
    return (val + "").equals("0");
  }
  
/**
* 转换值数组（创建于 2005.04.10）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字符串值为null时值也是null.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.10.
</pre>
</DL>
* @param vals 字符串数组.
* @param types 类型数组.
* @param asEmpty 用于替换空字符串的对象值.
* @return 值数组.
*/
  public static Object[] convert(String[] vals,Class[] types,Object asEmpty)
  { Object[] lObjs = new Object[vals.length];
    
    for(int i=0;i<vals.length;i++)
    { if(vals==null) lObjs[i] = null;
      else if(vals[i].equals("")) lObjs[i] = asEmpty;
      else lObjs[i] = convert(vals[i],types[i]);
    }
    return lObjs;
  }
  
/**
* 根据类型名称转换对应的对象类型（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param type 类型名称.
* @return 类型对象.
*/
  public static Class toClass(String type)
  { String[] ls_types = {"string","number","datetime","date"};
    Class[] lClasses = {String.class,java.math.BigDecimal.class,java.sql.Timestamp.class,java.util.Date.class};
    
    int li_index = ArrayMethod.findItemIgnoreCase(ls_types,type);
    
    if(li_index>=0) return lClasses[li_index];
    
    return null;
  }
  

  
/**
* 生成对应的类型数组（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param types 类型名称数组.
* @return 类型数组.
*/
  public static Class[] toClasses(String[] types)
  { Class[] lClasses = new Class[types.length];
    for(int i=0;i<types.length;i++)
      lClasses[i] = toClass(types[i]);
    return lClasses;
  }
  
/**
* 生成对应的类型数组（创建于 2005.04.10）.
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
创建于 2005.04.10.
</pre>
</DL>
* @param vals 值数组.
* @return 对应的类型数组.
*/
  public static Class[] getClasses(Object[] vals)
  { Class[] lClasses = new Class[vals.length];
    for(int i=0;i<vals.length;i++)
      lClasses[i] = (vals[i]!=null?vals[i].getClass():null);
      
    return lClasses;
  }
  
/**
* 基本数据类型对应的类（创建于 2003.03.04）.
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
创建于 2003.03.04.
</pre>
</DL>
*/
	private static String [] BASE_CLASS = {"java.lang.Double","java.lang.Long",
													"java.lang.Integer","java.lang.Float",
													"java.lang.Byte","java.lang.Char",
													"java.lang.Boolean","java.lang.Short"};
/**
* 可进行自动转换的数据类型（创建于 2003.03.05）.
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
创建于 2003.03.05.
</pre>
</DL>
*/
	private static String [] CONVERTABLE_TYPE = {"java.lang.Double","java.lang.Long",
													"java.lang.Integer","java.lang.Float",
													"java.lang.Byte","java.lang.Char","java.lang.String",
													"java.lang.Boolean","java.lang.Short",
													"double","long","int","float","byte","char","boolean","short",
													"java.math.BigDecimal","java.sql.Timestamp",
													"java.util.Date"};

/**
* 基本数据类型（创建于 2003.03.04）.
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
创建于 2003.03.04.
</pre>
</DL>
*/
	private static String [] BASE_TYPE = {"double","long",
													"int","float",
													"byte","char",
													"boolean","short"};
													

	
/**
* 比较两个类型是否一致（创建于 2003.10.15）.
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
创建于 2003.10.15.
</pre>
</DL>
* @param type1 类型1.
* @param type2 类型2.
* @param asIndividual 是否认同基本类型，true代表基本类型与相应的类是相等的，false代表不认为两者相等.
*/
	public static boolean equals(Class type1,Class type2,boolean asIndividual)
	{	if(asIndividual)
			return toBaseType(type1).equals(toBaseType(type2));
		else
			return type1.equals(type2);
	}
	

	
/**
* 将基本类型转换为对应的类（创建于 2003.10.15）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果type不是基本类型，则返回该类
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.15.
</pre>
</DL>
* @param type 基本类型.
* @return 与基本类型对应的类或该类本身.
*/
	public static Class toBaseClass(Class type)
	{	if(type.equals(double.class)) return Double.class;
		else if(type.equals(int.class)) return Integer.class;		
		else if(type.equals(float.class)) return Float.class;
		else if(type.equals(long.class)) return Long.class;
		else if(type.equals(short.class)) return Short.class;						
		else if(type.equals(char.class)) return Character.class;
		else if(type.equals(boolean.class)) return Boolean.class;
		else if(type.equals(byte.class)) return Byte.class;
		else return type;
	}
		/*another way
		public static Class toBaseClass(Class type)
		{
			try
			{	int i = ArrayMethod.findItem(BASE_TYPE,type.getName());
				return (i>=0?Class.forName(BASE_CLASS[i]):type);
			}
			catch(ClassNotFoundException e)
			{	return type;//will not happen
			}
		}
		*/
		

	
/**
* 将类转换为对应的基本类型（创建于 2003.10.15）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果找不到与之对应的基本类型，则返回该类
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.10.15.
</pre>
</DL>
* @param type 类.
* @return 与类相应的基本类型或该类本身.
* @see #toBaseClass(Class)
*/
	public static Class toBaseType(Class type)
	{	if(type.equals(Double.class)) return double.class;
		else if(type.equals(Integer.class)) return int.class;		
		else if(type.equals(Float.class)) return float.class;
		else if(type.equals(Long.class)) return long.class;
		else if(type.equals(Short.class)) return short.class;						
		else if(type.equals(Character.class)) return char.class;
		else if(type.equals(Boolean.class)) return boolean.class;
		else if(type.equals(Byte.class)) return byte.class;
		else return type;
	}
  

  
/**
* 判断类型是否为基本数据类型（创建于 2005.05.30）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
基本数据类型指:long/int/double/boolean/char/short/byte
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.05.30.
</pre>
</DL>
* @param type 类型.
* @return true代表是基本数据类型.
*/
  public static boolean isBaseType(Class type)
  { if(type.equals(double.class)) return true;
		else if(type.equals(int.class)) return true;		
		else if(type.equals(float.class)) return true;
		else if(type.equals(long.class)) return true;
		else if(type.equals(short.class)) return true;						
		else if(type.equals(char.class)) return true;
		else if(type.equals(boolean.class)) return true;
		else if(type.equals(byte.class)) return true;
		else return false;
  }
  /**
	 * 将字符串转换为指定类型的对象实例（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	目前仅支持以下类型
	java.lang.Character/char
	java.lang.Double/double
	java.lang.Integer/int
	java.lang.Float/float
	java.lang.Boolean/boolean
	java.lang.Long/long
	java.lang.Short/short
	java.lang.Byte/byte
	java.math.BigDecimal
	
	日期型的format要求：yyyy-mm-dd hh:mm:ss.fffffffff 	
	java.util.Date
	java.sql.Timestamp 
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//d 的值为11.05
	Double d = (Double)StringMethod.convert("11.05",double.class);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.05.
	</pre>
	</DL>
* @param text 字符串值.
* @param objType 要转换的对象类型.
* @return 根据字符串值转换的对象实例.
*/
	public static Object convert(String text,Class objType)
	{	if(text==null) return text;
    	if(objType.equals(triones.bas.None.class)) return text;
    	if(objType.equals(String.class)) return text;
		if(objType.equals(Character.class)||objType.equals(char.class)) return new Character(text.charAt(0));
		if(objType.equals(Double.class)||objType.equals(double.class)) return new Double(text);
		if(objType.equals(Integer.class)||objType.equals(int.class)) return new Integer(text);
		if(objType.equals(Float.class)||objType.equals(float.class)) return new Float(text);
		if(objType.equals(Long.class)||objType.equals(long.class)) return new Long(text);
		if(objType.equals(Boolean.class)||objType.equals(boolean.class)) return new Boolean(text);
		if(objType.equals(Short.class)||objType.equals(short.class)) return new Short(text);
		if(objType.equals(Byte.class)||objType.equals(byte.class)) return new Byte(text);
		if(objType.equals(java.math.BigDecimal.class)) return new java.math.BigDecimal(text);
		if(objType.equals(java.sql.Timestamp.class)) return java.sql.Timestamp.valueOf(text);
		try
		{	if(objType.equals(java.util.Date.class)) return java.text.DateFormat.getDateInstance().parse(text);
		}
		catch(java.text.ParseException e)
		{	throw new IllegalArgumentException(text + " can't be parsed in DateFormat");
		}
		
		throw new UnsupportedOperationException("Unsupported class type（不支持的类型转换）: " + objType);
	}	
  
 	/**
	 * 判断域是否为当前支持的可自动转换的变量类型（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	如果是当前支持的可自动转换的变量类型，则可以使用convert(String,Class)
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
* @param objType 对象类型.
* @return true代表是当前支持的可自动转换的变量类型.
	@see #convert(String,Class)
*/
	public static boolean isConvertableType(Class objType)
	{	return (ArrayMethod.findItem(CONVERTABLE_TYPE,objType.getName())>=0);
	}

}

