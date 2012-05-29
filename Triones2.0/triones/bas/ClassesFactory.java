package triones.bas;

import triones.util.ArrayMethod;

import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;


/**
* 默认遗传基因类（创建于 2003.03.07）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
主要用于按默认规则描述支持遗传基因的类的数据结构和构造方法.

规则是提取该类的公共属性，且该属性的类型必须是JDBC支持的.

该类必须支持无参数的构造方法。
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
* @version	2003.03.07
*/


public class ClassesFactory implements Factory
{	

/**
* 遗传基因类属性（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	ClassFactory[] _Factorys; 

/**
* 遗传基因类（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
*/
	Class[] _Classes; 
/**
* 构造方法（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
类必须具备下列条件：

有公共属性，且属性类型为JDBC支持的类型
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param cls 类.
*/
	public ClassesFactory(Class[] cls)
	{	_Classes = cls;
	
		_Factorys = new ClassFactory[_Classes.length];
		
		for(int i=0;i<_Classes.length;i++)
		{ _Factorys[i] = new ClassFactory(_Classes[i],true,true);
		}		
	}
	
/**
* 根据属性名和属性值构造遗传基因类的实例（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
属性名如果不存在，则被忽略。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性名称数组.
* @param vals 属性值数组.
* @return 遗传基因类实例.
* @exception InstantiationException 对象实例缺少默认的构造方法.
* @exception IllegalAccessException 对象属性不允许赋值，例如final变量.
*/
	public Object newInstance(String[] fields,Object[] vals)
		throws InstantiationException,IllegalAccessException
	{	Hashtable h = new Hashtable();
		
		for(int i=0;i<_Factorys.length;i++)
		{	h.put(_Classes[i],_Factorys[i].newInstance(fields,vals));
		}
		
		return h;
	}

/**
* 根据属性名和属性值构造遗传基因类的实例（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
属性名如果不存在，则被忽略。
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性名称数组.
* @param vals 属性值数组.
* @return 遗传基因类实例.
* @exception InstantiationException 对象实例缺少默认的构造方法.
* @exception IllegalAccessException 对象属性不允许赋值，例如final变量.
*/
	public Object newInstance(int[] fields,Object[] vals)
		throws InstantiationException,IllegalAccessException
	{	Hashtable h = new Hashtable();
		
		for(int i=0;i<_Factorys.length;i++)
		{	int[] li_fields = ArrayMethod.copy(fields,fields.length);
		
			for(int j=0;j<fields.length;j++)
			{	li_fields[j] -= (i+1)*100;
				if(li_fields[j]<=0||li_fields[j]>=100) li_fields[j] = 0;
			}
			
			h.put(_Classes[i],_Factorys[i].newInstance(li_fields,vals));
		}
		
		return h;
	}
/**
* 根据属性名返回属性标识（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回值-1代表属性名不存在.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param fieldNames 属性名.
* @return 属性标识列表.
*/
	public int[] getFieldIDs(String[] fieldNames)
	{	int[] li_fields = new int[fieldNames.length];
		
		for(int i=0;i<fieldNames.length;i++)
		{	li_fields[i] = getFieldID(fieldNames[i]);
		}				

		return li_fields;
	}

	
/**
* 查找列名（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
返回值 0 代表属性名不存在.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @param fieldName 属性名.
* @return 属性标识.
* @see #getFieldIDs(String[])
*/
	public int getFieldID(String fieldName)
	{	for(int i=0;i<_Factorys.length;i++)
		{	int li_index = _Factorys[i].getFieldID(fieldName);
			if(li_index > 0) return ((i + 1)*100 + li_index);	
		}
		
		return 0;
	}
	

	
/**
* 返回所有属性名数组（创建于 2007.09.05）.
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
创建于 2007.09.05.
</pre>
</DL>
* @return 属性名数组.
*/
	public String[] getFieldNames()
	{	List lList = new ArrayList();
		for(int i=0;i<_Factorys.length;i++)
		{	
			lList.addAll(Arrays.asList(_Factorys[i].getFieldNames()));
		}
		return (String[])lList.toArray();
	}
}

