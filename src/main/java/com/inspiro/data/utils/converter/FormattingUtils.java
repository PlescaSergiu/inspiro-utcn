package com.inspiro.data.utils.converter;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class FormattingUtils {

    /**
     * Full date format. E.g: 13 iun. 2021, 13:30:38
     */
    public static String convertToFullTime(LocalDateTime localDateTime){
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);
    }



}
