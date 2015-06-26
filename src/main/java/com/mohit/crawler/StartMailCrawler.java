package com.mohit.crawler;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mohit.crawler.constants.MailCrawlerPropertiesBean;

public class StartMailCrawler {

	public static void main(String args[]){
		
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"MailLinkCrawlerBeans.xml");

		MailCrawlerPropertiesBean mailCrawlerpropertiesBean = (MailCrawlerPropertiesBean) context.getBean("mailLinkCrawlerProperties");

		
		/*

		Properties prop = new Properties();
		InputStream input = null;
		String linkToCrawl="";

		try {

			input = new FileInputStream("LinkCrawler.properties");
			// load a properties file
			prop.load(input);
			linkToCrawl=prop.getProperty("linkToCrawl");

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
*/
		WebCrawler mailCrawler =new MailCrawler(mailCrawlerpropertiesBean.getLinkToCrawl());
		mailCrawler.crawl();
		mailCrawler.processCrawlData();
	}

}
