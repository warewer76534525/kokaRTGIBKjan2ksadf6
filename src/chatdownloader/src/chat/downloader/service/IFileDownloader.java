package chat.downloader.service;

import java.io.IOException;
import java.net.MalformedURLException;

public interface IFileDownloader {
	public void download(String url) throws MalformedURLException, IOException;
}
