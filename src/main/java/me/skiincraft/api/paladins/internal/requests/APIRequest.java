package me.skiincraft.api.paladins.internal.requests;

public interface APIRequest<T> {

    T get();

    default boolean isCompleted() {
        return code() != -1;
    }

    int code();
}
