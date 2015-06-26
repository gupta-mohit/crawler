package com.mohit.crawler.constants;

public class MailCrawlerPropertiesBean {
	
	private String year;
	private String filePath;
	private String linkToCrawl;
	
	public String getLinkToCrawl() {
		return linkToCrawl;
	}
	public void setLinkToCrawl(String linkToCrawl) {
		this.linkToCrawl = linkToCrawl;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
