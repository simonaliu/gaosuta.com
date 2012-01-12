package org.varkrs.sociality.local.business.resources;

import java.io.File;

/**PhotoResourceLocator的默认实现
 * 
 * @author lenovo
 *
 */
public class SimplePhotoResourceLocator implements PhotoResourceLocator {
	
	@Override
	public ProjectResource getPhotoResource(long userId, long photoAlbumId,
			long photoId, String postfix) {
		File photosDir = photosDir(userId, photoAlbumId);
		File file = new File(photosDir, photoId + "-a." + postfix);
		return new ProjectRelativePathResource(getPath(file));
	}

	@Override
	public ProjectResource getPreviewResource(long userId, long photoAlbumId,
			long photoId, String postfix) {
		File photosDir = photosDir(userId, photoAlbumId);
		File file = new File(photosDir, photoId + "-b." + postfix);
		return new ProjectRelativePathResource(getPath(file));
	}
	
	private File photosDir(long userId, long photoAlbumId) {
		File resourceDir = ResourceUtils.relativeResourcesDir();
		return new File(resourceDir, "photos/" + userId + "/" + photoAlbumId + "/");
	}

	private String getPath(File file) {
		String path = file.getPath();
		return path.replaceAll("\\\\", "/");
	}

}
