package org.varkrs.sociality.local.web.controllers;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.beanfactories.BeanFactories;
import org.varkrs.sociality.local.jpa.dao.LocalDAOFactory;

/*一种简化代码的方式. 
 * 所有继承该类的代码可以自动获取诸如LocalDAOFactory. 
 * 其新建的ModelAndView也自动匹配到json模式. 
 * 可以获取LocalApplicationContext.
 * 仅此而已, 别无它用. 非我别继承. 
 * 
 */
public class BaseLocalController implements ApplicationContextAware {
	protected static LocalDAOFactory daoFactory;

	@Override
	public void setApplicationContext(ApplicationContext ctxt)
			throws BeansException {
		ApplicationContext localContext = (ApplicationContext) ctxt.getBean("localApplicationContext");
		if(daoFactory == null)
			daoFactory = new LocalDAOFactory(localContext);
	}
	
	protected LocalDAOFactory getDAOFactory() {
		return daoFactory;
	}
	
	protected static ModelAndView getModelAndView() {
		return new ModelAndView("json-view");
	}
	
	protected ApplicationContext getLocalApplicationContext() {
		return BeanFactories.getLocalApplicationContext();
	}
	
	protected static class Constants {
		public static final String STATE = "state";
		public static final String ID = "id";
		
		public static final String USER = "user";
		public static final String RECORD = "record";
	}
}
