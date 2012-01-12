package org.varkrs.sociality.local.web.controllers.authority;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.ViewAuthorityDAO;
import org.varkrs.sociality.local.jpa.entities.ViewAuthority;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/viewAuthority/requestAuthority.do")
@SessionAttributes(SessionConstants.VIEW_AUTHORITY_ID)
public class ViewAuthorityRequestAuthorityController extends BaseViewAuthorityController {

	private final static int TOKEN_UNSET = 1;
	private final static int TOKEN_UNMATCHED = 2;
	
	@RequestMapping
	public ModelAndView doRequest(long id, String token) {
		ViewAuthority authority = getViewAuthority(id);
		ModelAndView mv = getModelAndView();
		if(authority == null)	//没有指定ID的Authority行
			return StateUtils.setUnknowError(mv);
		else if(authority.getToken() == null)	//没有预先设置Token
			return StateUtils.setState(mv, TOKEN_UNSET);
		else if(!authority.getToken().equals(token))	//Token不匹配
			return StateUtils.setState(mv, TOKEN_UNMATCHED);
		else {
			authority.setToken(null);	//Token用过, 置为null.
			ViewAuthorityDAO dao = getDAOFactory().getViewAuthorityDAO();
			if(!dao.update(authority))	//更新Authority, 即清除Token列
				return StateUtils.setUnknowError(mv);
			mv.addObject(SessionConstants.VIEW_AUTHORITY_ID, authority.getId());	//加入缓存
			return StateUtils.setSuccess(mv);
		}
	}
	
}
