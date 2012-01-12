package org.varkrs.sociality.local.web.controllers.user;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.StateCodes;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.dao.UserDAO;
import org.varkrs.sociality.local.jpa.entities.Login;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.jpa.entities.User;
import org.varkrs.sociality.local.web.controllers.BaseLoginController;

@Controller
@RequestMapping("**/local/user/register.do")
public class RegisterController extends BaseLoginController {

	private static final int USER_EXISTS = 1;
	
	@RequestMapping
	public ModelAndView doRequest(String userName, String password,
			String nickName, String headPhotoUrl) {

		ModelAndView mv = getModelAndView();
		UserDAO userDAO = getDAOFactory().getUserDAO();
		Login login = new Login(userName, encodeCleartext(password));
		if(userDAO.findByUserName(userName) != null) {
			mv.addObject(Constants.STATE, USER_EXISTS);
			return mv;
		}
		
		User user = new User();
		user.setLogin(login);
		user.setNickName(nickName);
		user.setPhotoUrl(headPhotoUrl);
		
		//设置默认相册
		PhotoAlbum initPhotoAlbum = new PhotoAlbum();
		initPhotoAlbum.setTitle("默认相册");
		initPhotoAlbum.setDescription("这是一个用户管理的默认存储相册");
		initPhotoAlbum.setGenerateTime(new Date().getTime());
		PhotoAlbumDAO photoAlbumDAO = getDAOFactory().getPhotoAlbumDAO();
		initPhotoAlbum.setId(photoAlbumDAO.insert(initPhotoAlbum));
		
		user.setWorkPhotoAlbum(initPhotoAlbum);
		Long id = userDAO.insert(user);
		
		if (id != null) {
			user.setId(id);
			initPhotoAlbum.setUser(user);
			photoAlbumDAO.update(initPhotoAlbum);
			mv.addObject(Constants.STATE, StateCodes.SUCCESS);
			mv.addObject(Constants.ID, id);
		} else
			mv.addObject(Constants.STATE, StateCodes.UNKNOW_ERROR);

		return mv;
	}

}
