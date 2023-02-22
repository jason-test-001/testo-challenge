package com.testo.service.impl;

import com.testo.mapper.LoginMapper;
import com.testo.model.SysUser;
import com.testo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * login service
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginMapper loginMapper;

    /**
     * query user base username
     *
     * @param userName
     * @return
     */
    @Override
    public SysUser queryUser(String userName) {
        return loginMapper.queryUser(userName);
    }

    /**
     * query user permission
     *
     * @param userName
     * @return
     */
    @Override
    public List<Map<String, Object>> getUserPower(String userName) {
        return loginMapper.getUserPower(userName);
    }
}