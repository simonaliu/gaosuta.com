package org.varkrs.sociality.local.business.resources;

/**一种项目资源的表示, 以实现不同场合下路径的表现. 
 * 有三种场合:
 * <li><ol>存储在数据库中的值: 一种相对表示, 以"/"开头或者一个相对地址(看具体实现). </ol>
 * 		<ol>远程引用的URL: 需要加上诸如"http://localhost等信息"</ol>
 * 		<ol>本地全路径</ol>
 * </li>
 */
public interface ProjectResource {
	
	String getDatabaseStored();
	
	String getRemoteUrl();
	
	String getLocalFilePath();
	
}
