package trionesII.sql;

import java.lang.Class;
import java.lang.reflect.Field;
import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;

import trionesII.util.*;
import trionesII.bas.Factory;


/**
* <img src="important.gif" width="35" height="25" border="0">类指令集（创建于 2003.03.07）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
    ClassOperator的设计目的在于提供逻辑层到存储层的统一接口标准。在大多数应用开发中，
业务逻辑的设计基本采用面向对象的设计方法，但到了数据存储层，往往需将业务逻辑的数据输
出转换为SQL语句。如果缺乏好的开发控制，很容易导致以下问题的发生：

1.编写与数据库交换数据的工作量很大，而且不容易调试。
2.表单结构发生变化后，与该表单相关的程序几乎全部重新调试和开发，并且很难保证所有相关
  代码都能被找到。
3.由于在将数据从数据库中提取出来的过程比较繁琐，因此以往的开发中，经常会直接将数据从
  存储层转到界面层，忽略了逻辑层，从而失去了三层结构为软件设计带来的灵活性、高适应性
	的优点。
4.一旦数据库系统需要移植到另一种数据库系统中，则很有可能产生SQL语句的不兼容性。
	
    采用ClassOperator可以在以上各个方面带来很大的改善。首先，它规范了逻辑层与存储层进
行数据交换的过程，并且可以保证60％以上的数据库操作过程是完全一致的，因此可以在此基础
上开发代码自动生成工具，从而提高数据交换方面的工作效率。其次，由于ClassOperator完成数据
库操作的语句是动态生成的，因此当库表结构发生变化时，只需修改字段的映射机制，95％以上
的程序代码不需要修改。再次，采用ClassOperator方式可以很好地保证在软件设计中即提高了编程的便利
性而又保证体系结构的灵活性和高适用性。最后，ClassOperator与Transaction、Connector很好地组成了
一整套与数据库系统具体类型略关的数据交换机制，可以方便地实现跨数据库平台的程序开发。

    目前ClassOperator除了能提供对单个库表的数据操作外，还提供了多表联合查询机制。此外，
trionesII还计划在未来提供更灵活更强大的条件组合方式、排序、分组等方面的功能。
</pre>
<DT><B>使用说明：</B><DD>
<pre>

假设数据库中创建表streamexample：

CREATE TABLE "STREAMEXAMPLE"
       ("NAME" VARCHAR2(256),
       "REG_DT" DATE,
       "GIFDATA" BLOB,
       "MONEY" NUMBER(5,2)) ;
				
为了存取其中的数据，设计MyExample类：

//列名的默认规则是与属性名一致
public class MyExample
	{	public String name;
		public TimeStamp reg_dt;
	}

    在使用时先将用于存取数据的对象转换成ClassOperator语义，然后调用DBTrans的insert、
update、delete、select等方法实现对数据库的存取操作。为了演示如何使用，我们设计一个
Test类：

class Test
{

public static void main(String args[]) 
{	Debug.IFDebug = true;
	try
	{ //连接数据库
		Connector DB = new Connector(new File("E:\\吴勇庆\\01_创作苑\\01_重用构件\\Java\\oracle.ini"));
		Transaction sqlca = DB.getTransaction();	
		
		MyExample example = new MyExample();
		
		//设置插入到数据库的记录
		example.name = "wyq1";		
		example.reg_dt = new Timestamp(1975,11,5,10,10,10,10);
		
		//转换成ClassOperator,指定与MyExample对应的数据库表StreamExample
		//实际开发中，建议通过实现trionesII.sql.Rule将类和属性名与数据库表及字段名以某种规则进行映射
		ClassOperator com = new ClassOperator(example,"StreamExample");
		
		//查询时reg_dt不作为条件表达式出现
		com.hideCndField("reg_dt");
		
		//以下语句相当于执行
		//INSERT INTO StreamExample(name,reg_dt) VALUES(?,?)
		SQLCA.insert(com);
		
		//提交事务操作
		SQLCA.commit();
		
		
		//以下语句相当于执行
		//SELECT name,reg_dt FROM StreamExample WHERE name = ?
		//查询结果集（参见trionesII.sql.RecordSet）可根据需要以Object或List的形式展现.
		MyExample result = (MyExample)(SQLCA.select(com).toObject(1,com.getFactory()));
		
		//打印结果
		System.out.println(result.name + "," + result.reg_dt);
		
		//断开数据库									
		SQLCA.close();
	}
	catch(Exception e)
	{	System.out.println(e);
	}
	
执行Test类，结果如下：

wyq1,3875-12-05 10:10:10.0

</pre>
<DT><B>注意事项：</B><DD>
<pre>
    &nbsp;&nbsp;&nbsp;&nbsp;值得注意的是，由于采用JDBC默认的数据转换模式，因此数据对象中的变量类型建议
		按以下要求设计：
		<TABLE BORDER=2>
		<TR><TH>数据库类型</TH><TH>JAVA类型</TH>
		<TR><TD>VARCHAR</TD><TD>java.lang.String</TD>
		<TR><TD>LONGVARCHAR</TD><TD>java.lang.String</TD>
		<TR><TD>BIT</TD><TD>boolean/java.lang.Boolean</TD>
		<TR><TD>TINYINT</TD><TD>byte/java.lang.Byte</TD>
		<TR><TD>VARBINARY</TD><TD>byte[]</TD>
		<TR><TD>SMALLINT</TD><TD>short/java.lang.Short</TD>
		<TR><TD>INTEGER</TD><TD>int/java.lang.Integer</TD>
		<TR><TD>BIGINT</TD><TD>long/java.lang.Long</TD>
		<TR><TD>REAL</TD><TD>float/java.lang.Float</TD>
		<TR><TD>DOUBLE</TD><TD>double/java.lang.Double</TD>
		<TR><TD>NUMERIC(n,m)</TD><TD>java.math.BigDecimal/double/java.lang.Double/float/java.lang.Float</TD>
		<TR><TD>NUMERIC(n,0)</TD><TD>short/java.lang.Short/int/java.lang.Integer/long/java.lang.Long</TD>
		<TR><TD>DATE</TD><TD>java.sql.Date/java.util.Date</TD>
		<TR><TD>TIME</TD><TD>java.sql.Time</TD>
		<TR><TD>TIMESTAMP</TD><TD>java.sql.Timestamp</TD>
		</TABLE>
</pre>
<DT><B>展望未来：</B><DD>
<pre>
1.增加排序的强化版	
2.增加对group的强化版
3.增加对条件关系的强化版
4.增加对XML的支持
</pre>
<DT><B>联系方式：</B><DD>
<pre>
email：wyq@triok.com
电  话:021-68672222-2013
</pre>
</DL>
*@author	吴勇庆
*@version 2003.03.07
*/
public class ClassOperator extends AbstractOperator implements Cloneable,Serializable,Operator
{	
/**版本号（创建于 2003.03.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
VERSION = "2003.03.07"
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
	public static final String VERSION = "2003.03.07 - Wu yongqing.";	

	
/**
* 导入对象的类型（创建于 2003.03.07）.
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
	private Class _SimpleClass;
		
/**
* 组成复合ClassOperator的对象类型清单（创建于 2003.03.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
只在_IsMultiple=true时使用
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
	private List _MultiClassList = new ArrayList();

/**
* 默认SQL语义规则（创建于 2003.03.07）.
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
	static Rule DEFAULT_RULE = new DefaultRule();

/**
* 基因工厂（创建于 2003.03.07）.
<DL>
<DT><B>说明：</B><DD>
<pre>
用于RecordSet构造toList()
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
	Factory _Factory;
	
/**
* <img src="important.gif" width="35" height="25" border="0">取对应构造参数的基因工厂（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
基因工厂可用于构造与对象同类的实例。
</pre>
<DT><B>示例1</B><DD>
<pre>
//非复合类型
...

FBI_USER u = new FBI_USER();
Transaction sqlca = DB.getTransaction();			
			
ClassOperator com = new ClassOperator(u);
com.hideCndFields();	
com.showCndField("u_email");
com.setFieldCndExpression("u_email","# != @");

RecordSet lSet = sqlca.select(com);

//根据基因工厂的设计创建List
List lList = lSet.toList(com.getFactory());

System.out.println("复合类型：" + com.isMultiple());
System.out.println(sqlca.getSQLSyntax());
sqlca.close();
			
for(int i=0;i&lt;lList.size();i++)
{	System.out.println(i + " : " + ((FBI_USER)lList.get(i)).u_email);
}

...

执行结果如下：
--------------------------------------------------

复合类型：false
SELECT u_id,u_name,u_pswd,u_status,u_level,u_email,u_canSee,u_score FROM FBI_USER WHERE (u_email IS NOT NULL)
0 : guest@beyondbit.com
1 : wusj@beyondbit.com
2 : zhangdh@beyondbit.com
3 : wyq@beyondbit.com
4 : wusj@beyondbit.com
5 : wujf@beyondbit.com

</pre>
<DT><B>示例2</B><DD>
<pre>
	//复合类型
	...
	
	FBI_USER u = new FBI_USER();
		
	u.u_name = "吴勇庆";
	
	FBI_QUESTION q = new FBI_QUESTION();
	
	ClassOperator com1 = new ClassOperator(u);
	ClassOperator com2 = new ClassOperator(q);
	
	//确定在FBI_USER中的显示字段
	com1.hideFields();
	OperatorMethod.showFields(com1,"u_email,u_name,u_id");	
	
	//确定在FBI_USER中的条件字段
	com1.hideCndFields();				
	com1.showCndField("u_name");
	
	//确定在FBI_QUESTION中的显示字段
	com2.hideFields();
	com2.showField("q_title");
	
	//确定在FBI_QUESTION中的条件字段
	com2.hideCndFields();
	
	//以u_id为两表关联的关键字
	ClassOperator com3 = ClassOperator.join(com1,com2,"u_id","u_id");
	
	Transaction sqlca = DB.getTransaction();	
	sqlca.disableBind(true);
	
	RecordSet lSet = sqlca.select(com3);
	List lList = lSet.toList(com3.getFactory());
	
	System.out.println("复合类型：" + com3.isMultiple());
	System.out.println(sqlca.getSQLSyntax());
	sqlca.close();

	for(int i=0;i&lt;lList.size();i++)
	{	Hashtable h = (Hashtable)(lList.get(i));
		System.out.println(i + " : " + ((FBI_QUESTION)h.get(FBI_QUESTION.class)).q_title);
	}

	...
	
	执行结果如下：
	--------------------------------------------------
	
	复合类型：true
	SELECT FBI_USER.u_id AS FBI_USER_u_id,FBI_USER.u_name AS FBI_USER_u_name,FBI_USER.u_email AS FBI_USER_u_email,FBI_QUESTION.q_title AS FBI_QUESTION_q_title 
	FROM FBI_USER,FBI_QUESTION 
	WHERE ((FBI_USER.u_id = FBI_QUESTION.u_id) AND (FBI_USER.u_name = '吴勇庆'))
	0 : 为什么伊拉克不拥有大规模杀伤性武器？
	1 : 为什么美国要侵略伊拉克？
	
</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.07.
</pre>
</DL>
* @return 基因工厂.
	@see RecordSet#toList(Factory)
*/
	public Factory getFactory()
	{ if(_Factory==null)
		{	if(isMultiple())
				_Factory = new MultipleFactory(getMultiClasses());
			else
				_Factory = new SimpleFactory(getSimpleClass());
		}
	
		return _Factory;
	}

