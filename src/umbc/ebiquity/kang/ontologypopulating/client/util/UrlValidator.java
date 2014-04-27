package umbc.ebiquity.kang.ontologypopulating.client.util;

import com.google.gwt.regexp.shared.RegExp;

public class UrlValidator {
	
	/***
	 * Validate if a string is a valid URL
	 * @param url - string version of the URL
	 * @return true if the string is a valid URL
	 */
	public static boolean isValidUrl(String url) {
		
		RegExp namespaceValidator = RegExp.compile(ValidatorFactory.getRegExpForNamespace());
		return namespaceValidator.test(url);
	}
	

}
