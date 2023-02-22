package com.testo.service;

import com.testo.model.SysUser;

import java.util.List;
import java.util.Map;

/**
 * login service
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
public interface LoginService {

    /**
     * query user base username
     *
     * @param userName
     * @return
     */
    SysUser queryUser(String userName);

    /**
     * query user permission base username
     *
     * @param userName
     * @return
     */
    List<Map<String, Object>> getUserPower(String userName);
}