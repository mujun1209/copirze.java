package triones.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import javax.servlet.ServletRequest;

import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.Hashtable;

/**
* 为反射机制提供的函数库（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供与反射有关的静态方法.
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
public final class ReflectMethod
{	
	
/**
* 私有构造方法（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
用于指定抽象类.
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
	private ReflectMethod(){}

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
	private static String [] BASE_TYPE = {"java.lang.Double","java.lang.Long",
													"java.lang.Integer","java.lang.Float",
													"java.lang.Byte","java.lang.Character",
													"java.lang.Boolean","java.lang.Short"};
	

/**
* 取对象数组对应的类型数组（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
当asIndividual = true时,将8中基本类型对应的Class以基本类型.class方式返回.
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
* @param objs 想要获取类的对象数组.
* @param asIndividual 是否将8种基本变量类型对应的类转换为基本变量类型.
* @return 类型数组.
*/
	public static Class[] getClasses(Object[] objs,boolean asIndividual)
	{	Class[] lClasses = new Class[objs.length];
		
		int li_type;
	
		for(int i=0;i<objs.length;i++)  
		{	lClasses[i] = objs[i].getClass();
			if(asIndividual)
			{	//li_type = ArrayMethod.findItemIgnoreCase(TYPE_BASE,lClasses[i].getName());
				li_type = ArrayMethod.findItem(BASE_TYPE,lClasses[i].getName());
				switch(li_type)
				{	case 0:	lClasses[i] = double.class;break;
				 	case 1:	lClasses[i] = long.class;break;
					case 2: lClasses[i] = int.class;break;
					case 3:	lClasses[i] = float.class;break;
					case 4:	lClasses[i] = byte.class;break;
					case 5:	lClasses[i] = char.class;break;
					case 6:	lClasses[i] = boolean.class;break;
					case 7:	lClasses[i] = short.class;break;
				}
			}
		}
				
		return lClasses;
	}
		

/**
* 动态执行对象提供的公共方法（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	String s = "abc";
	//示例中 i = 1
	int i = ((Integer)ReflectMethod.call(s,"indexOf",new Object[] {"bc"})).intValue();	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param	obj	方法所在的对象
* @param	method	方法名
* @param	args	参数列表,如果没有参数则置 null
* @return	方法的返回值,如果没有返回值则返回	null
* @exception NoSuchMethodException 不存在的方法.
* @exception IllegalAccessException 调用了非公共方法.
* @exception InvocationTargetException 调用参数类型或个数不匹配.
*/
	public static Object call(Object obj,String method,Object[] args)
 					throws NoSuchMethodException,IllegalAccessException,InvocationTargetException					
	{	Method lMethod;
		Class[] lc_argsType;
		Object lo_ret;
		
		lc_argsType = getClasses(args,true);
		
		lMethod = obj.getClass().getMethod(method,lc_argsType);
			
		return lMethod.invoke(obj,args);	
	}

/**
* 动态执行对象的不含参数的公共方法（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
  String s = "abc";
	//示例中 i = 3
	int i = ((Integer)ReflectMethod.call(s,"length")).intValue();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param obj 方法所在的对象.
* @param method 方法名.
* @return 方法的返回值,如果没有返回值则返回 null.
* @exception NoSuchMethodException 不存在的方法.
* @exception IllegalAccessException 调用了非公共方法.
* @exception InvocationTargetException 调用参数类型或个数不匹配.
*/
	public static Object call(Object obj,String method)
		throws NoSuchMethodException,IllegalAccessException,InvocationTargetException
	{	Method lMethod;
		
		lMethod = obj.getClass().getMethod(method,null);
		
		return lMethod.invoke(obj,null);
	}


	
/**
* 动态执行类的公共静态方法（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	int i = -5;
	//示例中 k = 5
	int k = ((Integer)ReflectMethod.call(Math.class,"abs",new Object[]{new Integer(i)})).intValue();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param cls 方法所在的类.
* @param method 方法名.
* @param args 参数列表,如果没有参数则置 null.
* @return 方法的返回值,如果没有返回值则返回	null.
* @exception NoSuchMethodException 不存在的方法.
* @exception IllegalAccessException 调用了非公共方法.
* @exception InvocationTargetException 调用参数类型或个数不匹配.
*/
	public static Object call(Class cls,String method,Object[] args) 
		throws NoSuchMethodException,IllegalAccessException,InvocationTargetException
	{	Method lMethod;
		Class[] lc_argsType;
		
		lc_argsType = getClasses(args,true);
		 
		lMethod = cls.getMethod(method,lc_argsType);
		
		return lMethod.invoke(null,args);		
	}


