package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.MenuDTO;
import com.samuel.crud_basic.DTO.ReservationDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Reservation;
import com.samuel.crud_basic.repository.Ireservation;

@Service
public class ReservationService {

    @Autowired
    private Ireservation data;

    // Obtener todas las reservas
    public List<Reservation> findAll() {
        return data.findAll();
    }

    public List<Reservation> getListReservationForName(String filter) {
        return data.getListReservationForName(filter);
    }

    // Buscar una reserva por ID
    public Optional<Reservation> findById(int id) {
        return data.findById(id);
    }

    // Eliminar una reserva
    public responseDTO deleteReservation(int id) {
        Optional<Reservation> reservation = findById(id);
        if (!reservation.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "The register does not exist");
            return respuesta; 
        }
        reservation.get().setStatus(false); 
        data.save(reservation.get());

        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
             "Se eliminó correctamente");
        return respuesta; 
    }

    // Registrar y actualizar una reserva
    public responseDTO save(ReservationDTO reservationDTO) {
        // Validación de datos básicos
        if (reservationDTO.getUser() == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El usuario no puede ser nulo"
            );
        }
        if (reservationDTO.getMesa() == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El número de mesa no puede ser nulo"
            );
        }
        if (reservationDTO.getNumeroPersonas() <= 0) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El número de personas debe ser mayor a 0"
            );
        }

        Reservation reservationRegister = convertToModel(reservationDTO);
        data.save(reservationRegister);

        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente");
        return respuesta;   
    }


    // Convertir a DTO
    public ReservationDTO convertToDTO(Reservation reservation) {
        ReservationDTO reservationDTO = new ReservationDTO(
            reservation.getName(),
            reservation.getFecha(),
            reservation.getHora(),
            reservation.getNumeroPersonas(),
            reservation.getNumeroCelular(),
            reservation.getUser(),
            reservation.getMesa()
        );
    }

    // Convertir a modelo
    public Reservation convertToModel(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation(
            0, // ID autogenerado
            reservationDTO.getName(),
            reservationDTO.getFecha(),
            reservationDTO.getHora(),
            reservationDTO.getNumeroPersonas(),
            reservationDTO.getNumeroCelular(),
            reservationDTO.getUser(),
            reservationDTO.getMesa(),
            true
        );
    }

    // Actualizar una reserva
    public responseDTO updateReservation(int id, ReservationDTO reservationDTO) {
        Optional<Reservation> reservationOpt = findById(id);
        if (!reservationOpt.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "La reserva con ID " + id + " no existe"
            );
        }

        Reservation existingReservation = reservationOpt.get();
        existingReservation.setName(reservationDTO.getName());
        existingReservation.setFecha(reservationDTO.getFecha());
        existingReservation.setHora(reservationDTO.getHora());
        existingReservation.setNumeroPersonas(reservationDTO.getNumeroPersonas());
        existingReservation.setNumeroCelular(reservationDTO.getNumeroCelular());
        existingReservation.setUser(reservationDTO.getUser());
        existingReservation.setMesa(reservationDTO.getMesa());

        data.save(existingReservation);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Reserva actualizada correctamente"
        );
    }
}