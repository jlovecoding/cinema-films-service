package com.cinema.films.repository;


import com.cinema.films.model.Film;
import com.cinema.films.model.FilmId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmRepository extends JpaRepository<Film, FilmId> {

    List<Film> findByTitle(String title);
}
