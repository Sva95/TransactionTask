import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class StringUtil {

    private static DateFormat formatTransactionTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static Date convertTransactionTime(String time) {
        try {
            return formatTransactionTime.parse(time);
        } catch (ParseException e) {
            HandleException.handleException(e);
        }
        return new Date();
    }

    public static String countFrequency(int count, long frequency) {
        if (frequency >= 9) {
            return String.format("%02d", count);
        }
        return String.valueOf(count);
    }

    public static String cleanWithReverse(String string) {
        return new StringBuilder(string.replaceAll("\"", "")
                .trim())
                .reverse()
                .toString();
    }

    public static String clean(String string) {
        return string.replaceAll("\"", "").trim();
    }

    public static String reverse(String string) {
        return new StringBuilder(string)
                .reverse()
                .toString();
    }
}
