package com.api.avris.controller;

import com.api.avris.exception.ResourceNotFoundException;
import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Hobi;
import com.api.avris.jpa.Market;
import com.api.avris.repositories.HobiRepository;
import com.api.avris.repositories.SubMenuRepository;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.util.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HobiController {

    @Autowired
    HobiRepository hobiRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SubMenuRepository subMenuRepository;

    @PostMapping("/api/hobi/{usersId}/sub/{subId}")
    public ApiResponse<Object> postChildMenu(@PathVariable(value = "usersId") Long usersId,
                                             @PathVariable(value = "subId") Long subId,
                                             @RequestParam("title") final String title,
                                             @RequestParam("information") final String information,
                                             @RequestParam("imgUrl") final String imgUrl) {
        Hobi h = new Hobi();
        h.setTitle(title);
        h.setTime(DateTimes.getHour());
        h.setDate(DateTimes.getDate());
        h.setInformation(information);
        h.setImgUrl(imgUrl);
        h.setUsers(usersRepository.findById(usersId).get());
        h.setSubMenu(subMenuRepository.findById(subId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", hobiRepository.save(h));
    }

    @GetMapping(value = "/api/hobi/{subId}")
    public ApiResponse<Hobi> getMarket(@PathVariable long subId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", hobiRepository.findHobiById(subId));
    }

    @GetMapping(value = "/api/hobi-edit/{usersId}/{menuId}")
    public ApiResponse<Hobi> getEditHobi(@PathVariable(value = "usersId") Long usersId, @PathVariable(value = "menuId") Long menuId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", hobiRepository.findHobiEditById(usersId, menuId));
    }

    @DeleteMapping("/api/hobi/{hobiId}")
    public Map<String, Boolean> deleteMarket(@PathVariable(value = "hobiId") Long hobiId)
            throws ResourceNotFoundException {
        Hobi hobi = hobiRepository.findById(hobiId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + hobiId));

        hobiRepository.delete(hobi);
        Map<String, Boolean> response = new HashMap<>();
        response.put("status", Boolean.TRUE);
        return response;
    }

}
