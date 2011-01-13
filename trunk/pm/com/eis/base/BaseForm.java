/*********************************************************
 * File:BaseForm.java
 * 
 * @version 1.0
 * 
 * Date     2005-8-31
 * @author   ����
 * 
 * Copyright (C) 2005 huateng
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.base;

import org.apache.struts.validator.ValidatorForm;

/**
 * ˵��������FormBean�ĸ���
 * 
 */

public abstract class BaseForm extends ValidatorForm {

    /**
     * ���캯��
     */
    public BaseForm() {
        super();        
    }
    
    private String act = null;
    

	/**
	 * @return
	 */
	public String getAct() {
		return act;
	}

	/**
	 * @param string
	 */
	public void setAct(String string) {
		act = string;
	}

}
