package org.varkrs.sociality.common.web.configurations;


/**每个json格式的内容形式为{state, content:{...}}
 * 这里就告诉你通用的state有哪些. 你看, 通用的部分基本都是负值. 
 */
public class StateCodes {
	public static final int SUCCESS = 0;
	public static final int UNKNOW_ERROR = -1;
	public static final int UNLOGIN = -2;
	public static final int UNAUTHORIZED = -3;
	public static final int UNEXISTS = -4;

	
}
