package mx.nexu.challenge.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ModelException extends Exception{
	
	private String message;

}
