package com.sdproject.controllers;

import com.sdproject.entities.Cinema;
import com.sdproject.entities.Reservation;
import com.sdproject.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping(value = "/api")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/reservation/")
    public Collection<Reservation> reservations() {
        return reservationRepository.findAll();
    }

    @GetMapping(value = "/reservation/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable("id") Integer reservationId) {
        Reservation reservation = reservationRepository.findOne(reservationId);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(reservation);
    }

    @DeleteMapping(value = "/reservation/delete/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Integer reservationId) {
        Reservation reservation = reservationRepository.findOne(reservationId);
        if (reservation == null) {
            return ResponseEntity.notFound().build();
        }
        reservationRepository.delete(reservation);

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/reservation/")
    public Reservation createReservation(@Valid @RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @PutMapping(value = "/reservation/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable("id") Integer reservationId, @Valid @RequestBody Reservation reseservation) {
        Reservation reservationExist = reservationRepository.findOne(reservationId);

        if (reservationExist == null) {
            return ResponseEntity.notFound().build();
        }

        reservationExist.setSeatNo(reseservation.getSeatNo());
        reservationExist.setClient(reseservation.getClient());
        reservationExist.setMovie(reseservation.getMovie());

        Reservation updatedReservation = reservationRepository.save(reservationExist);

        return ResponseEntity.ok(updatedReservation);
    }

}
