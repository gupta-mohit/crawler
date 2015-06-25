package com.mohit.crawler.services;

public interface FileDownloaderService {
	/*
	 *This method will download any HTML URL as text file  to given path
	 */
	public void downloadLink(String Url ,String path);

}
