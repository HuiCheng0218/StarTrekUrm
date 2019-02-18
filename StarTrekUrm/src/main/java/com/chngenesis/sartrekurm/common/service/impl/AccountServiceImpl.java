package com.chngenesis.sartrekurm.common.service.impl;

import com.chngenesis.sartrekurm.common.entity.Account;
import com.chngenesis.sartrekurm.common.mapper.AccountMapper;
import com.chngenesis.sartrekurm.common.service.IAccountService;
import com.chngenesis.sartrekurm.utils.Constants;
import com.chngenesis.sartrekurm.utils.MapTemplate;
import com.chngenesis.sartrekurm.utils.PasswordEncryption;
import com.chngenesis.sartrekurm.utils.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 账户登录相关
 * @Author: LJP
 * @Date: 2019/2/14 17:01
 */
@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;


    @Override
    public Map<String, Object> registerAccount(Account account) {
        try {
            // 判断手机号码
            if(StringUtils.isNotBlank(account.getPhonenum())){
                Account accountWithPhon = accountMapper.selecByPonenum(account.getPhonenum());
                if(accountWithPhon!=null && StringUtils.isNotBlank(accountWithPhon.getId())){
                    return MapTemplate.error("该手机号码已被注册");
                }
            }
            // 判断邮箱是否存在
            if(StringUtils.isNotBlank(account.getEmail())){
                Account accountWithEmail = accountMapper.selecByEmail(account.getEmail());
                if(accountWithEmail!=null && StringUtils.isNotBlank(accountWithEmail.getId())){
                    return MapTemplate.error("该邮箱已被注册");
                }
            }
            // 生成账号ID
            account.setId(UUIDUtil.getUUID());
            // 获得盐值
            String salt = PasswordEncryption.generateSalt();
            account.setSalt(salt);
            // 获得加密密码
            String encryptedPassword = PasswordEncryption.getEncryptedPassword(account.getPassword(), salt);
            account.setPassword(encryptedPassword);
            // 逻辑删除状态
            account.setDel(Constants.NOT_DEL);
            accountMapper.insertSelective(account);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MapTemplate.success();
    }

    @Override
    public Map<String, Object> checkLogin(HttpServletRequest request, String loginame, String password) {
        Account account = null;
        try{
            // 判断登录名
            boolean isEmail = loginame.contains("@");
            // 查询账户信息
            if(isEmail){
                account = accountMapper.selecByEmail(loginame);
            }else{
                account = accountMapper.selecByPonenum(loginame);
            }
            // 判断账户
            if(account==null || StringUtils.isBlank(account.getId())){
                return MapTemplate.error("该账户不存在，请检查账号是否输入正确");
            }
            // 校对密码
            boolean res = PasswordEncryption.authenticate(password, account.getPassword(), account.getSalt());
            if(!res){
               return MapTemplate.error("密码输入不正确，请重新输入");
            }
            // 存入session
            HttpSession session = request.getSession();
            session.setAttribute(Constants.ACCOUNT_IN_SESSION, account);
        }catch (Exception e){
            e.printStackTrace();
            return MapTemplate.error(e);
        }
        return MapTemplate.success("data", account);
    }
}
