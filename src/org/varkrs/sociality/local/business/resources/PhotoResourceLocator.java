package org.varkrs.sociality.local.business.resources;

/**图片资源的定位器, 从这里可以知道应该把图片资源放置在哪里. 
 * 
 * @author lenovo
 *
 */
public interface PhotoResourceLocator {
	
	/**相应指定ID的图片有一个配套的路径, 这样你就不同为图片要在哪个路径而操心了, 交给它吧. 
	 * 
	 * @param userId
	 * @param photoAlbumId
	 * @param photoId
	 * @param postfix
	 * @return
	 */
	ProjectResource getPhotoResource(long userId, long photoAlbumId, 
			long photoId, String postfix);

	/**相应指定ID的图片(这是缩略图版本)有一个配套的路径, 这样你就不同为图片要在哪个路径而操心了, 交给它吧.
	 * 
	 * @param userId
	 * @param photoAlbumId
	 * @param photoId
	 * @param postfix
	 * @return
	 */
	ProjectResource getPreviewResource(long userId, long photoAlbumId,
			long photoId, String postfix);
	
}
