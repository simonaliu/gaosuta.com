package org.varkrs.sociality.local.business.password;

public interface TokenGenerator {
	
	String generate(Object clientSalt, Object frameSalt);
	
}