/**
* 动态执行类的不含参数的公共静态方法（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	//示例中 d = 0.8397682081044828
	double d = ((Double)ReflectMethod.dynamicMethod(Math.class,"random")).doubleValue();
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param cls 方法所在的类.
* @param method 方法名.
* @return 方法的返回值,如果没有返回值则返回	null.
* @exception NoSuchMethodException 不存在的方法.
* @exception IllegalAccessException 调用了非公共方法.
* @exception InvocationTargetException 调用参数类型或个数不匹配.
*/
	public static Object call(Class cls,String method)
		throws NoSuchMethodException,IllegalAccessException,InvocationTargetException
	{	Method lMethod;
		
		lMethod = cls.getMethod(method,null);
		
		return lMethod.invoke(null,null);
	}
	

/**
* 判断域的值是否为null（创建于 2003.03.04）.
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
创建于 2003.03.04.
</pre>
</DL>
* @param obj 包含域的对象.
* @param f 要取值的域.
* @return true代表域值为null.
* @exception IllegalArgumentException 域不是对象的属性.
* @see #isNullField(Object,String)
*/
	public static boolean isNullField(Object obj,Field f)
		throws IllegalArgumentException
	{	Object lObject;
		boolean lb_isNull = false;
		
		try
		{	f.setAccessible(true);
			lObject = f.get(obj);		
		}
		catch(NullPointerException e)
		{	lb_isNull = true;
		}
		catch(IllegalAccessException e)
		{//it will not happened
		}
		
		return lb_isNull;
	}

	
/**
* 判断域的值是否为null（创建于 2003.03.04）.
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
创建于 2003.03.04.
</pre>
</DL>
* @param obj 包含域的对象.
* @param f 要取值的域名.
* @return true代表域值为null.
  @exception NoSuchFieldException 不存在的域名.
* @exception IllegalArgumentException 本异常不会发生.
* @see #isNullField(Object,String)
*/
	
	public static boolean isNullField(Object obj,String f)
		throws NoSuchFieldException,IllegalArgumentException
	{	Field lField = obj.getClass().getField(f);
		return isNullField(obj,lField);		
	}
	
	
/**
* 取类的名（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与Class.getName()类似.
可以忽略包名.
</pre>
<DT><B>示例：</B><DD>
<pre>
	Properties p = new Properties();
	//示例中 s = "Properties"
	String s = ReflectMethod.getClassName(p.getClass(),true);	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param cls 类.
* @param ignorePackage 是否忽略包名，true代表忽略.
* @return 类名.
  @see #getClassName(Class)
	@see #getClassName(Object)
	@see #getClassName(Object,boolean)
*/
	public static String getClassName(Class cls,boolean ignorePackage)
	{	int li_pos;
		String ls_className;
	
		ls_className = cls.getName();
		
		if(ignorePackage)
		{	li_pos = ls_className.lastIndexOf(".");
		
			if(li_pos >=0 )
				ls_className = ls_className.substring(li_pos + 1);
		}
	
		return ls_className;
	}
	

/**
* 取类的名（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与Class.getName()相同.
不忽略包名。
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
* @param cls 类.
* @return 类名.
  @see #getClassName(Class,boolean)
	@see #getClassName(Object)
	@see #getClassName(Object,boolean)
*/
	public static String getClassName(Class cls)
	{	return getClassName(cls,false);
	}


	
/**
* 取对象的类名（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
不忽略包名.
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
* @param obj 对象.
* @return 对象的类名.
* @see #getClassName(Object,boolean)
  @see #getClassName(Class)
	@see #getClassName(Class,boolean)
*/
	public static String getClassName(Object obj)
	{	return getClassName(obj,false);
	}

/**
* 取对象的类名（创建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	可以忽略包名.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	Properties p = new Properties();
	//示例中 s = "Properties"
	String s = ReflectMethod.getClassName(p,true);	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.04.
	</pre>
	</DL>
* @param obj 对象实例.
* @param ignorePackage 是否忽略包名.
* @return 类名.
* @see #getClassName(Class,boolean)
  @see #getClassName(Class)
	@see #getClassName(Object)
*/
	
	public static String getClassName(Object obj,boolean ignorePackage)
	{	return getClassName(obj.getClass(),ignorePackage);
	}
	

