package com.blue.tools;

import junit.framework.TestCase;

public class ToolsTest extends TestCase {

	public void testGetMonth() {
		assertEquals(1, Tools.getMonth());
	}
	public void testGetDay(){
		assertEquals(2, Tools.getDay());
	}
	public void testNeedGetLingPai(){
		assertTrue(Tools.needGetLingPai());
	}
}
