import com.eis.util.BirthdayUtilTest;
import com.huateng.creditcard.apply.taskctrl.ibatis.TaskCtrlNewBOTest;
import com.huateng.creditcard.apply.validator.ItemValidatorTest;

import junit.framework.TestSuite;
import junit.textui.TestRunner;

/*
 * @# TestAll.java 2009-8-4 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */

public class TestAll {
	public static void main(String[] args) {
		TestSuite t = new TestSuite();
//		t.addTest(TaskCtrlNewBOTest.suite());
//		t.addTest(BirthdayUtilTest.suite());
		t.addTest(ItemValidatorTest.suite());
		TestRunner.run(t);
	}
}
