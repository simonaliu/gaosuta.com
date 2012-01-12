package org.varkrs.sociality.local.web.controllers;

import org.springframework.context.ApplicationContext;
import org.varkrs.sociality.local.business.password.PasswordTranslator;

/*在扩展BaseLocalController的基础上, 增加了密码转译的特性.
 * 
 */
public class BaseLoginController extends BaseLocalController {
	protected String encodeCleartext(String cleartext) {
		ApplicationContext ctxt = getLocalApplicationContext();
		PasswordTranslator t = (PasswordTranslator) ctxt.getBean("passwordTranslator");
		return t.encodeCleartext(cleartext);
	}
}
