package com.blue.monstor;

import com.blue.common.User;

import junit.framework.TestCase;

public class MonstorTest extends TestCase{
	public void testGetMonstorUrl()throws Exception{
		User user = new User();
		user.setUserName("—≈µ‰ƒ»≈Æ…Ò");
		user.setPassword("771225*");
		user.login(false);
		String url = Monstor.getMonstorUrl(user);
		System.out.println(url);
	}
}
