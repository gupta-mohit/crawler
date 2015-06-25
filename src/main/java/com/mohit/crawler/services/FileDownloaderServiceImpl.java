package com.mohit.crawler.services;
import java.io.IOException;
import java.util.logging.Logger;

import com.mohit.crawler.utils.FileOperationUtils;
import com.mohit.crawler.utils.HtmlParserUtils;

/*
 * A generic thread class to download any file of from a particular URL
 */
public class FileDownloaderServiceImpl implements FileDownloaderService,Runnable{
	String urlToDownload;
	String path;
	public FileDownloaderServiceImpl(String urlToDownload,String path){
		this.urlToDownload=urlToDownload;
		this.path=path;
	}

	Logger logger = Logger.getLogger("UrlDownloader.java");
	public void run() {
        System.out.println(urlToDownload);
		downloadLink(urlToDownload,path);
	}
	public void downloadLink(String urlToDownload,String path) {

		String docText = "";
		int statusCode=0;
		logger.info("connecting to download URL ");

		try {
			statusCode = HtmlParserUtils.connectURL(urlToDownload);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (statusCode == 200) {
			logger.info("connection suuccessuful , downloading document from   "+urlToDownload);
			try {
				docText = HtmlParserUtils.getDocumentforUrl(urlToDownload);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		FileOperationUtils.writeToFile(docText ,path ,urlToDownload.replace('/', '_'));

	}

}
