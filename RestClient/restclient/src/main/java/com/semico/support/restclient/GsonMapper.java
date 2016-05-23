package com.semico.support.restclient;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Luis Ginanjar on 29/04/2016.
 * Purpose :
 */
class GsonMapper implements IJsonMapper {
    Gson gson = new Gson();

    @Override
    public <T> T read(String jsonString, Class<T> theClass) {
        return gson.fromJson(jsonString, theClass);
    }

    @Override
    public <T> List<T> readList(String jsonString, Class<T[]> theClass) {
        return Arrays.asList(gson.fromJson(jsonString, theClass));
    }

    @Override
    public <T> String write(T data) {
        return gson.toJson(data);
    }

    @Override
    public <T> String writeList(List<T> data) {
        return gson.toJson(data);
    }

}
