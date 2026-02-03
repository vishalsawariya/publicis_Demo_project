package com.movieticket;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.movieticket.model.*;
import com.movieticket.repo.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class SampleDataLoader {
    @Bean
    CommandLineRunner load(MovieRepository movies, TheatreRepository theatres, ScreenRepository screens, ShowRepository shows) {
        return args -> {
            if (!movies.findAll().isEmpty()) return;

            Movie m1 = new Movie();
            m1.setTitle("Sky Warriors");
            m1.setLanguage(Language.HINDI);
            m1.setGenre(Genre.ACTION);
            m1.setDurationMin(130);
            m1.setReleaseDate(LocalDate.now().minusDays(5));
            m1.setDescription("High-octane aerial action.");
            m1.setRating(4.2);
            movies.save(m1);

            Movie m2 = new Movie();
            m2.setTitle("City Strings");
            m2.setLanguage(Language.MARATHI);
            m2.setGenre(Genre.DRAMA);
            m2.setDurationMin(115);
            m2.setReleaseDate(LocalDate.now().minusDays(10));
            m2.setDescription("A heartfelt city tale.");
            m2.setRating(4.0);
            movies.save(m2);

            Theatre t1 = new Theatre();
            t1.setName("XYZ Cineplex Baner");
            t1.setCity("Pune");
            t1.setAddress("Baner Road");
            theatres.save(t1);

            Theatre t2 = new Theatre();
            t2.setName("XYZ Cinemas Andheri");
            t2.setCity("Mumbai");
            t2.setAddress("Andheri West");
            theatres.save(t2);

            Screen s1 = new Screen();
            s1.setName("Screen 1");
            s1.setRowsCount(8);
            s1.setColsCount(12);
            s1.setTheatre(t1);
            screens.save(s1);

            Screen s2 = new Screen();
            s2.setName("Screen 2");
            s2.setRowsCount(10);
            s2.setColsCount(14);
            s2.setTheatre(t2);
            screens.save(s2);

            Show sh1 = new Show();
            sh1.setMovie(m1);
            sh1.setScreen(s1);
            sh1.setStartTime(LocalDateTime.now().plusHours(3));
            sh1.setFormat(Format.TWO_D);
            sh1.setBasePrice(250);
            shows.save(sh1);

            Show sh2 = new Show();
            sh2.setMovie(m2);
            sh2.setScreen(s2);
            sh2.setStartTime(LocalDateTime.now().plusHours(5));
            sh2.setFormat(Format.DOLBY);
            sh2.setBasePrice(220);
            shows.save(sh2);
        };
    }
}