/**
* 判断域是否为公共的（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
目前的规则是:	当且仅当public时返回true
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
* @param f 域.
* @return true代表是公共域.
*/
	public static boolean isPublicField(Field f)
	{	return f.getModifiers() == Modifier.PUBLIC;
	}
	


	/**
	 * 为对象指定域赋值（创建于 2003.03.04）.
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
	创建于 2003.03.04.
	</pre>
	</DL>
* @param obj 对象实例.
* @param fieldName 域名.
* @param val 赋给域的值.
* @exception NoSuchFieldException 不存在的域名.
* @exception IllegalAccessException 该域不允许被赋值,例如final变量.
*/
	public static void setField(Object obj,String fieldName,Object val)
		throws NoSuchFieldException,IllegalAccessException
	{ Field lField = obj.getClass().getField(fieldName);
		lField.setAccessible(true);
		lField.set(obj,val);	
	}
	

	/**
	 * 创建对象实例（创建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在initVals中定义的属性名如果不存在于对象的属性清单中，则值被忽略。
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
* @param cls 对象类型.
* @param initVals 为对象提供初始化值的载体.
* @return 对象实例.
* @exception InstantiationException 对象实例缺少公共的不含参数的构造方法.
* @exception IllegalAccessException 对象属性不允许赋值，例如final变量.
*/
	
	public static Object newInstance(Class cls,Hashtable initVals)
		throws InstantiationException,IllegalAccessException
	{	Object lObj = cls.newInstance();
		Field[] lFields = cls.getFields();
		String ls_fieldName;
		Object lo_val;
		
		for(int i=0;i<lFields.length;i++)
		{	ls_fieldName = lFields[i].getName();
			if(initVals.containsKey(ls_fieldName))
			{	lo_val = initVals.get(ls_fieldName);
				lFields[i].setAccessible(true);
				lFields[i].set(lObj,lo_val);	
			}				
		}
		
		return lObj;
	}
	
	/**
	 * 创建对象实例（创建于 2003.03.04）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在initVals中定义的属性名如果不存在于对象的属性清单中，则值被忽略。
	可用的属性值的类型是：StringMethod.convert(String,Class)自动转换支持的类型。
	忽略对不存在的或类型自动转换不支持的属性的赋值。
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
* @param cls 对象类型.
* @param initVals 为对象提供初始化值的载体.
* @return 对象实例.
* @exception InstantiationException 对象实例缺少公共的不含参数的构造方法.
* @exception IllegalAccessException 对象属性不允许赋值，例如final变量.
*/
	public static Object newInstance(Class cls,ServletRequest initVals)
		throws IllegalAccessException,InstantiationException
	{ Field[] lFields = cls.getFields();
		String ls_col,ls_val;
		Object lo_val,lObject;
		
		lObject = cls.newInstance();
		
		for(int i=0;i<lFields.length;i++)
			if(DataTypeMethod.isConvertableType(lFields[i].getType()))
			{	ls_col = lFields[i].getName();
				ls_val = initVals.getParameter(ls_col);
				if(ls_val!=null)
				{	lo_val = DataTypeMethod.convert(ls_val,lFields[i].getType());
					lFields[i].setAccessible(true);
					lFields[i].set(lObject,lo_val);	
				}
			}
		
		return lObject;
	}

	/**
	 * 创建对象实例（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	用rs当前行的值生成指定对象.
	在rs中定义的属性名如果不存在于对象的属性清单中，则值被忽略。
	clsAsPrefiex如果为true，则代表用忽略包名的类名为属性名的前缀,即:ClassName_FieldName
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
* @param cls 要生成的对象实例的类型.
* @param rs	记录集.
  @param clsAsPrefiex 是否以类名为前缀.
* @return 当前行的值生成的对象.
* @throws SQLException 无效的列名，检查公共域名是否符合规范。
* @throws IllegalAccessException cls对应类的属性值必须初始化。
* @throws InstantiationException	如果cls不存在，或者是一个抽象类，或者构造函数全都有参数。
*/

	public static Object newInstance(Class cls,ResultSet rs,boolean clsAsPrefiex)
		throws SQLException,IllegalAccessException,InstantiationException
	{	Object lObj,lObjVal;
		Field[] lFields = cls.getFields();
		String ls_column,ls_class;

		ls_class = clsAsPrefiex?getClassName(cls,true) + "_":"";
		lObj = cls.newInstance();
		
		for(int i=0;i<lFields.length;i++)
		{	ls_column = ls_class + lFields[i].getName();
			int li_colID = ResultSetMethod.findColID(rs,ls_column);
			//如果名称长度超过命名长度，则取到命名长度为止
			//ls_column = StringMethod.truncate(ls_column,DBMS.NAMING_LENGTH);
			if(li_colID > 0)
			{	lObjVal = ResultSetMethod.getObject(rs,li_colID,lFields[i].getType());
					//Debug.println(lFields[i].getType() + " :" + lObjectVal);
				lFields[i].setAccessible(true);
				lFields[i].set(lObj,lObjVal);	
			}
		}
		
		return lObj;
	}
	
/**
* 获取类的属性名列表（创建于 2003.03.04）.
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
创建于 2003.03.04.
</pre>
</DL>
* @param cls 类.
* @return 属性名数组.
	@see #getDeclaredFieldNames(Class)
*/
	public static String[] getFieldNames(Class cls)
	{	Field[] f = cls.getFields();
		String[] s = new String[f.length];
		
		for(int i=0;i<f.length;i++)
			s[i] = f[i].getName();
					
		return s;
	}
/**
* 获取类声明的属性名列表（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与getFieldNames的不同之处在于只显示当前类声明的属性，不包括从父类继承的属性。
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
* @param cls 类.
* @return 属性名数组.
	@see #getFieldNames(Class)
*/
	public static String[] getDeclaredFieldNames(Class cls)
	{	Field[] f = cls.getDeclaredFields();
		String[] s = new String[f.length];
		
		for(int i=0;i<f.length;i++)
			s[i] = f[i].getName();
					
		return s;
	}
}

