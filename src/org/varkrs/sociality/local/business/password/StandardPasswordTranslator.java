package org.varkrs.sociality.local.business.password;

import org.springframework.security.authentication.encoding.PasswordEncoder;

public class StandardPasswordTranslator implements PasswordTranslator {

	private PasswordEncoder encoder;
	private String key;
	
	@Override
	public String encodeCleartext(String cleartext) {
		return encoder.encodePassword(cleartext, key);
	}

	public void setEncoder(PasswordEncoder encoder) {
		this.encoder = encoder;
	}

	public PasswordEncoder getEncoder() {
		return encoder;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
