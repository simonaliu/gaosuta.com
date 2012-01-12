package org.varkrs.sociality.local.web.controllers.record;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.RecordDAO;
import org.varkrs.sociality.local.jpa.entities.Record;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/record/findListByUserId.do")
@SessionAttributes(SessionConstants.VIEW_AUTHORITY_ID)
public class RecordFindListByUserIdController extends BaseViewAuthorityController {
	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.VIEW_AUTHORITY_ID) Object viewAuthorityId, 
			HttpSession session, long userId, int pageNum, int pageCapacity) {
		
		ModelAndView mv = getModelAndView();
		if(!resourcePermited(session, userId))
			return StateUtils.setUnauthorized(mv); //非所指用户资源
		
		RecordDAO dao = getDAOFactory().getRecordDAO();
		List<Record> records = dao.findByUserId(userId, pageNum, pageCapacity);
		if(records != null) {
			mv.addObject("records", records);
			return StateUtils.setSuccess(mv);
		} else
			return StateUtils.setUnknowError(mv);
	}
}
