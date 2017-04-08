package com.risingapp.likeit.util.mock.generators;

import lombok.extern.log4j.Log4j;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 08.04.17.
 */
@Log4j
public class WordGenerator extends Generator<String> {
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public String generateObject() {
        return requestWord();
    }

    @Override
    public List<String> generateObjects(int count) {
        List<String> words = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            words.add(requestWord());
        }
        return words;
    }


    private String requestWord() {
        try {
            return restTemplate.getForObject("http://www.setgetgo.com/randomword/get.php", String.class);
        } catch (RestClientException e) {
            log.error("Network problem", e);
            log.warn("Generate default word");
            return "word";
        }

    }
}
