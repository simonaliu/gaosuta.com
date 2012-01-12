package org.varkrs.sociality.local.web.controllers.photoAlbum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.web.controllers.BaseLocalController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photoAlbum/delete.do")
@SessionAttributes(SessionConstants.USER_ID)
public class PhotoAlbumDeleteController extends BaseLocalController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			long id) {
		PhotoAlbumDAO dao = getDAOFactory().getPhotoAlbumDAO();
		PhotoAlbum photoAlbum = dao.findById(id);
		ModelAndView mv = getModelAndView();
		if(!userId.equals(photoAlbum.getUser().getId()))
			return StateUtils.setUnauthorized(mv);
		else if(dao.delete(photoAlbum))
			return StateUtils.setSuccess(mv);
		else
			return StateUtils.setUnknowError(mv);
	}

}
