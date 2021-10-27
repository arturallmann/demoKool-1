package ee.kool.demokool.movies.services;

import ee.kool.demokool.movies.dto.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
public class MovieReceiver {


  @JmsListener( destination = "MOVIE_TOPIC", containerFactory = "jmsTopicFactory", subscription = "indrek", selector = "test=false OR test is null")
  public void listen(Film film) {
    log.info("Received movie <" + film + ">");
  }

}
