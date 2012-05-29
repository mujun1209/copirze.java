package triones.util;

import java.util.ArrayList;
import java.util.List;
import java.io.UnsupportedEncodingException;
import java.lang.Double;
import java.lang.Integer;
import java.lang.Float;
import java.lang.Boolean;
import java.lang.Long;
import java.lang.Short;
import java.lang.Byte;

/**
* 字符串函数库（创建于 2003.03.05）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供对String操作的静态方法.
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
* @version	2003.03.05
*/
public final class StringMethod
{	

/**
* 取字符串中第一次出现大写字母的位置（创建于 2003.03.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
位置从0开始。-1代表字符串中没有大写字母。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.24.
</pre>
</DL>
* @param text 字符串.
* @return 第一次出现大写字母的位置.
*/
public static int indexOfUpper(String text)
{	char[] lChars = text.toCharArray();

	for(int i=0;i<lChars.length;i++)
	{	if('A'<=lChars[i]&&lChars[i]<='Z') return i;
	}
	
	return -1;
}

/**
* 取字符串中第一次出现小写字母的位置（创建于 2003.03.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
位置从0开始。-1代表字符串中没有小写字母。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.24.
</pre>
</DL>
* @param text 字符串.
* @return 第一次出现小写字母的位置.
*/
public static int indexOfLower(String text)
{	char[] lChars = text.toCharArray();

	for(int i=0;i<lChars.length;i++)
	{	if('a'<=lChars[i]&&lChars[i]<='z') return i;
	}
	
	return -1;
}

/**
* 私有构造方法（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
用作抽象类.
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
	private StringMethod(){}
	

/**
* 将字符串中所有出现过的旧子串替换成新子串（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
功能类似于String.replace方法,区别是用字符串而不是字符.
值得注意的是,如果想将"bbb"中的"bb"替换成"aa",结果是"aab".
</pre>
<DT><B>示例：</B><DD>
<pre>
	String s = "id23/id";
	//示例中 v = "val23/val"
	String v = StringMethod.replaceAll(s,"id","val");	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
  *@param text 		原字符串
  *@param oldStr 	旧子串
	*@param newStr	新子串
  *@return 				新字符串
*/
	public static String replaceAll(String text,String oldStr,String newStr)
	{	int li_pos = 0;
		String ls_head,ls_tail,ls_text = "";

		ls_tail = text;

		while(li_pos != -1)
		{   li_pos = ls_tail.indexOf(oldStr);
		    
		    if(li_pos != -1)
				{	ls_head = ls_tail.substring(0,li_pos);
		      ls_tail = ls_tail.substring(li_pos + oldStr.length());
		      ls_text += ls_head + newStr;
		    }else  ls_text += ls_tail;
		}
		
		return ls_text;
	}
	
	
/**
* 将字符串中所有出现过的旧子串替换成新子串（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
新旧字符串为一一对应的.
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
* @param text 原字符串.
* @param oldStr 旧子串.
* @param newStr 新子串.
* @return 新字符串.
*/
	public static String replaceAll(String text,String[] oldStr,String[] newStr)
	{	String ls_text = text;
		for(int i=0;i<oldStr.length;i++)
		{	ls_text = replaceAll(ls_text,oldStr[i],newStr[i]);
		}
		return ls_text;
	}

/**
* 将字符串中所有出现过的旧字符替换成新子串（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
功能类似于String.replace方法,区别是用字符串而不是字符.
</pre>
<DT><B>示例：</B><DD>
<pre>
	String s = "id23/id";
	//示例中 v = "id23*id"
	String v = StringMethod.replaceAll(s,'/',"*");	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 原字符串
* @param oldChar	旧字符
* @param newStr	新子串
* @return 新字符串
*/
	public static String replaceAll(String text,char oldChar,String newStr)
	{	String ls_oldStr = "" + oldChar;
		return replaceAll(text,ls_oldStr,newStr);
	}

/**
* 将字符串中所有出现过的旧子串替换成新字符（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
功能类似于String.replace方法,区别是用字符串而不是字符.
</pre>
<DT><B>示例：</B><DD>
<pre>
	String s = "id23/id";
	//示例中 v = "#23/#"
	String v = StringMethod.replaceAll(s,"id",'#');	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 原字符串
* @param oldStr	旧字符
* @param newChar 新子串
* @return 新字符串
*/
	public static String replaceAll(String text,String oldStr,char newChar)
	{	String ls_newStr = "" + newChar;
		return replaceAll(text,oldStr,ls_newStr);
	}
	
/**
* 将字符串中所有出现过的旧字符替换成新字符（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
功能相当于String.replace方法
</pre>
<DT><B>示例：</B><DD>
<pre>
	String s = "id23/id";
	//示例中 v = "id23#id"
	String v = StringMethod.replaceAll(s,'/','#');	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 原字符串
* @param oldChar 旧字符
* @param newChar 新子串
* @return 新字符串
*/
	public static String replaceAll(String text,char oldChar,char newChar)
	{	return text.replace(oldChar,newChar);
	}
	
/**
* 用新字符替换原字符串中第一次出现的旧字符（创建于 2003.03.05）.
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
* @param text 原字符串.
* @param oldChar 旧字符.
* @param newChar 新字符.
* @return 新字符串.
*/
	public static String replace(String text,char oldChar,char newChar)
	{	char[] lc = text.toCharArray();
		
		for(int i=0;i<lc.length;i++)
			if(lc[i]==oldChar)
			{	lc[i] = newChar;
				break;
			}
		
		return String.copyValueOf(lc);
	}

/**
* 用新字符串替换原字符串中第一次出现的旧字符串（创建于 2003.03.05）.
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
* @param text 原字符串.
* @param oldStr 旧字符串.
* @param newStr 新字符串.
* @return 新字符串.
*/
	public static String replace(String text,String oldStr,String newStr)
	{	int li_pos = 0;
		String ls_head,ls_text;

		ls_text = text;

		li_pos = ls_text.indexOf(oldStr);
		    
		if(li_pos != -1)
		{	ls_head = ls_text.substring(0,li_pos);
		  ls_text = ls_text.substring(li_pos + oldStr.length());
		  ls_text = ls_head + newStr + ls_text;
		 }
		
		return ls_text;
	}
		

/**
* 用新字符串替换原字符串中第一次出现的旧字符（创建于 2003.03.05）.
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
* @param text 原字符串.
* @param oldChar 旧字符.
* @param newStr 新字符串.
* @return 新字符串.
*/
	public static String replace(String text,char oldChar,String newStr)
	{	String ls_oldChar = "" + oldChar;
		return replace(text,ls_oldChar,newStr);
	}
	
/**
* 用新字符替换原字符串中第一次出现的旧字符串（创建于 2003.03.05）.
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
* @param text 原字符串.
* @param oldStr 旧字符串.
* @param newChar 新字符.
* @return 新字符串.
*/
	public static String replace(String text,String oldStr,char newChar)
	{	String ls_newChar = "" + newChar;
		return replace(text,oldStr,ls_newChar);
	}

/**
* 获取字符串中以指定字符分隔的子字符串（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
主要用于设计自己的语义表达式。
所谓子字符串是指用指定分隔符分隔的字符串，
例如：分隔符为',' 则"abc,bcd,efg"中第1，2，3个子字符串分别为
"abc","bcd","efg"。
值得注意的是，对于"abc"，无论分隔符是什么，它的第1个子字符串就是
本身，它的第n(n>=2)个子字符串均为""。
</pre>
<DT><B>示例：</B><DD>
<pre>
// s = "oracle"
	String s = StringMethod.getChild("jdbc:oracle:test@dsfsf.1721",2,':');	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text	父字符串。
* @param index 索引号,第几个,从1开始编号。
* @param apart	分隔符。
* @return	子字符串,如果没有则返回""。
* @see #addChild(String,String,char)
*/
	public static String getChild(String text,int index,char apart)
	{	int li_pos = 0;
		String ls_val = "";
	
		for(int i = 1;i<=index;i++)
		{	li_pos = text.indexOf(apart);
			if(li_pos == -1)
			{	if(i==index) ls_val = text;
			 	else ls_val = "";
			}
			else
			{	ls_val = text.substring(0,li_pos);
				text = text.substring(li_pos + 1);
			}
		}
		
		return ls_val;
	}
	

/**
* 将字符串2作为子字符串加到字符串1中（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果text为空字符串则只将child加入,否则在先加apart然后再加入child
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
* @param text 字符串1.
* @param child 字符串2.
* @param apart 分隔符.
* @return 加入字符串2后的字符串信息.
* @see #getChild(String,int,char)
	@see #isChild(String,String,char)
	@see #addChild(String,String,String)
*/
	public static String addChild(String text,String child,char apart)
	{	return (isValid(text))? text + apart + child:child;
	}
	

