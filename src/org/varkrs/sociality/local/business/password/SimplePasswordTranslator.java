package org.varkrs.sociality.local.business.password;

/**PasswordTranslator的默认实现
 * 
 * @author lenovo
 *
 */
public class SimplePasswordTranslator implements PasswordTranslator {

	@Override
	public String encodeCleartext(String cleartext) {
		return cleartext;
	}

}
