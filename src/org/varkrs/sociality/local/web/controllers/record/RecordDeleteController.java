package org.varkrs.sociality.local.web.controllers.record;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.RecordDAO;
import org.varkrs.sociality.local.jpa.entities.Record;
import org.varkrs.sociality.local.web.controllers.BaseLocalController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/record/delete.do")
@SessionAttributes(SessionConstants.USER_ID)
public class RecordDeleteController extends BaseLocalController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId
			, Long id) {
		RecordDAO dao = getDAOFactory().getRecordDAO();
		ModelAndView mv = getModelAndView();
		Record record = dao.findById(id);
		if(!userId.equals(record.getUser().getId())) 
			StateUtils.setUnauthorized(mv);
		else if(dao.delete(record))
			StateUtils.setSuccess(mv);
		else
			StateUtils.setUnknowError(mv);
		
		return mv;
	}
	
}
