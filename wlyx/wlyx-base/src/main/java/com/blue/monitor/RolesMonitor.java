package com.blue.monitor;

import java.util.ArrayList;
import java.util.List;

import com.blue.common.User;

public class RolesMonitor {
	private static RolesMonitor role = new RolesMonitor();
	private static List<User> users = new ArrayList<User>();

	public static List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	private RolesMonitor(){}
	public static RolesMonitor getInstance(){
		return role;
	}
}
