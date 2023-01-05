package com.example.randomImage.extensionToMediaType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

public class ExtensionToMediaType {
	static HashMap<String, MediaType> extensionToMediaType = new HashMap<String, MediaType>();
	
	static {
		extensionToMediaType.put("jpeg", MediaType.IMAGE_JPEG);
		extensionToMediaType.put("jpg", MediaType.IMAGE_JPEG);
		extensionToMediaType.put("gif", MediaType.IMAGE_GIF);
		extensionToMediaType.put("png", MediaType.IMAGE_PNG);
	}
	
	
	private static String getExtension(String filename) {
		for (int i = filename.length() - 1; i >= 0; --i) {		
			if (filename.charAt(i) == '.') {
				return filename.substring(i + 1, filename.length());
			}
		}
		return null;
	}
	
	public static MediaType convert(String filename) {
		return extensionToMediaType.get(getExtension(filename));
	}
	
	
	@Test
	public void testGetExtension() {
		assertEquals("txt", getExtension("test.txt"));
		assertEquals("jpeg", getExtension("test.file.jpeg"));
	}
	
	@Test
	public void testGetMediaTypeFromExtension() {
		assertEquals(MediaType.IMAGE_JPEG, convert("test.jpg"));
		assertEquals(MediaType.IMAGE_GIF, convert("file.file.gif"));
	}
}
