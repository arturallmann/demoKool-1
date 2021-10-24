package ee.kool.demokool.movies.services;

import ee.kool.demokool.actors.dto.Actor;
import ee.kool.demokool.movies.dto.Film;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

  public List<Film> mockMovies(){

    List<Film> films = new ArrayList<>();
    Film film1 = new Film();
    film1.setId(1);
    film1.setName("Star Wars");
    Actor actor1 = new Actor();
    actor1.setForeName("Keira");
    actor1.setLastName("Knigtley");
    actor1.setId(1);
    List<Actor> actors = new ArrayList<>();
    actors.add(actor1);
    film1.setActors(actors);
    films.add(film1);
    return films;
  }

  public Film mockOneMovie(Integer id) {
     Film movie = new Film();
     movie.setId(id);
     movie.setName("James Bond");
     return movie;
  }
}
