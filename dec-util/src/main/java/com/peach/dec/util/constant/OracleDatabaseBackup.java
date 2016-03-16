package com.peach.dec.util.constant;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
 * Oracle数据库备份
 * 
 * @author GaoHuanjie
 */

public class OracleDatabaseBackup implements Runnable {
	/**
	 * Java代码实现Oracle数据库导出
	 * 
	 * @return 返回true表示导出成功，否则返回false。
	 */
	public static boolean exportDatabaseTool(String fileName)
			throws InterruptedException {
		// File saveFile = new File(savePath);
		// if (!saveFile.exists()) {// 如果目录不存在
		// saveFile.mkdirs();// 创建文件夹
		// }
		try {
			Runtime rt = Runtime.getRuntime();
			// String cmd =
			// "mysqldump -hlocalhost -uroot -pmysqladmin hiring > e:/mysql.sql";
			// // 一定要加 -hlocalhost(或是服务器IP地址)
			String cmd = "exp scott/tiger@DEC file=D:\\" + fileName
					+ ".dmp owner=(scott)"; // 一定要加
											// -hlocalhost(或是服务器IP地址)
			Process process = rt.exec("cmd /c " + cmd);
			// 可查看错误信息！
			InputStreamReader isr = new InputStreamReader(
					process.getErrorStream());
			LineNumberReader input = new LineNumberReader(isr);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line + "~~~~~~~~~~");
			}
			return true;
		} catch (IOException e) {
			System.out.println("备份失败!");
			e.printStackTrace();
		}
		return false;
	}

	public static void main(String[] args) throws InterruptedException {
		if (exportDatabaseTool("dec")) {
			System.out.println("数据库成功备份！！！");
		} else {
			System.out.println("数据库备份失败！！！");
		}
	}

	@Override
	public void run() {
		try {
			Runtime rt = Runtime.getRuntime();
			String cmd = "exp scott/tiger@DEC file=D:\\DEC.dmp owner=(scott)"; // 一定要加
			Process process = rt.exec("cmd /c " + cmd);
			// 可查看错误信息！
			InputStreamReader isr = new InputStreamReader(
					process.getErrorStream());
			LineNumberReader input = new LineNumberReader(isr);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line + "~~~~~~~~~~");
			}
			System.out.println("备份成功!");
		} catch (IOException e) {
			System.out.println("备份失败!");
			e.printStackTrace();
		}
	}
}
