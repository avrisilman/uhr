package com.api.avris.controller;

import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.ChildMenu;
import com.api.avris.jpa.Market;
import com.api.avris.repositories.MarketRepository;
import com.api.avris.repositories.SubMenuRepository;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.util.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class MarketController {

    @Autowired
    MarketRepository marketRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SubMenuRepository subMenuRepository;

    @PostMapping("/api/market/{usersId}/sub/{subId}")
    public ApiResponse<Object> postChildMenu(@PathVariable(value = "usersId") Long usersId,
                                             @PathVariable(value = "subId") Long subId,
                                             @Valid @RequestBody Market market) {
        Market m = new Market();
        m.setTitle(market.getTitle());
        m.setConditions(market.getConditions());
        m.setPrice(market.getPrice());
        m.setInformation(market.getInformation());
        m.setImgUrl(market.getImgUrl());
        m.setStatus(market.getStatus());
        m.setDate(DateTimes.getDate());
        m.setTime(DateTimes.getHour());
        m.setUsers(usersRepository.findById(usersId).get());
        m.setSubMenu(subMenuRepository.findById(subId).get());

        return new ApiResponse<>(HttpStatus.OK.value(), "success", marketRepository.save(m));
    }

    @GetMapping(value = "/api/market/{usersId}/sub/{subId}")
    public ApiResponse<Market> getMarket(@PathVariable long usersId, @PathVariable long subId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", marketRepository.findMarketById(usersId, subId));
    }

}
