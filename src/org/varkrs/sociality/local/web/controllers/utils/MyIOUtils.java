package org.varkrs.sociality.local.web.controllers.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class MyIOUtils {
	
	public static void write(MultipartFile multipartFile, File file) throws IOException {
		createFile(file);
		write(multipartFile.getBytes(), file);
	}
	
	public static void write(byte[] bytes, File file) throws IOException {
		try {
			createFile(file);
			OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			out.write(bytes);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static void createFile(File file) throws IOException {
		File parent = file.getParentFile();
		if(!parent.exists())
			parent.mkdirs();
		file.createNewFile();
	}
	
}
