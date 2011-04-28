package com.cd.chat;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class When_valid_download_arrive {
	private Pattern pattern;
	private Matcher matcher;
	private static final String REMOVE_PATTERN = "@\\s[_a-zA-Z0-9\\-\\.]*";
	
	
	@Before
	public void setUp() {
		pattern = Pattern.compile(REMOVE_PATTERN);
	}
	
	@Test
	public void testValidRequest() {
		String request = "@ data.com";
		
		matcher = pattern.matcher(request);
		Assert.assertTrue(matcher.matches());
	}
	
	@Test
	public void testValidExtRequest() {
		String request = "d adi.zip";
		
		matcher = pattern.matcher(request);
		Assert.assertTrue(matcher.matches());
	}
	
	@Test
	public void testWithoutFileRequest() {
		String request = "d ";
		
		matcher = pattern.matcher(request);
		Assert.assertTrue(matcher.matches());
	}
}
