package com.mohit.crawler.servics;

public interface FileDownloader {
	/*
	 *This method will download any HTML URL as text file  to given path
	 */
	public void downloadLink(String Url ,String path);

}
