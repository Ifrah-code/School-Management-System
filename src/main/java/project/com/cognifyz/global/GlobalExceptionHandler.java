package project.com.cognifyz.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import project.com.cognifyz.rateexception.RateLimitException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(RateLimitException.class)

	public ResponseEntity<String> handleRateLimit(RateLimitException ex){
		return new ResponseEntity<>("Too many requests – try again later.",HttpStatus.TOO_MANY_REQUESTS);
		
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGeneralException(Exception ex){
		return new ResponseEntity<>("An error occured: "+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
