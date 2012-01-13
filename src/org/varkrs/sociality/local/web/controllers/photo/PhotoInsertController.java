package org.varkrs.sociality.local.web.controllers.photo;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.SessionConstants;
import org.varkrs.sociality.local.business.resources.PhotoResourceLocator;
import org.varkrs.sociality.local.business.resources.ProjectResource;
import org.varkrs.sociality.local.jpa.dao.PhotoAlbumDAO;
import org.varkrs.sociality.local.jpa.dao.PhotoDAO;
import org.varkrs.sociality.local.jpa.entities.Photo;
import org.varkrs.sociality.local.jpa.entities.PhotoAlbum;
import org.varkrs.sociality.local.web.controllers.BaseUserController;
import org.varkrs.sociality.local.web.controllers.utils.MultipartUtils;
import org.varkrs.sociality.local.web.controllers.utils.MyIOUtils;
import org.varkrs.sociality.local.web.controllers.utils.StateUtils;

@Controller
@RequestMapping("**/local/photo/insert.do")
@SessionAttributes(SessionConstants.USER_ID)
public class PhotoInsertController extends BaseUserController {

	@RequestMapping
	public ModelAndView doRequest(HttpServletResponse response, 
			@ModelAttribute(SessionConstants.USER_ID) Long userId,
			long photoAlbumId, String title, String description,
			@RequestParam(required = false) MultipartFile file) throws IOException {
		
		response.setContentType("text/plain");
		ModelAndView mv = getModelAndView();
		
		/*根据指定ID查找相册, 如果没有找到, 返回, 并报告错误
		 * 
		 */
		PhotoAlbumDAO photoAlbumDAO = getDAOFactory().getPhotoAlbumDAO();
		PhotoAlbum photoAlbum = photoAlbumDAO.findById(photoAlbumId);
		if(photoAlbum == null) {
			StateUtils.setUnknowError(mv);
			return mv;
		}
		
		/*查看当前修改的相册是否属于已登录用户
		 * 
		 */
		if(!userId.equals(photoAlbum.getUser().getId())) {
			StateUtils.setUnauthorized(mv);
			return mv;
		}
		
		/*先将相片初稿插入数据库
		 * 
		 */
		Photo photo = new Photo();
		photo.setTitle(title);
		photo.setDescription(description);
		long currentTime = System.currentTimeMillis();
		photo.setGenerateTime(currentTime);
		photo.setLastModified(currentTime);
		photo.setPhotoAlbum(photoAlbum);
		PhotoDAO photoDAO = getDAOFactory().getPhotoDAO();
		Long photoId = photoDAO.insert(photo);
		if(photoId == null) {
			StateUtils.setUnknowError(mv);
			return mv;
		}
		else photo.setId(photoId);	//设置相片ID为刚插入值
		
		//获取相关的ProjectResource
		PhotoResourceLocator resourceLocator = (PhotoResourceLocator) getLocalApplicationContext()
		.getBean("photoResources");
		ProjectResource photoResource = resourceLocator.getPhotoResource(
				userId, photoAlbumId, photoId, MultipartUtils.getPostfix(file));
		ProjectResource previewResource = resourceLocator.getPreviewResource(
				userId, photoAlbumId, photoId, MultipartUtils.getPostfix(file));
		
		//写入本地磁盘
		File photoFile = new File(photoResource.getLocalFilePath());
		MyIOUtils.write(file, photoFile);
		//生成缩略图
		Thumbnails.of(photoFile)
			.size(160, 160)
			.toFile(previewResource.getLocalFilePath());
		
		//更新数据库
		photo.setUrl(photoResource.getDatabaseStored());
		photo.setPreviewUrl(previewResource.getDatabaseStored());
		if(photoDAO.update(photo)) {
			StateUtils.setSuccess(mv);
			mv.addObject(Constants.ID, photo.getId());
			return mv;
		} else
			return StateUtils.setUnknowError(mv);
	}
	
	protected static ModelAndView getModelAndView() {
//		return BaseLocalController.getModelAndView();
		return new ModelAndView("json-but-text-view");
	}

}
