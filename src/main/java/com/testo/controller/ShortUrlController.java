package com.testo.controller;

import com.testo.model.ShortUrl;
import com.testo.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
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
     * get short url
     *
     * @param longUrl
     * @return
     */
    @RequestMapping("/getShortUrl")
    public String getShortUrl(@RequestParam("longUrl") String longUrl) {
        return shortUrlService.getShortUrl(longUrl);
    }

    /**
     * only admin(adminHong) user can access this interface
     * get all statistic
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
     * not only common(jason、kevin) but also admin(adminHong) can access this interface
     * to get their own statistic
     *
     * @return
     */
    @RequiresRoles(value = {"admin", "common"}, logical = Logical.OR)
    @RequiresPermissions("queryUserRecord")
    @RequestMapping("/queryUserRecord")
    public List<ShortUrl> queryUserRecord() {
        return shortUrlService.queryUserRecord();
    }

}
