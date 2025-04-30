package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.ReservationDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.model.Reservation;
import com.samuel.crud_basic.repository.Imesa;
import com.samuel.crud_basic.repository.Ireservation;

@Service
public class ReservationService {

    @Autowired
    private Ireservation reservationRepository;

    @Autowired
    private Imesa mesaRepository;

    // Obtener todas las reservas
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    // Obtener mesas disponibles
    public List<Mesa> findAvailableMesas() {
        return mesaRepository.findByOcupada(false); // Solo mesas no ocupadas
    }

    // Agregar una nueva reserva
    public responseDTO addReservation(ReservationDTO reservationDTO) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(reservationDTO.getIdMesa());

        if (!mesaOptional.isPresent() || mesaOptional.get().isOcupada()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La mesa seleccionada no está disponible"
            );
        }

        Mesa mesa = mesaOptional.get();
        mesa.setOcupada(true); // Marcar la mesa como ocupada

        Reservation reservation = new Reservation(
            0,
            reservationDTO.getName(),
            reservationDTO.getFecha(),
            reservationDTO.getNumeroPersonas(),
            reservationDTO.getNumeroCelular(),
            mesa,
            true
        );

        reservationRepository.save(reservation);
        mesaRepository.save(mesa);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Reserva agregada correctamente"
        );
    }
}