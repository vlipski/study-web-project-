package by.pvt.util;



import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Component
public class ParsDate {

    private static final Logger log = Logger.getLogger(ParsDate.class);

    public Date parsStringToDate(String dateStr) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            return simpleDateFormat.parse(dateStr);
        } catch (java.text.ParseException e) {
            log.error(e);
            return null;
        }
    }
}
