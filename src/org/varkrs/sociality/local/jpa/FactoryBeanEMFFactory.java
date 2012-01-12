package org.varkrs.sociality.local.jpa;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.FactoryBean;

public class FactoryBeanEMFFactory implements EMFFactory {

	private FactoryBean<EntityManagerFactory> factoryBean;
	
	public FactoryBeanEMFFactory() {}
	
	public void setFactoryBean(FactoryBean<EntityManagerFactory> factoryBean) {
		this.factoryBean = factoryBean;
	}

	public FactoryBean<EntityManagerFactory> getFactoryBean() {
		return factoryBean;
	}

	@Override
	public EntityManagerFactory getEMF() {
		try {
			return factoryBean.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