	/**
	 * 构造方法（创建于 2003.03.07）.
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
*/
	private ClassOperator()
	{ super();
	}
	
	/**<img src="important.gif" width="35" height="25" border="0">构造方法（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	以当前默认的命名规则创建实例。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	假设数据库表streamexample，数据结构如下：

	CREATE TABLE "STREAMEXAMPLE"
       ("NAME" VARCHAR2(256),
       "REG_DT" DATE,
       "GIFDATA" BLOB,
       "MONEY" NUMBER(5,2)) ;
				
	对应的业务逻辑类为StreamExample，数据结构如下：

	public class StreamExample
	{	public String name;
		public TimeStamp reg_dt;
		public double money;
	}
	
	
	//使用示范：
	
	...
	
	StreamExample example = new StreamExample();
	
	example.name = "wyq";
	example.reg_dt = new Timestamp(1991,2,3,0,0,0,0);
	example.money = 100.1;
	
	ClassOperator com = new ClassOperator(example);
	
	...

	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
	@param obj 业务逻辑对象.
	@see #ClassOperator(Object,Rule)
	@see #ClassOperator(Object,String)
	*/

	public ClassOperator(Object obj)
	{	super();
		init(obj);
		setOperatorDBName(getName());
		addFields(obj,DEFAULT_RULE);	
	}


