Chat Downloader
=============

Software ini digunakan untuk mendownload file dengan cara mentriger download melalui chat GTalk ke account GTalk chat downloadernya

Software Required
-------

Untuk menjalankan software diperlukan ActiveMQ (http://activemq.apache.org/download.html)

Instalasi
------------

### Konfigurasi
Pada directory src terdapat dua service project, masing-masing project memiliki konfigurasi sendiri sendiri
* com.cd.chat\src\application.properties, yang perlu di perhatikan adalah:
	xmpp.user=gtalkuser
	xmpp.password=gtalkpassword
	
* com.cd.downloader, yang perlu di perhatikan adalah:
	downloaded.dir = D:/Downloads/cd -- dimana hasil download di simpan
	download.url = http://202.51.96.30/adyMNHYDSdfs1234/ -- dimana hasil download dapat di download

Want to contribute? Great! There are two ways to add markups.

### Generate Installer
Masuk ke directory src

	ant installer
	
ant akan menggenerate installer ke folder chatdownloader/installer. terdapat 3 jenis binary
* [bin-linux-x86-32] -- `gem install redcarpet`
* [.textile](http://www.textism.com/tools/textile/) -- `gem install RedCloth`
* bin-linux-x86-32
* bin-linux-x86-64
* bin-win-x86-32
Pilih binary yang sesuai dengan platform, lalu rename menjadi bin. mislanya bin-win-x86-32 => bin

### Install di Windows
Masuk ke directory installer -> chatdownloader/installer

Program ini akan di install di windows sebagai windows service.

Install windows service dengan menjalankan 
	$Install.bat

Jalankan service dengan menjalankan 
	$Startup.bat

### Install di Unix
Masuk ke directory installer -> chatdownloader/installer

Jalankan command
	$sh startup.sh

Usage
=============
	download file: [d url], ex. d https://d3nwyuy0nl342s.cloudfront.net/images/modules/header/logov3-hover.png
	remove file: [r url], ex. r logov3-hover.png
