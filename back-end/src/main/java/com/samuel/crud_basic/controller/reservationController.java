package com.samuel.crud_basic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.ReservationDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Reservation;
import com.samuel.crud_basic.service.ReservationService;

@RestController
@RequestMapping("/api/v1/reservation")
public class reservationController {

    @Autowired
    private ReservationService reservationService;

    /**
     * Registrar una nueva reserva
     */
    @PostMapping("/")
    public ResponseEntity<Object> registerReservation(@RequestBody ReservationDTO reservation) {
        responseDTO response = reservationService.save(reservation);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Obtener todas las reservas
     */
    @GetMapping("/")
    public ResponseEntity<Object> getAllReservations() {
        List<Reservation> reservationList = reservationService.findAll();
        return new ResponseEntity<>(reservationList, HttpStatus.OK);
    }

    /**
     * Obtener una reserva por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> getReservationById(@PathVariable int id) {
        Optional<Reservation> reservation = reservationService.findById(id);
        if (!reservation.isPresent())
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
    }

    /**
     * Filtrar reservas por criterio (ejemplo: usuario, fecha, etc.)
     */
    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListReservationForName(@PathVariable String filter) {
        List<Reservation> filteredReserva = reservationService.getListReservationForName(filter);
        return new ResponseEntity<>(filteredReserva, HttpStatus.OK);
    }

    /**
     * Actualizar una reserva por ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReservation(@PathVariable int id, @RequestBody ReservationDTO dto) {
        responseDTO response = reservationService.updateReservation(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Eliminar una reserva por ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable int id) {
        responseDTO response = reservationService.deleteReservation(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}