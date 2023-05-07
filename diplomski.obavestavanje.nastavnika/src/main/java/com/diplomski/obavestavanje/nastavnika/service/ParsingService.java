package com.diplomski.obavestavanje.nastavnika.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;


public interface ParsingService {

    JsonNode parse(String url);
}
