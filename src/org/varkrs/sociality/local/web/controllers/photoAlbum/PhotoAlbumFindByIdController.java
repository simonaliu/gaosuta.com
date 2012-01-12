package org.varkrs.sociality.local.web.controllers.photoAlbum;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.StateCodes;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photoAlbum/findById.do")
public class PhotoAlbumFindByIdController extends BaseViewAuthorityController {

	@RequestMapping
	public ModelAndView doRequest(
			HttpSession session, long id) {
		ModelAndView mv = getModelAndView();
		PhotoAlbumDAO dao = getDAOFactory().getPhotoAlbumDAO();
		PhotoAlbum photoAlbum = dao.findById(id);
		if(photoAlbum != null) {
			if(resourcePermited(session, photoAlbum.getUser().getId())) {
				mv.addObject(Constants.STATE, StateCodes.SUCCESS);
				mv.addObject("photoAlbum", photoAlbum);
			} else 
				StateUtils.setUnauthorized(mv);//非所指用户资源
		} else {
			StateUtils.setUnknowError(mv);
		}
		
		return mv;
	}

}
