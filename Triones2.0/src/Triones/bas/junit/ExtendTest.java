/**
* DefaultExtend 的单元测试（创建于 2012-5-9）.
<DL>
<DT><B>对象概述：</B><DD>
<pre>
略
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
 email：360144561@qq.com 
 电 话：13585880448
</pre>
</DL>
* @author	Administrator
* @version	2012-5-9
*/
package Triones.bas.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Triones.bas.Extend;


public class ExtendTest {
	private Extend extend;
	private String[] result;
	@Before
	public void setUp() throws Exception {
		extend = new Extend();
		extend.SetAttribute("name:readonly", " mujun");
		extend.SetAttribute(" yifu:color", "red ");
		extend.SetAttribute(" age", "19");
		extend.SetAttribute(" company ", "taosteel");
		extend.SetAttribute(" age2", "24");
		extend.SetAttribute(" cloany", "haha");
		extend.SetAttribute(" company23 ", "taosteelcompany23");
		extend.SetAttribute(" 12age2 ", "12age2");
		extend.SetAttribute(" cloany:readonly ", "cloany");
		
		result=new String[]{"name","yifu","age","company","age2","cloany","company23","12age2"};
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetAttribute() {
		assertEquals(extend.SetAttribute("12age2:readonly","new"), 1);
	}

//	@Test
//	public void testGetAttribute() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testAttributeNames() {
		
		//assertArrayEquals(extend.AttributeNames("*"), result);
		//assertEquals(extend.AttributeNames("*").length, result.length);
		assertEquals(extend.AttributeNames("*=age,company").length, 5);
	}

//	@Test
//	public void testContain() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsReadOnly() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCloneObject() {
//		fail("Not yet implemented");
//	}


}
