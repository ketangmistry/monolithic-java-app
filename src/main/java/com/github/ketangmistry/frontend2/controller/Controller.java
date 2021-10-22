package com.github.ketangmistry.frontend2.controller;

import com.github.ketangmistry.frontend2.service.ExternalMineralsFeed;
import com.github.ketangmistry.frontend2.service.MineralsService;
import com.github.ketangmistry.frontend2.model.Mineral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
class Controller {
    private static final String API_URI = System.getenv("API3_URI") != null ? System.getenv("API3_URI") : "http://1.2.3.4:8080";

    private Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private MineralsService mineralService;

    @Autowired
    private ExternalMineralsFeed apiService;

    @GetMapping(value="/")
    public ModelAndView showMinerals() {
        List<Mineral> minerals = mineralService.findAll();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("minerals", minerals);
        
        return new ModelAndView("showMinerals", params);
    }

    @PutMapping(value="/minerals/{name}/{amount}")
    public String showMinerals(@PathVariable(value = "name") String name,
                                    @PathVariable(value = "amount") int amount) {

        boolean update = mineralService.update(name, amount);  

        return amount + " of " + name + " purchased (" + update + ")";                          

    }



}