package com.peach.dec.util.constant;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class Bak {
    public static void main(String[] args) {
        try {
            Runtime rt = Runtime.getRuntime();
//            String cmd = "mysqldump -hlocalhost -uroot -pmysqladmin hiring > e:/mysql.sql"; // 一定要加 -hlocalhost(或是服务器IP地址)
            String cmd = "exp scott/tiger@DEC file=D:\\daochu.dmp owner=(scott,sys)"; // 一定要加 -hlocalhost(或是服务器IP地址)
            Process process = rt.exec("cmd /c " + cmd);
            // 可查看错误信息！
            InputStreamReader isr = new InputStreamReader(process.getErrorStream());
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
