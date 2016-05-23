package com.semico.support.restclient;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 2/29/2016.
 * Purpose : Interface for RestClient
 */
public interface IRestClient {

    void setAuthorization(String auth);

    <T> T get(Class<T> theClass, String urlString) throws Exception;

    <T> List<T> getList(Class<T[]> theClass, String urlString) throws Exception;

    <T> void post(String urlString, T data) throws Exception;

    void post(String urlString) throws Exception;

    void delete(String urlString) throws Exception;

    void put(String urlString) throws Exception;

    <T> void put(String urlString, T data) throws Exception;

}
