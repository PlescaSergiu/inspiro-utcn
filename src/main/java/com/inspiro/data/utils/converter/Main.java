package com.inspiro.data.utils.converter;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        LocalDateTime localDateTime =  LocalDateTime.now();
        LocalDateTime test =  LocalDateTime.now().minusDays(2);
        long diff = ChronoUnit.SECONDS.between(localDateTime, test);
        System.out.println(diff);

        if (diff > 60){
            System.out.println("test");
        }
    }
}
