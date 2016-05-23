package com.semico.support.restclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by luis on 2/26/2016.
 * Purpose : access direct to API, don't add specific query url here.
 */
public class RestClient implements IRestClient {
    IJsonMapper jsonMapper = new GsonMapper();
    private String authentication = "";
    private boolean isNeedAuth = false;

    @Override
    public void setAuthorization(String auth) {
        authentication = auth;
        isNeedAuth = (!authentication.equals(""));
    }

    @Override
    public <T> T get(Class<T> theClass, String urlString) throws Exception {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(Method.GET);
            urlConnection.setRequestProperty("Content-Type", "application/json");
            if(isNeedAuth)
                urlConnection.setRequestProperty("Authorization", "Basic " + authentication);

            if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + urlConnection.getResponseCode() + "-" + urlConnection.getResponseMessage());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    (urlConnection.getInputStream())));

            String output;
            String textResult = "";
            while ((output = reader.readLine()) != null) {
               textResult += output;
            }
            urlConnection.disconnect();

        return jsonMapper.read(textResult, theClass);
    }

    @Override
    public <T> List<T> getList(Class<T[]> theClass, String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(Method.GET);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        if (isNeedAuth)
            urlConnection.setRequestProperty("Authorization", "Basic " + authentication);

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + urlConnection.getResponseCode() + "-" + urlConnection.getResponseMessage());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                (urlConnection.getInputStream())));

        String output;
        String textResult = "";
        while ((output = reader.readLine()) != null) {
            textResult += output;
        }
        urlConnection.disconnect();

        if (textResult.equals("[]"))
            return new ArrayList<>();

        return jsonMapper.readList(textResult, theClass);
    }

    @Override
    public <T> void post(String urlString, T data) throws Exception {
        HttpURLConnection urlConnection = null;
        try {
            String inputValue = jsonMapper.write(data);
            urlConnection = getHttpURLConnection(Method.POST, urlString, inputValue);
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
    }

    @Override
    public  void post(String urlString) throws Exception {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = getHttpURLConnection(Method.POST, urlString, "");

        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    @Override
    public void delete(String urlString) throws Exception {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = getHttpURLConnection(Method.DELETE, urlString,"");

        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
    }

    @Override
    public void put(String urlString) throws Exception {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = getHttpURLConnection(Method.PUT, urlString, "");

        } finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
    }

    @Override
    public <T> void put(String urlString, T data) throws Exception {
        HttpURLConnection urlConnection = null;
        try {
            String inputValue = jsonMapper.write(data);
            urlConnection = getHttpURLConnection(Method.PUT, urlString, inputValue);

        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
    }

    private HttpURLConnection getHttpURLConnection(String method, String urlString, String inputValue) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        if (!inputValue.equals(""))
            urlConnection.setDoOutput(true);
        urlConnection.setRequestMethod(method);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        if (isNeedAuth)
            urlConnection.setRequestProperty("Authorization", "Basic " + authentication);

        if (!inputValue.equals("")) {
            OutputStream outputStream = urlConnection.getOutputStream();
            outputStream.write(inputValue.getBytes());
            outputStream.flush();
        }

        if (urlConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new Exception("Failed : HTTP error code : "
                    + urlConnection.getResponseCode() + "-" + urlConnection.getResponseMessage());
        }
        return urlConnection;
    }

     class Method {
         public  static  final String POST = "POST";
         public static final String PUT = "PUT";
         public static  final String DELETE = "DELETE";
         public static  final String GET = "GET";
     }

}
