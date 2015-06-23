package com.mohit.crawler.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.logging.Logger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class UtilityFunctions {
	static Logger logger = Logger.getLogger("UtilityFunctions,java");
	public static Document doc;
    /*
     * Method to connect to any passed URL and return corresponding status codes
     */
	public static int connectURL(String webURL) throws IOException {
		Connection.Response response = Jsoup
				.connect(webURL)
				.userAgent(
						"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
						.timeout(10000).execute();

		int statusCode = response.statusCode();
		return statusCode;
	}
    /*
     * Utility method to download any HTML  document as text 
     */
	public static String getDocumentforUrl(String URL) throws IOException {

		String resultString = "";
		logger.info("connecting to mail URL ");
		int statusCode = connectURL(URL);
		if (statusCode == 200) {
			logger.info("connection successuful , downloading Document from   "+URL);
			doc = Jsoup.connect(URL).get();
			resultString = doc.text();

		}

		return resultString;
	}
    /*
     * Utility method to write text content to any file
     * takes text to write , path and filename as param
     */
	public static void writeToFile(String textToWrite, String path,String fileName) {
		Writer w = null;
		File file = new File(path);
		file.deleteOnExit();
		file.mkdir();
		file = new File(path + fileName);
		logger.info("Writing to file  "+fileName);
		FileOutputStream is;
		try {
			is = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(is);
			w = new BufferedWriter(osw);
			w.write(textToWrite);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

}
