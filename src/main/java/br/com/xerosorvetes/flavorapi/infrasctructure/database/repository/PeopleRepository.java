package br.com.xerosorvetes.flavorapi.infrasctructure.database.repository;

import br.com.xerosorvetes.flavorapi.infrasctructure.database.entity.PeopleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeopleRepository extends ReactiveMongoRepository<PeopleEntity, String> {

}
