package project.com.cognifyz.rateexception;

@SuppressWarnings("serial")
public class RateLimitException extends RuntimeException {
	public RateLimitException(String message) {
		super(message);
	}

}
