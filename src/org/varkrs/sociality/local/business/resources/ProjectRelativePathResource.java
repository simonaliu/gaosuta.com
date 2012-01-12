package org.varkrs.sociality.local.business.resources;

/**相对路径(相对于项目根目录)的表示的资源.
 * 
 * @author lenovo
 *
 */
public class ProjectRelativePathResource implements ProjectResource {
	private final String basePath;
	
	public ProjectRelativePathResource(String path) {
		basePath = path;
	}

	@Override
	public String getDatabaseStored() {
		return basePath;
	}

	@Override
	public String getRemoteUrl() {
		return ResourceUtils.absoluteURLOfProjectRelativePath(basePath);
	}

	@Override
	public String getLocalFilePath() {
		return ResourceUtils.absoluteFilePathOfProjectRelativePath(basePath);
	}
}
