package triones.bas;

/**
* 比较类（创建于 2002.6.26）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
针对字符串的比较类,目的为了简化因比较模式不同而导致方法接口的不统一.
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
* @version	2002.6.26
*/
public class Compare
{	/**不忽略大小写,~Compare.CASE代表忽略大小写,只可用&操作符*/
	public static int CASE = 8;		//二进制:	1000,第4位判断大小写敏感
	/**相等,~Compare.EQUAL代表不等*/
	public static int EQUAL = 1|CASE;		//二进制:	001
	/**小于,~Compare.LESS代表不小于*/
	public static int LESS  = 2|CASE;		//二进制:	010
	/**大于,~Compare.GREATER代表不大于*/
	public static int GREATER = 4|CASE;	//二进制:	100
	
	public static int LIKE = 0;//二进制：	
	
	
	public static int TEXT =15;	  	//二进制: 01111
	

	/**
	 * 判断字符串与基准字符串的比较结果是否符合比较规则.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//假设有如下两个字符串和布尔变量lb_option
	String max = "abc";
	String min = "ABC";
	boolean lb_option;
	
	//以下各例中lb_option = false
	lb_option = Compare.isMatch(max,min,Compare.EQUAL);
	lb_option = Compare.isMatch(max,min,Compare.LESS);
	lb_option = Compare.isMatch(max,min,Compare.LESS|Compare.EQUAL);
		
	//以下各例中lb_option = true
	lb_option = Compare.isMatch(max,min,Compare.GREATER|Compare.LESS);
	lb_option = Compare.isMatch(max,min,~Compare.CASE&Compare.EQUAL);
	lb_option = Compare.isMatch(max,min,~Compare.LESS);
	
	</pre>
	</DL>
* @param a 字符串.
* @param b 基准字符串.
* @param op 比较规则.
* @return 符合规则为true,否则为false.
*/
	public static boolean isMatch(String a,String b,int op)
	{	int li_compare = 0;
		int li_op = EQUAL;
		
		if(bit(op,4)==1)
			li_compare = a.compareTo(b);			
		else
			li_compare = a.compareToIgnoreCase(b);
			
		
		if(li_compare > 0) 
			li_op = GREATER;
		else if(li_compare < 0)
			li_op = LESS;
		
		return (((op|CASE)&li_op)==li_op);		
	}
	

	/**
	 * 返回整型数二进制的第N位的值.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	无
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	无
	</pre>
	</DL>
* @param number 整型数.
* @param pos 第几位.
* @return 0或1.
*/
	private static long bit(long number,int pos)
	{	return (0xffff & number)>>>(pos-1)&1;	
	}
}

