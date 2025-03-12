package com.samuel.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.ReservationDTO;
import com.samuel.crud_basic.service.ReservationService;
@RestController
@RequestMapping("/api/v1/reservation")
public class reservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/")
    public ResponseEntity<Object> registerReservation(@RequestBody ReservationDTO reservation){
        reservationService.save(reservation);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }
    
}
