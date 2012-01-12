package org.varkrs.sociality.local.web.controllers.photoAlbum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.dao.UserDAO;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.jpa.entities.User;
import org.varkrs.sociality.local.web.controllers.BaseUserController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photoAlbum/setWork.do")
@SessionAttributes(SessionConstants.USER_ID)
public class SetWorkPhotoAlbumController extends BaseUserController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			@RequestParam("id") long photoAlbumId) {
		PhotoAlbumDAO photoAlbumDAO = getDAOFactory().getPhotoAlbumDAO();
		PhotoAlbum photoAlbum = photoAlbumDAO.findById(photoAlbumId);
		User user = getUser(userId);
		ModelAndView mv = getModelAndView();
		if(!user.equals(photoAlbum.getUser())) {
			return StateUtils.setUnauthorized(mv);
		} else {
			user.setWorkPhotoAlbum(photoAlbum);
			UserDAO userDAO = getDAOFactory().getUserDAO();
			if(userDAO.update(user))
				return StateUtils.setSuccess(mv);
			else
				return StateUtils.setUnknowError(mv);
		}
	}
	
}
