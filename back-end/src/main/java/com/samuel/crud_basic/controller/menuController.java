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

import com.samuel.crud_basic.DTO.MenuDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Menu;
import com.samuel.crud_basic.service.MenuService;

@RestController
@RequestMapping("/api/v1/menu")

public class menuController {

    @Autowired
    private MenuService menuService;

    @PostMapping("/")
    public ResponseEntity<Object> registerMenu(@RequestBody MenuDTO menu){
        menuService.save(menu);
        return new ResponseEntity<>("register OK", HttpStatus.OK);
    }

     @GetMapping("/")
    public ResponseEntity<Object> getAllUser() {
        List<Menu> listaUsuario = menuService.findAll();
        return new ResponseEntity<>(listaUsuario, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable int id) {
        Optional<Menu> menu = menuService.findById(id);
        if (!menu.isPresent())
            return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    @GetMapping("/filter/{filter}")
    public ResponseEntity<Object> getListUserForName(@PathVariable String filter) {
        List<Menu> menuList = menuService.getListUserForName(filter);
        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMenu(@PathVariable int id) {
    responseDTO message = menuService.deleteMenu(id);
    return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateExplorer(@PathVariable int id, @RequestBody MenuDTO dto) {
        //TODO: process PUT request
            responseDTO respuesta = menuService.updateExplorer(id, dto);
            return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }


    
    
}
