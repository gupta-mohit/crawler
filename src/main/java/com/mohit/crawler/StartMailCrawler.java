package com.mohit.crawler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class StartMailCrawler {

	public static void main(String args[]){

		Properties prop = new Properties();
		InputStream input = null;
		String baseUrl="";
		String year="";
		String path="";

		try {

			input = new FileInputStream("LinkCrawler.properties");

			// load a properties file
			prop.load(input);

			 baseUrl=prop.getProperty("baseUrl");
			 year=prop.getProperty("year");
			 path=prop.getProperty("pathToStoreMail");
			

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		WebCrawler mailCrawler =new MailCrawler(baseUrl,year,path);
		mailCrawler.crawl();
		mailCrawler.processCrawlData();
	}

}
