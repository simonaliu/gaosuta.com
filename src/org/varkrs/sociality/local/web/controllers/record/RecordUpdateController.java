package org.varkrs.sociality.local.web.controllers.record;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.common.web.configurations.StateCodes;
import org.varkrs.sociality.local.jpa.dao.RecordDAO;
import org.varkrs.sociality.local.jpa.entities.Record;
import org.varkrs.sociality.local.web.controllers.BaseLocalController;

@Controller
@RequestMapping("**/local/record/update.do")
@SessionAttributes(SessionConstants.USER_ID)
public class RecordUpdateController extends BaseLocalController {
	
	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			Long id, String title, String text, String imageUrl) {
		RecordDAO dao = getDAOFactory().getRecordDAO();
		ModelAndView mv = getModelAndView();
		Record record = dao.findById(id);
		if(record == null) {
			mv.addObject(Constants.STATE, StateCodes.UNEXISTS);
			return mv;
		}
		else if(!userId.equals(record.getUser().getId())) {
			mv.addObject(Constants.STATE, StateCodes.UNAUTHORIZED);
			return mv;
		} else {
			if(title != null)
				record.setTitle(title);
			if(text != null)
				record.setText(text);
			if(imageUrl != null)
				record.setImageUrl(imageUrl);
			record.setLastModified(new Date().getTime());
			dao.update(record);
			mv.addObject(Constants.STATE, StateCodes.SUCCESS);
			return mv;
		}
	}
	
}
