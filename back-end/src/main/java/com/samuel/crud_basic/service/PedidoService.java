package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.PedidoDTO;
import com.samuel.crud_basic.model.Pedido;
import com.samuel.crud_basic.repository.Ipedido;

@Service
public class PedidoService {
    
    @Autowired
    private Ipedido data;
    
    public void save(PedidoDTO pedidoDTO){
        Pedido pedidoRegister = convertToModel(pedidoDTO);
        data.save(pedidoRegister);
    }

    public PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO(
            pedido.getEstado()
        );
        return pedidoDTO;
    }
    
    public Pedido convertToModel(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido(
            0,
            pedidoDTO.getEstado());
        return pedido;
    }
}
