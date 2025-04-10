package com.samuel.crud_basic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.samuel.crud_basic.model.User;

public interface Iuser extends JpaRepository
<User,Integer>
{
    @Query("SELECT u FROM User u WHERE u.status != false")
    List<User> getListUserActive();

    @Query("SELECT u FROM User u WHERE u.nombre LIKE %?1%")
    List<User> getListUserForName(String filter);
    /*
     * C
     * R
     * U
     * D
     */

}
