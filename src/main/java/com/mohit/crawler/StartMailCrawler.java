package com.mohit.crawler;

public class StartMailCrawler {

	public static void main(String args[]){

		WebCrawler mailCrawler =new MailCrawler("http://mail-archives.apache.org/mod_mbox/maven-users/","2015","C:\\Users\\mohit\\Documents\\mails");
		mailCrawler.crawl();
		mailCrawler.processCrawlData();
	}

}
