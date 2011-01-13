/*
 * @# TaskCtrlNewBOTest.java 2009-8-4 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.huateng.creditcard.apply.taskctrl.ibatis;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import com.base.BaseTest;
import com.huateng.creditcard.apply.common.ApplyQuery;


public class TaskCtrlNewBOTest extends BaseTest {
	private TaskCtrlNewBO bo;
	public TaskCtrlNewBOTest(String string){
		super(string);
	}


	protected void setUp() throws Exception {
		bo = (TaskCtrlNewBO)getBean("taskCtrlNewBO");
		super.setUp();
	}
	
	public void testQueryForList(){
		ApplyQuery aq = new ApplyQuery();
		aq.setUserBranch("710009999");
		try{
			List l = bo.queryForList(aq);
			assertTrue(l.size()>=0);
			aq.setCust_name_f("ÕÅÈý");
			l = bo.queryForList(aq);
			assertTrue(l.size()==0);
		}catch(Exception e){
			assertFalse(false);
		}
	}
//	public void testQueryForBranchBackList(){
//		ApplyQuery aq = new ApplyQuery();
//		aq.setUserId("");
//		try{
//			List l = bo.queryForBranchBackList(aq);
//			assertTrue(l.size()==0);
//			aq.setUserId("00000186");
//			assertTrue(l.size()>=0);
//		}catch(Exception e){
//			assertFalse(false);
//		}
//	}
//	public void testQueryForBranchResendQueueList(){
//		ApplyQuery aq = new ApplyQuery();
//		aq.setUserId("");
//		try{
//			List l = bo.queryForBranchBackList(aq);
//			assertTrue(l.size()==0);
//			aq.setUserId("00000186");
//			assertTrue(l.size()>=0);
//		}catch(Exception e){
//			assertFalse(false);
//		}
//	}
//	public void testQueryForBranchQueueList(){
//		ApplyQuery aq = new ApplyQuery();
//		aq.setUserId("");
//		aq.setQueueId("L");
//		try{
//			List l = bo.queryForBranchBackList(aq);
//			assertTrue(l.size()==0);
//			aq.setUserId("00000186");
//			assertTrue(l.size()>=0);
//		}catch(Exception e){
//			assertFalse(false);
//		}
//	}
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public static Test suite() {
		return new TestSuite(TaskCtrlNewBOTest.class);
	}

	public static void main(String[] args) {
		TestRunner.run(suite());
	}
}
