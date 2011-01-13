/*
 * @# BaseTest.java 2009-8-4 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */

package com.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import junit.framework.TestCase;

public class BaseTest extends TestCase {
	protected ApplicationContext context =
		new FileSystemXmlApplicationContext(
			new String[] {
				"WebContent/WEB-INF/CreditCard-dao.xml",
				"WebContent/WEB-INF/CreditCard-bo.xml",
				"WebContent/WEB-INF/CreditCard-action.xml",
				"WebContent/WEB-INF/CreditCard-common-test.xml" });

	public BaseTest(String arg0) {
		super(arg0);
	}

	public Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}
