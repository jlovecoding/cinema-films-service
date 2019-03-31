package com.cinema.films.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(FilmId.class)
public class Film {
    private String title;
    @Id
    private Integer screen;
    @Id
    private LocalDateTime start;
}
