package com.api.avris.controller;


import com.api.avris.jpa.ApiResponse;
import com.api.avris.jpa.Info;
import com.api.avris.jpa.Market;
import com.api.avris.repositories.InfoRepository;
import com.api.avris.util.DateTimes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class InfoController {

    @Autowired
    InfoRepository infoRepository;

    @PostMapping("/api/info")
    public ApiResponse<Object> postMenu(@RequestParam("title") final String title,
                                        @RequestParam("information") final String information
                                       ) {
        Info i = new Info();
        i.setTitle(title);
        i.setInformation(information);
        i.setDate(DateTimes.getDate());
        i.setTime(DateTimes.getHour());
        return new ApiResponse<>(HttpStatus.OK.value(), "success", infoRepository.save(i));
    }

    @GetMapping(value = "/api/info")
    public ApiResponse<Market> getInfo(){
        return new ApiResponse<>(HttpStatus.OK.value(), "success", infoRepository.findAll());
    }

}
