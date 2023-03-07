package br.com.xerosorvetes.flavorapi.infrasctructure.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "peoples")
@ToString
@Builder
public class PeopleEntity {

  @Id
  private String id;
  private String name;
}
