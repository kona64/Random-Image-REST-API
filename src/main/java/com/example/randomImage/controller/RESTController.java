package com.example.randomImage.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.randomImage.bucket.BucketWrapper;
import com.example.randomImage.errorJSON.ErrorJSON;
import com.example.randomImage.errorJSON.NoTokensLeftException;
import com.example.randomImage.extensionToMediaType.ExtensionToMediaType;
import com.example.randomImage.fileManager.DirectoryManager;
import com.example.randomImage.fileManager.NoDirectoryFoundException;

@RestController
public class RESTController {
		
	DirectoryManager directoryManager = new DirectoryManager();
	@Autowired
	BucketWrapper bucket;
	
	@GetMapping(value = "/{apiName}")
	public ResponseEntity<byte[]> getImageWithMediaType(@PathVariable("apiName") String apiName)  throws IOException, NoDirectoryFoundException, NoTokensLeftException {
		if (bucket.tryConsume() == false) {
			throw new NoTokensLeftException();
		}
		String imagePath = null;
		imagePath = directoryManager.getRandomFile(apiName);
		FileInputStream in = new FileInputStream(imagePath);
		
		return ResponseEntity.ok().contentType(ExtensionToMediaType.convert(imagePath)).body(IOUtils.toByteArray(in));
	}
	
	@GetMapping(value = "apiList")
	public String[] getApiList() {
		return directoryManager.getDirectories();
	}
	
	@ExceptionHandler(NoDirectoryFoundException.class)
	public ErrorJSON getNoDirectoryFoundException() {
		return new ErrorJSON("NOT_FOUND", "the api couldn't be found");
	}
	
	@ExceptionHandler(NoTokensLeftException.class)
	public ErrorJSON getNoTokensLeftException() {
		return new ErrorJSON("NO_TOKENS_LEFT", "not enough tokens to do transaction");
	}
	
}
