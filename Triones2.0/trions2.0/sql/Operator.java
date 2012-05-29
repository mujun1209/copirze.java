package trionesII.sql;

import java.util.List;
import java.util.NoSuchElementException;


/**
* SQL指令集（创建于 2003.03.12）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
针对二维表数据结构记录的特点，设计与之相应的SQL指令集
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
public interface Operator  extends Translet
{	/**
	 * 根据列名取列标识（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@param fieldName 列名
	@return 列标识,0代表不存在的列名.
*/
	public int getFieldID(String fieldName);
/**
* 取列的名称（创建于 2003.03.12）.
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
* @param fieldID 列标识.
* @return 列名.
	@exception NoSuchElementException 不存在的列.
*/
	public String getFieldName(int fieldID);
/**
* 设置列的值（创建于 2003.03.12）.
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
* @param fieldID 列标识.
* @param val 列值.
	@exception NoSuchElementException 不存在的列.
* @see #setFieldValue(String,Object)
*/
	public void setFieldValue(int fieldID,Object val);
/**
* 设置列的值（创建于 2003.03.12）.
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
* @param fieldName 列名(注:不要与数据库表的字段名混淆)..
* @param val 列值.
	@exception NoSuchElementException 不存在的列.
* @see #setFieldValue(int,Object)
*/
	public void setFieldValue(String fieldName,Object val);
/**
* 取列的值（创建于 2003.03.12）.
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
* @param fieldID 列标识.
* @return 列值.
	@exception NoSuchElementException 不存在的列.
* @see #getFieldValue(String)
*/
	public Object getFieldValue(int fieldID);
/**
* 取列的值（创建于 2003.03.12）.
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
* @param fieldName 列名(注:不要与数据库表的字段名混淆).
* @return 列值.
	@exception NoSuchElementException 不存在的列.
* @see #getFieldValue(int)
*/
	public Object getFieldValue(String fieldName);
/**
* 列个数（创建于 2003.03.05）.
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
* @return 列个数.
*/
	public int fieldCount();


/**
* 构造简单条件表达式的操作符（创建于 2003.03.05）.
<DL>
<DT><B>说明：</B><DD>
<pre>
目前支持的操纵符如下：
">",">=","<>","!=","=","<","<=","like","not like"
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
	public final static String[] OP = {">",">=","<>","!=","=","<","<=","like","not like"};
	
	/**
	 * 附加条件表达式（创建于 2003.03.23）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	条件表达式将以 fieldName op val 的形式出现。
	如果val为null，且op不为"=","<>","!="，则附加条件表达式将被过滤，返回false，成功返回true。
	目前op支持以下几种逻辑操作：
	<>、!=、	>、	>=、	<、	<=、	=、	like、not like
	其中like代表模糊匹配，如果值中不包含"%"，则自动在条件值前后加入"%"
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.13.
	修改于 2003.03.19 - 允许当op为"=","<>","!="时，val可以为null.
	</pre>
	</DL>
* @param fieldName 条件列名.
* @param op 逻辑表达式的操作符.
* @param val 逻辑表达式的值.
	@param cndRelation 与已有条件表达式之间的关系.	
* @return true代表附加成功，false代表附加失败.
* @exception NoSuchElementException 不存在的列.
* @exception UnsupportedOperationException 不支持的操作符.
*/	
	public boolean appendCndFieldOP(int cndRelation,String fieldName,String op,Object val);
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 [NOT] BETWEEN ... AND ...
	参数 not：true代表采用否定语义，false（默认值）代表不采用否定语义
	参数 cndRelation：SQLSyntax.CNDRELATION_AND 或者 SQLSyntax.CNDRELATION_OR，默认值为getFieldCndRelation()
	
	appendCndFieldBetween(fieldName,beginVal,endVal[,not][,cndRelation])
	
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
	@param not 是否采用否定语义.
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,Object beginVal,Object endVal,boolean not);
	/**
	 * 附加范围查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 [NOT] BETWEEN ... AND ...
	参数 not：true代表采用否定语义，false（默认值）代表不采用否定语义
	参数 cndRelation：SQLSyntax.CNDRELATION_AND 或者 SQLSyntax.CNDRELATION_OR，默认值为getFieldCndRelation()
	
	appendCndFieldBetween(fieldName,beginVal,endVal[,not][,cndRelation])
	
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
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldBetween(int,String,Object,Object,boolean)
*/
	public void appendCndFieldBetween(int cndRelation,String fieldName,Object beginVal,Object endVal);
	
