package com.api.avris.controller;

import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Event;
import com.api.avris.jpa.Numpang;
import com.api.avris.repositories.EventRepository;
import com.api.avris.repositories.NumpangRepository;
import com.api.avris.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class NumpangController {

    @Autowired
    NumpangRepository numpangRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/api/numpang/{usersId}")
    public ApiResponse<Object> postNumpang(@PathVariable(value = "usersId") Long usersId,
                                             @Valid @RequestBody Numpang n) {
        Numpang numpang = new Numpang();
        numpang.setGoingto(n.getGoingto());
        numpang.setClock(n.getClock());
        numpang.setDate(n.getDate());
        numpang.setInformation(n.getInformation());
        numpang.setIdNumpang(n.getIdNumpang());
        numpang.setUsers(usersRepository.findById(usersId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", numpangRepository.save(numpang));
    }

    @GetMapping(value = "/api/numpang")
    public ApiResponse<Event> getNumpang(){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", numpangRepository.findNumpangById());
    }

}
