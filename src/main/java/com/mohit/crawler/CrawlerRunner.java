package com.mohit.crawler;

public class CrawlerRunner {

	public static void main(String args[]){

		WebCrawler crawler=new WebCrawlerImpl("http://mail-archives.apache.org/mod_mbox/maven-users/","2015","/home/mohitg/Documents/mails");
		crawler.crawl();
	}

}
