package com.risingapp.likeit.util.mock.generators;

import com.risingapp.likeit.entity.ChatRoom;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleg on 08.04.17.
 */
@Log4j
@Component
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
            String word = restTemplate.getForObject("http://www.setgetgo.com/randomword/get.php", String.class);
            word = word.substring(0, 1).toUpperCase() + word.substring(1);
            return word;
        } catch (RestClientException e) {
            log.error("Network problem", e);
            log.warn("Generate default word");
            return "word";
        }

    }
}