	/**
	 * 将字符2作为子字符串加到字符串1中.（创建于 2003.03.05）.
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
* @param text 字符串1.
* @param child 字符2.
* @param apart 分隔符.
* @return 加入字符2后的字符串信息.
*/
	public static String addChild(String text,char child,char apart)
	{	return (isValid(text))? text + apart + child:child + "";
	}
	
/**
* 将字符串2作为子字符串加到字符串1中（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果text为空字符串则只将child加入,否则在先加apart然后再加入child
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
* @param text 字符串1.
* @param child 字符串2.
* @param apart 分隔符.
* @return 加入字符串2后的字符串信息.
	@see #addChild(String,String,char)
*/
	public static String addChild(String text,String child,String apart)
	{	return (text.length() > 0)? text + apart + child:child;
	}


/**
* 从字符串1中去掉与字符串2相等的子字符串（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字符串2中不能包含分隔符。
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
* @param text 字符串1.
* @param child 字符串2.
* @param apart 分隔符.
* @return 去除字符串2后的字符串信息.
* @see #addChild(String,String,char)
	@see #isChild(String,String,char)
*/
	public static String removeAllChild(String text,String child,char apart)
	{	String[] ls_child = toChilds(text,apart);
		String ls_text = "";
	
		for(int i=0;i<ls_child.length;i++)
			if(!child.equals(ls_child[i])) ls_text = addChild(ls_text,ls_child[i],apart);
		
		return ls_text;
	}
	

/**
* 比较字符串2是否为字符串1的子字符串（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
字符串2中不能包含分隔符。
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
* @param text 字符串1.
* @param child 字符串2.
* @param apart 分隔符.
* @return 如果字符串2是字符串1的子字符串返回true，否则返回false.
* @see #addChild(String,String,char)
*/
	public static boolean isChild(String text,String child,char apart)
	{	String[] ls_child = toChilds(text,apart);
			
		for(int i=0;i<ls_child.length;i++)
			if(child.equals(ls_child[i])) return true;
		
		return false;
	}

  
	/**
	 * 将字符串值转换成int型值（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与Integer.parseInt(String)相同.
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
* @param val 字符串值.
* @return int型值.
*/
	public static int toInt(String val)
	{	return Integer.parseInt(val);
	}
	/**
	 * 将字符串值转换成double型值（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与Double.parseDouble(String)相同.
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
* @param val 字符串值.
* @return double型值.
*/
	static double toDouble(String val)
	{	return Double.parseDouble(val);
	}	
	/**
	 * 将字符串值转换成byte型值（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与Byte.parseByte(String)相同.
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
* @param val 字符串值.
* @return byte型值.
*/
	static byte toByte(String val)
	{	return Byte.parseByte(val);
	}
	/**
	 * 将字符串值转换成float型值（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与Float.parseFloat(String)相同.
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
* @param val 字符串值.
* @return float型值.
*/
	static float toFloat(String val)
	{	return Float.parseFloat(val);
	}
	/**
	 * 将字符串值转换成short型值（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与Short.parseShort(String)相同.
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
* @param val 字符串值.
* @return short型值.
*/
	static short toShort(String val)
	{	return Short.parseShort(val);
	}
	/**
	 * 将字符串值转换成long型值（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与Long.parseLong(String)相同.
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
* @param val 字符串值.
* @return long型值.
*/
	static long toLong(String val)
	{	return Long.parseLong(val);
	}
	/**
	 * 将字符串值转换成char型值（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	与String.charAt(0)相同.
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
* @param val 字符串值.
* @return char型值.
*/
	static char toChar(String val)
	{ return val.charAt(0);
	}

/**
* 将用指定字符分隔的子字符串转换成字符串数组（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
在进行数据交换时采用分隔符方式较为方便,但在数据处理时用数组更合理些.
</pre>
<DT><B>示例：</B><DD>
<pre>
	String ls = "abc,dfasf,asdfasf";
	
	//larray的值:{"abc","dfasf","asdfasf"}
	String [] larray = StringMethod.toChilds(ls,',');	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 包含子字符串的语义.
* @param apart 分隔符.
* @return 字符串数组.
*/
	public static String [] toChilds(String text,char apart)
	{	int li_pos,li_index = 0;
		String ls_val;
		int li_count = charCount(text,apart) + 1;
		String[] lsArray = new String[li_count];

    while((li_pos = text.indexOf(apart)) >= 0)
		{	ls_val = text.substring(0,li_pos);
			text = text.substring(li_pos + 1);
			if(li_index < li_count)lsArray[li_index ++] = ls_val;
    }
		
		lsArray[li_index] = text;
		
		return lsArray;
	}
	
	
/**
* 将用指定字符分隔的子字符串转换成字符串数组（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
在进行数据交换时采用分隔符方式较为方便,但在数据处理时用数组更合理些.
</pre>
<DT><B>示例：</B><DD>
<pre>
	String ls_text = "a.;b;c.;d;e;.f.;gh";
	String[] ls_array = StringMethod.toChilds(ls_text,".;");
	
	for(int i=0;i<ls_array.length;i++)
		System.out.println("array[" + i + "] = '" + ls_array[i] + "'");
		
	运行结果如下：
	----------------------------------------------
	
	array[0] = 'a'
	array[1] = 'b;c'
	array[2] = 'd;e;.f'
	array[3] = 'gh'
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 包含子字符串的语义.
* @param apart 分隔符.
* @return 字符串数组.
*/
	public static String [] toChilds(String text,String apart)
	{	int li_pos,li_index = 0;
		String ls_val;
		List lList = new ArrayList();

    while((li_pos = text.indexOf(apart)) >= 0)
		{	ls_val = text.substring(0,li_pos);
			text = text.substring(li_pos + apart.length());
			lList.add(ls_val);
    }
		
		lList.add(text);
		
		String[] ls_Array = new String[lList.size()];
		lList.toArray(ls_Array);
		
		return ls_Array;
	}

/**
* 计算字符串中特征字符出现的个数（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	String ls = "abc,dfasf,asdfasf";
	
	//li_count的值为 2
	int li_count = StringMethod.charCount(ls,',');
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 字符串.
* @param key 特征字符.
* @return 特征字符出现的个数.
*/
	public static int charCount(String text,char key)
	{	char[] lc_Array = text.toCharArray();
		int li_count = 0;
		
		for(int i=0;i<lc_Array.length;i++)
			if(key==lc_Array[i])li_count ++;
			
		return li_count;
	}
/**
* 计算字符串中特征字符出现的个数（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	String ls = "abc,dfasf,asdfasf";
	
	//li_count的值为 2
	int li_count = StringMethod.charCount(ls,",.*");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 字符串.
* @param keys 特征字符.
* @return 特征字符出现的个数.
*/
	public static int charCount(String text,String keys)
	{	char[] lc_Array = text.toCharArray();
		int li_count = 0;
		
		for(int i=0;i<lc_Array.length;i++)
			if(keys.indexOf(lc_Array[i])>=0)li_count ++;
			
		return li_count;
	}

/**
* 判断字符串是否有效（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
规则是：不为null，且不是空字符串
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
* @param text 要判断有效性的字符串.
* @return true代表有效，false代表无效.
*/
	public static boolean isValid(String text)
	{	return !(text==null||text.equals(""));
	}
	

/**
* 填充字符（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
将chars重复n次.
</pre>
<DT><B>示例：</B><DD>
<pre>
//屏幕显示为 ?,?,?,?,?,
	System.out.println(StringMethod.fill("?,",5));
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param chars 用于填充的字符.
* @param n 填充次数.
* @return 填充后的字符串.
*/
	public static String fill(String chars,int n)
	{	String ls_text = "";
		
		for(int i = 0;i<n;i++)
			ls_text += chars;
			
		return ls_text;
	}	
	

