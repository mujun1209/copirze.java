package trionesII.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**为数组提供的方法（创建于 2003.03.04）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
本对象主要提供方便数组操作的静态方法.
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
public class ArrayMethod
{	
/**
* 将两个数组合并成一个（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	String [] a = {"abc","def","ghi"};
	Integer [] b = {new Integer(123),new Integer(456),new Integer(789)};
	
	//以下程序相当于Object [] c = {"abc","def","ghi",new Integer(123),new Integer(456),new Integer(789)}
	Object [] c = ArrayMethod.merge(a,b);
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param obj1 对象数组1，合并后元素在前.
* @param obj2 对象数组2，合并后元素在后.
* @return 合并后的对象数组.
*/
	public static Object[] merge(Object[] obj1,Object[] obj2)
	{	Object[] lObj = null;
		int li_num1,li_num2;
		
		li_num1 = li_num2 = 0;

		if(obj1 != null) li_num1 = obj1.length;
		if(obj2 != null) li_num2 = obj2.length;		
		
		if(li_num1 + li_num2 > 0) 
		{	lObj = new Object[li_num1 + li_num2];
		
			for(int i=0;i<li_num1;i++)
				lObj[i] = obj1[i];
				
			for(int j=0;j<li_num2;j++)
				lObj[li_num1+j] = obj2[j];
		}
		return lObj;
	}
	

/**
* 在数组中的指定索引处增加一个对象实例（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果索引超出数组的长度，则对象实例将存放到数组的最后，索引号从0开始。
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
* @param array 数组.
* @param index 元素插入的索引.
* @param item 待插入的对象实例.
* @return 增加元素后的数组.
*/
	public static Object[] addItem(Object[] array,int index,Object item)
	{	Object[] lObj = new Object[array.length + 1];
		int j = 0;
		for(int i=0;i<array.length;i++)
		{	if(j==index) j++;
			lObj[j++] = array[i];
		}
		
		if(index > j) index = j;
		
		lObj[index] = item;
		
		return lObj;
	}

/**
* 查找对象实例在对象数组中的位置（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>
	String[] sArray = {"abc","ABC","Abc"};
	
	//示例中 i = 2
	int i = ArrayMethod.findItem(sArray,"Abc");	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param list 对象数组.
* @param item 被查找的对象实例.
* @return 在list中的位置,如果没有找到则返回 -1.
	@see #findItemIgnoreCase(String[],String)
*/
	public static int findItem(Object[] list,Object item)
 	{	for(int i=0;i < list.length;i++)
		if(item.equals(list[i])) return i;

		return -1;
 	}


/**
* 查找子字符串在字符串数组中的位置（创建于 2003.03.04）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
忽略大小写.
</pre>
<DT><B>示例：</B><DD>
<pre>
	String[] sArray = {"abc","ABC","Abc"};
	
	//示例中 i = 0
	int i = ArrayMethod.findItemIgnoreCase(sArray,"Abc");	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.04.
</pre>
</DL>
* @param list 字符串数组.
* @param item 字符串.
* @return item在list中的位置.
* @see #findItem(Object[],Object)
*/
	public static int findItemIgnoreCase(String[] list,String item)
 	{	for(int i=0;i < list.length;i++)
		if(item.equalsIgnoreCase(list[i])) return i;

		return -1;
 	}



	/**
	 * 根据数组生成字符串（创建于 2002.09.10）.
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
	创建于 2002.09.10.
	</pre>
	</DL>
* @param objs 对象数组.
* @param apart 结果字符串中不同对象实例之间的分隔符.
* @return 结果字符串.
*/
	public static String toString(Object[] objs,char apart)
	{ String ls_text = "";
	
		for(int i=0;i<objs.length;i++)
		{	if(i>0) ls_text += apart;			
			ls_text += objs[i];				
		}
		
		return ls_text;
	}
	

	
/**
* 数组复制（创建于 2003.01.22）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果原始数组为null或者个数小于复制个数，则复制数组中的元素用null补足.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.22.
</pre>
</DL>
* @param objs 原始数组.
* @param num 复制个数.
* @return 复制数组.
* @see #copy(Object[],int,Object[])
*/
	public static Object[] copy(Object[] objs,int num)
	{	Object[] lObjs = new Object[num];
		
		copy(objs,num,lObjs);
		
		return lObjs;
	}
	

	
/**
* 数组复制（创建于 2003.01.22）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果原始数组为null或者个数小于复制个数，则复制数组中的元素用0补足.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.22.
</pre>
</DL>
* @param objs 原始数组.
* @param num 复制个数.
* @return 复制数组.
* @see #copy(Object[],int,Object[])
*/
	public static int[] copy(int[] objs,int num)
	{	int[] lObjs = new int[num];
		
		for(int i=0;i<num;i++)
		{ lObjs[i] = (i<objs.length?objs[i]:0);
		}
				
		return lObjs;
	}

	
/**
* 数组复制（创建于 2003.01.24）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
objsCopy需预先分配大于复制个数的空间，
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
* @param objs 原始数组.
* @param num 复制个数.
* @param objsCopy 复制数组.
* @return 复制数组.
* @see #copy(Object[],int)
*/
	public static Object[] copy(Object[] objs,int num,Object[] objsCopy)
	{	for(int i=0;i<num;i++)
		{	objsCopy[i] = ((objs!=null&&i<=objs.length)?objs[i]:null);
		}	
		
		return objsCopy;
	}
/**
* 根据对象数组创建序列（创建于 2003.01.22）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
与Arrays.asList(Object[])的区别是前者分配了一个新的数组对象，而后者仅仅是引用。
</pre>
<DT><B>示例：</B><DD>
<pre>
		//下列程序用于说明asList与toList之间的区别：
		
		String[] lsArray = {"WYQ","ABC","DEF","BED"};		
		List ls_array1 = ArrayMethod.asList(lsArray);
		List ls_array2 = ArrayMethod.toList(lsArray);
		
		lsArray[1] = "TEST";
	
		for(int i =0;i<lsArray.length;i++)
		{	System.out.println(ls_array1.get(i) + " = " + ls_array2.get(i));			
		}
		
		
		//程序运行结果如下：
		－－－－－－－－－－－－－－－－－－－－－－－－－
		WYQ = WYQ
		TEST = ABC
		DEF = DEF
		BED = BED
		
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.22.
</pre>
</DL>
* @param vals 对象数组.
* @return 对象序列.
* @see #asList(Object[])
*/
	public static List toList(Object[] vals)
	{	List lList = new ArrayList(vals.length);
		
		for(int i=0;i<vals.length;i++)
		{ lList.add(vals[i]);
		}
		
		return lList;
	}
	

	
/**
* 根据对象数组引用序列（创建于 2003.01.22）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
同Arrays.asList(Object[]),使用本方法产生的List不能使用添加或删除元素的操作，
如需修改元素值，则应注意元素类型是否匹配.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.01.22.
</pre>
</DL>
* @param vals 对象数组.
* @return 对象序列.
* @see #toList(Object[])
*/
	public static List asList(Object[] vals)
	{	return Arrays.asList(vals);
	}


}

