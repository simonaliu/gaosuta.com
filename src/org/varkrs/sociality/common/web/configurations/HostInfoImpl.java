package org.varkrs.sociality.common.web.configurations;

/**HostInfo的默认实现
 * 
 * @author lenovo
 *
 */
public class HostInfoImpl implements HostInfo {

	private String protocal;
	private String domainName;
	
	public HostInfoImpl() {}

	@Override
	public String protocal() {
		return protocal;
	}

	@Override
	public String domainName() {
		return domainName;
	}

	public void setProtocal(String protocal) {
		this.protocal = protocal;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}
