package org.varkrs.sociality.local.web.controllers.photo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.PhotoDAO;
import org.varkrs.sociality.local.jpa.entities.Photo;
import org.varkrs.sociality.local.web.controllers.BaseLocalController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photo/delete.do")
@SessionAttributes(SessionConstants.USER_ID)
public class PhotoDeleteController extends BaseLocalController {

	@RequestMapping
	public ModelAndView doRequest(
			@ModelAttribute(SessionConstants.USER_ID) Long userId, long id) {
		PhotoDAO dao = getDAOFactory().getPhotoDAO();
		ModelAndView mv = getModelAndView();
		Photo photo = dao.findById(id);
		if(photo == null)
			StateUtils.setUnknowError(mv);
		else if(!userId.equals(photo.getPhotoAlbum().getUser().getId())) 
			StateUtils.setUnauthorized(mv);
		else if(dao.delete(photo))
			StateUtils.setSuccess(mv);
		else
			StateUtils.setUnknowError(mv);
		
		return mv;
	}
	
}
