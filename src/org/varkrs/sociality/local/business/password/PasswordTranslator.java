package org.varkrs.sociality.local.business.password;

/**将明文密码转译成加密形式的接口定义
 * 
 * @author lenovo
 *
 */
public interface PasswordTranslator {
	public String encodeCleartext(String cleartext);
}
