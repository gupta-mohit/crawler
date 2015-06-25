package com.mohit.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.mohit.crawler.dataobject.LinkCrawlerDataObject;
import com.mohit.crawler.services.FileDownloaderServiceImpl;
import com.mohit.crawler.worker.MailLinkCrawler;

public class MailCrawler extends WebCrawler {


	private String year;
	private String filePath;
	boolean isTaskComplete=false;

	ExecutorService executor = Executors.newFixedThreadPool(30);
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(20);
	Logger logger = Logger.getLogger("WebCrawlerImpl.class");

	public MailCrawler(String baseUrl, String year, String filePath) {
		super(baseUrl);
		this.filePath=filePath;
		this.year=year;
		linkCrawlerDataObject.setYear(year);
	}
	
	LinkCrawlerDataObject linkCrawlerDataObject = new  LinkCrawlerDataObject(getBaseUrl(),year);

	public void  crawl() {

		for(int i =0 ; i<50;i++){
			executor.execute(new MailLinkCrawler(linkCrawlerDataObject));
		}

		executor.shutdown();
		try {
			executor.awaitTermination(120, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		logger.info("finished crawling");
	}

	public void processCrawlData() {
		downloadMails();

	}

	private void downloadMails() {
		logger.info("Downloading mails..starting mail downloader service");
		for(String s : linkCrawlerDataObject.getMailsToDownload()){
			downloadExecutor.execute(new FileDownloaderServiceImpl(s,filePath));
		}
		downloadExecutor.shutdown();
		try {
			downloadExecutor.awaitTermination(120, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("finished downloading mails");
	}



}
