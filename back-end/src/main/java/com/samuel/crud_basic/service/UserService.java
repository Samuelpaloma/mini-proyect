package com.samuel.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.UserDTO;
import com.samuel.crud_basic.DTO.responseDTO;
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

    public List<User> findAll(){
        return data.getListUserActive();
    }

    public List<User> getListUserForName(String filter) {
        return data.getListUserForName(filter);
    }

    public Optional<User> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteUser(int id) {
        Optional<User> user=findById(id);
        if(!user.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "The register does not exist");
            return respuesta;    
        }
        user.get().setStatus(false);
        data.save(user.get());

        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
             "Se eliminó correctamente");
        return respuesta;     
    }

    // register and update
    public responseDTO save(UserDTO userDTO) {
        if(userDTO.getNombre().length() < 1 ||
        userDTO.getNombre().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre debe estar entre 1 y 50 caracteres");
            return respuesta;    
        }
        User userRegister = convertToModel(userDTO);
        data.save(userRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se gurado correctamente");
        return respuesta;    
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
                userDTO.getDireccion(),
                true);
        return user;
    }

    public responseDTO updateExplorer(int id, ExplorerDTO dto) {
        Optional<Explorer> explorerOpt = repository.findById(id);
        if (!explorerOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El cliente con ID " + id + " no existe");
            return respuesta;
        }
        Explorer existingExplorer = explorerOpt.get();
        existingExplorer.setName(dto.getName());
        existingExplorer.setNationality(dto.getNationality());
        existingExplorer.setAge(dto.getAge());
        existingExplorer.setReputation(dto.getReputation());
        existingExplorer.setImageExplorer(dto.getImageExplorer());

        repository.save(existingExplorer);

        responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "Explorer actualizado correctamente");
        return respuesta;
    }

}

