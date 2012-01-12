package org.varkrs.sociality.common.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class EmptyView implements View {

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public void render(Map<String, ?> arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) throws Exception {
	}

}
