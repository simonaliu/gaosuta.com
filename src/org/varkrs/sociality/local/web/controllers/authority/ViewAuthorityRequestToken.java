package org.varkrs.sociality.local.web.controllers.authority;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.local.business.password.TokenGenerator;
import org.varkrs.sociality.local.jpa.dao.ViewAuthorityDAO;
import org.varkrs.sociality.local.jpa.entities.ViewAuthority;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/viewAuthority/requestToken.do")
public class ViewAuthorityRequestToken extends BaseViewAuthorityController {

	@RequestMapping
	public ModelAndView doRequest(@RequestParam("id") long viewAuthorityId, String password, String clientSalt) {
		ViewAuthority authority = getViewAuthority(viewAuthorityId);
		ModelAndView mv = getModelAndView();
		if(authority == null) {
			return StateUtils.setUnknowError(mv);
		} 
		
		TokenGenerator tokenGenerator = (TokenGenerator) getLocalApplicationContext()
			.getBean("temporaryTokenGenerator");
		String token = tokenGenerator.generate(clientSalt, System.currentTimeMillis());
		authority.setToken(token);
		ViewAuthorityDAO dao = getDAOFactory().getViewAuthorityDAO();
		if(dao.update(authority)) {
			StateUtils.setSuccess(mv);
			mv.addObject("token", token);
			return mv;
		} else {
			return StateUtils.setUnknowError(mv);
		}
	}
	
}
