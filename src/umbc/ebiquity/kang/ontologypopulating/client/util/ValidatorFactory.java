package umbc.ebiquity.kang.ontologypopulating.client.util;

import com.smartgwt.client.widgets.form.validator.RegExpValidator;
import com.smartgwt.client.widgets.form.validator.Validator;

public class ValidatorFactory {
	
	public static Validator getNamespaceValidator() {
		RegExpValidator regExpValidator = new RegExpValidator();
		regExpValidator.setExpression("^((ftp|http|https)://[\\w@.\\-\\_]+(:\\d{1,5})?(/[\\w#!:.?+=&%@!\\_\\-/]+)*){1}/$");
		regExpValidator.setErrorMessage("The namespace is not valid");
		return regExpValidator;
	}

	public static Validator getTermNameValidator() {
		return null;
	}
	
	public static String getRegExpForNamespace(){
		return "^((ftp|http|https)://[\\w@.\\-\\_]+\\.[a-zA-Z]{2,}(:\\d{1,5})?(/[\\w#!:.?+=&%@!\\_\\-/]+)*){1}$";
	}
	
	public static Validator getItemNameValidator(){
		
		RegExpValidator regExpValidator = new RegExpValidator();
		regExpValidator.setExpression("^[a-zA-Z][a-zA-Z0-9_-]+$");
		regExpValidator.setErrorMessage("The name is not valid. Only letters, numbers, '_' and '-' are allowed");
		return regExpValidator;
	}

}
