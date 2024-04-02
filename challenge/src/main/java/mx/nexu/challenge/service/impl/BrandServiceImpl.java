package mx.nexu.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.nexu.challenge.exception.BrandException;
import mx.nexu.challenge.exception.ModelException;
import mx.nexu.challenge.mongo.document.Brand;
import mx.nexu.challenge.mongo.document.Model;
import mx.nexu.challenge.mongo.repository.BrandRepository;
import mx.nexu.challenge.mongo.repository.ModelRepository;
import mx.nexu.challenge.service.BrandService;
import mx.nexu.challenge.web.request.AddBrandRequest;
import mx.nexu.challenge.web.request.AddModelRequest;

@Service
public class BrandServiceImpl implements BrandService{
	
	@Autowired
	private BrandRepository repository;
	
	@Autowired
	private ModelRepository modelRepository;

	@Override
	public List<Brand> getBrands() {
		return repository.findAll();
	}

	@Override
	public List<Model> getModels(String uuid) {
		return modelRepository.findByBrandUuid(uuid);
	}

	@Override
	public void addBrand(AddBrandRequest request) throws BrandException {
		Optional<Brand> brand = repository.findByName(request.getName()); 
		if(brand.isPresent())
			throw new BrandException("Brand: " + request.getName() + " already exist!"); 
		Brand newBrand = Brand.builder().name(request.getName()).average_price(0).total(0).quantity(0).build();
		repository.save(newBrand);
	}

	@Override
	public void addModel(AddModelRequest request, String brandUuid) throws BrandException, ModelException {
		Optional<Brand> brand = repository.findById(brandUuid);
		if(!brand.isPresent())
			throw new BrandException("Brand: " + brandUuid + " not exist!"); 
		Optional<Model> model = modelRepository.findByNameAndBrandUuid(request.getName(), brandUuid);
		if(model.isPresent())
			throw new ModelException("Model: " + request.getName() + " already exist!");
		if( request.getAverage_price() != null && request.getAverage_price().get() < 100000)
			throw new ModelException("Model: the average price needs be greater than 100,000, your value( " + request.getAverage_price().get() + " ) not is valid!");
		Model newModel = Model.builder()
				.name(request.getName())
				.average_price(request.getAverage_price() != null? request.getAverage_price().get() : 0)
				.brand_name(brand.get().getName())
				.brandUuid(brandUuid)
				.build();
		modelRepository.save(newModel);
		Brand brandToUpdate = brand.get();
		brandToUpdate.setQuantity(brandToUpdate.getQuantity() + 1);
		brandToUpdate.setAverage_price((brandToUpdate.getTotal() + newModel.getAverage_price()) / brandToUpdate.getQuantity());
		repository.save(brandToUpdate);
	}
}
