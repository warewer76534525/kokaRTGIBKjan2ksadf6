package com.cd.downloader.service;

import com.cd.message.DownlodRequest;
import com.cd.message.RemoveDownloaded;

public interface IDownloadManager {
	void queueDownloadRequest(DownlodRequest downloadRequest );
	void removeDownloadedFile(RemoveDownloaded removeDownloaded );
}
