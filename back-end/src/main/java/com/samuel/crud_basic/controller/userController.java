package com.samuel.crud_basic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuel.crud_basic.DTO.UserDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.User;
import com.samuel.crud_basic.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class userController {

    /*
     * GET
     * POST(REGISTER)
     * PUT
     * DELETE
     */
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<Object> registerUser(@RequestBody UserDTO user) {
        responseDTO respuesta = userService.save(user);
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        List<User> listaUsuario = userService.findAll();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        Optional<User> usuario = userService.findById(id);
        if (!usuario.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListUserForName(@PathVariable String filter) {
        List<User> userList = userService.getListUserForName(filter);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable int id) {
    responseDTO message = userService.deleteUser(id);
    return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateExplorer(@PathVariable int id, @RequestBody UserDTO dto) {
        //TODO: process PUT request
            responseDTO respuesta = userService.updateExplorer(id, dto);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

}