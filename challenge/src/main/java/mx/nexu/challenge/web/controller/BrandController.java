package mx.nexu.challenge.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.nexu.challenge.exception.BrandException;
import mx.nexu.challenge.exception.ModelException;
import mx.nexu.challenge.mongo.document.Brand;
import mx.nexu.challenge.mongo.document.Model;
import mx.nexu.challenge.service.BrandService;
import mx.nexu.challenge.web.request.AddBrandRequest;
import mx.nexu.challenge.web.request.AddModelRequest;

@RestController
@RequestMapping("brands")
public class BrandController {

	@Autowired
	private BrandService service;
	
	@GetMapping("")
	public ResponseEntity<List<Brand>> getBrands() {
		 return new ResponseEntity<List<Brand>>(service.getBrands(), HttpStatus.OK);
	}

	@GetMapping("{uuid}/models")
	public ResponseEntity<List<Model>> getModels(@PathVariable("uuid") String uuid) {
		return new ResponseEntity<List<Model>>(service.getModels(uuid), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Void> addBrand(@RequestBody AddBrandRequest request) throws BrandException {
		service.addBrand(request);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@PostMapping("{uuid}/models")
	public ResponseEntity<Void> addModel(@RequestBody AddModelRequest request, @PathVariable("uuid") String uuid) throws BrandException, ModelException {
		service.addModel(request, uuid);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

}
