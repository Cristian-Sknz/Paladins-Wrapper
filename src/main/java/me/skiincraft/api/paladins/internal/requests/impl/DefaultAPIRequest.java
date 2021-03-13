package me.skiincraft.api.paladins.internal.requests.impl;

import me.skiincraft.api.paladins.Paladins;
import me.skiincraft.api.paladins.internal.requests.APIRequest;
import me.skiincraft.api.paladins.internal.requests.ResponseParser;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Objects;
import java.util.function.Function;

public class DefaultAPIRequest<T> implements APIRequest<T> {

    private final String endpoint;
    private final String sessionId;
    private final String[] args;
    private final Request request;
    private final Paladins api;
    private int code = -1;
    private T item = null;
    private Function<Response, T> function;
    private Function<Response, ResponseParser<T>> responseParser;

    public DefaultAPIRequest(@Nonnull String endpoint, String[] args, @Nonnull Paladins api) {
        this.endpoint = endpoint;
        this.sessionId = null;
        this.args = args;
        this.api = api;
        this.request = null;
        this.responseParser = (r) -> new DefaultResponseParser<>(r, function);
    }

    public DefaultAPIRequest(@Nonnull String endpoint, String sessionId, String[] args, @Nonnull Paladins api) {
        this.endpoint = endpoint;
        this.sessionId = sessionId;
        this.args = args;
        this.api = api;
        this.request = null;
        this.responseParser = (r) -> new DefaultResponseParser<>(r, function);
    }

    public DefaultAPIRequest(@Nonnull Request request, @Nonnull Paladins api) {
        this.endpoint = null;
        this.sessionId = null;
        this.args = null;
        this.api = api;
        this.request = request;
        this.responseParser = (r) -> new DefaultResponseParser<>(r, function);
    }

    public DefaultAPIRequest(@Nonnull String endpoint, String[] args, @Nonnull Function<Response, T> function, @Nonnull Paladins api) {
        this.endpoint = endpoint;
        this.sessionId = null;
        this.args = args;
        this.function = function;
        this.api = api;
        this.request = null;
        this.responseParser = (r) -> new DefaultResponseParser<>(r, function);
    }

    public DefaultAPIRequest(@Nonnull String endpoint, String sessionId, String[] args, @Nonnull Function<Response, T> function, @Nonnull Paladins api) {
        this.endpoint = endpoint;
        this.sessionId = sessionId;
        this.args = args;
        this.function = function;
        this.api = api;
        this.request = null;
        this.responseParser = (r) -> new DefaultResponseParser<>(r, function);
    }

    public DefaultAPIRequest(@Nonnull Request request, @Nonnull Function<Response, T> function, @Nonnull Paladins api) {
        this.endpoint = null;
        this.sessionId = null;
        this.args = null;
        this.function = function;
        this.api = api;
        this.request = request;
        this.responseParser = (r) -> new DefaultResponseParser<>(r, function);
    }

    public void setResponseParser(@Nonnull Function<Response, ResponseParser<T>> responseParser) {
        this.responseParser = responseParser;
    }

    public void setFunction(@Nonnull Function<Response, T> function) {
        this.function = function;
    }

    @Override
    public T get() {
        if (item != null) {
            return item;
        }
        try {
            OkHttpClient client = getClient();
            Call call = client.newCall(buildRequest());
            ResponseParser<T> parser = responseParser.apply(call.execute())
                    .setFunction(function);

            return this.item = parser.parse();
        } catch (IOException e) {
            e.printStackTrace();
            this.code = 400;
            return null;
        }
    }

    private Request buildRequest() {
        if (this.request != null) {
            return this.request;
        }
        String endPoint = Objects.requireNonNull(endpoint, "endpoint method is null");
        String url = (sessionId == null) ? api.getAccessUtils().makeUrl(endPoint, args) : api.getAccessUtils().makeUrl(endPoint, sessionId, args);
        Request.Builder request = new Request.Builder()
                .url(Objects.requireNonNull(url, "url is null"))
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json");

        return request.build();
    }

    @Override
    public int code() {
        return code;
    }

    private OkHttpClient getClient() {
        return api.getClient();
    }
}
