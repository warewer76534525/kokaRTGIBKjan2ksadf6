package chat.downloader.service.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import chat.downloader.service.IFileDownloader;

@Service
public class SimpleFileDownloader implements IFileDownloader {
	
	@Value("${downloaded.dir}")
	private String downloadedDir;

	
	@Override
	public void download(String urlStr) throws MalformedURLException, IOException {
		URL url = new URL(urlStr);
		String fileName = new File(url.getFile()).getName();
		
		FileUtils.copyURLToFile(new URL(urlStr), new File(downloadedDir , fileName));
	}
	
}
