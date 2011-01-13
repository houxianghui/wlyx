/*********************************************************
 * File:StringBuf.java
 * 
 * Version 1.0
 * 
 * Date     2005-8-24
 * Author   ÐÁÓÂ
 * 
 * Copyright (C) 2004 huateng.
 * all rights reserved.
 * 
 ********************************************************/

package com.eis.util;

/**
 * ËµÃ÷£º
 * 
 */


public class StringBuf {
    
    
    private StringBuffer strBuf = new StringBuffer();
    
    public StringBuf() {
     
    }
    public void appendln( String str ){
        strBuf.append( str + "\n" );
    }
        
    public String toString(){
        return( strBuf.toString() );
    }
    
    

}
