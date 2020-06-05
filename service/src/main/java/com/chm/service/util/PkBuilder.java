package com.chm.service.util;

import java.util.Date;
import java.util.Random;


public class PkBuilder {

    public String getPk(String param){
        if (param != null){
            StringBuilder result = new StringBuilder();
            long first = new Date().getTime();
            result.append(first);
            int len = param.length();
            len = len%26;
            char second = (char)(len+65);
            result.append(second);
            String s = "ABCDEFGHIJKLMNOBQRSTUVWSYZ";
            char [] third = s.toCharArray();
            for (int i = 0;i < 3;i++) {
                result.append(third[new Random().nextInt(s.length())]);
            }
            for (int i = 0;i < 3;i++) {
                result.append(new Random().nextInt(9));
            }
            return result.toString();
        }else {
            return null;
        }
    }

}
