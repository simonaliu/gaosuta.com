package org.varkrs.sociality.local.web.controllers.user;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.web.controllers.BaseLocalController;

@Controller
@RequestMapping("**/local/user/logout.do")
public class LogoutConntroller extends BaseLocalController {
	
	@RequestMapping
	public ModelAndView doRequest(HttpSession session) {
		ModelAndView mv = getModelAndView();
		session.removeAttribute(SessionConstants.USER_ID);
		return mv;
	}
	
}
