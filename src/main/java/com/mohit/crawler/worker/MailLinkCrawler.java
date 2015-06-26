package com.mohit.crawler.worker;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mohit.crawler.constants.MailCrawlerPropertiesBean;
import com.mohit.crawler.dataobject.MailLinkCrawlerDataObject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.logging.Logger;

public class MailLinkCrawler implements Runnable {

	String urlToCrawl=null;
	ApplicationContext context = new ClassPathXmlApplicationContext(
			"MailLinkCrawlerBeans.xml");

	MailCrawlerPropertiesBean mailCrawlerpropertiesBean = (MailCrawlerPropertiesBean) context.getBean("mailLinkCrawlerProperties");
	private MailLinkCrawlerDataObject sharedDataObject=new MailLinkCrawlerDataObject();
	public MailLinkCrawler( MailLinkCrawlerDataObject dataObject ){
		this.sharedDataObject=dataObject;
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
		boolean isNotEmpty = !sharedDataObject.getLinksToVisit().isEmpty();
		if(!isNotEmpty){
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return !sharedDataObject.getLinksToVisit().isEmpty();
	}


	private void crawlingLogic() {
		System.out.println("Starting " + Thread.currentThread().getName());
		urlToCrawl=sharedDataObject.getNextLinkToVisit();
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
				Elements urls = doc.select("a[href*="+mailCrawlerpropertiesBean.getYear()+"]");
				for(Element s:urls){
					String href=s.attr("abs:href");
					if(href.toLowerCase().contains("thread")|| href.toLowerCase().contains("author"))
						continue;
					if(!href.contains("http://mail-archives.apache.org")) continue;
					if (sharedDataObject.getLinksToVisit().contains(href)) continue;
					if(! sharedDataObject.isAlreadyVisitedLink(href)){
						sharedDataObject.addLinksToVisit(href);}
					if(s.text().contains("View raw message")){sharedDataObject.addToMailsToDownload(href);}

				}

				sharedDataObject.addToVisitedLinks(urlToCrawl);
				//System.out.println("visited set size"+manager.getVisitedLinks().size()+"queue size "+manager.getLinksToVisit().size()+"downloadmail links = "+manager.getMailsToDownload().size());
				logger.info("crawled and fetched links from "+urlToCrawl);

			}
		}

	}
}
