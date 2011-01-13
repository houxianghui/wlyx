/*
 * @# BaseVOInterface.java 2009-9-10 houxh
 *
 * Copyright  (c)  2009 	Huateng. All Right Reserv
 */
 
package com.eis.base;

import java.io.Serializable;


public interface BaseVOInterface extends Serializable {
	public int getQueryCount() ;
	public void setQueryCount(int i);
}
