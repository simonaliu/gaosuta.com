package org.varkrs.sociality.local.jpa.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;
import org.varkrs.sociality.local.jpa.dao.ViewAuthorityDAO;
import org.varkrs.sociality.local.jpa.entities.ViewAuthority;

public class ViewAuthorityJpaImpl implements ViewAuthorityDAO {

	private JpaTemplate template;
	
	public ViewAuthorityJpaImpl() {}
	
	public void setTemplate(JpaTemplate template) {
		this.template = template;
	}

	public JpaTemplate getTemplate() {
		return template;
	}
	
	public ViewAuthority findById(final Object id) {
		try {
			return template.execute(new JpaCallback<ViewAuthority>() {

				@Override
				public ViewAuthority doInJpa(EntityManager em)
						throws PersistenceException {
					return em.find(ViewAuthority.class, id);
				}
				
			});
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Long insert(final ViewAuthority authority) {
		try {
			return template.execute(new JpaCallback<Long>() {
				@Override
				public Long doInJpa(EntityManager em) throws PersistenceException {
					em.getTransaction().begin();
					ViewAuthority mergedAuthority = em.merge(authority);
					em.getTransaction().commit();
					return mergedAuthority.getId();
				}
			});
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean update(final ViewAuthority authority) {
		try {
			return template.execute(new JpaCallback<Boolean>() {

				@Override
				public Boolean doInJpa(EntityManager em)
						throws PersistenceException {
					if(authority.getId() == null)
						return false;
					em.getTransaction().begin();
					em.merge(authority);
					em.getTransaction().commit();
					return true;
				}
				
			});
		} catch(DataAccessException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
