package com.testo.mapper;

import com.testo.model.SysUser;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

/**
 * login mapper
 *
 * @author:heyaolei
 * @create: 2023-02-20
 */
@Mapper
public interface LoginMapper {

    /**
     * query user base userName
     * @param userName
     * @return
     */
    SysUser queryUser(String userName );

    /**
     * query user permission
     *
     * @param userName
     * @return
     */
    List<Map<String,Object>> getUserPower(String userName);
}

