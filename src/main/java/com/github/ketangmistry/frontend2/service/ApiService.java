package com.github.ketangmistry.frontend2.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;


@Service
public class ApiService {
    private Logger logger = LoggerFactory.getLogger(ApiService.class);
    
    public String getMineralAmount()
    {
        // Hard coded
        // Again
        final String uri = "http://35.190.195.220";
    
        RestTemplate restTemplate = new RestTemplate();
        ((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(2000);

        String result = "0";
        try {
            result = restTemplate.getForObject(uri, String.class);
        }
        catch(RestClientException rce) {
            logger.error("API get exception: {}", rce.getMessage());
        }

        return result;
    }
}
