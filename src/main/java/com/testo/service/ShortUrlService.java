package com.testo.service;

import com.testo.model.ShortUrl;

import java.util.List;

/**
 * short url service
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
public interface ShortUrlService {
    /**
     * get short url
     *
     * @param longUrl
     * @return
     */
    String getShortUrl(String longUrl);

    /**
     * query all Record
     *
     * @return
     */
    List<ShortUrl> queryAllRecord();

    /**
     * query user Record
     *
     * @return
     */
    List<ShortUrl> queryUserRecord();
}
