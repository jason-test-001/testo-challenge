package com.testo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.testo.config.ShortUrlGenerator;
import com.testo.mapper.ShortUrlMapper;
import com.testo.model.ShortUrl;
import com.testo.service.ShortUrlService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * short url service
 *
 * @author:heyaolei
 * @create: 2023-02-21
 */
@Service
public class ShortUrlServiceImpl implements ShortUrlService {

    @Value("${short.url.prefix}")
    private String urlPrefix;

    @Autowired
    private ShortUrlMapper shortUrlMapper;

    /**
     * get short url
     *
     * @param longUrl
     * @return
     */
    @Override
    public String getShortUrl(String longUrl) {
        ShortUrl shortUrlObject = shortUrlMapper.queryShortUrl(longUrl);
        if (shortUrlObject != null && !StringUtils.isEmpty(shortUrlObject.getShortUrl())) {
            update(shortUrlObject, 1, 0);
            return shortUrlObject.getShortUrl();
        }
        String shortUrl = urlPrefix + ShortUrlGenerator.shortUrl(longUrl);
        save(shortUrl, longUrl);
        return shortUrl;
    }

    /**
     * update shorturl
     *
     * @param object
     * @param ShortTimesIncre
     * @param accessTimeIncre
     */
    private void update(ShortUrl object, int ShortTimesIncre, int accessTimeIncre) {
        //get user name
        Object principal = SecurityUtils.getSubject().getPrincipal();
        String username = principal == null ? "customer" : principal.toString();
        object.setUpdateUser(username);
        object.setUpdateDate(new Date());
        object.setShortTimes(object.getShortTimes() + ShortTimesIncre);
        object.setAccessTimes(object.getAccessTimes() + accessTimeIncre);
        shortUrlMapper.update(object);
    }

    /**
     * build shortUrl and save
     *
     * @param shortUrl
     * @param longUrl
     */
    private void save(String shortUrl, String longUrl) {
        //get user name
        Object principal = SecurityUtils.getSubject().getPrincipal();
        String username = principal == null ? "customer" : principal.toString();
        //save
        ShortUrl model = new ShortUrl();
        model.setCreateUser(username);
        model.setCreateDate(new Date());
        model.setUpdateUser(username);
        model.setUpdateDate(new Date());
        model.setShortUrl(shortUrl);
        model.setLongUrl(longUrl);
        model.setShortTimes(1);
        model.setAccessTimes(1);
        shortUrlMapper.save(model);
    }

    /**
     * query all Record
     *
     * @return
     */
    @Override
    public List<ShortUrl> queryAllRecord() {
        return shortUrlMapper.queryShortRecord(null);
    }

    /**
     * query user Record
     *
     * @return
     */
    @Override
    public List<ShortUrl> queryUserRecord() {
        Object principal = SecurityUtils.getSubject().getPrincipal();
        String username = principal == null ? "customer" : principal.toString();
        return shortUrlMapper.queryShortRecord(username);
    }
}
