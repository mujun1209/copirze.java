package triones.bas;

import javax.servlet.ServletOutputStream;
import javax.servlet.jsp.JspWriter;

import java.io.PrintStream;
import java.io.PrintWriter;


/**异常祖先类（创建于 2002.03.29）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
无
</pre>
<DT><B>使用说明：</B><DD>
<pre>
无
</pre>
<DT><B>名词解释：</B><DD>
<pre>
无
</pre>
<DT><B>注意事项：</B><DD>
<pre>
无
</pre>
<DT><B>展望未来：</B><DD>
<pre>
无
</pre>
<DT><B>联系方式：</B><DD>
<pre>
 email：wyq@wondersgroup.com 
 电  话:021-64950118-2346
</pre>
</DL>
*@author	吴勇庆
*@version 2002.03.29
*/
public class MyException extends Exception
{	/**异常信息（创建于 2002.03.29）.*/
  String _Text;
	/**异常代码（创建于 2002.03.29）.*/
  int _Code;
	/**异常标题（创建于 2002.03.29）.*/
  String _Title = "应用错误";
	
/**
* 构造方法（创建于 2002.03.29）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
设定字符串格式的提示信息。
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.03.29.
</pre>
</DL>
* @param text 提示信息.
* @see #MyException()
*/
  public MyException(String text)
  { super(text);
    _Text = text;
  }
	
/**
* 构造方法（创建于 2002.03.29）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
无
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.03.29.
</pre>
</DL>
* @see #MyException(String)
*/
  public MyException()
  { super();
  }
	

/**
* 出错提示（创建于 2002.03.29）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
无
</pre>
<DT><B>示例：</B><DD>
<pre>
无
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.03.29.
</pre>
</DL>
* @return 提示信息.
*/
  public String toString()
  { return _Text;
  }
	

	/**
	 * 打印异常跟踪信息（创建于 2002.03.29）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在JSP开发中，可以利用本功能将错误输出到页面。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.03.29.
	</pre>
	</DL>
* @param out 页面输出流.
*/
	public void printStackTrace(ServletOutputStream out)
	{ super.printStackTrace(new PrintStream(out));		
	}
	

	/**
	 * 打印异常跟踪信息（创建于 2002.08.27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在JSP开发中，可以利用本功能将错误输出到页面。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.08.27.
	</pre>
	</DL>
* @param e 异常.
* @param out ServletOutputStream输出流.
* @see #printStackTrace(Exception,JspWriter)
*/
	public static void printStackTrace(Exception e,ServletOutputStream out)
	{ e.printStackTrace(new PrintStream(out));		
	}

	/**
	 * 打印异常跟踪信息（创建于 2002.03.29）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在JSP开发中，可以利用本功能将错误输出到页面。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.03.29.
	</pre>
	</DL>
* @param out 页面打印流.
*/
	public void printStackTrace(JspWriter out)
	{	super.printStackTrace(new PrintWriter(out,out.isAutoFlush()));
	}
 

	/**
	 * 打印异常跟踪信息（创建于 2002.08.27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在JSP开发中，可以利用本功能将错误输出到页面。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.08.27.
	</pre>
	</DL>
* @param e 异常.
* @param out JSP输出流.
* @see #printStackTrace(Exception,ServletOutputStream)
*/
	public static void printStackTrace(Exception e,JspWriter out)
	{	e.printStackTrace(new PrintWriter(out,out.isAutoFlush()));
	}
	
	/**
	 * 打印异常跟踪信息（创建于 2002.08.27）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在JSP开发中，可以利用本功能将错误输出到页面。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2002.08.27.
	</pre>
	</DL>
* @param e 异常.
* @see #printStackTrace(Exception,JspWriter)
*/
	public static void printStackTrace(Exception e)
	{	e.printStackTrace();
	}
}
