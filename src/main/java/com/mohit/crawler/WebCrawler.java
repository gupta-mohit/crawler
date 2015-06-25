package com.mohit.crawler;

public abstract class WebCrawler {
	private String baseUrl;
	
	public WebCrawler(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	public String getBaseUrl() {
		return baseUrl;
	}
	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
	
	/*
	 * This method is used to start the crawler application
	 */
	public abstract  void crawl();
	/*
	 * This method is used to do any other processing if needed with crawled data
	 */
	public abstract void processCrawlData();

}
