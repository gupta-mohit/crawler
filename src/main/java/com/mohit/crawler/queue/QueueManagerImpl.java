package com.mohit.crawler.queue;

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArraySet;

public class QueueManagerImpl implements QueueManager{

	//private Queue<String> linksToVisit= new ArrayBlockingQueue<String>(2000);
	private Queue<String> linksToVisit= new ConcurrentLinkedQueue<String>();
	private Set<String> visitedLinks=Collections.synchronizedSet(new HashSet<String>());
	private Set<String> mailsToDownload= Collections.synchronizedSet(new HashSet<String>());

	public QueueManagerImpl() {
	}

	public QueueManagerImpl(String linksToVisit) {
		this.linksToVisit.offer(linksToVisit);
	}


	public Queue<String> getLinksToVisit() {
		return linksToVisit;
	}
	public void setLinksToVisit(Queue<String> linksToVisit) {
		this.linksToVisit = linksToVisit;
	}
	public Set<String> getVisitedLinks() {
		return visitedLinks;
	}
	public void setVisitedLinks(Set<String> visitedLinks) {
		this.visitedLinks = visitedLinks;
	}

	private Set<String> getMailsToDownload() {
		return mailsToDownload;
	}

	private void setMailsToDownload(Set<String> mailsToDownload) {
		this.mailsToDownload = mailsToDownload;
	}

	public void addLinksToVisit(String url) {
		linksToVisit.offer(url);
	}

	// Get URL at front of queue
	public String getNextLinkToVisit() {
		return linksToVisit.poll();
	}

	// Links to visit size
	public int links_to_visit_size() {
		return linksToVisit.size();
	}

	// Add visited URL
	public void addToVisitedLinks(String url) {
		visitedLinks.add(url);
	}

	// Checks if link has already been visited
	public boolean isAlreadyVisitedLink(String link) {

		synchronized(this.visitedLinks){
			for(String s : visitedLinks) {
				if(s.equals(link)) {
					return true;
				}
			}   }
		return false;
	}

	public void addToMailsToDownload(String url) {
		mailsToDownload.add(url);

	}



}
