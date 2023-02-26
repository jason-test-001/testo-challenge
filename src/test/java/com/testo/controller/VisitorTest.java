package com.testo.controller;

import com.testo.TestoChallengeApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

/**
 * imitation visitor  test
 *
 * @author:heyaolei
 * @create: 2023-02-25
 */
@Slf4j
@SpringBootTest
public class VisitorTest {

    private RestTemplate restTemplate = new RestTemplate();

    /**
     * run this method to start server
     * before all the test
     */
    @BeforeAll
    public static void beforeAll() {
        SpringApplication.run(TestoChallengeApplication.class);
    }


    /**
     * get short url
     */
    @Test
    public void shortUrl() {
        String longUrl = "https://3w.huanqiu.com/a/de583b/4Bq44PBJKOp?agt=128";
        String requestUrl = "http://localhost:8077/getShortUrl?longUrl=" + longUrl;

        String shortUrl = restTemplate.getForObject(requestUrl, String.class);
        log.info("shortUrl: {}", shortUrl);
    }


    /**
     * have no permission to access
     */
    @Test
    public void queryUserRecord() {
        String requestUrl = "http://localhost:8077/queryUserRecord";
        restTemplate.getForObject(requestUrl, String.class);
    }

    /**
     * have no permission to access
     */
    @Test
    public void queryAllRecord() {
        String requestUrl = "http://localhost:8077/queryAllRecord";
        restTemplate.getForObject(requestUrl, String.class);
    }


}
