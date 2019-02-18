package com.chngenesis.sartrekurm.common.controller;

import com.chngenesis.sartrekurm.common.entity.Account;
import com.chngenesis.sartrekurm.common.service.IAccountService;
import com.chngenesis.sartrekurm.utils.MapTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: LJP
 * @Date: 2019/2/15 11:13
 */
@Controller
@RequestMapping("account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    /**
     * 用户注册
     * @param request
     * @param loginame 登录名 手机号/邮箱地址
     * @param password
     * @return
     */
    @RequestMapping("checkLogin")
    @ResponseBody
    public Map<String, Object> checkLogin(HttpServletRequest request, String loginame, String password){
        Map<String, Object> base;
        try {
            if(StringUtils.isBlank(loginame)){
                return MapTemplate.error("请填写登录账号");
            }
            if(StringUtils.isBlank(password)){
                return MapTemplate.error("请填写登录密码");
            }
            base = accountService.checkLogin(request, loginame, password);
        }catch (Exception e){
            e.printStackTrace();
            return MapTemplate.error(e);
        }
        return base;
    }

    /**
     * 用户注册
     * @param account
     * @return
     */
    @RequestMapping("register")
    @ResponseBody
    public Map<String, Object> register(Account account){
        Map<String, Object> base;
        try{
            if(account != null){
                if(StringUtils.isBlank(account.getPhonenum())){
                    return MapTemplate.error("联系电话未填写");
                }
                if(StringUtils.isBlank(account.getEmail())){
                    return MapTemplate.error("邮件地址未填写");
                }
                if(StringUtils.isBlank(account.getPassword())){
                    return MapTemplate.error("密码未填写");
                }
                if(StringUtils.isBlank(account.getOrgId())){
                    return MapTemplate.error("所属机构未填写");
                }
                if(StringUtils.isBlank(account.getUsername())){
                    return MapTemplate.error("用户昵称未填写");
                }
                // 保存账号信息
                base = accountService.registerAccount(account);
            }else{
                return MapTemplate.error("存在必要信息未填写");
            }
        }catch (Exception e){
            e.printStackTrace();
            return MapTemplate.error(e);
        }
        return base;
    }
}
