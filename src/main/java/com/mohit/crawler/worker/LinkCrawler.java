package com.mohit.crawler.worker;

import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.mohit.crawler.constants.ApplicationConstants;
import com.mohit.crawler.queue.QueueManager;
import com.mohit.crawler.queue.QueueManagerImpl;
import java.util.logging.Logger;

public class LinkCrawler implements Runnable {

	String urlToCrawl;
	private QueueManager manager=new QueueManagerImpl();
	public LinkCrawler( QueueManagerImpl queueManager ){
		this.manager=queueManager;
	}
	Document doc;
	Logger logger = Logger.getLogger("LinkCrawler.java");
	public void run() {
		urlToCrawl=manager.getNextLinkToVisit();
		logger.info("Fetching links from URL "+urlToCrawl);
		Connection.Response response=null;
		try {
			response = Jsoup
					.connect(urlToCrawl)
					.userAgent(
							"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21")
							.timeout(10000).execute();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		int statusCode = response.statusCode();
		if (statusCode == 200) {

			try {
				doc = Jsoup.connect(urlToCrawl).get();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Elements urls = doc.select("a[href*=2014]");
			for(Element s:urls){
				//if(!s.attr("abs:href").contains("http")){
					String href=s.attr("abs:href");
					if(href.toLowerCase().contains("thread")|| href.toLowerCase().contains("author"))
						continue;
					if (manager.getLinksToVisit().contains(href)) continue;
					if(! manager.isAlreadyVisitedLink(href)){
						manager.addLinksToVisit(href);}

					if(href.contains("2014")&& href.contains("raw"))
					{manager.addToMailsToDownload(href);
					}
					//}
			}
			manager.addToVisitedLinks(urlToCrawl);
			logger.info("crawled and fetched links from "+urlToCrawl);

		}

	}
}
