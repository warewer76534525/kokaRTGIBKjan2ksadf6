package com.cd.chat;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.cd.chat.service.impl.WebPageChecker;

public class while_spec_webpage {
	
	private WebPageChecker _checker;
	private String url = "http://forum.android.or.id/forumdisplay.php?12-Sony-Ericsson";
	private String spec = "Quiz Harian Sony Ericsson 1 Juni 2011";
	
	@Before
	public void setUp() throws Exception {
		_checker = new WebPageChecker(url);
	}

	@Test
	public void should_return_true() {
		Assert.assertTrue(_checker.check(spec));
	}
}
