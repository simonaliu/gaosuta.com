package org.varkrs.sociality.local.web.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.common.web.configurations.StateCodes;
import org.varkrs.sociality.local.jpa.dao.UserDAO;
import org.varkrs.sociality.local.jpa.entities.Login;
import org.varkrs.sociality.local.jpa.entities.User;
import org.varkrs.sociality.local.web.controllers.BaseLoginController;

@Controller
@RequestMapping("**/local/user/login.do")
@SessionAttributes({SessionConstants.USER_ID, SessionConstants.USER_ID})
public class LoginController extends BaseLoginController {
	
	@RequestMapping
	public ModelAndView doRequest(String userName, String password) {
		UserDAO dao = getDAOFactory().getUserDAO();
		User user = null;
		ModelAndView mv = null;
		user = dao.findByLogin(new Login(userName, encodeCleartext(password)));
		mv = getModelAndView();
		if (user != null) {
			mv.addObject(Constants.STATE, StateCodes.SUCCESS);
			mv.addObject(SessionConstants.USER_ID, user.getId());
		} else
			mv.addObject(Constants.STATE, StateCodes.UNKNOW_ERROR);
		
		return mv;
	}
	
}
