package com.api.avris.controller;

import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.ChildMenu;
import com.api.avris.repositories.ChildRepository;
import com.api.avris.repositories.SubMenuRepository;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.util.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
public class SubChildMenuController {

    @Autowired
    SubMenuRepository subMenuRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ChildRepository childRepository;

    @PostMapping("/api/subchildmenu/{usersId}/sub/{subId}")
    public ApiResponse<Object> postChildMenu(@PathVariable(value = "usersId") Long usersId,
                                             @PathVariable(value = "subId") Long subId,
                                             @Valid @RequestBody ChildMenu childMenu) {
        ChildMenu ch = new ChildMenu();
        ch.setStandMeter(childMenu.getStandMeter());
        ch.setUsageElectric(childMenu.getUsageElectric());
        ch.setPeriode(childMenu.getPeriode());
        ch.setAmount(childMenu.getAmount());
        ch.setStatus(childMenu.getStatus());
        ch.setDate(DateTimes.getDate());
        ch.setTime(DateTimes.getHour());
        ch.setUsers(usersRepository.findById(usersId).get());
        ch.setSubMenu(subMenuRepository.findById(subId).get());

        return new ApiResponse<>(HttpStatus.OK.value(), "success", childRepository.save(ch));
    }

    @GetMapping(value = "/api/subchildmenu/{usersId}/sub/{subId}")
    public ApiResponse<ChildMenu> getChild(@PathVariable long usersId, @PathVariable long subId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", childRepository.findChildMenuById(usersId, subId));
    }

}
