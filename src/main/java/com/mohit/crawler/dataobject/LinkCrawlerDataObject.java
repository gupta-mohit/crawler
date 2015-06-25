package com.mohit.crawler.dataobject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class LinkCrawlerDataObject extends CrawlerDataObject{


	private Set<String> mailsToDownload= Collections.synchronizedSet(new HashSet<String>());
    
	public LinkCrawlerDataObject() {
		}
	public LinkCrawlerDataObject(String baseUrl) {
		super(baseUrl);
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



}
