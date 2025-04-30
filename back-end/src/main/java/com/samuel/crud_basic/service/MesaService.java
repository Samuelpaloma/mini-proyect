package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.MesaDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.repository.Imesa;

@Service
public class MesaService {

    @Autowired
    private Imesa mesaRepository;

    // Obtener todas las mesas
    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }

    // Obtener mesas disponibles
    public List<Mesa> findAvailableMesas() {
        return mesaRepository.findByOcupada(false); // Solo mesas no ocupadas
    }

    // Agregar una nueva mesa
    public responseDTO addMesa(MesaDTO mesaDTO) {
        Mesa mesa = new Mesa(
            0, // ID autogenerado
            mesaDTO.getCapacidad(),
            mesaDTO.getUbicacion(),
            mesaDTO.isOcupada(),
            null // No hay reservas asociadas inicialmente
        );

        mesaRepository.save(mesa);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Mesa agregada correctamente"
        );
    }

    // Actualizar una mesa existente
    public responseDTO updateMesa(int id, MesaDTO mesaDTO) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);

        if (!mesaOptional.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "La mesa con ID " + id + " no existe"
            );
        }

        Mesa mesa = mesaOptional.get();
        mesa.setCapacidad(mesaDTO.getCapacidad());
        mesa.setUbicacion(mesaDTO.getUbicacion());
        mesa.setOcupada(mesaDTO.isOcupada());

        mesaRepository.save(mesa);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Mesa actualizada correctamente"
        );
    }

    // Eliminar una mesa por ID
    public responseDTO deleteMesa(int id) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);

        if (!mesaOptional.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "La mesa con ID " + id + " no existe"
            );
        }

        mesaRepository.deleteById(id);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Mesa eliminada correctamente"
        );
    }
}