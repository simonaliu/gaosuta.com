package org.varkrs.sociality.local.web.controllers.record;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.common.web.configurations.StateCodes;
import org.varkrs.sociality.local.jpa.dao.RecordDAO;
import org.varkrs.sociality.local.jpa.entities.Record;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/record/findById.do")
@SessionAttributes(SessionConstants.VIEW_AUTHORITY_ID)
public class RecordFindByIdController extends BaseViewAuthorityController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.VIEW_AUTHORITY_ID) Object viewAuthorityId, 
			HttpSession session, long id) {
		ModelAndView mv = getModelAndView();
		RecordDAO dao = (RecordDAO) getLocalApplicationContext().getBean("recordDAO");
		Record record = dao.findById(id);
		
		if(record != null) {
			if(resourcePermited(session, record.getUser().getId())) {
				mv.addObject(Constants.STATE, StateCodes.SUCCESS);
				mv.addObject(Constants.RECORD, record);
			} else
				StateUtils.setUnauthorized(mv);//非所指用户资源
		} else
			mv.addObject(Constants.STATE, StateCodes.UNKNOW_ERROR);
		return mv;
	}
	
}
