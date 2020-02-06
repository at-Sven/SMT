package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * Class for general helper functions
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
            //String sdt = "2019-01-06"+"T"+time;
            String stringPostDateTime = ld.toString()+"T"+time;
            LocalDateTime postDateTime = LocalDateTime.parse(stringPostDateTime);
            if(postDateTime.isAfter(now)){
                //System.out.println(postDateTime.isAfter(now));
                return stringPostDateTime;
            }
        }
        return null;
    }
    /**
     * checks if the given LocalDate and Time is in the future or null / empty
     * @param posttime is Datetime in form yyyy-MM-ddThh:mm:ss... (like 2020-02-12T02:59:16) Isodate
     * @return result boolean true if now is after posttime
     */
    public boolean timeNowIsAfterPosttime(String posttime){
        boolean result = false;
        if(posttime != null && !posttime.isEmpty()){
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime postDateTime = LocalDateTime.parse(posttime);
            if(now.isAfter(postDateTime)){
                result = true;
            }
        }
        return result;
    }

    /* main for tests:
    public static void main(String[]args){
        Helper h = new Helper();
        System.out.println(h.timeNowIsAfterPosttime("2019-02-12T02:59:16"));
    }
    */
}
