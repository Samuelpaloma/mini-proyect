package com.samuel.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samuel.crud_basic.DTO.MenuDTO;
import com.samuel.crud_basic.model.Menu;
import com.samuel.crud_basic.repository.Imenu;

@Service
public class MenuService {
    

    @Autowired
    private Imenu data;

    public void save(MenuDTO menuDTO){
        Menu menuRegister = convertToModel(menuDTO);
        data.save(menuRegister);
    }

    public MenuDTO convertToDTO(Menu menu) {
        MenuDTO menuDTO = new MenuDTO(
            menu.getName(),
            menu.getDescription(),
            menu.getPrecio()
        );
        return menuDTO;
    }
    
    public Menu convertToModel(MenuDTO menuDTO) {
        Menu menu = new Menu(
            0,
            menuDTO.getName(),
            menuDTO.getDescription(),
            menuDTO.getPprecio()
        );
        return menu;
    }
}
