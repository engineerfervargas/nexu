package mx.nexu.challenge.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mx.nexu.challenge.mongo.document.Brand;
import mx.nexu.challenge.mongo.document.Model;

@Configuration
public class ChallengeConfig {

	@Bean(name = "models")
	public List<Model> getModels() throws JsonParseException, JsonMappingException, IOException {
		String pathResource = "/models.json";
		InputStream resource = getClass().getResourceAsStream(pathResource);
		ObjectMapper mapper = new ObjectMapper();
		List<Model> models = (List<Model>)mapper.readValue(resource, new TypeReference<List<Model>>(){});
		return models;
	}
	
	@Bean(name = "brands")
	public List<Brand> getBrands(List<Model> models) {
		return models.stream().collect(
				ArrayList<Brand>::new, 
				(list, item) -> {
					Brand brand = list.stream().filter(x -> x.getName() == item.getBrand_name()).findAny().orElse(null);
					if(brand == null) {
						brand = Brand.builder()
								.id(UUID.randomUUID().toString())
								.name(item.getBrand_name())
								.average_price(0)
								.total(0)
								.quantity(0)
								.build();
						list.add(brand);
					}
					item.setId(UUID.randomUUID().toString());
					item.setBrandUuid(brand.getId());
					brand.setQuantity(brand.getQuantity() + 1);
					brand.setTotal(brand.getTotal() + item.getAverage_price());
					brand.setAverage_price(brand.getTotal()/brand.getQuantity());
				}, 
				(list1, list2) -> {}
			);
	}
	
}
