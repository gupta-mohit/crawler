package com.mohit.crawler.queue;

import java.util.Queue;

public interface QueueManager {
	
	
    // Get URL at front of queue
    public String getNextLinkToVisit();

    // L to visit size
    public void addLinksToVisit(String url);

    // Add visited URL
    public void addToVisitedLinks(String url);
    
    // Add visited URL to 
    public void addToMailsToDownload(String url);
    

    // Checks if link has already been visited
    public boolean isAlreadyVisitedLink(String link);    
    
    public Queue<String> getLinksToVisit();

}