	/**
	 * <img src="important.gif" width="35" height="25" border="0">构造方法（创建于 2003.03.08）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本构造方法指定了数据库表对象与业务逻辑类之间的映射规则。
	本方法不会改变默认的命名映射规则，只对本次构造有效。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	假设数据库表streamexample，数据结构如下：

	CREATE TABLE "STREAMEXAMPLE"
       ("NAME" VARCHAR2(256),
       "REG_DT" DATE,
       "GIFDATA" BLOB,
       "MONEY" NUMBER(5,2)) ;
				
	对应的业务逻辑类为MyExample，数据结构如下：

	public class MyExample
	{	public String strName;
		public TimeStamp timeReg_dt;
		public double dMoney;
	}
	
	设计ExampleRule，如下：
	
	public class ExampleRule implements trionesII.sql.Rule
	{	public String toDBName(String cls)
		{	return "StreamExample";//注，在使用时，需保证Connection是忽略大小写的
		}
		
		public String toFieldDBName(String field)
		{	int li_begin = StringMethod.indexOfUpper(field);
		
			if(li_begin == -1) 
				return field;
			else
				return field.subString(li_begin);			
		}
	}
	
	//使用示范：
	
	...
	
	Rule rule = new ExampleRule();
	
	MyExample example = new MyExample();
	
	example.strName = "wyq";
	example.timeReg_dt = new Timestamp(1991,2,3,0,0,0,0);
	example.dMoney = 100.1;
	
	ClassOperator com = new ClassOperator(example,rule);
	
	...

	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.03.
	</pre>
	</DL>
* @param obj 业务逻辑对象.
* @param rule 数据库表对象与业务逻辑对象之间的映射规则.
* @see #ClassOperator(Object)
	@see #ClassOperator(Object,String)
*/
	public ClassOperator(Object obj,Rule rule)
	{	super();
		init(obj);
		setOperatorDBName(rule.toDBName(getName()));
		addFields(obj,rule);
	}
	
