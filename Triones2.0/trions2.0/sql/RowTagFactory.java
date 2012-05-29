package trionesII.sql;

import trionesII.bas.Factory;
import trionesII.Debug;
import trionesII.util.MyMethod;
import trionesII.util.StringMethod;

/**
* 行记录基因工厂（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
用于创建行记录(RowTag)的基因工厂.
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
* @version	2003.03.12
*/
class RowTagFactory implements Factory
{ 
/**
* 行指令集（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于构造与行记录相关的指令集.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
*/
	private Operator _Row;

/**
* 关键字（创建于 2003.03.12）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于构造与行记录相关的指令集.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
*/
	private String[] _PrimaryKey;


	
/**
* 构造方法（创建于 2003.03.12）.
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
创建于 2003.03.12.
</pre>
</DL>
* @param com 指令集.
*/
	public RowTagFactory(Operator com)
	{	_Row = com;
		_PrimaryKey = null;
	}
/**
* 构造方法（创建于 2003.03.12）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
如果关键字由多个列组成，则列与列之间用','分隔.
</pre>
<DT><B>示例：</B><DD>
<pre>
略
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.12.
</pre>
</DL>
* @param com 指令集.
	@param pkSyntax 关键字表达式.
*/	
	public RowTagFactory(Operator com,String pkSyntax)
	{	_Row = com;
		_PrimaryKey = (StringMethod.isValid(pkSyntax)?StringMethod.toChilds(pkSyntax,','):null);
	}

/**
* 根据属性名返回属性标识（创建于 2003.03.11）.
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
创建于 2003.03.11.
</pre>
</DL>
* @param fieldNames 属性名.
* @return 属性标识列表.
*/
	public int[] getFieldIDs(String[] fieldNames)
	{	int[] li_fieldIDs = new int[fieldNames.length];
	
		for(int i=0;i<fieldNames.length;i++)
		{	li_fieldIDs[i] = _Row.getFieldID(fieldNames[i]);
		}
		
		return li_fieldIDs;
	}
/**
* 构造类实例（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性列表.
* @param vals 属性值列表.
* @return 对象实例.
*/
	public Object newInstance(int[] fields,Object[] vals)
	{	Operator lRow = (Operator)MyMethod.deepClone(_Row);
	
		for(int i=0;i<fields.length;i++)
		{ if(fields[i] > 0) lRow.setFieldValue(fields[i],vals[i]);	
		}
		
		if(_PrimaryKey!=null)//生成默认的关键字条件
		{	for(int i=0;i<_PrimaryKey.length;i++)
			{	lRow.appendCndFieldOP(Syntax.CNDRELATION_AND,_PrimaryKey[i],"=",lRow.getFieldValue(_PrimaryKey[i]));
			}
		}
		
		return new RowTag(lRow);
	}
/**
* 构造类实例（创建于 2003.03.07）.
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
创建于 2003.03.07.
</pre>
</DL>
* @param fields 属性名列表.
* @param vals 属性值列表.
* @return 对象实例.
*/	
	public Object newInstance(String[] fields,Object[] vals)
	{ int[] li_fields = getFieldIDs(fields);				
		return newInstance(li_fields,vals);
	}
}

