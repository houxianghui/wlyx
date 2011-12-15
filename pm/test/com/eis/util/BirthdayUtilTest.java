/*
 * @# BirthdayUtilTest.java 2009-8-21 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.eis.util;

import java.util.Calendar;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.base.BaseTest;


public class BirthdayUtilTest extends BaseTest {
	public BirthdayUtilTest(String s){
		super(s);
	}
	public void testGetAge(){
		Calendar c = Calendar.getInstance();
		assertEquals(26,BirthdayUtil.getAge("19830916",c));
	}
	public void testGetConstellation(){
		assertEquals("Ìì³Ó×ù",BirthdayUtil.getConstellation("19840926"));
		assertEquals("Ä§Ð«×ù",BirthdayUtil.getConstellation("19840119"));
		assertEquals("½ðÅ£×ù",BirthdayUtil.getConstellation("19850429"));
	}
	public void testGetAnimalOfTheYear(){
		assertEquals("Öí",BirthdayUtil.getAnimalOfTheYear("19830911"));
		assertEquals("Å£",BirthdayUtil.getAnimalOfTheYear("19850101"));
	}
	public static Test suite() {
		return new TestSuite(BirthdayUtilTest.class);
	}

	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
