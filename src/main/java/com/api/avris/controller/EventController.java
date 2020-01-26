package com.api.avris.controller;

import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.ChildMenu;
import com.api.avris.jpa.Event;
import com.api.avris.repositories.EventRepository;
import com.api.avris.repositories.SubMenuRepository;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.util.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UsersRepository usersRepository;


    @PostMapping("/api/event/{usersId}")
    public ApiResponse<Event> postEvent(@PathVariable(value = "usersId") Long usersId,@RequestParam("clock") final String clock, @RequestParam("date") final String date, @RequestParam("information") final String information){
        Event e = new Event();
        e.setClock(clock);
        e.setDate(date);
        e.setInformation(information);
        e.setStatus("BELUM DI SETUJUI");
        e.setUsers(usersRepository.findById(usersId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", eventRepository.save(e));
    }

    @GetMapping(value = "/api/event/{usersId}")
    public ApiResponse<Event> getEvent(@PathVariable long usersId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", eventRepository.findEventBy(usersId));
    }

}
