package com.mohit.crawler;

import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.mohit.crawler.queue.QueueManagerImpl;
import com.mohit.crawler.servics.FileDownloaderImpl;
import com.mohit.crawler.worker.LinkCrawler;

public class WebCrawlerImpl implements WebCrawler {
	
	String baseUrl;
	String year;
	ExecutorService executor = Executors.newFixedThreadPool(100);
	boolean isTaskComplete=false;
	public WebCrawlerImpl(String baseUrl, String year) {
		this.baseUrl=baseUrl;
		this.year=year;
		
	 }

	public void  crawl() {
		
		QueueManagerImpl queueManager = new QueueManagerImpl(baseUrl);
		//while(!queueManager.getLinksToVisit().isEmpty()){
		//int a=0;
			//while(a<1000){
			//executor.execute(new LinkCrawler(queueManager));
			
			
		//a++;
		//}
		
	do{
		executor.execute(new LinkCrawler(queueManager));
		
	} while(!isTaskComplete);
	
	if(!queueManager.getMailsToDownload().isEmpty())
	{  for(String s : queueManager.getMailsToDownload()){
		executor.execute(new FileDownloaderImpl(s));
		
	}
		executor.shutdown();
		
	}
	}
	


	

}
