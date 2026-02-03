package com.movieticket.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieticket.dto.CreateScreenRequest;
import com.movieticket.dto.CreateShowRequest;
import com.movieticket.dto.CreateTheatreRequest;
import com.movieticket.model.*;
import com.movieticket.repo.*;

@Service
public class PartnerService {
    private final TheatreRepository theatreRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;
    private final ShowRepository showRepository;

    public PartnerService(TheatreRepository theatreRepository, ScreenRepository screenRepository, MovieRepository movieRepository, ShowRepository showRepository) {
        this.theatreRepository = theatreRepository;
        this.screenRepository = screenRepository;
        this.movieRepository = movieRepository;
        this.showRepository = showRepository;
    }

    @Transactional
    public Theatre createTheatre(CreateTheatreRequest req) {
        Theatre t = new Theatre();
        t.setName(req.getName());
        t.setCity(req.getCity());
        t.setAddress(req.getAddress());
        return theatreRepository.save(t);
    }

    @Transactional
    public Screen createScreen(CreateScreenRequest req) {
        Theatre theatre = theatreRepository.findById(req.getTheatreId()).orElseThrow();
        Screen s = new Screen();
        s.setName(req.getName());
        s.setRowsCount(req.getRowsCount());
        s.setColsCount(req.getColsCount());
        s.setTheatre(theatre);
        return screenRepository.save(s);
    }

    @Transactional
    public Show createShow(CreateShowRequest req) {
        Movie movie = movieRepository.findById(req.getMovieId()).orElseThrow();
        Screen screen = screenRepository.findById(req.getScreenId()).orElseThrow();
        Show show = new Show();
        show.setMovie(movie);
        show.setScreen(screen);
        show.setStartTime(req.getStartTime());
        show.setFormat(req.getFormat());
        show.setBasePrice(req.getBasePrice());
        return showRepository.save(show);
    }
}