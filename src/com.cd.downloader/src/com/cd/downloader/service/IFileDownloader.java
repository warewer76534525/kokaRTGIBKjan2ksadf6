package com.cd.downloader.service;

import java.io.IOException;
import java.net.MalformedURLException;

import com.cd.message.DownlodaRequest;


public interface IFileDownloader {
	public void download(DownlodaRequest downloadRequest) throws MalformedURLException, IOException;
}
