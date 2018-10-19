package com.sdproject.controllers;

import com.sdproject.entities.Cinema;
import com.sdproject.mail.MailService;
import com.sdproject.repositories.CinemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CinemaController {

    @Autowired
    private CinemaRepository cinemaRepository;

    final MailService mailService = new MailService("apptestmarc@gmail.com","apptesttoni");

    @GetMapping("/cinema/")
    public Collection<Cinema> cinemas() {
        return cinemaRepository.findAll();
    }

    @GetMapping(value = "/cinema/{id}")
    public ResponseEntity<Cinema> getCinema(@PathVariable("id") Integer cinemaId) {
        Cinema cinema = cinemaRepository.findOne(cinemaId);
        if (cinema == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(cinema);
    }

    @DeleteMapping(value = "/cinema/delete/{id}")
    public ResponseEntity<Cinema> deleteCinema(@PathVariable("id") Integer cinemaId) {
        Cinema cinema = cinemaRepository.findOne(cinemaId);
        if (cinema == null) {
            return ResponseEntity.notFound().build();
        }
        cinemaRepository.delete(cinema);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/cinema/")
    public Cinema createCinema(@Valid @RequestBody Cinema cinema) {
        mailService.sendMail("antomarc19@yahoo.com", "New cinema added !!!", cinema.toString());
        return cinemaRepository.save(cinema);
    }

    @PutMapping(value = "/cinema/{id}")
    public ResponseEntity<Cinema> updateCinema(@PathVariable("id") Integer cinemaId, @Valid @RequestBody Cinema cinema) {
        Cinema cinemaExist = cinemaRepository.findOne(cinemaId);

        if (cinemaExist == null) {
            return ResponseEntity.notFound().build();
        }

        cinemaExist.setName(cinema.getName());
        cinemaExist.setAddress(cinema.getAddress());
        cinemaExist.setMovies(cinema.getMovies());

        Cinema updatedCinema = cinemaRepository.save(cinemaExist);

        return ResponseEntity.ok(updatedCinema);
    }

}
