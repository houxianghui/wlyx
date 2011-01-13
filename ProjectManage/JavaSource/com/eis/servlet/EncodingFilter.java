/*
 * 创建日期 2010-1-11
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
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
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class EncodingFilter implements Filter {

	/* （非 Javadoc）
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自动生成方法存根

	}

	/* （非 Javadoc）
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

	/* （非 Javadoc）
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO 自动生成方法存根

	}

}
