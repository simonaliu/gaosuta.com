package org.varkrs.sociality.local.web.controllers.photo;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.dao.PhotoDAO;
import org.varkrs.sociality.local.jpa.entities.Photo;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photo/findListByPhotoAlbumId.do")
public class PhotoFindListByPhotoAlbumIdController extends BaseViewAuthorityController {
	
	@RequestMapping
	public ModelAndView doRequest(
			HttpSession session, long photoAlbumId, int pageNum, int pageCapacity) {
		PhotoAlbumDAO photoAlbumDAO = getDAOFactory().getPhotoAlbumDAO();
		PhotoAlbum photoAlbum = photoAlbumDAO.findById(photoAlbumId);
		ModelAndView mv = getModelAndView();
		if(photoAlbum == null)
			return StateUtils.setUnknowError(mv);
		if(!resourcePermited(session, photoAlbum.getUser().getId())) {
			//非所指用户资源
			return StateUtils.setUnauthorized(mv);
		}
		
		PhotoDAO dao = getDAOFactory().getPhotoDAO();
		List<Photo> photos = dao.findByPhotoAlbumId(photoAlbumId, pageNum, pageCapacity);
		if(photos != null) {
			StateUtils.setSuccess(mv);
			mv.addObject("photos", photos);
		} else
			StateUtils.setUnknowError(mv);
		
		return mv;
	}
}
