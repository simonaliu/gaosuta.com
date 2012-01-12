package org.varkrs.sociality.local.web.controllers.photoAlbum;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.web.controllers.BaseUserController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photoAlbum/insert.do")
@SessionAttributes(SessionConstants.USER_ID)
public class PhotoAlbumInsertController extends BaseUserController {

	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.USER_ID) Long userId, 
			String title, String description) {
		PhotoAlbum photoAlbum = new PhotoAlbum();
		photoAlbum.setTitle(title);
		photoAlbum.setDescription(description);
		long currentTime = System.currentTimeMillis();
		photoAlbum.setGenerateTime(currentTime);
		photoAlbum.setUser(getUser(userId));
		PhotoAlbumDAO dao = getDAOFactory().getPhotoAlbumDAO();
		Long id = dao.insert(photoAlbum);
		ModelAndView mv = getModelAndView();
		if(id != null) {
			mv.addObject(Constants.ID, id);
			return StateUtils.setSuccess(mv);
		} else
			return StateUtils.setUnknowError(mv);
	}
	
}
