package com.example.todo.util;

import java.util.HashMap;

public class FaDigitsConverter {

    public static String convert(String s){
        String temp = s;

        HashMap<Character,Character> digits = new HashMap<>();
        digits.put('0','۰');
        digits.put('1','۱');
        digits.put('2','۲');
        digits.put('3','۳');
        digits.put('4','۴');
        digits.put('5','۵');
        digits.put('6','۶');
        digits.put('7','۷');
        digits.put('8','۸');
        digits.put('9','۹');

        for (Character c : temp.toCharArray()){
            if (Character.isDigit(c)){
                temp = temp.replace(c,digits.get(c));
            }
        }

        return temp;
    }
}
