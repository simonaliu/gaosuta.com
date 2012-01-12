package org.varkrs.sociality.common.web.configurations;

import javax.servlet.ServletContext;

import org.springframework.web.context.WebApplicationContext;

/**处理项目路径上下文的部分, 从这里可以获取ServletContext(Servlet接口)和WebApplicationContext(Spring接口).
 * 
 */
public class ProjectWebContext {
	private static WebApplicationContext webApplicationContext;
	
	public static ServletContext getServletContext() {
		return webApplicationContext.getServletContext();
	}
	
	public static WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}
	
	public static void setWebApplicationContext(WebApplicationContext ctxt) {
		webApplicationContext  = ctxt;
	}
	
}
