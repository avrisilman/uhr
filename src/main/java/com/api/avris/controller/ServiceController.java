package com.api.avris.controller;

import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Event;
import com.api.avris.jpa.Service;
import com.api.avris.repositories.ServiceRespository;
import com.api.avris.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ServiceController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ServiceRespository serviceRespository;


    @PostMapping("/api/service/{usersId}")
    public ApiResponse<Event> postEvent(@PathVariable(value = "usersId") Long usersId, @RequestParam("information") final String information, @RequestParam("images") final String images){
        Service e = new Service();
        e.setInformation(information);
        e.setImages(images);
        e.setStatus("belum");
        e.setUsers(usersRepository.findById(usersId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", serviceRespository.save(e));
    }

}
