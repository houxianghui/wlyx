/*
 * 创建日期 2009-12-28 houxh
 * 为弹出窗口类的异常信息提供处理
 *
 */
package com.eis.exception;

import com.eis.base.BaseException;


public class OpenWinException extends BaseException {
	public OpenWinException(String msg){
		super(msg);
	}
}
