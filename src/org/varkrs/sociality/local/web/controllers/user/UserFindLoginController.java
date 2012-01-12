package org.varkrs.sociality.local.web.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.web.controllers.BaseLocalController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/user/findLogin.do")
@SessionAttributes(SessionConstants.USER_ID)
public class UserFindLoginController extends BaseLocalController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) long userId) {
		ModelAndView mv = getModelAndView();
		StateUtils.setSuccess(mv);
		mv.addObject(Constants.ID, userId);
		return mv;
	}
	
}
