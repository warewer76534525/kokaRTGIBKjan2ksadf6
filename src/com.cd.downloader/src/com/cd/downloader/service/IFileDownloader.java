package com.cd.downloader.service;

import java.io.IOException;
import java.net.MalformedURLException;

import com.cd.message.DownlodRequest;


public interface IFileDownloader {
	public void download(DownlodRequest downloadRequest) throws MalformedURLException, IOException;
}
