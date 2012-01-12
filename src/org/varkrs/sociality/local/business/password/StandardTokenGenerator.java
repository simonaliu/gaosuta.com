package org.varkrs.sociality.local.business.password;


public class StandardTokenGenerator implements TokenGenerator {
	
	private PasswordTranslator translator;
	
	public StandardTokenGenerator() {}
	
	public void setTranslator(PasswordTranslator translator) {
		this.translator = translator;
	}

	public PasswordTranslator getTranslator() {
		return translator;
	}

	@Override
	public String generate(Object clientSalt, Object frameSalt) {
		return translator.encodeCleartext(getInit(clientSalt, frameSalt));
	}
	
	private String getInit(Object clientSalt, Object frameSalt) {
		String a = "";
		if(clientSalt != null)
			a += clientSalt.toString();
		if(frameSalt != null)
			a += frameSalt.toString();
		return a;
	}

}
