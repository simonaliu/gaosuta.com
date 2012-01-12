package org.varkrs.sociality.common.web.initial;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;
import org.varkrs.sociality.common.web.configurations.ProjectWebContext;

public class WebLoadeInitializer implements ApplicationContextAware {


	@Override
	public void setApplicationContext(ApplicationContext ctxt)
			throws BeansException {
		WebApplicationContext webCtxt = (WebApplicationContext) ctxt;
		ProjectWebContext.setWebApplicationContext(webCtxt);
	}
	
}
