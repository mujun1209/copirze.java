package triones.bas;

import java.io.FilenameFilter;
import java.io.File;

/**
* <img src="new.gif" width="28" height="11" border="0">文件扩展名过滤类（创建于 2002.12.13）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
根据文件扩展名过滤指定文件夹的文件。
</pre>
<DT><B>使用说明：</B><DD>
<pre>
略
</pre>
<DT><B>名词解释：</B><DD>
<pre>
文件扩展名：文件名最后1个“.”之后的字母。
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
* @version	2002.12.13
*/
public class FileExtendNameFilter implements FilenameFilter
{	private String[] _FileExtendName;
	

	
/**
* 构造方法（创建于 2002.12.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用默认的扩展名：“*”
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
* @see #FileExtendNameFilter(String[])
*/
	public FileExtendNameFilter()
	{	_FileExtendName = new String[] {".*"};
	}
/**
* 构造方法（创建于 2002.12.13）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
采用指定的扩展名。
如要设定无扩展名类型的文件直接用""。
如要设定所有类型的文件则用"*"。
</pre>
<DT><B>示例：</B><DD>
<pre>
File dir = new File("e:\\");

//设定有效文件为xml,无扩展名的以及zip类型的文件
FilenameFilter filter = new FileExtendNameFilter(StringMethod.toArray("xml,,ZIP",','));

String[] ls_file = dir.list(filter);

for(int i=0;i＜ls_file.length;i++)
	System.out.println(ls_file[i]);
	
//执行效果如下：
-------------------------------------
vssver
test3.xml
test1.xml
test2.xml
test.xml
wyq.xml
Beyondbit 产品安全策略与方案.zip
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2002.12.13.
</pre>
</DL>
* @see #FileExtendNameFilter()
*/
	public FileExtendNameFilter(String[] fileExtendName)
	{ _FileExtendName = new String[fileExtendName.length];
		for(int i=0;i<fileExtendName.length;i++)
			_FileExtendName[i] = "." + fileExtendName[i].toUpperCase();
	}
	

	
/**
* 基于FilenameFilter的接口实现（创建于 2002.12.13）.
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
创建于 2002.12.13.
</pre>
</DL>
* @param dir 文件目录.
* @param name 文件名.
* @return 是否为匹配文件.
* @see FilenameFilter#accept(File,String)
*/
	public boolean accept(File dir,String name)
	{	for(int i=0;i<_FileExtendName.length;i++)
			if(new File(dir + File.separator + name).isFile())
			{	if(_FileExtendName[i].equals(".*")) return true;
				if(_FileExtendName[i].equals(".")) 
				{	if(name.indexOf('.') < 0) return true;
				}
				if(name.toUpperCase().endsWith(_FileExtendName[i]))	return true;
			}
	
		return false;
	}
}


