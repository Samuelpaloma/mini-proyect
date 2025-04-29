package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.MesaDTO;
import com.samuel.crud_basic.DTO.ReservationDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.model.Reservation;
import com.samuel.crud_basic.repository.Ireservation;

@Service
public class ReservationService {

    @Autowired
    private Ireservation data;

    /**
     * Obtener todas las reservas
     * @return Lista de ReservationDTO
     */
    public List<ReservationDTO> findAllReservations() {
        List<Reservation> reservations = data.findAll();
        return reservations.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    /**
     * Buscar una reserva por ID
     * @param id ID de la reserva
     * @return Optional de Reservation
     */
    public Optional<Reservation> findById(int id) {
        return data.findById(id);
    }

    /**
     * Buscar una reserva por ID y devolver un DTO
     * @param id ID de la reserva
     * @return Optional de ReservationDTO
     */
    public Optional<ReservationDTO> findReservationById(int id) {
        return data.findById(id).map(this::convertToDTO);
    }

    /**
     * Eliminar una reserva por ID
     * @param id ID de la reserva
     * @return responseDTO con el resultado de la operación
     */
    public responseDTO deleteReservation(int id) {
        Optional<Reservation> reservation = findById(id);
        if (!reservation.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "La reserva con ID " + id + " no existe"
            );
        }

        reservation.get().setStatus(false); // Cambiar el estado a inactivo
        data.save(reservation.get());

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Reserva eliminada correctamente"
        );
    }

    /**
     * Guardar una nueva reserva
     * @param reservationDTO DTO con los datos de la reserva
     * @return responseDTO con el resultado de la operación
     */
    public responseDTO save(ReservationDTO reservationDTO) {
        Reservation reservation = convertToModel(reservationDTO);
        Reservation savedReservation = data.save(reservation);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Reserva guardada correctamente con ID: " + savedReservation.getIdReservation()
        );
    }

    /**
     * Actualizar una reserva existente
     * @param id ID de la reserva
     * @param reservationDTO DTO con los datos actualizados
     * @return responseDTO con el resultado de la operación
     */
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
        existingReservation.setNumeroPersonas(reservationDTO.getNumeroPersonas());
        existingReservation.setNumeroCelular(reservationDTO.getNumeroCelular());
        Mesa mesa = new Mesa();
        mesa.setIdMesa(reservationDTO.getMesa().getIdMesa());
        mesa.setCapacidad(reservationDTO.getMesa().getCapacidad());
        mesa.setUbicacion(reservationDTO.getMesa().getUbicacion());
        mesa.setOcupada(reservationDTO.getMesa().isOcupada());
        existingReservation.setMesa(mesa);

        data.save(existingReservation);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Reserva actualizada correctamente"
        );
    }

    /**
     * Convertir una entidad Reservation a DTO
     * @param reservation Entidad Reservation
     * @return ReservationDTO
     */
    private ReservationDTO convertToDTO(Reservation reservation) {
        return new ReservationDTO(
            reservation.getName(),
            reservation.getFecha(),
            reservation.getNumeroPersonas(),
            reservation.getNumeroCelular(),
            new MesaDTO(
                reservation.getMesa().getIdMesa(),
                reservation.getMesa().getCapacidad(),
                reservation.getMesa().getUbicacion(),
                reservation.getMesa().isOcupada()
            )
        );
    }

    /**
     * Convertir un DTO a una entidad Reservation
     * @param reservationDTO DTO de la reserva
     * @return Entidad Reservation
     */
    private Reservation convertToModel(ReservationDTO reservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setName(reservationDTO.getName());
        reservation.setFecha(reservationDTO.getFecha());
        reservation.setNumeroPersonas(reservationDTO.getNumeroPersonas());
        reservation.setNumeroCelular(reservationDTO.getNumeroCelular());
        Mesa mesa = new Mesa();
        mesa.setIdMesa(reservationDTO.getMesa().getIdMesa());
        mesa.setCapacidad(reservationDTO.getMesa().getCapacidad());
        mesa.setUbicacion(reservationDTO.getMesa().getUbicacion());
        mesa.setOcupada(reservationDTO.getMesa().isOcupada());
        reservation.setMesa(mesa);
        return reservation;
    }
}