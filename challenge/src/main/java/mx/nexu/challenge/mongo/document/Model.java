package mx.nexu.challenge.mongo.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "models")
public class Model {
	
	@MongoId(value = FieldType.STRING)
	@Field("_id")
	private String id;
	private String name;
	private double average_price;
	@JsonIgnore
	private String brand_name;
	@JsonIgnore
	@Field("brand_uuid")
	private String brandUuid;

}
