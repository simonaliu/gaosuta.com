package org.varkrs.sociality.local.web.controllers.examples;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("**/test/multipart.do")
public class MultipartFileController {

	@RequestMapping
	public String doRequest(@RequestParam(required=false)MultipartFile file) {
		System.out.println(file);
		return "default-view";
	}
	
}
