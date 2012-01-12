package org.varkrs.sociality.local.web.controllers.photoAlbum;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photoAlbum/findListByUserId.do")
public class PhotoAlbumFindListByUserIdController extends BaseViewAuthorityController {
	@RequestMapping
	public ModelAndView doRequest(
			HttpSession session, long userId, int pageNum, int pageCapacity) {
		
		ModelAndView mv = getModelAndView();
		if(!resourcePermited(session, userId))
			return StateUtils.setUnauthorized(mv); //非所指用户资源
		
		PhotoAlbumDAO dao = getDAOFactory().getPhotoAlbumDAO();
		List<PhotoAlbum> photoAlbums = dao.findByUserId(userId, pageNum, pageCapacity);
		if(photoAlbums != null) {
			mv.addObject("photoAlbums", photoAlbums);
			return StateUtils.setSuccess(mv);
		} else
			return StateUtils.setUnknowError(mv);
	}
}
