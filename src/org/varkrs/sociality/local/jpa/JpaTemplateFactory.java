package org.varkrs.sociality.local.jpa;

import javax.persistence.EntityManagerFactory;

import org.springframework.orm.jpa.JpaTemplate;

public class JpaTemplateFactory {
	private EMFFactory emfFactory;
	
	public JpaTemplateFactory() {}
	
	public JpaTemplate getInatance() {
		EntityManagerFactory emf = getEMF();
		JpaTemplate template = new JpaTemplate(emf);
		return template;
	}

	private EntityManagerFactory getEMF() {
		return emfFactory.getEMF();
	}
	
	public void setEMFFactory(EMFFactory factory) {
		this.emfFactory = factory;
	}
	
}
