package com.example.randomImage.fileManager;

import java.beans.JavaBean;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

public class DirectoryManager {
	
	HashMap<String, FileManager> fileManagers = new HashMap<String, FileManager>();

	public DirectoryManager() {
		try {
			File directory = new ClassPathResource("static/apis").getFile();
			for (File file : directory.listFiles()) {
				if (file.isDirectory()) {
					fileManagers.put(file.getName(), new FileManager(file));
				}
			}
		}
		catch (NullPointerException e) {
			System.out.println("Null Pointer Exception Error");
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			System.out.println("Check that apiDirectory.path is Available in application.yml");
			e.printStackTrace();
		}
		catch (IOException e) {
			System.out.println("File not found. Check application.yml and make sure the path is correct");
			e.printStackTrace();
		}
	}
	
	public String getRandomFile(String directoryPath) throws NoDirectoryFoundException {
		if (!fileManagers.containsKey(directoryPath)) {
			throw new NoDirectoryFoundException();
		}
		else {
			return fileManagers.get(directoryPath).getRandomFile();
		}
	}
	
	public String[] getDirectories() {
		Set<String> set = fileManagers.keySet();
		String[] ret = set.toArray(new String[set.size()]);
		return ret;
	}
}
