package com.testo.model;

import lombok.Data;

import java.util.Date;


/**
 * short_url
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@Data
public class ShortUrl {

    private Integer id;
    private String createUser;
    private Date createDate;
    private String updateUser;
    private Date updateDate;
    private String shortUrl;
    private String longUrl;
    private int shortTimes;
    private int accessTimes;
}