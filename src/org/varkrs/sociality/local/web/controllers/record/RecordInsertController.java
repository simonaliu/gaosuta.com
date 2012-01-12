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
import org.varkrs.sociality.local.web.controllers.BaseUserController;

@Controller
@RequestMapping("**/local/record/insert.do")
@SessionAttributes(SessionConstants.USER_ID)
public class RecordInsertController extends BaseUserController {
	
	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			String title, String text, String imageUrl) {
		ModelAndView mv = getModelAndView();
		if(userId == null) {
			mv.addObject(Constants.STATE, StateCodes.UNLOGIN);
			return mv;
		} else {
			Record record = new Record();
			long currentTime = new Date().getTime();
			record.setGenerateTime(currentTime);
			record.setImageUrl(imageUrl);
			record.setLastModified(currentTime);
			record.setText(text);
			record.setTitle(title);
			record.setUser(getUser(userId));
			
			RecordDAO dao = getDAOFactory().getRecordDAO();
			Long id = dao.insert(record);
			if(id != null) {
				mv.addObject(Constants.STATE, StateCodes.SUCCESS);
				mv.addObject(Constants.ID, id);
			} else {
				mv.addObject(Constants.STATE, StateCodes.UNKNOW_ERROR);
			}
			
			return mv;
		}
	}
}
