package mx.nexu.challenge.web.request;

import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddModelRequest {
	private String name;
	private Optional<Double> average_price; 
}
