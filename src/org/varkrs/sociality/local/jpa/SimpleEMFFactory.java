package org.varkrs.sociality.local.jpa;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.ApplicationContext;
import org.varkrs.sociality.common.beanfactories.BeanFactories;

public class SimpleEMFFactory implements EMFFactory {

	@Override
	public EntityManagerFactory getEMF() {
		ApplicationContext ctxt = BeanFactories.getLocalApplicationContext();
		EntityManagerFactory emf = (EntityManagerFactory) ctxt.getBean("entityManagerFactory");
		return emf;
	}

}
