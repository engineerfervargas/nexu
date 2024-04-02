package mx.nexu.challenge.mongo.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "brands")
public class Brand {
	
	@MongoId(value = FieldType.STRING)
	@Field("_id")
	private String id;
	private String name;
	@JsonIgnore
	private int quantity;
	@JsonIgnore
	private double total;
	private double average_price;

}
