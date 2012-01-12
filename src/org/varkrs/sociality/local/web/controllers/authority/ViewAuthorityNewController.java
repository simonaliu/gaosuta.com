package org.varkrs.sociality.local.web.controllers.authority;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.business.password.PasswordTranslator;
import org.varkrs.sociality.local.jpa.dao.ViewAuthorityDAO;
import org.varkrs.sociality.local.jpa.entities.ViewAuthority;
import org.varkrs.sociality.local.web.controllers.BaseUserController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/viewAuthority/new.do")
@SessionAttributes(SessionConstants.USER_ID)
public class ViewAuthorityNewController extends BaseUserController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			String description, String password) {
		ModelAndView mv = getModelAndView();
		ViewAuthority authority = new ViewAuthority();
		authority.setDescription(description);
		PasswordTranslator translator = (PasswordTranslator) 
			getLocalApplicationContext().getBean("passwordTranslator");
		authority.setPassword(translator.encodeCleartext(password));
		authority.setUser(getUser(userId));
		ViewAuthorityDAO dao = getDAOFactory().getViewAuthorityDAO();
		Long id = dao.insert(authority);
		if(id != null) {
			StateUtils.setSuccess(mv);
			mv.addObject(Constants.ID, id);
		} else
			StateUtils.setUnknowError(mv);
		return mv;
	}
	
}
