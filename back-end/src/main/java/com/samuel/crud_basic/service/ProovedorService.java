package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.ProovedorDTO;
import com.samuel.crud_basic.model.Proovedor;
import com.samuel.crud_basic.repository.Iproovedor;

@Service
public class ProovedorService {
    
    /*
     * save
     * findAll
     * findById
     * Delete
     */
    /* establish connection with the interface */
    @Autowired
    private Iproovedor data;

    // register and update
    public void save(ProovedorDTO proovedorDTO) {
        Proovedor proovedorRegister = convertToModel(proovedorDTO);
        data.save(proovedorRegister);
    }

    public ProovedorDTO convertToDTO(Proovedor proovedor) {
        ProovedorDTO proovedorDTO = new ProovedorDTO(
            proovedor.getName(),
            proovedor.getTelefono(),
            proovedor.getDireccion()
        );
        return proovedorDTO;
    }

    public Proovedor convertToModel(ProovedorDTO proovedorDTO) {
        Proovedor proovedor = new Proovedor(
            0,
            proovedorDTO.getName(),
            proovedorDTO.getTelefono(),
            proovedorDTO.getDireccion()
        );
        return proovedor;
    }

}
