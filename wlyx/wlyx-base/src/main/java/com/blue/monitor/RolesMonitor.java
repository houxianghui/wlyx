package com.blue.monitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blue.common.User;

public class RolesMonitor {
	private static RolesMonitor role = new RolesMonitor();
	private List<User> users = new ArrayList<User>();
	private static Map<String,User> userMap = new HashMap<String, User>();
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
		for(User user:users){
			userMap.put(user.getUserName(), user);
		}
	}
	private RolesMonitor(){}
	public static RolesMonitor getInstance(){
		return role;
	}
	public User getUser(String userName){
		return userMap.get(userName);
	}
}
