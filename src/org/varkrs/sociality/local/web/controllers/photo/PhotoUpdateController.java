package org.varkrs.sociality.local.web.controllers.photo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.local.web.controllers.BaseLocalController;

@Controller
@RequestMapping("**/local/photo/update.do")
public class PhotoUpdateController extends BaseLocalController {

	@RequestMapping
	public ModelAndView doRequest() {
		//TODO Controller:更新图片
		throw new UnsupportedOperationException("还没有实现, 也许用不着实现");
	}
	
}
