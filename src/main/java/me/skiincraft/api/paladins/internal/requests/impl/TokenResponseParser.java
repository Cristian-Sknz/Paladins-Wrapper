package me.skiincraft.api.paladins.internal.requests.impl;

import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.internal.requests.ResponseParser;
import okhttp3.Response;

import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

public class TokenResponseParser<T> extends ResponseParser<T> {

    public TokenResponseParser(Response response, Function<Response, T> function) {
        super(response, function);
    }

    @Override
    public boolean onFailure(Response response) {
        if (response.code() >= 400) {
            try {
                throw new RequestException(String.format("Your credentials may be incorrect:%n%s", Objects.requireNonNull(response.body()).string()));
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean onSuccessful(Response response) {
        return true;
    }
}
