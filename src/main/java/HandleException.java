import java.io.IOException;
import java.text.ParseException;

public class HandleException {

    public static void handleException(Exception exception) {

        if (exception instanceof IOException) {
            System.out.println(Const.FILE_NOT_FOUND);
        }

        if (exception instanceof ArrayIndexOutOfBoundsException
                || exception instanceof ParseException) {
            System.out.println(Const.INVALID_LINE);
        }
    }
}
