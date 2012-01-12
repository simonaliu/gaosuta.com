package org.varkrs.sociality.common.web.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.varkrs.sociality.common.web.configurations.StateCodes;

public class DefaultExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception e) {
		ModelAndView mv = new ModelAndView("json-view");
		String state = "state";
		
		if(e instanceof HttpSessionRequiredException) {
			//未登录
			mv.addObject(state, StateCodes.UNLOGIN);
		} else {
			e.printStackTrace();
			mv.addObject(state, StateCodes.UNKNOW_ERROR);
		}
		
		return mv;
	}

}
