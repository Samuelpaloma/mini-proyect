package com.samuel.crud_basic.DTO;

import com.samuel.crud_basic.model.Mesa;
import com.samuel.crud_basic.model.User;

public class ReservationDTO {
    
    private User user;
    private Mesa mesa;
    
    public ReservationDTO(User user, Mesa mesa) {
        this.user = user;
        this.mesa = mesa;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Mesa getMesa() {
        return mesa;
    }
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

}
