package com.chngenesis.sartrekurm.common.mapper;

import com.chngenesis.sartrekurm.common.entity.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(String id);

    int insert(Account record);

    /**
     * 选择性添加账户信息
     * @param record
     * @return
     */
    int insertSelective(Account record);

    Account selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * 根据电话号码查询账户
     * @param phonenum
     * @return
     */
    Account selecByPonenum(String phonenum);

    /**
     * 根据邮箱查询账户
     * @param email
     * @return
     */
    Account selecByEmail(String email);
}