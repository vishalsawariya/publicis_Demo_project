package com.movieticket.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import com.movieticket.dto.CreateScreenRequest;
import com.movieticket.dto.CreateShowRequest;
import com.movieticket.dto.CreateTheatreRequest;
import com.movieticket.model.Screen;
import com.movieticket.model.Show;
import com.movieticket.model.Theatre;
import com.movieticket.service.PartnerService;

@RestController
@RequestMapping("/api/partner")
public class PartnerController {
    private final PartnerService partnerService;

    public PartnerController(PartnerService partnerService) { this.partnerService = partnerService; }

    @PostMapping("/theatres")
    public Theatre createTheatre(@Valid @RequestBody CreateTheatreRequest req) {
        return partnerService.createTheatre(req);
    }

    @PostMapping("/screens")
    public Screen createScreen(@Valid @RequestBody CreateScreenRequest req) {
        return partnerService.createScreen(req);
    }

    @PostMapping("/shows")
    public Show createShow(@Valid @RequestBody CreateShowRequest req) {
        return partnerService.createShow(req);
    }
}