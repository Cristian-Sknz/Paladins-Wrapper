package me.skiincraft.api.paladins.internal.requests.impl;

import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.internal.requests.ResponseParser;
import okhttp3.Response;

import java.io.IOException;
import java.util.function.Function;

public class DefaultResponseParser<T> extends ResponseParser<T> {

    private final int code;

    public DefaultResponseParser(Response response, Function<Response, T> function) {
        super(response, function);
        this.code = response.code();
    }

    @Override
    public boolean onFailure(Response response) {
        try {
            if (code >= 400 && code <= 401) {
                throw new RequestException("Unauthorized access, check that your credentials are correct.\n" + (response.body() != null ? response.body().string() : null));
            }
            if (code == 404) {
                throw new IOException("This resource was not found by the API.\n" + (response.body() != null ? response.body().string() : null));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean onSuccessful(Response response) {
        return true;
    }
}
