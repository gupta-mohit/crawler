package com.mohit.crawler.worker;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.mohit.crawler.queue.QueueManager;
import com.mohit.crawler.queue.QueueManagerImpl;
import java.util.logging.Logger;

public class MailLinkCrawler implements Runnable {

	String urlToCrawl=null;
	private QueueManager manager=new QueueManagerImpl();
	public MailLinkCrawler( QueueManagerImpl queueManager ){
		this.manager=queueManager;
	}
	Document doc;
	Logger logger = Logger.getLogger("LinkCrawler.java");
	public void run() {

		while(isNotEmpty()){
			crawlingLogic();
		}
		System.out.println("Exiting thread " + Thread.currentThread());
	}


	private boolean isNotEmpty() {
		boolean isNotEmpty = !manager.getLinksToVisit().isEmpty();
		if(!isNotEmpty){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return !manager.getLinksToVisit().isEmpty();
	}


	private void crawlingLogic() {
		System.out.println("Starting " + Thread.currentThread().getName());
		urlToCrawl=manager.getNextLinkToVisit();
		if(urlToCrawl != null)
		{

			logger.info("Fetching links from URL "+urlToCrawl);
			Connection.Response response=null;
			try {
				response = Jsoup
						.connect(urlToCrawl)
						.userAgent(
								"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
								.timeout(10000).execute();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			int statusCode = response.statusCode();
			if (statusCode == 200) {

				try {
					doc = Jsoup.connect(urlToCrawl).get();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Elements urls = doc.select("a[href*=2015]");
				for(Element s:urls){
					String href=s.attr("abs:href");
					if(href.toLowerCase().contains("thread")|| href.toLowerCase().contains("author"))
						continue;
					if(!href.contains("http://mail-archives.apache.org")) continue;
					if (manager.getLinksToVisit().contains(href)) continue;
					if(! manager.isAlreadyVisitedLink(href)){
						manager.addLinksToVisit(href);}
					if(s.text().contains("View raw message")){manager.addToMailsToDownload(href);}

				}

				manager.addToVisitedLinks(urlToCrawl);
				System.out.println("visited set size"+manager.getVisitedLinks().size()+"queue size "+manager.getLinksToVisit().size()+"downloadmail links = "+manager.getMailsToDownload().size());
				logger.info("crawled and fetched links from "+urlToCrawl);

			}
		}

	}
}
