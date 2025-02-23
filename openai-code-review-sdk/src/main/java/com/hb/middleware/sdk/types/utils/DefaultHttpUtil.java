package com.hb.middleware.sdk.types.utils;

import com.alibaba.fastjson2.JSON;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

public class DefaultHttpUtil {


    /**
     * 发送get请求
     * @param uri Get请求地址
     * @param headers 请求头
     * @return 字符串类型的返回结果
     */
    public static String executeGetRequest(String uri, Map<String, String> headers) throws Exception{
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // 设置请求头
        headers.forEach((key, value) -> connection.setRequestProperty(key, value));

        connection.setDoOutput(true);
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return content.toString();
    }


    /**
     * 发送get请求
     * @param uri Get请求地址
     * @param headers 请求头
     * @param body
     * @return 字符串类型的返回结果
     */
    public static String executePostRequest(String uri, Map<String, String> headers, Object body) throws Exception{
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        // 设置请求头
        headers.forEach((key, value) -> connection.setRequestProperty(key, value));

        connection.setDoOutput(true);

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = JSON.toJSONString(body).getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while((inputLine = in.readLine()) != null){
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();
        return content.toString();
    }

    public static InputStream getHttpInputStream(String uri) throws Exception{
        URL url = new URL(uri);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        return connection.getInputStream();
    }
}
