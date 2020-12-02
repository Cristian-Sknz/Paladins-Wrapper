package me.skiincraft.api.paladins.impl.paladins;

import com.github.kevinsawicki.http.HttpRequest;
import me.skiincraft.api.paladins.common.Request;
import me.skiincraft.api.paladins.exceptions.RequestException;
import me.skiincraft.api.paladins.utils.AccessUtils;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class PaladinsRequest<T> implements Request<T> {

    private final Function<String, T> function;
    private final String url;
    private final boolean isStored;
    private String json;
    private T object;

    public PaladinsRequest(String url, Function<String, T> requestResult, boolean isStored) {
        this.function = requestResult;
        this.url = url;
        this.isStored = isStored;
    }

    public PaladinsRequest(String url, Function<String, T> requestResult) {
        this.function = requestResult;
        this.url = url;
        this.isStored = false;
    }



    @Override
    public T get() {
        if (isStored && !wasRequested() || url == null) {
            return object = function.apply("{\"storage\": true}");
        }

        if (!wasRequested()) {
            this.json = HttpRequest.get(url).body();
            if (!AccessUtils.checkResponse(json))
                throw new RequestException("Possibly the session is invalid. Check the session.");
            this.object = function.apply(json);
        }
        return this.object;
    }

    @Override
    public void getWithJson(BiConsumer<T, String> biConsumer) {
        biConsumer.accept(get(), json);
    }

    @Override
    public boolean wasRequested() {
        return Objects.nonNull(object);
    }

    public boolean isStored() {
        return isStored;
    }
}