	/**
	 * 转换字符串的字符集编码格式（创建于 2003.03.05）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在接受web页面的数据时经常会遇到因web服务器的不同使得接受到的数据是乱码的情况。
	本方法可以帮助你解决这个问题。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//以下程序使 a 以GBK的编码格式保存数据
	a = StringMethod.encode(a,"iso8859-1","GBK");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.05.
	</pre>
	</DL>
* @param text 需转换的字符串.
* @param oldCharSet 原来的字符集.
* @param newCharSet 新字符集.
* @return 转换后的字符串.
	@throws UnsupportedEncodingException 不支持的字符集名称.
*/
	public static String encode(String text,String oldCharSet,String newCharSet)
		throws UnsupportedEncodingException
	{ return new String(text.getBytes(oldCharSet),newCharSet);
	}
	

	
/**
* 比较文本结尾是否与特征字符串匹配，忽略大小写（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
//示例中，lb_match的值为true
boolean lb_match = StringMethod.endsWithIgnoreCase("wyq.abc","ABc");
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 文本.
* @param suffix 结尾特征字符串.
* @return true代表匹配，false代表不匹配.
* @see String#endsWith(String)
*/
	public static boolean endsWithIgnoreCase(String text,String suffix)
	{	return text.toUpperCase().endsWith(suffix.toUpperCase());
	}
	

	
/**
* 将字符串中的指定字符去掉（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
System.out.println(StringMethod.trim("aaasdfasfasdfsf",'a'));

//示例结果如下：
-------------------------
sdfsfsdfsf

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 字符串.
* @param key 被过滤的字符.
* @return 被过滤字符后的字符串.
* @see #leftTrim(String,char)
	@see #rightTrim(String,char)
*/
	public static String trim(String text,char key)
	{	char[] lc = text.toCharArray();
		String ls_text = "";
		
		for(int i=0;i<lc.length;i++)
		{	if(lc[i]==key) continue;
			
			ls_text += lc[i];
		}
		
		return ls_text;
	}
/**
* 将字符串最左边的指定字符去掉（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
System.out.println(StringMethod.leftTrim("aaasdfasfasdfsf",'a'));

//示例结果如下：
-------------------------
sdfasfasdfsf

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 字符串.
* @param key 被过滤的字符.
* @return 被过滤字符后的字符串.
* @see #trim(String,char)
	@see #rightTrim(String,char)
*/
	public static String leftTrim(String text,char key)
	{ char[] lc = text.toCharArray();
		String ls_text = "";
		
		for(int i=0;i<lc.length;i++)
			if(lc[i]!=key)
			{	ls_text = text.substring(i);
				break;
			}
				
		return ls_text;
	}
/**
* 将字符串最右边的指定字符去掉（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
System.out.println(StringMethod.rightTrim("aasdfasfasdfsfaa",'a'));

//示例结果如下：
-------------------------
aasdfasfasdfsf

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.05.
</pre>
</DL>
* @param text 字符串.
* @param key 被过滤的字符.
* @return 被过滤字符后的字符串.
* @see #trim(String,char)
	@see #leftTrim(String,char)
*/
	public static String rightTrim(String text,char key)
	{ char[] lc = text.toCharArray();
		String ls_text = "";
		
		for(int i=lc.length - 1;i>=0;i--)
			if(lc[i]!=key)
			{	ls_text = text.substring(0,i+1);
				break;
			}
				
		return ls_text;
	}
	

	
/**
* 将字符串截断到指定长度（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果字符串长度小于指定长度，则不发生变化.
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
* @param text 字符串.
* @param len 限制长度.
* @return 截断后的字符串.
*/
	public static String truncate(String text,int len)
	{	return text.length() > len ?text.substring(0,len):text;
	}
	

	
/**
* 取字符左边的字符串（创建于 2003.10.20）.
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
创建于 2003.10.20.
</pre>
</DL>
* @param text 文本.
* @param key 关键字.
* @return 关键字左边的文本.
* @see #right(String,char)
*/
	public static String left(String text,char key)
	{	int i = text.indexOf(key);	
		return text.substring(0,i);
	}
/**
* 取字符右边的字符串（创建于 2003.10.20）.
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
创建于 2003.10.20.
</pre>
</DL>
* @param text 文本.
* @param key 关键字.
* @return 关键字右边的文本.
* @see #left(String,char)
*/
	public static String right(String text,char key)
	{	int i = text.indexOf(key);	
		return text.substring(i+1);
	}
	


	
/**
* 按关键字在文本中的顺序生成相应的字符数组（创建于 2003.10.20）.
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
创建于 2003.10.20.
</pre>
</DL>
* @param text 文本.
* @param keys 关键字.
* @return 关键字字符数组.
*/
	public static char[] toCharArray(String text,String keys)
	{	char[] lc_text = text.toCharArray();
		String ls_keys = "";
	
		for(int i=0;i<lc_text.length;i++)
		{	if(keys.indexOf(lc_text[i])>=0)
				ls_keys += lc_text[i];
		}
		
		return ls_keys.toCharArray();
	}
	

	
/**
* 将null变成空字符串（创建于 2003.10.23）.
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
创建于 2003.10.23.
</pre>
</DL>
* @param text 文本.
* @return 如果文本是null则返回"",否则返回该文本.
*/
	public static String nullAsEmpty(String text)
	{	return (text==null?"":text);	
	}
  

  
/**
* 判断文本中从起始位置开始第一次出现关键字符的位置（创建于 2005.04.10）.
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
* @param text 文本.
* @param key 关键字符集.
* @param beginPos 文本起始位置.
* @return 关键字符的位置.
*/
  public static int indexOfKey(String text,String key,int beginPos)
  { int li_len = text.length();
  
    for(int i=beginPos;i<li_len;i++)
	   if(key.indexOf(text.charAt(i))>=0) return i;   
      
    return -1;
  }
}

