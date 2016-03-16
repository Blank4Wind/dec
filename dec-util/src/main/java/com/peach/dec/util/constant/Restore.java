package com.peach.dec.util.constant;

import java.io.IOException;

public class Restore {

    public static void main(String[] args) {
        try {
            Runtime rt = Runtime.getRuntime();
            //String cmd = "mysql -hlocalhost -uroot -pmysqladmin hiring < e:/mysql.sql"; // 一定要加 -hlocalhost(或是服务器IP地址)
            String cmd = "imp scott/tiger@DEC fully file=d:\\daochu.dmp ignore=y"; // 一定要加 -hlocalhost(或是服务器IP地址)
            rt.exec("cmd /c " + cmd);
            System.out.println("还原成功!");
        } catch (IOException e) {
            System.out.println("还原失败!");
            e.printStackTrace();
        }

    }
}
