/*********************************************************
 * File:UserContext.java
 * 
 * @version 1.0
 * 
 * Date     2005-8-1
 * @author   辛勇
 * 
 * Copyright (C) 2005 huateng.
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.portal;


import java.util.HashMap;


/**
 * 说明：
 * 
 */
/**
 * 说明：
 * 
 */

public class UserContext implements java.io.Serializable{

	/**
	 * 
	 */
	public UserContext() {
		super();
	}


   
    /**
     * 用户代码
     */
    private String userID;
    
    /**
     * 用户名称
     */
    private String userName;
    
	/**
	 * 登录编号
	 */
	private String loginID;
	
    /**
     * 机构代码
     */
    private String orgID;
    
	/**
	 * 机构级别
	 */
	private String orgLevel;
    
    /**
     * 机构名称
     */
    private String orgName;
    
    
    /**
     * 角色编号
     */
    private String roleID;
    
    /**
     * 角色名称
     */
    private String roleName;
    
    /**
     * 角色组合
     */
    private String[] role;
    
    /**
     * 部门代码
     */
    private String deptID;
    

	/**
	 * 部门名称
	 */
	private String deptName;
	
	

	/**
	 * 记账员已录入笔数
	 */
	private int inputCount=0;
	
    
    private HashMap map = new HashMap();
    
 



	/**
	  * 获得属性值
	  * @param name 属性名称
	  * @return Object 属性值
	  */
    public Object getAttribute(String name) {
        return map.get(name);
    }
    

    /**
     * 获得用户编号
     * @return String 用户编号
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 获得用户姓名
     * @return String 用户姓名
     */
    public String getUserName() {
    	
        return userName.replaceAll("\\d+", "");
    }

    /**
     * 获得机构号
     * @return String 机构号
     */
    public String getOrgID() {
        return orgID;
    }

    /**
     * 获得机构名称
     * @return String 机构名称
     */
    public String getOrgName() {
        return orgName;
    }

 

    /**
     * 获得部门代码
     * @return String 部门代码
     */
    public String getDeptID() {
        return deptID;
    }

    /**
     * 获得部门名称
     * @return String 部门名称
     */
    public String getDeptName() {
        return deptName;
    }

	/**
	 * 获得角色编号
	 * @return String 角色编号
	 */
	public String getRoleID() {
		return roleID;
	}
	
	/**
	 * 获得角色名称
	 * @return String 角色名称
	 */
	public String getRoleName() {
		return roleName;
	}
	
    /**
     * 获得角色列表
     * @return String[] 角色列表
     */
    public String[] getRole() {
        return role;
    }

	/**
	 * 设置用户编号
	 * @param String 用户编号
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * 设置用户名称
	 * @param String 用户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 设置机构号
	 * @param String 机构号
	 */
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	/**
	 * 设置机构名称
	 * @param String 机构名称
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    /**
     * 设置部门代码
     * @param String 部门代码
     */
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    /**
     * 设置部门名称
     * @param String 部门名称
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

	/**
	 * 设置用户当前的角色编号
	 * @param String 角色编号
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	/**
	 * 设置用户当前的角色名称
	 * @param String 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
    /**
     * 设置用户当前的角色组合
     * @param String[] 角色组合
     */
    public void setRole(String[] role) {
        this.role = role;
    }

    /**
     * 获得登录编号
     * @return String 登录编号
     */
    public String getLoginID() {
        return loginID;
    }

    /**
     * 设置登录编号
     * @param String 登录编号
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
        
    }
    
 
	/**
	 * 设置属性值
	 * @param name 属性名称
	 * @param Object  属性值
	 */

	public void setAttribute(String name,Object obj) {
		map.put(name,obj);
	}

	/**
	 * @return 获取机构级别
	 */
	public String getOrgLevel() {
		return orgLevel;
	}

	/**
	 * @param string设置机构级别
	 */
	public void setOrgLevel(String string) {
		orgLevel = string;
	}


	/**
	 * @return 获取本用户已经录入的交易笔数
	 */
	public int getInputCount() {
		return inputCount;
	}


	/**
	 * @param 更新本用户的交易录入笔数
	 */
	public void setInputCount(int inputCount) {
		this.inputCount = inputCount;
	}

}
