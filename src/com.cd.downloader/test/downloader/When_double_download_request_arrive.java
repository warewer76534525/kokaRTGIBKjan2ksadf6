package downloader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cd.downloader.service.IDownloadManager;
import com.cd.message.DownloadFile;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/downloader-context.xml")
public class When_double_download_request_arrive {
	
	@Autowired
	private IDownloadManager _downloadManager;
	private List<DownloadFile> _downloadRequests = new ArrayList<DownloadFile>();
	
	@Value("${downloaded.dir}")
	private String downloadedDir;
	
	private final static String URL1 = "http://www.rollstream.com/assets/cs/cs_owensminor_2009.pdf";
	private final static String URL2 = "http://static.springsource.org/spring/docs/3.0.x/spring-framework-reference/pdf/spring-framework-reference.pdf";
	
	@Before
	public void setUp() {
		DownloadFile downloadRequest1 = new DownloadFile("sembiring.adi@gmail.com", URL1);
		DownloadFile downloadRequest2 = new DownloadFile("sembiring.adi@gmail.com", URL2);
		
		_downloadRequests.add(downloadRequest1);
		_downloadRequests.add(downloadRequest2);
	}
	
	@Test
	public void should_able_to_download_request() throws InterruptedException {
		for (DownloadFile downloadRequest : _downloadRequests) {
			_downloadManager.queueDownloadRequest(downloadRequest);
			File downloadedFile = new File(downloadedDir, downloadRequest.getFileName());
			
			Thread.sleep(5 * 1000);
			System.out.println(downloadedFile.getPath());
			Assert.assertTrue(downloadedFile.exists());	
		}	
	}
	
}	
