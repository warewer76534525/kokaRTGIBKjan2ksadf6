package downloader;

import java.io.File;

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
public class When_single_download_request_arrive {
	
	@Autowired
	private IDownloadManager _downloadManager;
	private DownloadFile _downloadRequest;
	
	@Value("${downloaded.dir}")
	private String downloadedDir;
	
	private final static String URL = "http://static.ak.fbcdn.net/rsrc.php/v1/zx/r/Pvubnmvx_0f.png"; 
	
	@Before
	public void setUp() {
		_downloadRequest = new DownloadFile("sembiring.adi@gmail.com", URL);
	}
	
	@Test
	public void should_able_to_download_request() {
		_downloadManager.queueDownloadRequest(_downloadRequest);
		File downloadedFile = new File(downloadedDir, _downloadRequest.getFileName());
		
		System.out.println(downloadedFile.getPath());
		Assert.assertTrue(downloadedFile.exists());
	}
}	
