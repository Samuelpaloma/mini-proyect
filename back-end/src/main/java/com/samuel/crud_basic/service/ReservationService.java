package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.ReservationDTO;
import com.samuel.crud_basic.model.Reservation;
import com.samuel.crud_basic.repository.Ireservation;



@Service
public class ReservationService {

    /*
     * save
     * findAll
     * findById
     * Delete
     */
    /* establish connection with the interface */
    @Autowired
    private Ireservation data;

    // register and update
    public void save(ReservationDTO reservationDTO) {
        Reservation reservationRegister = convertToModel(reservationDTO);
        data.save(reservationRegister);
    }

    public ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO(
                reservation.getUser(),
                reservation.getMesa());
        return reservationDTO;
    }

    public Reservation convertToModel(ReservationDTO reservationDTO) {
            Reservation reservation = new Reservation(
                0, 
                null, 
                reservationDTO.getUser(),
                reservationDTO.getMesa());
        return reservation;
    }

}
