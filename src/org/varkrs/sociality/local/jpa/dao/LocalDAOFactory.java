package org.varkrs.sociality.local.jpa.dao;

import org.springframework.context.ApplicationContext;


public class LocalDAOFactory {
	
	private ApplicationContext ctxt;
	
	public LocalDAOFactory(ApplicationContext ctxt) {
		this.ctxt = ctxt;
	}
	
	public UserDAO getUserDAO() {
		return (UserDAO) getDAOContext().getBean("userDAO");
	}
	
	public RecordDAO getRecordDAO() {
		return (RecordDAO) getDAOContext().getBean("recordDAO");
	}
	
	public PhotoDAO getPhotoDAO() {
		return (PhotoDAO) getDAOContext().getBean("photoDAO");
	}

	public ViewAuthorityDAO getViewAuthorityDAO() {
		return (ViewAuthorityDAO) getDAOContext().getBean("viewAuthorityDAO");
	}
	
	public PhotoAlbumDAO getPhotoAlbumDAO() {
		return (PhotoAlbumDAO) getDAOContext().getBean("photoAlbumDAO");
	}
	
	private ApplicationContext getDAOContext() {
		return ctxt;
	}
	
}
