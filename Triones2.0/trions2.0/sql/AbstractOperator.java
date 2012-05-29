package trionesII.sql;


/**
* 抽象指令集扩展（创建于 2003.03.20）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
提供对诸如方法参数类型的扩展支持.
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
* @version	2003.03.20
*/
public class AbstractOperator extends AbsOperator implements Operator
{ 
/**
* 构造方法（创建于 2003.03.20）.
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
创建于 2003.03.20.
</pre>
</DL>
*/
	protected AbstractOperator()
	{	super();
	}

	/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(int,String,String,Object)
*/
	public boolean appendCndFieldOP(int cndRelation,String fieldName,String op,long val)		
	{	return appendCndFieldOP(cndRelation,fieldName,op,new Long(val));
	}
	

	/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(int,String,String,Object)
*/
	public boolean appendCndFieldOP(int cndRelation,String fieldName,String op,double val)		
	{	return appendCndFieldOP(cndRelation,fieldName,op,new Double(val));
	}
		/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(int,String,String,Object)

*/
	public boolean appendCndFieldOP(int cndRelation,String fieldName,String op,char val)		
	{	return appendCndFieldOP(cndRelation,fieldName,op,val + "");
	}

	/**
	 * 附加条件表达式（创建于 2003.03.23）.
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
	创建于 2003.03.23.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
* @see #appendCndFieldOP(int,String,String,Object)

*/
	public boolean appendCndFieldOP(int cndRelation,String fieldName,String op,boolean val)		
	{	return appendCndFieldOP(cndRelation,fieldName,op,new Boolean(val));
	}


	
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
	@param not 以否定形式出现.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object,boolean)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,long beginVal,long endVal,boolean not)		
	{	appendCndFieldBetween(cndRelation,fieldName,new Long(beginVal),new Long(endVal),not);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,long beginVal,long endVal)
	{	appendCndFieldBetween(cndRelation,fieldName,beginVal,endVal,false);		
	}
		/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
	@param not 以否定形式出现.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object,boolean)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,double beginVal,double endVal,boolean not)
	{	appendCndFieldBetween(cndRelation,fieldName,new Double(beginVal),new Double(endVal),not);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,double beginVal,double endVal)
		
	{	appendCndFieldBetween(cndRelation,fieldName,beginVal,endVal,false);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.
	@param not 以否定形式出现.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object,boolean)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,char beginVal,char endVal,boolean not)
	{	appendCndFieldBetween(cndRelation,fieldName,new Character(beginVal),new Character(endVal),not);		
	}
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 BETWEEN ... AND ...
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.28.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param beginVal 范围开始值.
* @param endVal 范围结束值.
	@param cndRelation 与已有条件表达式之间的关系.	
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,char beginVal,char endVal)
	{	appendCndFieldBetween(cndRelation,fieldName,beginVal,endVal,false);		
	}


}

