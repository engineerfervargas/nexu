package mx.nexu.challenge.service;

import java.util.List;
import java.util.Optional;

import mx.nexu.challenge.exception.ModelException;
import mx.nexu.challenge.mongo.document.Model;
import mx.nexu.challenge.web.request.UpdateAveragePriceModelRequest;

public interface ModelService {
	
	public void updateAveragePrice(UpdateAveragePriceModelRequest request, String uuid) throws ModelException;
	
	public List<Model> getModels(Optional<Double> greater, Optional<Double> lower) throws ModelException;

}
