package com.mohit.crawler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mohit.crawler.constants.MailCrawlerPropertiesBean;
import com.mohit.crawler.dataobject.MailLinkCrawlerDataObject;
import com.mohit.crawler.services.FileDownloaderServiceImpl;
import com.mohit.crawler.worker.MailLinkCrawler;

public class MailCrawler extends WebCrawler {
	
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"MailLinkCrawlerBeans.xml");
    MailCrawlerPropertiesBean mailCrawlerpropertiesBean = (MailCrawlerPropertiesBean) context.getBean("mailLinkCrawlerProperties");


    ExecutorService executor = Executors.newFixedThreadPool(30);
	ExecutorService downloadExecutor = Executors.newFixedThreadPool(20);
	Logger logger = Logger.getLogger("WebCrawlerImpl.class");

	public MailCrawler(String linkToCrawl) {
		super(linkToCrawl);
	}
	
	MailLinkCrawlerDataObject mailLinkCrawlerDataObject = new  MailLinkCrawlerDataObject(getLinkToCrawl());

	public void  crawl() {
         
		for(int i =0 ; i<50;i++){
			executor.execute(new MailLinkCrawler(mailLinkCrawlerDataObject));
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
		
		for(String s : mailLinkCrawlerDataObject.getMailsToDownload()){
			downloadExecutor.execute(new FileDownloaderServiceImpl(s,mailCrawlerpropertiesBean.getFilePath()));
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
