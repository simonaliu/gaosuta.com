package org.varkrs.sociality.local.web.controllers.utils;

import org.springframework.web.servlet.ModelAndView;

import static org.varkrs.sociality.common.web.configurations.StateCodes.*;

public class StateUtils {
	
	private final static String STATE = "state";
	
	public static ModelAndView setSuccess(ModelAndView mv) {
		mv.addObject(STATE, SUCCESS);
		return mv;
	}
	
	public static ModelAndView setUnknowError(ModelAndView mv) {
		mv.addObject(STATE, UNKNOW_ERROR);
		return mv;
	}
	
	public static ModelAndView setUnlogin(ModelAndView mv) {
		mv.addObject(STATE, UNLOGIN);
		return mv;
	}
	
	public static ModelAndView setUnauthorized(ModelAndView mv) {
		mv.addObject(STATE, UNAUTHORIZED);
		return mv;
	}

	public static ModelAndView setUnexists(ModelAndView mv) {
		mv.addObject(STATE, UNEXISTS);
		return mv;
	}
	
	public static ModelAndView setState(ModelAndView mv, Object state) {
		mv.addObject(STATE, state);
		return mv;
	}
}
