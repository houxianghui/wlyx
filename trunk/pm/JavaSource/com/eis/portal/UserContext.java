/*********************************************************
 * File:UserContext.java
 * 
 * @version 1.0
 * 
 * Date     2005-8-1
 * @author   ����
 * 
 * Copyright (C) 2005 huateng.
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.portal;


import java.util.HashMap;


/**
 * ˵����
 * 
 */
/**
 * ˵����
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
     * �û�����
     */
    private String userID;
    
    /**
     * �û�����
     */
    private String userName;
    
	/**
	 * ��¼���
	 */
	private String loginID;
	
    /**
     * ��������
     */
    private String orgID;
    
	/**
	 * ��������
	 */
	private String orgLevel;
    
    /**
     * ��������
     */
    private String orgName;
    
    
    /**
     * ��ɫ���
     */
    private String roleID;
    
    /**
     * ��ɫ����
     */
    private String roleName;
    
    /**
     * ��ɫ���
     */
    private String[] role;
    
    /**
     * ���Ŵ���
     */
    private String deptID;
    

	/**
	 * ��������
	 */
	private String deptName;
	
	

	/**
	 * ����Ա��¼�����
	 */
	private int inputCount=0;
	
    
    private HashMap map = new HashMap();
    
 



	/**
	  * �������ֵ
	  * @param name ��������
	  * @return Object ����ֵ
	  */
    public Object getAttribute(String name) {
        return map.get(name);
    }
    

    /**
     * ����û����
     * @return String �û����
     */
    public String getUserID() {
        return userID;
    }

    /**
     * ����û�����
     * @return String �û�����
     */
    public String getUserName() {
    	
        return userName.replaceAll("\\d+", "");
    }

    /**
     * ��û�����
     * @return String ������
     */
    public String getOrgID() {
        return orgID;
    }

    /**
     * ��û�������
     * @return String ��������
     */
    public String getOrgName() {
        return orgName;
    }

 

    /**
     * ��ò��Ŵ���
     * @return String ���Ŵ���
     */
    public String getDeptID() {
        return deptID;
    }

    /**
     * ��ò�������
     * @return String ��������
     */
    public String getDeptName() {
        return deptName;
    }

	/**
	 * ��ý�ɫ���
	 * @return String ��ɫ���
	 */
	public String getRoleID() {
		return roleID;
	}
	
	/**
	 * ��ý�ɫ����
	 * @return String ��ɫ����
	 */
	public String getRoleName() {
		return roleName;
	}
	
    /**
     * ��ý�ɫ�б�
     * @return String[] ��ɫ�б�
     */
    public String[] getRole() {
        return role;
    }

	/**
	 * �����û����
	 * @param String �û����
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * �����û�����
	 * @param String �û�����
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * ���û�����
	 * @param String ������
	 */
	public void setOrgID(String orgID) {
		this.orgID = orgID;
	}

	/**
	 * ���û�������
	 * @param String ��������
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

    /**
     * ���ò��Ŵ���
     * @param String ���Ŵ���
     */
    public void setDeptID(String deptID) {
        this.deptID = deptID;
    }

    /**
     * ���ò�������
     * @param String ��������
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

	/**
	 * �����û���ǰ�Ľ�ɫ���
	 * @param String ��ɫ���
	 */
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	/**
	 * �����û���ǰ�Ľ�ɫ����
	 * @param String ��ɫ����
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
    /**
     * �����û���ǰ�Ľ�ɫ���
     * @param String[] ��ɫ���
     */
    public void setRole(String[] role) {
        this.role = role;
    }

    /**
     * ��õ�¼���
     * @return String ��¼���
     */
    public String getLoginID() {
        return loginID;
    }

    /**
     * ���õ�¼���
     * @param String ��¼���
     */
    public void setLoginID(String loginID) {
        this.loginID = loginID;
        
    }
    
 
	/**
	 * ��������ֵ
	 * @param name ��������
	 * @param Object  ����ֵ
	 */

	public void setAttribute(String name,Object obj) {
		map.put(name,obj);
	}

	/**
	 * @return ��ȡ��������
	 */
	public String getOrgLevel() {
		return orgLevel;
	}

	/**
	 * @param string���û�������
	 */
	public void setOrgLevel(String string) {
		orgLevel = string;
	}


	/**
	 * @return ��ȡ���û��Ѿ�¼��Ľ��ױ���
	 */
	public int getInputCount() {
		return inputCount;
	}


	/**
	 * @param ���±��û��Ľ���¼�����
	 */
	public void setInputCount(int inputCount) {
		this.inputCount = inputCount;
	}

}
