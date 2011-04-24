package com.cd.downloader.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("downloader-context.xml");
	}
}
