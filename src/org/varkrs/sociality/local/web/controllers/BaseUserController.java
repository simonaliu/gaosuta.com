package org.varkrs.sociality.local.web.controllers;

import org.varkrs.sociality.local.jpa.entities.User;

/*在扩展BaseLocalController的基础上, 增加了获取数据库User类的功能. 
 * 
 */
public class BaseUserController extends BaseLocalController {
	
	protected User getUser(Long userId) {
		return getDAOFactory().getUserDAO().findById(userId);
	}
	
}
