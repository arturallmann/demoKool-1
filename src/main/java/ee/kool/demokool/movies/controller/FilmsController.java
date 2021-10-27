package ee.kool.demokool.movies.controller;


import ee.kool.demokool.email.Email;
import ee.kool.demokool.movies.dto.Film;
import ee.kool.demokool.movies.services.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class FilmsController {

  @Autowired
  MovieService movieService;


  @GetMapping("/v1/films")
  List<Film> all() {
    log.info("bla");
    return movieService.mockMovies();
  }

  @GetMapping("/v1/films/{id}")
  Film one(@PathVariable Integer id) {
    return movieService.mockOneMovie(id);
  }

  @PostMapping(path = "/v1/films",
    consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public void create(@RequestBody Film newFilm) {
    movieService.storeMovie(newFilm);

  }



}
