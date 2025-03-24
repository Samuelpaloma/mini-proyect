package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.UserDTO;
import com.samuel.crud_basic.model.User;
import com.samuel.crud_basic.repository.Iuser;



@Service
public class UserService {

    /*
     * save
     * findAll
     * findById
     * Delete
     */
    /* establish connection with the interface */
    @Autowired
    private Iuser data;

    // register and update
    public void save(UserDTO userDTO) {
        User userRegister = convertToModel(userDTO);
        data.save(userRegister);
    }

    public UserDTO convertToDTO(User user) {
        UserDTO userdto = new UserDTO(
                user.getNombre(),
                user.getEmail(),
                user.getContrasena(),
                user.getTelefono(),
                user.getDireccion());
        return userdto;
    }

    public User convertToModel(UserDTO userDTO) {
        User user = new User(
            0,
                userDTO.getNombre(),
                userDTO.getEmail(),
                userDTO.getContrasena(),
                userDTO.getTelefono(),
                userDTO.getDireccion());
        return user;
    }

}

