package com.api.avris.controller;

import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Menu;
import com.api.avris.repositories.SubMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubMenuController {

    @Autowired
    SubMenuRepository subMenuRepository;

    @GetMapping(value = "/api/submenu/{menuId}")
    public ApiResponse<Menu> getSubMenuId(@PathVariable long menuId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", subMenuRepository.findMenuById(menuId));
    }

}
