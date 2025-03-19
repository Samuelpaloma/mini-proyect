package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.DetallePedidoDTO;
import com.samuel.crud_basic.model.DetallePedido;
import com.samuel.crud_basic.repository.IdetallePedido;

@Service
public class DetallePedidoService {
    

    @Autowired
    private IdetallePedido data;


    public void save(DetallePedidoDTO detallePedidoDTO){
        DetallePedido detallePedidoRegister = convertToModel(detallePedidoDTO);
        data.save(detallePedidoRegister);
    }

    public DetallePedidoDTO convertToDTO(DetallePedido detallePedido) {
        DetallePedidoDTO detallePedidoDTO = new DetallePedidoDTO(
            detallePedido.getCantidad(),
            detallePedido.getCargo(),
            detallePedido.getSalario(),
            detallePedido.getTelefono()
        );
        return detallePedidoDTO;
    }
    
    public DetallePedido convertToModel(DetallePedidoDTO detallePedidoDTO) {
        DetallePedido detallePedido = new DetallePedido(
            0,
            detallePedidoDTO.getCantidad(),
            detallePedidoDTO.getCargo(),
            detallePedidoDTO.getSalario(),
            detallePedidoDTO.getTelefono(),
            null,
            null
        );
        return detallePedido;
    }
}
