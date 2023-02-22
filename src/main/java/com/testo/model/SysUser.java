package com.testo.model;

import lombok.Data;


/**
 * sys_user
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@Data
public class SysUser {

    private Integer userId;
    private String userName;
    private String password;
    private String userRemarks;
}