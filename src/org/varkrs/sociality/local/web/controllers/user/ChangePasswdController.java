package org.varkrs.sociality.local.web.controllers.user;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.business.password.PasswordTranslator;
import org.varkrs.sociality.local.jpa.dao.UserDAO;
import org.varkrs.sociality.local.jpa.entities.Login;
import org.varkrs.sociality.local.jpa.entities.User;
import org.varkrs.sociality.local.web.controllers.BaseUserController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/user/changePasswd.do")
@SessionAttributes(SessionConstants.USER_ID)
public class ChangePasswdController extends BaseUserController {

	private final static int OLD_PASSWORD_ERROR = 1;
	
	@RequestMapping
	public ModelAndView deRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			String oldPasswd, String newPasswd) {
		User user = getUser(userId);
		ModelAndView mv = getModelAndView();
		if(!user.getLogin().getPassword().equals(oldPasswd)) {
			mv.addObject(Constants.STATE, OLD_PASSWORD_ERROR);
			return mv;
		}
		
		String userName = user.getLogin().getUserName();
		ApplicationContext ctxt = getLocalApplicationContext();
		PasswordTranslator t = (PasswordTranslator) ctxt.getBean("passwordTranslator");
		String password = t.encodeCleartext(newPasswd);
		user.setLogin(new Login(userName, password));
		UserDAO dao = getDAOFactory().getUserDAO();
		if(dao.update(user)) {
			return StateUtils.setSuccess(mv);
		} else {
			return StateUtils.setUnknowError(mv);
		}
		
	}
	
}
