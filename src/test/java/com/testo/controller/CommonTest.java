package com.testo.controller;

import com.testo.TestoChallengeApplication;
import com.testo.model.ShortUrl;
import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * imitation common user test
 *
 * @author:heyaolei
 * @create: 2023-02-25
 */
@Slf4j
@SpringBootTest
public class CommonTest {

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
     * login as common, common has permission to run getUserPermission interface
     * but hasn't permission to run getAllPermission
     *
     * @return
     */
    @Test
    public List<String> login() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("username", "jason");
        map.add("password", "233");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(map, httpHeaders);

        String requestUrl = "http://localhost:8077/login";
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(requestUrl, requestEntity, String.class);
        List<String> cookieList = responseEntity.getHeaders().get("Set-Cookie");
        log.info("cookieList:{} ", cookieList);
        return cookieList;
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
     * login to get cookie
     *
     * @return
     */
    public String getCookie() {
        List<String> list = login();
        String cookieStr = "";
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).contains("JSESSIONID")) {
                cookieStr = list.get(i);
            }
        }
        return cookieStr;
    }

    /**
     * query record
     *
     * @param requestUrl
     */
    public void queryRecord(String requestUrl) {
        //set cookie to httpHeaders
        String cookieStr = getCookie();
        List<String> cookieList = new ArrayList<>();
        cookieList.add(cookieStr);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.put(HttpHeaders.COOKIE, cookieList);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> map = new HashMap<>();
        HttpEntity<Map<String, String>> httpEntity = new HttpEntity(map, httpHeaders);

        ResponseEntity<List> responseEntity = restTemplate.postForEntity(requestUrl, httpEntity, List.class);
        List<ShortUrl> shortUrlList = (List<ShortUrl>) responseEntity.getBody();
        log.info("request: {};  response:{} ", requestUrl, JSONArray.toJSONString(shortUrlList));
    }


    /**
     * query common's own statistic
     */
    @Test
    public void queryUserRecord() {
        String requestUrl = "http://localhost:8077/queryUserRecord";
        queryRecord(requestUrl);
    }

    /**
     * have no permission to access
     */
    @Test
    public void queryAllRecord() {
        String requestUrl = "http://localhost:8077/queryAllRecord";
        queryRecord(requestUrl);
    }


}
