package com.testo.mapper;

import com.testo.model.ShortUrl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * short Url mapper
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@Mapper
public interface ShortUrlMapper{

    /**
     * get short url
     *
     * @param longUrl
     * @return
     */
    ShortUrl queryShortUrl(String longUrl);

    /**
     * save
     *
     * @param shortUrl
     * @return
     */
    void save(ShortUrl shortUrl);

    /**
     * update shortUrl
     *
     * @param shortUrl
     */
    void update(ShortUrl shortUrl);

    /**
     * query short Record
     *
     * @param username
     * @return
     */
    List<ShortUrl> queryShortRecord(@Param("username") String username);

}

