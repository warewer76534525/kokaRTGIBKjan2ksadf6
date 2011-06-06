package com.cd.chat.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WebPageChecker {
	@Value("${web.url}")
	private String _url;
	
	protected Log log = LogFactory.getLog(getClass());

	public WebPageChecker() {
		
	}

	public WebPageChecker(String url) {
		_url = url;
	}

	public boolean check(String spec) {
		URL web;
		BufferedReader in = null;
		try {
			web = new URL(_url);

			in = new BufferedReader(new InputStreamReader(web.openStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				if (inputLine.contains(spec)) {
					return true;
				}
			}	
		} catch (Exception e) {
			
		} finally {
			try {
				in.close();
			} catch (Exception e) {
			}
		}

		return false;
	}
}