/**
 * 附加枚举查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 [NOT] IN (element1,element2,...)
	参数 not：true代表采用否定语义，false（默认值）代表不采用否定语义
	参数 cndRelation：SQLSyntax.CNDRELATION_AND 或者 SQLSyntax.CNDRELATION_OR，默认值为getFieldCndRelation()

	appendCndFieldIn(fieldName,val[,not][,cndRelation])
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
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @param fieldName 条件列名.
* @param val 枚举值数组.
	@param not 是否采用否定语义.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(int,String,List)
*/
	public void appendCndFieldIn(int cndRelation,String fieldName,List val,boolean not);
/**
 * 附加枚举查询的条件列（创建于 2003.03.28）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	采用本方法可以实现 [NOT] IN (element1,element2,...)
	参数 not：true代表采用否定语义，false（默认值）代表不采用否定语义
	参数 cndRelation：SQLSyntax.CNDRELATION_AND 或者 SQLSyntax.CNDRELATION_OR，默认值为getFieldCndRelation()

	appendCndFieldIn(fieldName,val[,not][,cndRelation])
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
	@param cndRelation 与先前设定的条件表达式之间的关系符.
* @param fieldName 条件列名.
* @param val 枚举值数组.
* @exception NoSuchElementException 不存在的列.
	@see #appendCndFieldIn(int,String,List,boolean)
*/
	public void appendCndFieldIn(int cndRelation,String fieldName,List val);
/**
* 清除指令集的分组设置（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
* @see #groupByField(String)
*/
	public void clearGroup();
	/**
	 * 清除指令集的排序设置（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	@see #sortByField(String,int)		
*/
	public void clearSort();
	
	/**
	 * 设置列的分组模式（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	可以多次使用，按调用的先后顺序设定排序的优先级。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param fieldName 需要分组的列名.
* @exception NoSuchElementException 不存在的列.
* @see #clearGroup()
*/
	public void groupByField(String fieldName);
	/**
	 * 隐藏作为条件的列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,被隐藏的列对应的数据库表字段名将不出现在WHERE表达式中.
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//reg_dt不在WHERE表达式中出现
	com.hideCndField("reg_dt");	
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND money = 1200		
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 * @exception NoSuchElementException 不存在的列.
	 */	
	public void hideCndField(String fieldName);
	
	/** 隐藏作为条件的列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的列是不隐藏的。
	列标识取值范围:≥1
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldID 列标识(注:不要与数组或列表的索引混淆).
	@see NoSuchElementException 不存在的列.
	*/
	public void hideCndField(int fieldID);
	
	/**
	 * 隐藏所有条件列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于在showCndField方法使用前，隐藏所有条件列。
	</pre>
	<DT><B>示例：</B><DD>
		<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//隐藏条件列name
	com.hideCndFields();	

	//显示条件列money,reg_dt
	com.showCndFields("money,reg_dt");	
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE money= 1200 and reg_dt is NULL
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 */	
	public void hideCndFields();
	
	/** 隐藏指定列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,被隐藏的列对应的数据库表字段名将不出现在非WHERE表达式中.	
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq";	
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//money不在非WHERE表达式出现
	com.hideField("money");
				
	//以下语句相当于执行
	//SELECT name,reg_dt FROM StreamExample
	//WHERE name="wyq" AND reg_dt IS NULL AND money = 1200
	RecordSet lRecord = SQLCA.select(com);		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@see NoSuchElementException 不存在的列.
	*/
	public void hideField(String fieldName);
	/** 隐藏指定列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的列是不隐藏的。
	列标识取值范围:≥1
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldID 列标识(注:不要与数组或列表的索引混淆).
	@see NoSuchElementException 不存在的列.
	*/
	public void hideField(int fieldID);
	/**
	 * 隐藏所有的列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于在showField方法使用前先将所有列隐藏.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//name,money,reg_dt均不在非WHERE表达式中出现
	com.hideFields();
	
	//money、reg_dt在非WHERE表达式中出现
	com.showField("money");
	com.showField("reg_dt");
	
	//reg_dt不在WHERE表达式中出现
	com.hideCndField("reg_dt");	
	
	//以下语句相当于执行
	//SELECT reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND money = 1200		
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	 */	
	public void hideFields();
	
	/**
	* 判断指定的条件列是否被隐藏（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 	 @return true代表已隐藏，反之为false.
	 * @exception NoSuchElementException 不存在的列.
	 */
	public boolean isHiddenCndField(String fieldName);
	
	/** 判断指定的条件列是否被隐藏（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的列是不隐藏的。
	列标识取值范围:≥1
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldID 列标识(注:不要与数组或列表的索引混淆).
	@see NoSuchElementException 不存在的列.
	*/
	public boolean isHiddenCndField(int fieldID);
	/**
	* 判断列是否被隐藏（创建于 2003.03.09）.
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
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 	 @return true代表已隐藏，反之为false.
	 * @exception NoSuchElementException 不存在的列.
   */
	public boolean isHiddenField(String fieldName);
	
	/** 判断列是否被隐藏（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的列是不隐藏的。
	列标识取值范围:≥1
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldID 列标识(注:不要与数组或列表的索引混淆).
	@see NoSuchElementException 不存在的列.
	*/
	public boolean isHiddenField(int fieldID);
	
	/**
	 * 取被指令集操纵的对象名（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	一般指库表或视图.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @return 指令对象名.
*/	
	public String getDBName();

	/**
	 * 设置实例对应的数据库表名（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的规则为数据库表名等于忽略路径的对象名
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//MyExample对应于库表user_list	
	MyExample user = new MyExample("wyq","wu yongqing");
	com.setDBName("user_list");
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param dbname 数据库表名.
* @see #getDBName()
*/
	public void setDBName(String dbName);
	
	/**
	 * 显示作为条件的列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,不隐藏的列对应的数据库表字段名将出现在WHERE表达式中.
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//隐藏所有条件列
	com.hideCndFields;	
	
	//显示条件列name
	com.showCndField("name");
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1"
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 * @param fieldName 列名(注:不要与数据库表的字段名混淆).
	 * @exception NoSuchElementException 不存在的列.
	 */
		public void showCndField(String fieldName);
	/** 显示作为条件的列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的列是不隐藏的。
	列标识取值范围:≥1
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldID 列标识(注:不要与数组或列表的索引混淆).
	@see NoSuchElementException 不存在的列.
	*/
	public void showCndField(int fieldID);
		/**
	 *显示所有作为条件的列（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	主要用于在使用hideCndField方法之前，使所有条件列为显示。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//隐藏条件列name
	com.hideCndField("name");	
	
	//显示所有条件列
	com.showCndFields();
	
	//隐藏条件列money,reg_dt
	com.hideCndFields("money,reg_dt");	
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1"
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
	 */	
		public void showCndFields();
	 /**显示指定列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	在生成SQL语句时,不隐藏的列对应的数据库表字段名将出现在非WHERE表达式中.	
	默认的列是不隐藏的。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq";	
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//money不在非WHERE表达式中出现
	com.hideField("money");
	
	//reg_dt不在非WHERE表达式中出现
	com.hideField("reg_dt");
	
	//reg_dt在非WHERE表达式中出现
	com.showField("reg_dt");
	
	//以下语句相当于执行
	//SELECT name,reg_dt FROM StreamExample
	//WHERE name="wyq" AND reg_dt IS NULL AND money = 1200
	RecordSet lRecord = SQLCA.select(com);		
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldName 列名(注:不要与数据库表的字段名混淆).
	@see NoSuchElementException 不存在的列.
	*/
	public void showField(String fieldName);
