package mx.nexu.challenge.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.nexu.challenge.exception.ModelException;
import mx.nexu.challenge.mongo.document.Brand;
import mx.nexu.challenge.mongo.document.Model;
import mx.nexu.challenge.mongo.repository.BrandRepository;
import mx.nexu.challenge.mongo.repository.ModelRepository;
import mx.nexu.challenge.service.ModelService;
import mx.nexu.challenge.web.request.UpdateAveragePriceModelRequest;

@Service
public class ModelServiceImpl implements ModelService {

	@Autowired
	private ModelRepository repository;
	
	@Autowired 
	private BrandRepository brandRepository;

	@Override
	public void updateAveragePrice(UpdateAveragePriceModelRequest request, String uuid) throws ModelException {
		Optional<Model> model = repository.findById(uuid);
		if (!model.isPresent())
			throw new ModelException("Model: " + uuid + " not exist!");
		if (request.getAverage_price() < 100000)
			throw new ModelException("Model: the average price needs be greater than 100,000, your value( "
					+ request.getAverage_price() + " ) not is valid!");
		Model modelToUpdate = model.get();
		Optional<Brand> brand = brandRepository.findById(modelToUpdate.getBrandUuid());
		Brand brandToUpdate = brand.get();
		brandToUpdate.setTotal(brandToUpdate.getTotal() - modelToUpdate.getAverage_price() + request.getAverage_price());
		brandToUpdate.setAverage_price(brandToUpdate.getTotal() / brandToUpdate.getQuantity());
		brandRepository.save(brandToUpdate);
		modelToUpdate.setAverage_price(request.getAverage_price());
		repository.save(modelToUpdate);
	}

	@Override
	public List<Model> getModels(Optional<Double> greater, Optional<Double> lower) throws ModelException {
		if(greater.isPresent() && lower.isPresent())
			return repository.findBetweenPrices(greater.get(), lower.get());
		else if(greater.isPresent() && !lower.isPresent())
			return repository.findPriceGreaterThan(greater.get());
		else if(!greater.isPresent() && lower.isPresent())
			return repository.findPriceLessThan(lower.get());
		else 
			throw new ModelException("Add less one parameter (grater or lower)");
	}

}
