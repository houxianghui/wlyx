package com.blue.task;

import com.blue.common.User;
import com.blue.tools.FileUtil;


public class AutoTaskTest {
	public static void main(String[] args)throws Exception {
		String page = FileUtil.readFileToString("./task.html");
		AutoTask.filterTask(page, new User());
		
	}
}
