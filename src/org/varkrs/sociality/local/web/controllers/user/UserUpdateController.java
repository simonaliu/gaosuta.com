package org.varkrs.sociality.local.web.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.UserDAO;
import org.varkrs.sociality.local.jpa.entities.User;
import org.varkrs.sociality.local.web.controllers.BaseUserController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/user/update.do")
@SessionAttributes(SessionConstants.USER_ID)
public class UserUpdateController extends BaseUserController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			String nickName, String headPhotoUrl) {
		User user = getUser(userId);
		if(nickName != null)
			user.setNickName(nickName);
		if(headPhotoUrl != null)
			user.setPhotoUrl(headPhotoUrl);
		UserDAO dao = getDAOFactory().getUserDAO();
		ModelAndView mv = getModelAndView();
		if(dao.update(user))
			return StateUtils.setSuccess(mv);
		else
			return StateUtils.setUnknowError(mv);
	}
	
}
