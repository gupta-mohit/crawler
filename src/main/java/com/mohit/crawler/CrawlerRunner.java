package com.mohit.crawler;

public class CrawlerRunner {
	
	public static void main(String args[]){
		//Read base url from properties file and year 
		WebCrawler crawler=new WebCrawlerImpl("http://mail-archives.apache.org/mod_mbox/maven-users/","2014");
		crawler.crawl();
	}

}
