package com.artica.telesalud.tph.android.exception;

/**
 * Created by interoperabilidad on 26/05/14.
 */
import org.springframework.http.HttpStatus;

public class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}