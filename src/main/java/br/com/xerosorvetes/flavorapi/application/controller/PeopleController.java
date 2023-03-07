package br.com.xerosorvetes.flavorapi.application.controller;

import br.com.xerosorvetes.flavorapi.domain.dto.PeopleDto;
import br.com.xerosorvetes.flavorapi.domain.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/people")
public class PeopleController {

  @Autowired
  private PeopleService service;

  @GetMapping
  public Flux<PeopleDto> get() {
    return service.findAll();
  }

  @PostMapping
  public Mono<PeopleDto> save(@RequestBody PeopleDto dto) {

    return service.save(dto);
  }

  @PutMapping
  public Mono<PeopleDto> update(@RequestBody PeopleDto dto) {
    return service.update(dto);
  }

  @DeleteMapping("{id}")
  public Mono<Void> delete(@PathVariable final String id) {
    return service.delete(id);
  }
}
