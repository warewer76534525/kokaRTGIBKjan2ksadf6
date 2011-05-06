package com.cd.chat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class When_download_request_arrive {
	private Pattern pattern;
	private Matcher matcher;
	private static final String REMOVE_PATTERN = "^d\\s(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
	
	
	@Before
	public void setUp() {
		pattern = Pattern.compile(REMOVE_PATTERN);
	}
	
	@Test
	public void testValidRequest() {
		String request = "d http://www.sstatic.net/stackoverflow/img/sprites.png";
		
		matcher = pattern.matcher(request);
		Assert.assertTrue(matcher.matches());
	}
	
	@Test
	public void testHttpsRequest() {
		String request = "d https://sstatic.net/stackoverflow/img/sprites.png";
		
		matcher = pattern.matcher(request);
		Assert.assertTrue(matcher.matches());
	}
	
	@Test
	public void testHttpxRequest() {
		String request = "d httpx://sstatic.net/stackoverflow/img/sprites.png";
		
		matcher = pattern.matcher(request);
		Assert.assertTrue(matcher.matches());
	}
}
