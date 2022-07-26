package com.diplomski.obavestavanje.nastavnika.Service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class JsonParsingService implements ParsingService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JsonNode parse(String url) {
        return restTemplate.getForObject(url, JsonNode.class);
    }
}
