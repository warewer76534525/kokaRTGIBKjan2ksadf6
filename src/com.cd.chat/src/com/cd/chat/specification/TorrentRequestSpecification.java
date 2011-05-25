package com.cd.chat.specification;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TorrentRequestSpecification implements ISpecification<String> {
	private Pattern pattern;
	private Matcher matcher;
	private static final String TORRENT_PATTERN = "^td\\s(http|https)://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?()%&=]*)?";
	
	public TorrentRequestSpecification() {
		pattern = Pattern.compile(TORRENT_PATTERN);
	}
	

	public boolean isSatisfiedBy(String body) {
		matcher = pattern.matcher(body);
		return matcher.matches();
	}

}
