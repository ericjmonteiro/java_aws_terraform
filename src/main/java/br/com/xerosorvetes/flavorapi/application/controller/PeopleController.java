package br.com.xerosorvetes.flavorapi.application.controller;

import br.com.xerosorvetes.flavorapi.domain.dto.PeopleDto;
import br.com.xerosorvetes.flavorapi.domain.service.PeopleService;
import io.swagger.v3.oas.annotations.Operation;
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

  @Operation(summary = "get all people", description = "endpoint to get all people")
  @GetMapping
  public Flux<PeopleDto> get() {
    return service.findAll();
  }

  @Operation(summary = "create people", description = "endpoint to create people")
  @PostMapping
  public Mono<PeopleDto> save(@RequestBody PeopleDto dto) {
    return service.save(dto);
  }


  @Operation(summary = "update people", description = "endpoint to update people")
  @PutMapping
  public Mono<PeopleDto> update(@RequestBody PeopleDto dto) {
    return service.update(dto);
  }

  @Operation(summary = "delete people", description = "endpoint to delete people")
  @DeleteMapping("{id}")
  public Mono<Void> delete(@PathVariable final String id) {
    return service.delete(id);
  }
}
