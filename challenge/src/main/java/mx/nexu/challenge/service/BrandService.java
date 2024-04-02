package mx.nexu.challenge.service;

import java.util.List;

import mx.nexu.challenge.exception.BrandException;
import mx.nexu.challenge.exception.ModelException;
import mx.nexu.challenge.mongo.document.Brand;
import mx.nexu.challenge.mongo.document.Model;
import mx.nexu.challenge.web.request.AddBrandRequest;
import mx.nexu.challenge.web.request.AddModelRequest;

public interface BrandService {
	
	public List<Brand> getBrands();
	
	public List<Model> getModels(String uuid);
	
	public void addBrand(AddBrandRequest request) throws BrandException;
	
	public void addModel(AddModelRequest request, String brandUuid) throws BrandException, ModelException;

}
