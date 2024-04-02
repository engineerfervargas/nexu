package mx.nexu.challenge.mongo.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import mx.nexu.challenge.mongo.document.Brand;

@Repository
public interface BrandRepository extends MongoRepository<Brand, String>{

	Optional<Brand> findByName(String name);
	
	@Query("{_id : ?0}")
	Optional<Brand> findById(String uuid);

}
