package org.varkrs.sociality.local.jpa.dao;

import org.varkrs.sociality.local.jpa.entities.ViewAuthority;

public interface ViewAuthorityDAO {

	/**这个方式通过ViewAuthority的id来获取ViewAuthority的对象
	 * 
	 * @param id ViewAuthority的id
	 * @return ViewAuthority对象
	 */
	ViewAuthority findById(Object authorityId);
	
	/**
	 * 插入ViewAuthority实体
	 * @param ViewAuthority的对象
	 * @return 如果成功返回插入的实体的id,如果失败,返回null
	 */
	Long insert(ViewAuthority authority);

	/**
	 * 更新操作
	 * @param ViewAuthority
	 * @return 返回boolean 若成功,返回true
	 */
	boolean update(ViewAuthority authority);
}
