package org.varkrs.sociality.local.business.resources;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;

import org.springframework.core.io.Resource;
import org.springframework.web.context.support.ServletContextResource;
import org.varkrs.sociality.common.beanfactories.BeanFactories;
import org.varkrs.sociality.common.web.configurations.HostInfo;
import org.varkrs.sociality.common.web.configurations.ProjectWebContext;

public class ResourceUtils {
	
	public static File relativeResourcesDir() {
		return new File("resources/");
	}
	
	public static String absoluteFilePathOfProjectRelativePath(String relativePath) {
		if(relativePath == null)
			return null;
		try {
			return getResource(relativePath).getFile().getAbsolutePath();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static String absoluteURLOfProjectRelativePath(String relativePath) {
		if(relativePath == null)
			return null;
		else 
			return getPrefix() + relativePath;
	}

	private static Resource getResource(String relativePath) {
		return new ServletContextResource(getServletContext(), relativePath);
	}

	private static ServletContext getServletContext() {
		return ProjectWebContext.getServletContext();
	}	
	
	private static String getPrefix() {
		ServletContext servletContext = getServletContext();
		HostInfo info = (HostInfo) BeanFactories.
			getLocalApplicationContext().getBean("hostInfo");
		return info.protocal() + "://" 
			+ info.domainName() + 
			servletContext.getContextPath() + "/";
	}
	
}
