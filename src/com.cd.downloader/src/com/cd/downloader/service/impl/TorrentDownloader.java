package com.cd.downloader.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.gudy.azureus2.core3.disk.DiskManagerFileInfo;
import org.gudy.azureus2.core3.download.DownloadManager;
import org.gudy.azureus2.core3.download.DownloadManagerListener;
import org.gudy.azureus2.core3.global.GlobalManager;
import org.springframework.beans.factory.annotation.Value;

import com.aelitis.azureus.core.AzureusCore;
import com.aelitis.azureus.core.AzureusCoreException;
import com.aelitis.azureus.core.AzureusCoreFactory;
import com.cd.downloader.service.IFileDownloader;
import com.cd.message.DownloadFile;

public class TorrentDownloader implements IFileDownloader {
	
	@Value("${downloaded.dir}")
	private String _downloadedDir;

	private DownloadStateListener listener;
	private static AzureusCore core;
	
	public TorrentDownloader(String downloadedDir) {
		_downloadedDir = downloadedDir;
		listener = new DownloadStateListener();
	}


	@Override
	public void download(DownloadFile downloadRequest) throws MalformedURLException, IOException {
		String url = downloadRequest.getUrl();
		
		
		//If not set, it defaults to the user's home directory.
		System.setProperty("azureus.config.path", "run-environment/az-config");
		
		
		if (AzureusCoreFactory.isCoreAvailable()) { 
			core = AzureusCoreFactory.getSingleton();
		} else {
			core = AzureusCoreFactory.create();
			core.start();
		}
		
		System.out.println("Attempting to download torrent at : " + url);
		
		File downloadedTorrentFile = downloadTorrentFile(new URL(url));
		
		System.out.println("Completed download of : " + url);
		System.out.println("File stored as : " + downloadedTorrentFile.getAbsolutePath());
		
		File downloadDirectory = new File(_downloadedDir);
		if(downloadDirectory.exists() == false) downloadDirectory.mkdir();
		
		//Start the download of the torrent 
		GlobalManager globalManager = core.getGlobalManager();
		DownloadManager manager = globalManager.addDownloadManager(downloadedTorrentFile.getAbsolutePath(),
				   downloadDirectory.getAbsolutePath());
				
		manager.addListener(listener);		
		globalManager.startAllDownloads();
	}
	
	
	/**
	 * A listener class that will print out information to the system console
	 * on the current status of the torrent data file download.
	 */
	private class DownloadStateListener implements DownloadManagerListener{

		public void stateChanged(DownloadManager manager, int state) {
			switch(state){
				case DownloadManager.STATE_CHECKING :
					System.out.println("Checking....");
				break;
				case DownloadManager.STATE_DOWNLOADING :
					System.out.println("Downloading....");
					
					//Start a new daemon thread periodically check 
					//the progress of the upload and print it out 
					//to the command line
					Runnable checkAndPrintProgress = new Runnable(){
						@SuppressWarnings("unchecked")
						public void run(){
							try{
								boolean downloadCompleted = false;
								while(!downloadCompleted){
									AzureusCore core = AzureusCoreFactory.getSingleton();
									List<DownloadManager> managers = core.getGlobalManager().getDownloadManagers();

									//There is only one in the queue.
									DownloadManager man = managers.get(0);
									System.out.println("Download is " + 
													   (man.getStats().getCompleted() / 10.0) + 
													   " % complete");
									downloadCompleted = man.isDownloadComplete(true);
									//Check every 10 seconds on the progress
									Thread.sleep(10000);
								}

							}catch(Exception e){
								throw new RuntimeException(e);
							}

						}
					};
					
					Thread progressChecker = new Thread(checkAndPrintProgress);
					progressChecker.setDaemon(true);
					progressChecker.start();
				break;
				case DownloadManager.STATE_FINISHING :
					System.out.println("Finishing Download....");
				break;
				case DownloadManager.STATE_SEEDING:
					System.out.println("Download Complete - Seeding for other users....");
				break;
				case DownloadManager.STATE_STOPPED:
					System.out.println("Download Stopped.");
				break;
			}
		}
		
		public void downloadComplete(DownloadManager manager) {
			System.out.println("Download Completed - Exiting.....");
			AzureusCore core = AzureusCoreFactory.getSingleton();
			try{
				core.requestStop();
			}catch(AzureusCoreException aze){
				System.out.println("Could not end Azureus session gracefully - " +
								   "forcing exit.....");
				core.stop();
			}
		}
		
		public void filePriorityChanged(DownloadManager download, DiskManagerFileInfo file) {}
		public void positionChanged(DownloadManager download, int oldPosition, int newPosition) {}
		public void completionChanged(DownloadManager manager, boolean bCompleted) {}
	}

	
	/**
	 * A utility method used to download the torrent file from the 
	 * tracker / torrent file hosting server.
	 * 
	 * @return the downloaded torrent file. This is a temporary file.
	 */
	private File downloadTorrentFile(URL torrentFileURL){
		
		InputStream ins = null; 

		OutputStream ous = null;
		BufferedOutputStream bufOus = null;
		File downloadedTorrentFile = null; 
		
		try{
			downloadedTorrentFile = File.createTempFile("torrentDownload", "torrent");
			ins = torrentFileURL.openStream();
			byte[] buffer = new byte[4096];
			
			ous = new FileOutputStream(downloadedTorrentFile);
			bufOus = new BufferedOutputStream(ous);
			
			int rd = 0;
			
			while((rd = ins.read(buffer)) != -1){
				bufOus.write(buffer,0,rd);
			}
			
		}catch(IOException e){
			throw new RuntimeException("Could not download torrent file", e);
		}finally{
			try {
				if(ins != null) ins.close();
				if(bufOus != null) bufOus.close();
				if(ous != null) ous.close();
			} catch (IOException e) {
				throw new RuntimeException("Could not download torrent file", e);
			}
		}
		return downloadedTorrentFile;
	}
}
