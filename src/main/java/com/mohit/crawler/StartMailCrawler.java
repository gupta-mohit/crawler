package com.mohit.crawler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mohit.crawler.constants.MailCrawlerPropertiesBean;

public class StartMailCrawler {

	public static void main(String args[]){
		ApplicationContext context = new ClassPathXmlApplicationContext("MailLinkCrawlerBeans.xml");
		MailCrawlerPropertiesBean mailCrawlerpropertiesBean = (MailCrawlerPropertiesBean) context.getBean("mailLinkCrawlerProperties");

		WebCrawler mailCrawler =new MailCrawler(mailCrawlerpropertiesBean.getLinkToCrawl());
		mailCrawler.crawl();
		mailCrawler.processCrawlData();
	}

}
