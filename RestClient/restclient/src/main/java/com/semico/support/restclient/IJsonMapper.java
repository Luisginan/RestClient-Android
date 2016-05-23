package com.semico.support.restclient;
import java.io.IOException;
import java.util.List;

/**
 * Created by Luis Ginanjar on 29/04/2016.
 * Purpose :
 */
public interface IJsonMapper {
    <T> T read(String JsonString, Class<T> theClass) throws IOException;

    <T> List<T> readList(String JsonString, Class<T[]> theClass) throws IOException;

    <T> String write(T data) throws  IOException;

    <T> String writeList(List<T> data) throws IOException;

}
