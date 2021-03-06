package com.mohit.crawler.utils;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;

import java.util.logging.Logger;


/*
 * Utility method to write text content to any file
 * takes text to write , path and filename as param
 */
public class FileOperationUtils {
	static Logger logger = Logger.getLogger("FileOperationUtils.java");
	public static void writeToFile(String textToWrite, String path,String fileName) {

		File directory = new File(path);
		if (!directory.exists())
			directory.mkdir();

		File file = new File(path+"/"+fileName.substring(7)+".txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				logger.info("unable to create file"+fileName.substring(7));
				e.printStackTrace();
			}
		}

		FileWriter fw;
		try {
			fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(textToWrite);
			bw.close();
		} catch (IOException e) {
			logger.info("unable to write to file "+fileName);			
			e.printStackTrace();
		}
		logger.info("successfully saved file"+fileName);
	}


}