	/**
	 * 构造方法（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	指定业务逻辑类对应的数据库表名。
	需保证该逻辑类中的属性名与数据库表中的字段名一致。
	本方法不会改变默认的命名映射规则，只对本次构造有效。
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	假设数据库表streamexample，数据结构如下：

	CREATE TABLE "STREAMEXAMPLE"
       ("NAME" VARCHAR2(256),
       "REG_DT" DATE,
       "GIFDATA" BLOB,
       "MONEY" NUMBER(5,2)) ;
				
	对应的业务逻辑类为MyExample，数据结构如下：

	public class MyExample
	{	public String name;
		public TimeStamp reg_dt;
		public double money;
	}
	
	
	//使用示范：
	
	...
	
	MyExample example = new MyExample();
	
	example.name = "wyq";
	example.reg_dt = new Timestamp(1991,2,3,0,0,0,0);
	example.money = 100.1;
	
	ClassOperator com = new ClassOperator(example,"StreamExample");
	
	...

	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.09.
	</pre>
	</DL>
* @param obj 业务逻辑对象.
* @param dbName 对应业务逻辑对象的数据库表名.
	@see #ClassOperator(Object)
	@see #ClassOperator(Object,Rule)
	@see #setDBName(String)
*/
	public ClassOperator(Object obj,String dbName)
	{	super();
		init(obj);
		setOperatorDBName(dbName);
		addFields(obj,DEFAULT_RULE);
	}

/**
* 初始化方法（创建于 2003.03.07）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
从对象中获取必要信息，如对象类型、指令集名称（等于没有包名的类名）
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
* @param obj 对象.
*/
	private void init(Object obj)
	{	_SimpleClass = obj.getClass();
		_MultiClassList.add(_SimpleClass);
		setName(ReflectMethod.getClassName(_SimpleClass,true));	
	}

