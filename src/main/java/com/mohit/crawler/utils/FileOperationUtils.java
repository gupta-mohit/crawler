package com.mohit.crawler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.SocketTimeoutException;
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
			e.printStackTrace();
		}
		logger.info("successfully saved file"+fileName);
	}


}





