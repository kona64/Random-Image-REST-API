package com.example.randomImage.fileManager;

import java.io.File;
import java.util.ArrayList;

public class FileManager {
	private ArrayList<String> fileList = new ArrayList<String>();
	
	public FileManager(File directory) {
		for (File file : directory.listFiles()) {
			fileList.add(file.getAbsolutePath());
		}
	}
	
	public String getRandomFile() {
		int fileIndex = (int) Math.floor(Math.random() * fileList.size());
		return fileList.get(fileIndex);
	}
}
