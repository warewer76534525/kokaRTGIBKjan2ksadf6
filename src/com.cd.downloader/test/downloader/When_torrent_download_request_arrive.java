package downloader;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.cd.downloader.service.impl.TorrentDownloader;
import com.cd.message.DownloadFile;



public class When_torrent_download_request_arrive {
	
	private TorrentDownloader downloader; 
	
	private String downloadedDir = "D:/software/javalib/bittorrent/torrentexample/downloads";
	private final static String URL = "http://torrents.thepiratebay.org/6200401/How_to_Analyze_People_on_Sight-Mantesh.6200401.TPB.torrent";
	private DownloadFile downloadRequest;
	
	@Before
	public void setUp() {
		downloadRequest = new DownloadFile("sembiring.adi@gmail.com", URL);
		downloader = new TorrentDownloader(downloadedDir);
	}
	
	@Test
	public void should_able_to_download_request() throws InterruptedException, MalformedURLException, IOException {
		downloader.download(downloadRequest);
	}
	
}	
