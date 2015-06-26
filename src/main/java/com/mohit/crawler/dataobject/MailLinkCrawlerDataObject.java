package com.mohit.crawler.dataobject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class MailLinkCrawlerDataObject extends CrawlerDataObject{


	private Set<String> mailsToDownload= Collections.synchronizedSet(new HashSet<String>());




	public MailLinkCrawlerDataObject(String linkToCrawl) {
		super(linkToCrawl);

	}


	public MailLinkCrawlerDataObject() {
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
