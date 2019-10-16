package com.dgpt.weatherinfo;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;


public class HttpUtil {
    //创建公共的类和公共地静态方法，可以在需要时直接调用，增加代码的复用性

    /**
     *
     * @param address   需要访问的URL网址
     * @param callback   添加回调方法
     */
    //使用Get方式获取服务器上数据
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //使用Post方式向服务器上提交数据并获取返回提示数据
    /**
     * 获取post请求的结果，注意此为耗时操作
     */
    public static void sendPostOkHttpRequest(String address, JSONObject object, okhttp3.Callback callback) {
        try {
            OkHttpClient client = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json");
            String requestBody = object.toString();
            Request request = new Request.Builder()
                    .url(address)
                    .post(RequestBody.create(mediaType, requestBody))
                    .build();
            client.newCall(request).enqueue(callback);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
