package com.mohit.crawler;

public abstract class WebCrawler {
	private String linkToCrawl;
	
	public WebCrawler(String linkToCrawl) {
		this.linkToCrawl = linkToCrawl;
	}
	public String getLinkToCrawl() {
		return linkToCrawl;
	}
	public void setLinkToCrawl(String linkToCrawl) {
		this.linkToCrawl = linkToCrawl;
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
