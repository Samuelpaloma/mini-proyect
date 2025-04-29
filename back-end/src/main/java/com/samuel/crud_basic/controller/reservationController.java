package com.samuel.crud_basic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.samuel.crud_basic.DTO.MesaDTO;
import com.samuel.crud_basic.DTO.ReservationDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.model.Reservation;
import com.samuel.crud_basic.service.MesaService;
import com.samuel.crud_basic.service.ReservationService;

@RestController
@RequestMapping("/api/v1/reservation")
public class reservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private MesaService mesaService;

    @PostMapping("/")
    public ResponseEntity<Object> registerReservation(@RequestBody ReservationDTO reservationDTO) {
        // Log para verificar los datos recibidos
        System.out.println("Datos recibidos: " + reservationDTO);
    
        // Verificar si la mesa existe
        Optional<MesaDTO> mesaDTOOptional = mesaService.findById(reservationDTO.getMesa().getIdMesa());
        if (!mesaDTOOptional.isPresent()) {
            return new ResponseEntity<>("La mesa no existe", HttpStatus.BAD_REQUEST);
        }
    
        // Verificar si la mesa está ocupada
        MesaDTO mesaDTO = mesaDTOOptional.get();
        if (mesaDTO.isOcupada()) {
            return new ResponseEntity<>("La mesa no está disponible", HttpStatus.BAD_REQUEST);
        }
    
        // Marcar la mesa como ocupada
        Mesa mesa = new Mesa();
        mesa.setIdMesa(mesaDTO.getIdMesa());
        mesa.setCapacidad(mesaDTO.getCapacidad());
        mesa.setUbicacion(mesaDTO.getUbicacion());
        mesa.setOcupada(true); // Cambiar el estado a ocupada
        MesaDTO mesaDTOToSave = new MesaDTO();
        mesaDTOToSave.setIdMesa(mesa.getIdMesa());
        mesaDTOToSave.setCapacidad(mesa.getCapacidad());
        mesaDTOToSave.setUbicacion(mesa.getUbicacion());
        mesaDTOToSave.setOcupada(mesa.isOcupada());
        mesaService.save(mesaDTOToSave);
        // Guardar la reserva
        responseDTO response = reservationService.save(reservationDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Obtener todas las reservas
     */
    @GetMapping("/")
    public ResponseEntity<Object> getAllReservations() {
        List<ReservationDTO> reservationList = reservationService.findAllReservations();
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
     * Actualizar una reserva por ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateReservation(@PathVariable int id, @RequestBody ReservationDTO dto) {
        responseDTO response = reservationService.updateReservation(id, dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Eliminar una reserva por ID
     * Libera la mesa asociada a la reserva eliminada.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteReservation(@PathVariable int id) {
        Optional<Reservation> reservationOptional = reservationService.findById(id);
        if (!reservationOptional.isPresent()) {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }

        // Liberar la mesa asociada
        Mesa mesa = reservationOptional.get().getMesa();
        mesa.setOcupada(false);
        MesaDTO mesaDTOToSave = new MesaDTO();
        mesaDTOToSave.setIdMesa(mesa.getIdMesa());
        mesaDTOToSave.setCapacidad(mesa.getCapacidad());
        mesaDTOToSave.setUbicacion(mesa.getUbicacion());
        mesaDTOToSave.setOcupada(mesa.isOcupada());
        mesaService.save(mesaDTOToSave);

        // Eliminar la reserva
        responseDTO response = reservationService.deleteReservation(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}