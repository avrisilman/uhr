package com.api.avris.controller;

import com.api.avris.exception.ResourceNotFoundException;
import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Market;
import com.api.avris.jpa.PinjamAlat;
import com.api.avris.repositories.PinjamAlatRepository;
import com.api.avris.repositories.SubMenuRepository;
import com.api.avris.repositories.UsersRepository;
import com.api.avris.util.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PinjamController {

    @Autowired
    PinjamAlatRepository pinjamAlatRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    SubMenuRepository subMenuRepository;


    @PostMapping("/api/sewa-alat/{usersId}/sub/{subId}")
    public ApiResponse<Object> postChildMenu(@PathVariable(value = "usersId") Long usersId,
                                             @PathVariable(value = "subId") Long subId,
                                             @RequestParam("title") final String title,
                                             @RequestParam("lamaPinjam") final String lamaPinjam,
                                             @RequestParam("price") final String price,
                                             @RequestParam("information") final String information,
                                             @RequestParam("imgUrl") final String imgUrl,
                                             @RequestParam("handphone") final String handphone) {
        PinjamAlat m = new PinjamAlat();
        m.setTitle(title);
        m.setLamaPinjam(lamaPinjam);
        m.setPrice(price);
        m.setInformation(information);
        m.setImgUrl(imgUrl);
        m.setStatus("-");
        m.setDate(DateTimes.getDate());
        m.setTime(DateTimes.getHour());
        m.setHandphone(handphone);
        m.setUsers(usersRepository.findById(usersId).get());
        m.setSubMenu(subMenuRepository.findById(subId).get());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", pinjamAlatRepository.save(m));
    }

    @GetMapping(value = "/api/sewa-alat/{subId}")
    public ApiResponse<PinjamAlat> getSewaAlat(@PathVariable(value = "subId") Long subId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", pinjamAlatRepository.findPinjamAlatBy(subId));
    }

    @GetMapping(value = "/api/sewa-edit/{usersId}/{menuId}")
    public ApiResponse<PinjamAlat> getEditSewaAlat(@PathVariable(value = "usersId") Long usersId, @PathVariable(value = "menuId") Long menuId){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", pinjamAlatRepository.findPinjamAlatEditById(usersId, menuId));
    }

    @DeleteMapping("/api/sewa-alat/{sewaId}")
    public Map<String, Boolean> deleteMarket(@PathVariable(value = "sewaId") Long sewaId)
            throws ResourceNotFoundException {
        PinjamAlat pinjamAlat = pinjamAlatRepository.findById(sewaId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + sewaId));

        pinjamAlatRepository.delete(pinjamAlat);
        Map<String, Boolean> response = new HashMap<>();
        response.put("status", Boolean.TRUE);
        return response;
    }

}
