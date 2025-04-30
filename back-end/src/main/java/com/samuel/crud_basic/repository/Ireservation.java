package com.samuel.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samuel.crud_basic.model.Reservation;

public interface Ireservation extends JpaRepository
<Reservation,Integer>

{


}
