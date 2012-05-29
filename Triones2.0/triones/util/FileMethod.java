package triones.util;

import java.io.File;
import java.io.IOException;


/**
* 文件函数库（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供与文件操作相关的静态方法。
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
public class FileMethod
{
	

/**
* 取文件的扩展名（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
文件扩展名指文件名中在"."之后的名称，如果没有则返回空字符串.
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
* @param fileName 文件.
* @return 文件扩展名.
*/
	static String nameGetExtension(String fileName)
	{ int li_pos = fileName.lastIndexOf('.');
		String ls_file;
		
		if(li_pos > 0) 
			ls_file = fileName.substring(li_pos + 1);
		else 
			ls_file = "";
			
		return ls_file;
	}


/**
* 取不含扩展名的文件名（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
扩展名指
</pre>
<DT><B>示例：</B><DD>
<pre>
文件扩展名指文件名中在"."之后的名称，如果没有则返回空字符串.
文件名不含所在路径的信息.
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param fileName 文件.
* @return 不含扩展名的文件名.
*/
  static String nameIgnoreExtension(String fileName)
	{ int li_pos = fileName.lastIndexOf('.');
		String ls_file;
		
		if(li_pos > 0) 
			ls_file = fileName.substring(0,li_pos);
		else
			ls_file = fileName;
			
		return ls_file;
	}


/**
* 获取当前的文件存取路径（创建于 2003.03.04）.
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
* @return 路径表达式.
*/
	public static String currentDir()		
	{	try
		{	return (new File(".")).getCanonicalPath();
		}
		catch(IOException e)
		{	return	null;
		}
	}


  
/**
* 取文件名,不包含扩展名部分（创建于 2005.04.17）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

System.out.println(FileMethod.getNameIgnoreExtension(new File("E:\\t_user_info.properties")));

...

执行结果如下：
-------------------------------------------
t_user_info


</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2005.04.17.
</pre>
</DL>
* @param file 文件对象.
* @return 不包含扩展名的文件名.
*/
  public static String getNameIgnoreExtension(File file)
  {return nameIgnoreExtension(file.getName());
  }
}

