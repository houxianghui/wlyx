/*
 * �������� 2010-1-11
 *
 * �����������ļ�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
package com.eis.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author doria
 *
 * ��������������ע�͵�ģ��Ϊ
 * ���� > ��ѡ�� > Java > �������� > �����ע��
 */
public class EncodingFilter implements Filter {

	/* ���� Javadoc��
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO �Զ����ɷ������

	}

	/* ���� Javadoc��
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(
		ServletRequest request,
		ServletResponse response,
		FilterChain filterChain)
		throws IOException, ServletException {
			String encoding = "gb18030";
			request.setCharacterEncoding(encoding);
			response.setContentType("text/html;charset=" + encoding + "");    
			filterChain.doFilter(request,response);


	}

	/* ���� Javadoc��
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO �Զ����ɷ������

	}

}
