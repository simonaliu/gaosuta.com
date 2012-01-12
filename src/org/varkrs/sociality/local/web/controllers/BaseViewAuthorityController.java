package org.varkrs.sociality.local.web.controllers;

import javax.servlet.http.HttpSession;

import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.ViewAuthorityDAO;
import org.varkrs.sociality.local.jpa.entities.ViewAuthority;

public class BaseViewAuthorityController extends BaseLocalController {
	
	protected boolean resourcePermited(HttpSession session, Object userId) {
		Object userIdInSession = session.getAttribute(SessionConstants.USER_ID);
		if(userIdInSession != null && userIdInSession.equals(userId)) {
			return true;
		} else {
			Object viewAuthorityIdInSession = session.getAttribute(SessionConstants.VIEW_AUTHORITY_ID);
			if(viewAuthorityIdInSession == null)
				return false;
			else {
				ViewAuthority authority = getViewAuthority(viewAuthorityIdInSession);
				if(authority == null)
					return false;
				else {
					return authority.getUser().getId().equals(userId);
				}
			}
		}
	}
	
	protected ViewAuthority getViewAuthority(Object viewAuthorityId) {
		ViewAuthorityDAO dao = getDAOFactory().getViewAuthorityDAO();
		ViewAuthority authority = dao.findById(viewAuthorityId);
		return authority;
	}
	
}
