package com.samuel.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samuel.crud_basic.model.Reservation;

public interface Ireservation extends JpaRepository
<Reservation,Integer>

{

    @Query("SELECT r FROM Reservation r WHERE r.status != false")
    List<Reservation> getListReservationsActive();

    // Filtrar reservas por nombre del usuario
    @Query("SELECT r FROM Reservation r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :filter, '%'))")
    List<Reservation> getListReservationForName(String filter);
    /*
     * C
     * R
     * U
     * D
     */

}
