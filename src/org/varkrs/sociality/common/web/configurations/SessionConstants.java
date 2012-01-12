package org.varkrs.sociality.common.web.configurations;

/**如果你想知道HttpSession里面存取的是什么, 这里就可以找到答案. 
 * HttpSession里什么也没有存, 只有已经登录的用户ID. 
 * 它的键值名称存在域USER_ID中.
 * 
 */
public class SessionConstants {
	public static final String USER_ID = "userId";
	
	public static final String VIEW_AUTHORITY_ID = "viewAuthorityId";
}
