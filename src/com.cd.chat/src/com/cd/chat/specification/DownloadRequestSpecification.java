package com.cd.chat.specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadRequestSpecification implements ISpecification<String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String REMOVE_PATTERN = "^d\\s(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
	
	public DownloadRequestSpecification() {
		pattern = Pattern.compile(REMOVE_PATTERN);
	}
	

	public boolean isSatisfiedBy(String body) {
		matcher = pattern.matcher(body);
		return matcher.matches();
	}

}
