package mx.nexu.challenge.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import mx.nexu.challenge.exception.BrandException;
import mx.nexu.challenge.mongo.document.Brand;
import mx.nexu.challenge.mongo.document.Model;
import mx.nexu.challenge.mongo.repository.BrandRepository;
import mx.nexu.challenge.mongo.repository.ModelRepository;
import mx.nexu.challenge.web.request.AddBrandRequest;

@SpringBootTest
public class BrandServiceTest {
	
	@MockBean
	private BrandRepository repository;
	
	@MockBean
	private ModelRepository modelRepository;
	
	@Autowired
	private BrandService service;
	
	@Autowired
	private List<Brand> brands;
	
	@Autowired
	private List<Model> models;
	
	@Test
	public void getBrands() {
		Mockito.when(repository.findAll()).thenReturn(brands);
		List<Brand> result = service.getBrands();
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void getModels() {
		Brand brand = brands.get(brands.size() -1);
		List<Model> filterModels = models.stream().filter(item -> item.getBrandUuid() == brand.getId()).collect(Collectors.toList());
		Mockito.when(modelRepository.findByBrandUuid(brand.getId())).thenReturn(filterModels);
		List<Model> result = service.getModels(brand.getId());
		assertTrue(result.size() > 0);
	}
	
	@Test
	public void AddBrand() {
		AddBrandRequest request = AddBrandRequest.builder().name("Toyota") .build();
		Optional<Brand> brand = brands.stream().filter(x -> x.getName() == request.getName()).findAny();
		Mockito.when(repository.findByName(request.getName())).thenReturn(brand);
		try {
			service.addBrand(request);
		} catch (BrandException e) {
			// TODO Auto-generated catch block
			assertTrue(e.getMessage() == "Brand: " + request.getName() + " already exist!");
		}
	}
	

}
