package umbc.ebiquity.kang.ontologypopulating.client.util;

public class ErrorMessageHandler {

	public static String getErrorMessage(Throwable caught) {

		String message = caught.getMessage();
//		if (caught instanceof InternalServerErrorException) {
//			message = getErrorMessage(((InternalServerErrorException) caught).getMessage());
//		}
//		else if (caught instanceof InvalidUrlException) {
//			message = getErrorMessage(((InvalidUrlException) caught).getMessage());
//		}
//		else if (caught instanceof JSONMessageErrorException) {
//			message = getErrorMessage(((JSONMessageErrorException) caught).getMessage());
//		}
//		else if (caught instanceof ConflictException) {
//			message = getErrorMessage(((ConflictException) caught).getMessage());
//		}
//		else if (caught instanceof JSONParseException) {
//			message = getErrorMessage(((JSONParseException) caught).getMessage());
//		}
//		else if (caught instanceof JSONConvertingException) {
//			message = getErrorMessage(((JSONConvertingException) caught).getMessage());
//		}
//		else if (caught instanceof LoginException) {
//			message = getErrorMessage(((LoginException) caught).getMessage());
//		} else {
//			message = getErrorMessage(message);
//		}

		message = getErrorMessage(message);
		return message;
	}
	
	public static String getErrorMessage(String error) {
		return "<span style=\"color: red \">" + error + "</span>";
	}

}