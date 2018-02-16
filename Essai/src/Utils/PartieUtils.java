package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PartieUtils {

    public String aujourdhui() {
        final Date date = new Date();
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
 
}