/** 显示指定列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	默认的列是不隐藏的。
	列标识取值范围:≥1
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	@param fieldID 列标识(注:不要与数组或列表的索引混淆).
	@throws NoSuchElementException 不存在的列.
	*/
	public void showField(int fieldID);
	
	/**
	 *显示所有的列（创建于 2003.03.19）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本方法主要用于在hideField方法使用前先显示所有列.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	//example对应于StreamExample表，name,reg_dt,money对应于该表的列
	example.name = "wyq1";
	example.money = 1200;
	example.reg_dt = null;
	
	//SQLObject为AbstractOperatorKernel的子类
	SQLObject com = new SQLObject(example);
	
	//money、reg_dt不在非WHERE表达式中出现
	com.hideField("money");
	com.hideField("reg_dt");
	
	//reg_dt不在WHERE表达式中出现
	com.hideCndField("reg_dt");
	
	//name,money,reg_dt均在非WHERE表达式中出现
	com.showFields();
	
	//以下语句相当于执行
	//SELECT name,reg_dt,money FROM StreamExample
	//WHERE name="wyq1" AND money = 1200		
	List lArrylist = SQLCA.select(com);
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.19.
	</pre>
	</DL>
	 */	
	public void showFields();
	
	/**
	 * 设置列的排序模式（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	可以多次使用，按调用的先后顺序设定排序的优先级。
	
	mode的值请参见：
	SQLSyntax.SORT_ASC
	SQLSyntax.SORT_DESC
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	略
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param fieldName 需要排序的列名.
* @param mode 排序模式.
* @exception NoSuchElementException 不存在的列.
* @see #clearSort()
	@see Syntax#SORT_ASC
	@see Syntax#SORT_DESC
*/
	public void sortByField(String fieldName,int mode);
/**
* 取指令集名称（创建于 2003.03.05）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
指令集名称用于控制操作
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
* @return 指令集名称.
* @see #setName(String)
*/
	public String getName();
	
/**
* 设置指令集名称（创建于 2003.03.05）.
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
* @param name 指令集名称.
* @see #getName()
*/
	public void setName(String name);
}

