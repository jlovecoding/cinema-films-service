package com.cinema.films.controller;

import com.cinema.films.model.Film;
import com.cinema.films.model.FilmId;
import com.cinema.films.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME;

@RestController
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public List<Film> findAll(@RequestParam(value = "title", required = false) final String title) {
        return ofNullable(title)
                .map(filmRepository::findByTitle)
                .orElse(filmRepository.findAll());
    }

    @GetMapping("/{screen}/{start}")
    public Film findByScreenAndTime(@PathVariable final Integer screen,
                                    @PathVariable @DateTimeFormat(iso = DATE_TIME) final LocalDateTime start) {
        return of(FilmId.builder().screen(screen).start(start).build())
                .flatMap(filmRepository::findById)
                .orElse(null);
    }

}
