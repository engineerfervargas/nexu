package mx.nexu.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandException extends Exception{
	
	private String message;

}
