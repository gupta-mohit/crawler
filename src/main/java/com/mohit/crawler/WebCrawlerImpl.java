package com.mohit.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.mohit.crawler.queue.QueueManagerImpl;
import com.mohit.crawler.services.FileDownloaderImpl;
import com.mohit.crawler.worker.MailLinkCrawler;

public class WebCrawlerImpl implements WebCrawler {

	private String baseUrl;
	private String year;
	private String filePath;
	boolean isTaskComplete=false;

	ExecutorService executor = Executors.newFixedThreadPool(20);
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(20);
	Logger logger = Logger.getLogger("WebCrawlerImpl.class");

	public WebCrawlerImpl(String baseUrl, String year, String filePath) {
		this.baseUrl=baseUrl;
		this.filePath=filePath;

	}

	public void  crawl() {

		QueueManagerImpl queueManager = new  QueueManagerImpl(baseUrl);

		for(int i =0 ; i<50;i++){
			executor.execute(new MailLinkCrawler(queueManager));
		}

		executor.shutdown();
		try {
			executor.awaitTermination(120, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		logger.info("Downloading mails..starting mail downloader service");
		for(String s : queueManager.getMailsToDownload()){
			downloadExecutor.execute(new FileDownloaderImpl(s,filePath));
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
