package com.testo.controller;

import com.testo.model.ShortUrl;
import com.testo.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ShortUrlController
 *
 * @author:heyaolei
 * @create: 2023-02-19
 */
@Slf4j
@RestController
public class ShortUrlController {

    @Autowired
    private ShortUrlService shortUrlService;

    /**
     * getShortUrl
     *
     * @param longUrl
     * @return
     */
    @RequestMapping("/getShortUrl")
    public String getShortUrl(@RequestParam("longUrl") String longUrl) {
        return shortUrlService.getShortUrl(longUrl);
    }

    /**
     * queryAllRecord
     * only admin user(adminHong) can access this interface
     *
     * @return
     */
    @RequiresRoles("admin")
    @RequiresPermissions("queryAllRecord")
        @RequestMapping("/queryAllRecord")
    public List<ShortUrl> queryAllRecord() {
        return shortUrlService.queryAllRecord();
    }

    /**
     * queryUserRecord
     * common(jason、kevin) user can access this interface
     *
     * @return
     */
    @RequiresRoles("common")
    @RequiresPermissions("queryUserRecord")
    @RequestMapping("/queryUserRecord")
    public List<ShortUrl> queryUserRecord() {
        return shortUrlService.queryUserRecord();
    }

}
