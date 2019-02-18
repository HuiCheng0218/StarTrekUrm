package com.chngenesis.sartrekurm.utils;

/**
 * HDFS 文件相关操作
 * @author Zhanghengrui 2018-12-27
 *
 */
public class HadoopFileUtil {

	/**
	 * 根据文件名产生新的文件名
	 * @param fileName 文件名
	 * @return
	 */
	public static String getFileName(String fileName) {
		return UUIDUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
	}
}
