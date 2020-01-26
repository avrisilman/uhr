package com.api.avris.controller;

import com.api.avris.exception.ResourceNotFoundException;
import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Market;
import com.api.avris.jpa.Numpang;
import com.api.avris.repositories.NumpangRepository;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.util.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NumpangController {

    @Autowired
    NumpangRepository numpangRepository;

    @Autowired
    UsersRepository usersRepository;

    @PostMapping("/api/numpang/{usersId}")
    public ApiResponse<Object> postNumpang(@PathVariable(value = "usersId") Long usersId,
                                           @RequestParam("goingto") final String goingto,
                                           @RequestParam("clock") final String clock,
                                           @RequestParam("date") final String date,
                                           @RequestParam("information") final String information,
                                           @RequestParam("harga") final String harga
                                           ) {
        Numpang numpang = new Numpang();
        numpang.setGoingto(goingto);
        numpang.setClock(clock);
        numpang.setDate(date);
        numpang.setInformation(information);
        numpang.setHarga(harga);
        numpang.setUsers(usersRepository.findById(usersId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", numpangRepository.save(numpang));
    }

    @GetMapping(value = "/api/numpang")
    public ApiResponse<Numpang> getNumpang(){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", numpangRepository.findAll());
    }

    @GetMapping(value = "/api/numpang-detail/{usersId}")
    public ApiResponse<Object> getNumpangDetail(@PathVariable(value = "usersId") Long usersId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", usersRepository.findNumpangById(usersId));
    }

    @GetMapping(value = "/api/numpang-edit/{usersId}")
    public ApiResponse<Numpang> getEditNumpang(@PathVariable(value = "usersId") Long usersId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", numpangRepository.findNumpangEditById(usersId));
    }

    @DeleteMapping("/api/numpang/{numpangId}")
    public Map<String, Boolean> deleteNumpang(@PathVariable(value = "numpangId") Long numpangId)
            throws ResourceNotFoundException {
        Numpang numpang = numpangRepository.findById(numpangId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + numpangId));

        numpangRepository.delete(numpang);
        Map<String, Boolean> response = new HashMap<>();
        response.put("status", Boolean.TRUE);
        return response;
    }

}
