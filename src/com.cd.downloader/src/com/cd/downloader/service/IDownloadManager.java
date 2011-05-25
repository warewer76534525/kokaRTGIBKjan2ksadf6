package com.cd.downloader.service;

import com.cd.message.DownloadFile;
import com.cd.message.RemoveFile;

public interface IDownloadManager {
	void queueDownloadRequest(DownloadFile downloadRequest );
	void removeDownloadedFile(RemoveFile removeDownloaded );
	
	void queueTorrentDownloadRequest(DownloadFile downloadRequest);
}
