package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.MesaDTO;
import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.repository.Imesa;

@Service
public class MesaService {
    
    @Autowired
    private Imesa data;

    public void save(MesaDTO mesaDTO){
        Mesa mesaRegister = convertToModel(mesaDTO);
        data.save(mesaRegister);
    }

    public MesaDTO convertToDTO(Mesa mesa) {
        MesaDTO mesaDTO = new MesaDTO(
            mesa.getCapacidad(),
            mesa.getUbicacion()
        );
        return mesaDTO;
    }
    
    public Mesa convertToModel(MesaDTO mesaDTO) {
        Mesa mesa = new Mesa(
            0,
            mesaDTO.getCapacidad(),
            mesaDTO.getUbicacion()
        );
        return mesa;
    }
}
