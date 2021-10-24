package ee.kool.demokool.movies.dto;

import ee.kool.demokool.actors.dto.Actor;
import lombok.Data;

import java.util.List;

@Data
public class Film {
  private int id;
  private String name;
  private List<Actor> actors;

}
