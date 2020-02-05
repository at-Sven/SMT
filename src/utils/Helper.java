package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class for general functions
 */
public class Helper {

    /**
     * checks if the given LocalDate and Time is in the future or null / empty
     * @param ld LocalDate
     * @param time as String
     * @return LocalDateTime object or null
     */
    public String checkIfLocalDateTimeIsNotNullAndInFuture(LocalDate ld, String time){
        if(ld != null && time != null && !time.isEmpty()){
            LocalDateTime now = LocalDateTime.now();
            System.out.println("now:" + now.toString());
            //String sdt = "2019-01-06"+"T"+time;
            System.out.println("in");
            String stringPostDateTime = ld.toString()+"T"+time;
            LocalDateTime postDateTime = LocalDateTime.parse(stringPostDateTime);
            System.out.println(postDateTime);
            if(postDateTime.isAfter(now)){
                System.out.println(postDateTime.isAfter(now));
                return stringPostDateTime;
            }
        }
        System.out.println("null");
        return null;
    }

}
