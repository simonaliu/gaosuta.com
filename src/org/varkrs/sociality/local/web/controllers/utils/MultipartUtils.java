package org.varkrs.sociality.local.web.controllers.utils;

import org.springframework.web.multipart.MultipartFile;

public class MultipartUtils {
	
	public static String getPostfix(MultipartFile file) {
		return getPostfixFromFileName(getOriginalNameFromMultipartFile(file));
	}
	
	private static String getOriginalNameFromMultipartFile(MultipartFile file) {
		return file.getOriginalFilename();
	}

	private static String getPostfixFromFileName(String fileName) {
		int k = fileName.lastIndexOf(".");
		if(k > 0)
			return fileName.substring(k + 1);
		else
			return "";
	}
	
}
