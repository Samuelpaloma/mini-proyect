package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private Imesa data;

    /**
     * Obtener mesas disponibles (ocupada = false)
     * @return Lista de mesas desocupadas
     */
    public List<Mesa> getMesasDisponibles() {
        return data.findByOcupadaFalse(); // Llama al método del repositorio
    }

    /**
     * Guardar una nueva mesa
     * @param mesaDTO DTO con los datos de la mesa
     * @return responseDTO con el resultado de la operación
     */
    public responseDTO save(MesaDTO mesaDTO) {
        if (mesaDTO.getCapacidad() <= 0) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La capacidad de la mesa debe ser mayor a 0"
            );
        }
        if (mesaDTO.getUbicacion() == null || mesaDTO.getUbicacion().isEmpty()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La ubicación de la mesa no puede estar vacía"
            );
        }

        Mesa mesa = convertToModel(mesaDTO);
        data.save(mesa);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Mesa guardada correctamente"
        );
    }

    /**
     * Buscar una mesa por su ID
     * @param idMesa ID de la mesa
     * @return Optional de MesaDTO
     */
    public Optional<MesaDTO> findById(int idMesa) {
        return data.findById(idMesa)
                   .map(this::convertToDTO);
    }

    /**
     * Actualizar una mesa existente
     * @param idMesa ID de la mesa a actualizar
     * @param mesaDTO DTO con los datos actualizados
     * @return responseDTO con el resultado de la operación
     */
    public responseDTO update(int idMesa, MesaDTO mesaDTO) {
        Optional<Mesa> optionalMesa = data.findById(idMesa);
        if (!optionalMesa.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "La mesa con ID " + idMesa + " no existe"
            );
        }

        Mesa existingMesa = optionalMesa.get();
        existingMesa.setCapacidad(mesaDTO.getCapacidad());
        existingMesa.setUbicacion(mesaDTO.getUbicacion());
        existingMesa.setOcupada(mesaDTO.isOcupada());

        data.save(existingMesa);

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Mesa actualizada correctamente"
        );
    }

    /**
     * Obtener mesas disponibles (ocupada = false)
     * @return Lista de MesaDTO
     */
    public List<MesaDTO> findAvailable() {
        return data.findByOcupadaFalse() // Método del repositorio
                   .stream()
                   .map(this::convertToDTO) // Convertir a DTO
                   .collect(Collectors.toList());
    }

    /**
 * Liberar una mesa (cambiar ocupada a false)
 * @param id ID de la mesa a liberar
 * @return true si la mesa fue liberada, false si no se encontró
 */
public boolean liberarMesa(int id) {
    return data.findById(id).map(mesa -> {
        mesa.setOcupada(false); // Cambiar el estado a desocupada
        data.save(mesa); // Guardar los cambios
        return true;
    }).orElse(false); // Retornar false si la mesa no fue encontrada
}

    /**
     * Convertir una entidad Mesa a DTO
     * @param mesa Entidad Mesa
     * @return MesaDTO
     */
    private MesaDTO convertToDTO(Mesa mesa) {
        return new MesaDTO(
            mesa.getIdMesa(),
            mesa.getCapacidad(),
            mesa.getUbicacion(),
            mesa.isOcupada()
        );
    }

    /**
     * Convertir un DTO a una entidad Mesa
     * @param mesaDTO DTO de la mesa
     * @return Entidad Mesa
     */
    private Mesa convertToModel(MesaDTO mesaDTO) {
        Mesa mesa = new Mesa();
        mesa.setIdMesa(mesaDTO.getIdMesa()); // ID autogenerado si es nuevo
        mesa.setCapacidad(mesaDTO.getCapacidad());
        mesa.setUbicacion(mesaDTO.getUbicacion());
        mesa.setOcupada(mesaDTO.isOcupada());
        return mesa;
    }
}