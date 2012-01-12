package org.varkrs.sociality.local.web.controllers.photo;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.common.web.configurations.StateCodes;
import org.varkrs.sociality.local.business.resources.ResourceUtils;
import org.varkrs.sociality.local.jpa.dao.PhotoDAO;
import org.varkrs.sociality.local.jpa.entities.Photo;
import org.varkrs.sociality.local.web.controllers.BaseViewAuthorityController;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photo/findById.do")
@SessionAttributes(SessionConstants.VIEW_AUTHORITY_ID)
public class PhotoFindByIdController extends BaseViewAuthorityController {
	
	@RequestMapping
	public ModelAndView doRequest(@ModelAttribute(SessionConstants.VIEW_AUTHORITY_ID) Object viewAuthorityId, 
			HttpSession session, long id) {
		ModelAndView mv = getModelAndView();
		PhotoDAO dao = getDAOFactory().getPhotoDAO();
		Photo photo = dao.findById(id);
		if(photo != null) {
			if(resourcePermited(session, photo.getPhotoAlbum().getUser().getId())) {
				photo.setUrl(ResourceUtils.
						absoluteURLOfProjectRelativePath(photo.getUrl()));
				photo.setPreviewUrl(ResourceUtils.
						absoluteURLOfProjectRelativePath(photo.getPreviewUrl()));
				mv.addObject(Constants.STATE, StateCodes.SUCCESS);
				mv.addObject("photo", photo);
			} else 
				StateUtils.setUnauthorized(mv);//非所指用户资源
		} else {
			StateUtils.setUnknowError(mv);
		}
		
		return mv;
	}
	
}
