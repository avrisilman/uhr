package com.api.avris.controller;

import com.api.avris.exception.ResourceNotFoundException;
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
import java.util.HashMap;
import java.util.Map;

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
                                             @RequestParam("title") final String title,
                                             @RequestParam("conditions") final String conditions,
                                             @RequestParam("price") final String price,
                                             @RequestParam("information") final String information,
                                             @RequestParam("imgUrl") final String imgUrl,
                                             @RequestParam("handphone") final String handphone) {
        Market m = new Market();
        m.setTitle(title);
        m.setConditions(conditions);
        m.setPrice(price);
        m.setInformation(information);
        m.setImgUrl(imgUrl);
        m.setStatus("-");
        m.setDate(DateTimes.getDate());
        m.setTime(DateTimes.getHour());
        m.setHandphone(handphone);
        m.setUsers(usersRepository.findById(usersId).get());
        m.setSubMenu(subMenuRepository.findById(subId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", marketRepository.save(m));
    }

    @GetMapping(value = "/api/market/{subId}")
    public ApiResponse<Market> getMarket(@PathVariable(value = "subId") Long subId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", marketRepository.findMarketById(subId));
    }

    @GetMapping(value = "/api/market-edit/{usersId}/{menuId}")
    public ApiResponse<Market> getEditMarket(@PathVariable(value = "usersId") Long usersId, @PathVariable(value = "menuId") Long menuId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", marketRepository.findMarketEditById(usersId, menuId));
    }

    @DeleteMapping("/api/market/{marketId}")
    public Map<String, Boolean> deleteMarket(@PathVariable(value = "marketId") Long marketId)
            throws ResourceNotFoundException {
        Market market = marketRepository.findById(marketId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + marketId));

        marketRepository.delete(market);
        Map<String, Boolean> response = new HashMap<>();
        response.put("status", Boolean.TRUE);
        return response;
    }

}
