package com.daiyida.api.security;

import java.util.regex.Pattern;

public class RegexUrlPathMatcher{

	public static boolean pathMatchesUrl(String pattern, String url) {
		if (("/**".equals(url)) || ("**".equals(url))) {
			return true;
		}
		Pattern pattn = Pattern.compile(pattern);
		return pattn.matcher(url).matches();
	}
}