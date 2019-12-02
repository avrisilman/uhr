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
    public ApiResponse<Object> postChildMenu(@PathVariable(value = "usersId") Long usersId,
                                             @Valid @RequestBody Event e) {
        Event event = new Event();
        event.setClock(e.getClock());
        event.setDate(e.getDate());
        event.setInformation(e.getInformation());
        event.setUsers(usersRepository.findById(usersId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", eventRepository.save(event));
    }

    @GetMapping(value = "/api/event/{usersId}")
    public ApiResponse<Event> getEvent(@PathVariable long usersId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", eventRepository.findEventBy(usersId));
    }

}
