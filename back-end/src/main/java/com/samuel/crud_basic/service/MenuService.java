package com.samuel.crud_basic.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.MenuDTO;
import com.samuel.crud_basic.DTO.responseDTO;
import com.samuel.crud_basic.model.Menu;
import com.samuel.crud_basic.repository.Imenu;

@Service
public class MenuService {
    

    @Autowired
    private Imenu data;


    public List<Menu> findAll(){
        return data.getListMenuActive();
    }

    public List<Menu> getListUserForName(String filter) {
        return data.getListMenuForName(filter);
    }

    public Optional<Menu> findById(int id){
        return data.findById(id);
    }

    public responseDTO deleteMenu(int id) {
        Optional<Menu> menu=findById(id);
        if(!menu.isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "The register does not exist");
            return respuesta;    
        }
        menu.get().setStatus(false);
        data.save(menu.get());

        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
             "Se eliminó correctamente");
        return respuesta;     
    }

    // register and update
    public responseDTO save(MenuDTO menuDTO) {
        System.out.println("Nombre recibido: " + menuDTO.getName());
        // Verificamos primero si name es null
        if(menuDTO.getName() == null || 
           menuDTO.getName().length() < 1 || 
           menuDTO.getName().length() > 50) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre no puede ser nulo y debe estar entre 1 y 50 caracteres");
            return respuesta;    
        }
    
        Menu menuRegister = convertToModel(menuDTO);
        data.save(menuRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardó correctamente");
        return respuesta;    
    }

    public MenuDTO convertToDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO(
            menu.getName(),
            menu.getImagen(),
            menu.getPrecio()
        );
        return menuDTO;
    }
    
    public Menu convertToModel(MenuDTO menuDTO) {
        Menu menu = new Menu(
            0,
            menuDTO.getImagen(),
            menuDTO.getName(),
            menuDTO.getPrecio(),
            true
        );
        return menu;
    }

    public responseDTO updateExplorer(int id, MenuDTO dto) {
        Optional<Menu> menuOpt = data.findById(id);
        if (!menuOpt.isPresent()) {
            responseDTO respuesta = new responseDTO(
                    HttpStatus.NOT_FOUND.toString(),
                    "El cliente con ID " + id + " no existe");
            return respuesta;
        }
        Menu existingMenu = menuOpt.get();
        existingMenu.setName(dto.getName());
        existingMenu.setImagen(dto.getImagen());
        existingMenu.setPrecio(dto.getPrecio());

        data.save(existingMenu);

        responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "usuario actualizado correctamente");
        return respuesta;
    }

}
