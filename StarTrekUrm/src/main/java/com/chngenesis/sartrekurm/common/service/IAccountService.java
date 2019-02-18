package com.chngenesis.sartrekurm.common.service;

import com.chngenesis.sartrekurm.common.entity.Account;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface IAccountService {

    /**
     * 注册账号
     * @param account
     * @return
     */
    Map<String, Object> registerAccount(Account account);

    /**
     * 用户登录
     * @param request
     * @param loginame
     * @param password
     * @return
     */
    Map<String, Object> checkLogin(HttpServletRequest request, String loginame, String password);
}
