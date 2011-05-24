package com.cd.downloader.service;

import java.io.IOException;
import java.net.MalformedURLException;

import com.cd.message.DownloadFile;


public interface IFileDownloader {
	public void download(DownloadFile downloadRequest) throws MalformedURLException, IOException;
}