	/**
	 * 根据对象创建列（创建于 2003.03.07）.
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
* @param obj 对象实例.
* @param rule 命名规则.
*/
	private void addFields(Object obj,Rule rule)
	{	try
		{	Field[] lFields = _SimpleClass.getFields();	
			String ls_fName,ls_fDBName;
			Class lc_fType;
			Object lo_fVal;
			
			for(int i=0;i<lFields.length;i++)
				if(isValidField(lFields[i]))
				{	ls_fName = lFields[i].getName();
					ls_fDBName = rule.toFieldDBName(ls_fName);
					lc_fType = lFields[i].getType();
					lo_fVal = lFields[i].get(obj);//maybe throw IllegalAccessException
					addField(ls_fName,ls_fDBName,lc_fType,lo_fVal);
				}
		}
		catch(IllegalAccessException e)
		{	//will not happened
		}
	}


	/**
	 * 判断列是否可被用于生成表达式（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	目前的规则是：当且仅当公共列，且为JDBC支持的类型
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
* @param f 列实例
* @return 是否为有效的列
*/
	protected static boolean isValidField(Field f)
	{	return ReflectMethod.isPublicField(f) && DBMS.isSupportedFieldType(f.getType());
	}


	
	/**
	 * 取生成扩展信息对象的类型（创建于 2003.03.09）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	用于DBTrans中，生成ArrayList时的对象类型。
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
* @return 对象类型.
*/
	Class getSimpleClass()
	{	return _SimpleClass;
	}



	/**
	 * 创建一个复合类型的ClassOperator（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	返回值中的ClassOperator只继承参数ClassOperator的条件信息，排序、分组等信息不继承.
	</pre>
	<DT><B>示例：</B><DD>
	<pre>
	
	...
	
	FBI_USER u = new FBI_USER();
		
	u.u_name = "吴勇庆";
	
	FBI_QUESTION q = new FBI_QUESTION();
	
	ClassOperator com1 = new ClassOperator(u);
	ClassOperator com2 = new ClassOperator(q);
	
	//确定在FBI_USER中的显示字段
	com1.hideFields();
	OperatorMethod.showFields(com1,"u_email,u_name,u_id");	
	
	//确定在FBI_USER中的条件字段
	com1.hideCndFields();				
	com1.showCndField("u_name");
	
	//确定在FBI_QUESTION中的显示字段
	com2.hideFields();
	com2.showField("q_title");
	
	//确定在FBI_QUESTION中的条件字段
	com2.hideCndFields();
	
	//以u_id为两表关联的关键字
	ClassOperator com3 = ClassOperator.join(com1,com2,"u_id","u_id");
	
	Transaction sqlca = DB.getTransaction();	
	sqlca.disableBind(true);
	
	RecordSet lSet = sqlca.select(com3);
	List lList = lSet.toList(com3.getFactory());
	
	System.out.println(sqlca.getSQLSyntax());
	sqlca.close();

	for(int i=0;i&lt;lList.size();i++)
	{	Hashtable h = (Hashtable)(lList.get(i));
		System.out.println(i + " : " + ((FBI_QUESTION)h.get(FBI_QUESTION.class)).q_title);
	}

	...
	
	执行结果如下：
	--------------------------------------------------
	
	SELECT FBI_USER.u_id AS FBI_USER_u_id,FBI_USER.u_name AS FBI_USER_u_name,FBI_USER.u_email AS FBI_USER_u_email,FBI_QUESTION.q_title AS FBI_QUESTION_q_title 
	FROM FBI_USER,FBI_QUESTION 
	WHERE ((FBI_USER.u_id = FBI_QUESTION.u_id) AND (FBI_USER.u_name = '吴勇庆'))
	0 : 为什么伊拉克不拥有大规模杀伤性武器？
	1 : 为什么美国要侵略伊拉克？
	
	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.07.
	</pre>
	</DL>
* @param com1 组成部分1.
* @param com2 组成部分2.
* @param fieldNames1 组成部分1中用于关联的列名.
* @param fieldNames2 组成部分2中用于关联的列名.
* @return 复合类型的ClassOperator.
* @exception NoSuchElementException 找不到的列名.
*/
	public static ClassOperator join(ClassOperator com1,ClassOperator com2,String fieldNames1,String fieldNames2)
	{	return join(com1,com2,fieldNames1,fieldNames2," = ");
	}
	
