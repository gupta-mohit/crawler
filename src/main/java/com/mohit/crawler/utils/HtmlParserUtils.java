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

public class HtmlParserUtils {
	static Logger logger = Logger.getLogger("HtmlParserUtils.java");
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
    
}
