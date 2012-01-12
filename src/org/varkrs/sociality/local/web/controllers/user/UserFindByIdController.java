package org.varkrs.sociality.local.web.controllers.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.UserDAO;
import org.varkrs.sociality.local.jpa.entities.User;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/user/findById.do")
@SessionAttributes(SessionConstants.VIEW_AUTHORITY_ID)
public class UserFindByIdController extends BaseViewAuthorityController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.VIEW_AUTHORITY_ID) Object viewAuthorityId, 
			HttpSession session, long id) {
		UserDAO dao = getDAOFactory().getUserDAO();
		User user = dao.findById(id);
		ModelAndView mv = getModelAndView();
		if(user != null) {
			if(resourcePermited(session, user.getId())) {
				StateUtils.setSuccess(mv);
				mv.addObject("user", user);
			} else
				StateUtils.setUnauthorized(mv);//非所指用户资源
		} else
			StateUtils.setUnknowError(mv);
		return mv;
	}
	
}
