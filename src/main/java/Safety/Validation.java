package Safety;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	private static Pattern pattern;
	private static Matcher matcher;

	private static final String NAME_PATTERN = "^[A-Za-z]{3,15}$";
	private static final String PASSWORD_PATTERN = "^[!@#$%&*+=:;,.?<>A-Za-z0-9_-]{6,15}$";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean nameValidation(final String text) {
		pattern = Pattern.compile(NAME_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

	public static boolean passwordValidation(final String text) {
		pattern = Pattern.compile(PASSWORD_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

	public static boolean emailValidation(final String text) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(text);
		return matcher.matches();
	}

}
