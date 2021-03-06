package com.github.ketangmistry.frontend2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiService {
    
    public String getMineralAmount()
    {
        // Hard coded
        // Again
        final String uri = "http://35.190.195.220";
    
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        return result;
    }
}
