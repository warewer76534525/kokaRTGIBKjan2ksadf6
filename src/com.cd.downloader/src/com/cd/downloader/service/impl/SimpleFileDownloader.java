package com.cd.downloader.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;

import com.cd.downloader.service.IFileDownloader;
import com.cd.message.DownlodaRequest;

public class SimpleFileDownloader implements IFileDownloader {
	
	@Value("${downloaded.dir}")
	private String _downloadedDir;

	
	public SimpleFileDownloader(String downloadedDir) {
		_downloadedDir = downloadedDir;
	}


	@Override
	public void download(DownlodaRequest downloadRequest) throws MalformedURLException, IOException {
		URL url = new URL(downloadRequest.getUrl());
		FileUtils.copyURLToFile(url, new File(_downloadedDir , downloadRequest.getFileName()));
	}
	
}
