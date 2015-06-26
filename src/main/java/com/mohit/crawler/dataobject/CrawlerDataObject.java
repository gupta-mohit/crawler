package com.mohit.crawler.dataobject;

import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;


public class CrawlerDataObject {
	
	public CrawlerDataObject() {
	}
	public CrawlerDataObject(String linkToCrawl){
		this.linksToVisit.offer(linkToCrawl);
	}
    
	private Queue<String> linksToVisit= new ConcurrentLinkedQueue<String>();
	private Set<String> visitedLinks=Collections.synchronizedSet(new HashSet<String>());

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
	//Next link to visit
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
		if (this.visitedLinks.contains(link)) return true;
		return false;
	}
	public void addLinksToVisit(String url) {
		linksToVisit.offer(url);
	}

}
