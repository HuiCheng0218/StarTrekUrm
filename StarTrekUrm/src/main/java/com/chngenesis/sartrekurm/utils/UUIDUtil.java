package com.chngenesis.sartrekurm.utils;
import java.util.UUID;

/**
 * UUID获取 截取掉-
 * @author zhanghengrui 2017-11-22
 *
 */
public class UUIDUtil {
	
	/**
	 * 获取没有-的UUID
	 * @return UUID字符串
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	public static void main(String[] args) {
		System.err.println(getUUID());
	}
}
