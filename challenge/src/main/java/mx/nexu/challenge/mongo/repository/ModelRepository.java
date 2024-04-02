package mx.nexu.challenge.mongo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import mx.nexu.challenge.mongo.document.Model;

@Repository
public interface ModelRepository extends MongoRepository<Model, String>{

	@Query("{brand_uuid : ?0}")
	public List<Model> findByBrandUuid(String uuid);

	public Optional<Model> findByName(String name);
	
	@Query("{'average_price' : { '$gt' : ?0, '$lt' : ?1 }}")
	public List<Model> findBetweenPrices(double gt, double lt);
	
	@Query("{'average_price' : { '$gt' : ?0 }}")
	public List<Model> findPriceGreaterThan(double gt);
	
	@Query("{'average_price' : { '$lt' : ?0 }}")
	public List<Model> findPriceLessThan(double lt);

	@Query("{ name: ?0, brand_uuid : ?1}")
	public Optional<Model> findByNameAndBrandUuid(String name, String brandUuid);

	@Query("{_id : ?0}")
	public Optional<Model> findById(String uuid);

}