	/**
	 * <img src="new.gif" width="28" height="11" border="0">创建一个复合类型的ClassOperator（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	本方法可以指定关联字段之间的关联方式，如" = "," > "," < "," >= "," <= "等。
	
	值得注意的是，对于左右关联目前各个数据库有的提供，有的没有提供，因此无法统一：
	
	在ORACLE中，分别为" (+)= "和" =(+) ".
	特别需要指出的是：com1和com2中原有的排序、分组等信息将不对新产生的ClassOperator有效.
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
* @param com1 组成部分1.
* @param com2 组成部分2.
* @param fieldNames1 组成部分1中用于关联的列名.
* @param fieldNames2 组成部分2中用于关联的列名.
	@param op	关联操作符.
* @return 复合类型的ClassOperator.
* @exception NoSuchElementException 找不到的列名.
*/
	private static ClassOperator join(ClassOperator com1,ClassOperator com2,String fieldNames1,String fieldNames2,String op)
  {	String [] ls_FieldNames1 = StringMethod.toChilds(fieldNames1,Syntax.FIELD_APART);
		String [] ls_FieldNames2 = StringMethod.toChilds(fieldNames2,Syntax.FIELD_APART);
	
		if(ls_FieldNames1.length != ls_FieldNames2.length)
			throw new IllegalArgumentException("对应域的个数不匹配:" + fieldNames1 + " -> " + fieldNames2);
			
		ClassOperator com = new ClassOperator();
	
		com.addFields(com1.getFields());
		com.addFields(com2.getFields());
		
		com.setMultiple(true);
		
		com._MultiClassList.addAll(com1._MultiClassList);
		com._MultiClassList.addAll(com2._MultiClassList);
		
		com.setOperatorDBName(com1.getDBName() + Syntax.FIELD_APART + com2.getDBName());
		/*
		com.setAppendCndSyntax(com1._AppendCndSyntax);
		com.setAppendCndSyntax(com2._AppendCndSyntax);
		*/	
		//相关列之间的条件限制
		String ls_condition = "";
		
		for(int i=0;i<ls_FieldNames1.length;i++)
		{	if(StringMethod.isValid(ls_condition))
				ls_condition += Syntax.CNDRELATION_SYNTAX[Syntax.CNDRELATION_AND];
				
			ls_condition += com1.getFieldProperty(ls_FieldNames1[i],FIELD_FULLDBNAME) + op + com2.getFieldProperty(ls_FieldNames2[i],FIELD_FULLDBNAME);			
		}
		
		com.setSQLCondition(Syntax.CNDRELATION_AND,ls_condition);
		//com.hideCndFields();
		
		//将条件合并
		com.appendCndOperator(Syntax.CNDRELATION_AND,com1);
		com.appendCndOperator(Syntax.CNDRELATION_AND,com2);
		
		return com;
	}
/**
* <img src="important.gif" width="35" height="25" border="0">设置附加条件表达式（创建于 2003.03.26）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
	附加条件表达式直接采用SQL描述，附加条件表达式将作为WHERE子句的一部分。	
	本方法不能用于累加条件，多次使用本方法，只保留最后一次设置的条件。
	使用本方法虽然简化了拼装条件的过程，但可能损害跨数据库的特性，需谨慎使用！
</pre>
<DT><B>示例：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
Transaction sqlca = DB.getTransaction();			
			
ClassOperator com = new ClassOperator(u);
com.hideCndFields();	
com.showCndField("u_email");
com.setFieldCndExpression("u_email","# != @");

//设置附加条件
com.setSQLCondition(Syntax.CNDRELATION_AND,"u_name like '吴%'");
			
RecordSet lSet = sqlca.select(com);
List lList = lSet.toList(com.getFactory());

System.out.println(sqlca.getSQLSyntax());
sqlca.close();
			
for(int i=0;i&lt;lList.size();i++)
{	System.out.println(i + " : " + ((FBI_USER)lList.get(i)).u_email);
}

...

执行结果如下：
--------------------------------------------------

SELECT u_id,u_name,u_pswd,u_status,u_level,u_email,u_canSee,u_score FROM FBI_USER WHERE ((u_email IS NOT NULL) AND u_name like '吴%')
0 : wusj@beyondbit.com
1 : wyq@beyondbit.com
2 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.26.
</pre>
</DL>
* @param cndRelation 附加条件关系符.
* @param condition 附加条件表达式.
	@see #clearSQLCondition()
	@see Syntax#CNDRELATION_AND
	@see Syntax#CNDRELATION_OR
*/
	public void setSQLCondition(int cndRelation,String condition)
	{	super.setSQLCondition(cndRelation,condition);
	}
	
/**
* 清除附加条件（创建于 2003.03.28）.
<P><DL>
<DT><B>说明：</B><DD>
<pre>
略
</pre>
<DT><B>示例：</B><DD>
<pre>

...

FBI_USER u = new FBI_USER();
Transaction sqlca = DB.getTransaction();			
			
ClassOperator com = new ClassOperator(u);
com.hideCndFields();	
com.showCndField("u_email");
com.setFieldCndExpression("u_email","# != @");

//设置附加条件
com.setSQLCondition(Syntax.CNDRELATION_AND,"u_name like '吴%'");
//清除附加条件
com.clearSQLCondition();

RecordSet lSet = sqlca.select(com);
List lList = lSet.toList(com.getFactory());

System.out.println(sqlca.getSQLSyntax());
sqlca.close();
			
for(int i=0;i&lt;lList.size();i++)
{	System.out.println(i + " : " + ((FBI_USER)lList.get(i)).u_email);
}

...

执行结果如下：
--------------------------------------------------

SELECT u_id,u_name,u_pswd,u_status,u_level,u_email,u_canSee,u_score FROM FBI_USER WHERE (u_email IS NOT NULL)
0 : guest@beyondbit.com
1 : wusj@beyondbit.com
2 : zhangdh@beyondbit.com
3 : wyq@beyondbit.com
4 : wusj@beyondbit.com
5 : wujf@beyondbit.com

</pre>
<DT><B>日志：</B><DD>
<pre>
创建于 2003.03.28.
</pre>
</DL>
	@see #setSQLCondition(int,String)
*/
	public void clearSQLCondition()
	{	super.clearSQLCondition();
	}
	/**
	 * <img src="important.gif" width="35" height="25" border="0">指令集是否为复合类型（创建于 2003.03.07）.
	<P><DL>
	<DT><B>说明：</B><DD>
	<pre>
	由join方法产生的指令集是复合类型的。
	是否为复合类型的指令集，一般由指令集根据情况自动设定。
	</pre>
	<DT><B>示例1</B><DD>
	<pre>
	
	...
	
	FBI_USER u = new FBI_USER();
	ClassOperator com = new ClassOperator(u);
	
	//显示是否为复合类型		
	System.out.println(com.isMultiple());
	
	...
	
	执行结果如下：
	--------------------------------------------------
	
	false

</pre>
<DT><B>示例2</B><DD>
<pre>
	
	...

	ClassOperator com = ClassOperator.join(com1,com2,"u_id","u_id");
	
	//显示是否为复合类型		
	System.out.println(com.isMultiple());
	
	...
	
	执行结果如下：
	--------------------------------------------------
	
	true

	</pre>
	<DT><B>日志：</B><DD>
	<pre>
	创建于 2003.03.03.
	</pre>
	</DL>
* @return true代表是复合类型，反之为false.
*/
	public boolean isMultiple()
	{	return super.isMultiple();
	}
	/**
	 * 取复合元素类型清单（创建于 2003.03.07）.
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
* @return 复合元素类型清单.
*/
	Class[] getMultiClasses()
	{	Class[] lClass = new Class[_MultiClassList.size()];
	
		_MultiClassList.toArray(lClass);
		
		return lClass;
	}
	

}

