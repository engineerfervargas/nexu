package mx.nexu.challenge.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import mx.nexu.challenge.exception.BrandException;
import mx.nexu.challenge.exception.ModelException;

@Slf4j
@RestControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler({BrandException.class})
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseHandler handler(BrandException ex) {
		log.error(ex.getMessage());
		return ResponseHandler.builder().status(HttpStatus.NOT_ACCEPTABLE.value()).message(ex.getMessage()).build();
	}
	
	
	@ExceptionHandler({ModelException.class})
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public ResponseHandler handler(ModelException ex) {
		log.error(ex.getMessage());
		return ResponseHandler.builder().status(HttpStatus.NOT_ACCEPTABLE.value()).message(ex.getMessage()).build();
	}
}
