package ee.kool.demokool.movies.services;

import ee.kool.demokool.movies.dto.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MovieReciever2 {

    @JmsListener( destination = "MOVIE_TOPIC", containerFactory = "jmsTopicFactory", subscription = "indrek2", selector = "test=false OR test is null")
    public void listen(Film film) {
        log.info("Received movie <" + film + ">");
    }
}
