package com.github.ketangmistry.frontend2.controller;

import com.github.ketangmistry.frontend2.service.IMineralService;
import com.github.ketangmistry.frontend2.service.ApiService;
import com.github.ketangmistry.frontend2.model.Mineral;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@RestController
class Controller {

    @Autowired
    private IMineralService mineralService;

    @Autowired
    private ApiService apiService;

    @GetMapping(value = "/")
    public String index(Model model) {

        return "index";
    }

    @GetMapping(value="/minerals")
    public ModelAndView showMinerals() {
        List<Mineral> minerals = mineralService.findAll();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("minerals", minerals);
        System.out.println("Mineral Amount from API:" + apiService.getMineralAmount());
        
        return new ModelAndView("showMinerals", params);
    }

    @PutMapping(value="/minerals/{name}/{amount}")
    public String showMinerals(@PathVariable(value = "name") String name,
                                    @PathVariable(value = "amount") int amount) {

        boolean update = mineralService.updateMineral(name, amount);  

        return amount + " of " + name + " purchased (" + update + ")";                          

    }



}