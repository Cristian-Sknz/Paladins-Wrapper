package me.skiincraft.api.paladins.internal.requests.impl;

import me.skiincraft.api.paladins.internal.requests.APIRequest;

public class FakeAPIRequest<T> implements APIRequest<T> {

    private final T item;
    private final int code;

    public FakeAPIRequest(T item, int code) {
        this.item = item;
        this.code = code;
    }

    @Override
    public T get() {
        return item;
    }

    @Override
    public int code() {
        return code;
    }
}
