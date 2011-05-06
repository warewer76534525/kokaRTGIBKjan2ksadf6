package com.cd.chat.specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RemoveRequestSpecification implements ISpecification<String> {
	private static final String REMOVE_PATTERN = "^r\\s[_a-zA-Z0-9\\-\\.]*";
	
	private Pattern pattern;
	private Matcher matcher;

	public RemoveRequestSpecification() {
		pattern = Pattern.compile(REMOVE_PATTERN);
	}

	public boolean isSatisfiedBy(String body) {
		matcher = pattern.matcher(body);
		return matcher.matches();
	}

}
