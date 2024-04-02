package mx.nexu.challenge.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.nexu.challenge.exception.ModelException;
import mx.nexu.challenge.mongo.document.Model;
import mx.nexu.challenge.service.ModelService;
import mx.nexu.challenge.web.request.UpdateAveragePriceModelRequest;

@RestController
@RequestMapping("models")
public class ModelController {

	@Autowired
	private ModelService service;

	@PutMapping("{uuid}")
	public ResponseEntity<Void> updateAveragePrice(@RequestBody UpdateAveragePriceModelRequest request,
			@PathVariable("uuid") String uuid) throws ModelException {
		service.updateAveragePrice(request, uuid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("")
	public ResponseEntity<List<Model>> getModels(@RequestParam("greater") Optional<Double> greater, @RequestParam("lower") Optional<Double> lower) throws ModelException {
		return new ResponseEntity<List<Model>>(service.getModels(greater, lower), HttpStatus.OK);
	}

}
