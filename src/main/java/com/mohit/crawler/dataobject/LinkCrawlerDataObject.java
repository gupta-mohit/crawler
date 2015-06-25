package com.mohit.crawler.dataobject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class LinkCrawlerDataObject extends CrawlerDataObject{


	private Set<String> mailsToDownload= Collections.synchronizedSet(new HashSet<String>());
    private String year;
	
    public String getYear() {
		return year;
	}
	
	
	public LinkCrawlerDataObject() {
		}
	public LinkCrawlerDataObject(String baseUrl,String year) {
		super(baseUrl);
		this.year=year;
	}
	

	public Set<String> getMailsToDownload() {
		return mailsToDownload;
	}

	private void setMailsToDownload(Set<String> mailsToDownload) {
		this.mailsToDownload = mailsToDownload;
	}

	public void addToMailsToDownload(String url) {
		mailsToDownload.add(url);

	}


	public void setYear(String year) {
		this.year=year;
		
	}



}
