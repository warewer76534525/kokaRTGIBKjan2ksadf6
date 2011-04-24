package com.cd.downloader.service;

import com.cd.message.DownlodRequest;

public interface IDownloadManager {
	void queueDownloadRequest(DownlodRequest downloadRequest );
}
