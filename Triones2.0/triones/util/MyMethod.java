package triones.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

/**
* 未归类的方法（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
尚未归类的静态方法，今后可能归到其他类中，因此不建议调用。
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
public class MyMethod
{

/** Given a string, this method replaces all occurrences of
   *  '<' with '&lt;', all occurrences of '>' with
   *  '&gt;', and (to handle cases that occur inside attribute
   *  values), all occurrences of double quotes with
   *  '&quot;' and all occurrences of '&' with '&amp;'.
   *  Without such filtering, an arbitrary string
   *  could not safely be inserted in a Web page.
   */


	
/**
* 将html中无法正确显示的字符替换成对应的字码（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
例如：
  '<' => '&lt;'
	'>' => '&gt;'
	空格 => '&quot;'
	'&' => '&amp;'
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04 - 王虎友情提供.
</pre>
</DL>
* @param input 未调整过的字符串.
* @return 经过转换后的可在html中正常显示的字符串.
*/
	public static String filter(String input) 
	{
		// add by zord @ 2002/1/26
			if (null == input) return "&nbsp;";
		// end of add
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for(int i=0; i<input.length(); i++) {
		  c = input.charAt(i);
		  if (c == '<') {
		    filtered.append("&lt;");
		  } else if (c == '>') {
		    filtered.append("&gt;");
		  } else if (c == '"') {
		    filtered.append("&quot;");
		  } else if (c == '&') {
		    filtered.append("&amp;");
		  } else if(c == '\n') {
		    filtered.append("<br>");
		  } else {
		    filtered.append(c);
		  }
		}
		return(filtered.toString());
	}

	
/**
* 深拷贝一个对象（创建于 2003.03.04）.
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
创建于 2003.03.04 - 王虎友情提供.
</pre>
</DL>
* @param obj 被复制的对象
* @return 成功返回复制出的对象, 异常或失败返回 null
*/
	public static Object deepClone(Object obj)
	{ try
		{	ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			return ois.readObject();	
		 }
		 catch(ClassNotFoundException e)
		 { return null;//无法找到被序列化的对象的类型.Class of a serialized object cannot be found
		 }
		 catch(IOException e)
		 { return null;//本异常不会发生.
		 }
	}	
	

	
/**
* 取指定文件的配置信息（创建于 2006.01.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
根据配置文件名取配置属性,文件名中不包含路径
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2006.01.24.
</pre>
</DL>
* @param cls 发布对象类.
* @param fileName 配置文件名,不含路径.
* @return 含配置信息的属性对象.
* @exception IOException 找不到指定文件.
*/
	public static Properties loadProperties(Class cls,String fileName) throws IOException
	{
	    Properties p = new Properties();
		p.load(cls.getClassLoader().getResourceAsStream(fileName));
	    return p;
	}

	
	
}

