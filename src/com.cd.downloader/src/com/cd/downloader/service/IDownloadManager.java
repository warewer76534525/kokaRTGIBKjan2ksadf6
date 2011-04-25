package com.cd.downloader.service;

import com.cd.message.DownlodaRequest;

public interface IDownloadManager {
	void queueDownloadRequest(DownlodaRequest downloadRequest );
}
