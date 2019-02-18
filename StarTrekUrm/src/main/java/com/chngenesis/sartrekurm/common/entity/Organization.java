package com.chngenesis.sartrekurm.common.entity;

import java.util.Date;

/**
 * 机构信息表
 */
public class Organization {

    private String id;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 总负责人
     */
    private String chargeman;

    /**
     * 机构地址
     */
    private String address;

    /**
     * 机构成立时间
     */
    private Date creatime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getChargeman() {
        return chargeman;
    }

    public void setChargeman(String chargeman) {
        this.chargeman = chargeman == null ? null : chargeman.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }
}