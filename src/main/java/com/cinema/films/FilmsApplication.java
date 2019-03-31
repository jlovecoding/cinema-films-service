package com.cinema.films;

import com.cinema.films.model.Film;
import com.cinema.films.repository.FilmRepository;
import io.vavr.Tuple3;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static io.vavr.Tuple.of;
import static java.time.LocalDateTime.of;

@SpringBootApplication
public class FilmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmsApplication.class, args);
    }


    @Component
    public class FilmsLoader implements CommandLineRunner {

        @Autowired
        private FilmRepository filmRepository;

        @Override
        public void run(String... strings) {
            createFilms().forEach(filmRepository::save);
        }

        private Stream<Film> createFilms() {
            Stream<Tuple3<String, Integer, LocalDateTime>> films = Stream.of(
                    of("green_book", 1, of(2019, 4, 1, 18, 30)),
                    of("green_book", 1, of(2019, 4, 1, 21, 0)),
                    of("et2", 2, of(2019, 4, 1, 16, 0)),
                    of("et2", 2, of(2019, 4, 1, 20, 0)),
                    of("jurassic_park_v", 1, of(2019, 4, 2, 18, 30)),
                    of("jurassic_park_v", 5, of(2019, 4, 2, 18, 30)));

            return films
                    .map(tuple3 -> Film.builder().title((tuple3._1())).screen(tuple3._2()).start(tuple3._3()).build());
        }
    }

}
