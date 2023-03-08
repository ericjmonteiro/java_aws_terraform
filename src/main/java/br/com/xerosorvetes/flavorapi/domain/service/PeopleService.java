package br.com.xerosorvetes.flavorapi.domain.service;

import br.com.xerosorvetes.flavorapi.application.exception.PeopleNotFoundException;
import br.com.xerosorvetes.flavorapi.domain.dto.PeopleDto;
import br.com.xerosorvetes.flavorapi.infrasctructure.database.entity.PeopleEntity;
import br.com.xerosorvetes.flavorapi.infrasctructure.database.repository.PeopleRepository;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class PeopleService {

  @Autowired
  private PeopleRepository repository;

  private static PeopleDto apply(PeopleEntity people) {
    return PeopleDto.builder().id(people.getId()).name(people.getName()).build();
  }

  public Mono<PeopleDto> save(PeopleDto dto) {

    PeopleEntity entity = PeopleEntity.builder().id(dto.getId()).name(dto.getName()).build();
    return this.repository.save(entity).map(PeopleService::apply);
  }

  public Mono<PeopleDto> update(final PeopleDto dto) {

    if (Objects.isNull(dto)) {
      throw new IllegalArgumentException("People is not null");
    }

    if (Strings.isBlank(dto.getId())) {
      throw new IllegalArgumentException("Field id is not null");
    }

    return repository.findById(dto.getId())
        .switchIfEmpty(Mono.error(new PeopleNotFoundException(dto.getId())))
        .flatMap(peopleEntity -> {
          peopleEntity.setName(dto.getName());
          repository.save(peopleEntity);
          return Mono.just(apply(peopleEntity));
        });
  }

  public Mono<Void> delete(String id) {
    if (Strings.isBlank(id)) {
      throw new IllegalArgumentException("Field id is not null");
    }

    return this.repository.findById(id)
        .switchIfEmpty(Mono.error(new IllegalArgumentException("Não foi possivel localizar id")))
        .flatMap(peopleEntity -> this.repository.deleteById(id));
  }


  public Flux<PeopleDto> findAll() {
    log.info("Search all people");
    return this.repository.findAll().switchIfEmpty(Flux.empty()).map(PeopleService::apply);
  }
}
