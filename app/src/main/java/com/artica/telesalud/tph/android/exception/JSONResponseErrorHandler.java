package com.artica.telesalud.tph.android.exception;

import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by interoperabilidad on 26/05/14.
 */
public class JSONResponseErrorHandler implements ResponseErrorHandler{

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
    }

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return RestUtil.isError(response.getStatusCode());
    }
}
