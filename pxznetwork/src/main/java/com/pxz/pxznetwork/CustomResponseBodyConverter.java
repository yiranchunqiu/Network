package com.pxz.pxznetwork;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.StringReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * 类说明：响应转换成实体
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @time 2019/6/10 17:53
 */
public class CustomResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CustomResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String responseText = value.string();
        JsonObject jsonObject = null;
        JsonArray jsonArray = null;
        try {
            // 如果data为null，会造成io.reactivex.exceptions.OnErrorNotImplementedException:
            // onNext called with null. Null values are generally not allowed in 2.x operators and sources.
            jsonObject = new Gson().fromJson(responseText, JsonObject.class);
            JsonElement jsonElement = jsonObject.get("data");
            if (jsonElement == null || jsonElement instanceof JsonNull) {
                jsonObject.add("data", new JsonObject());
            }
            responseText = jsonObject.toString();
        } catch (Exception e) {
            if (e instanceof JsonSyntaxException) {
                jsonArray = new Gson().fromJson(responseText, JsonArray.class);
                responseText = jsonArray.toString();
                JsonReader jsonReader = gson.newJsonReader(new StringReader(responseText));
                return adapter.read(jsonReader);
            } else {
                e.printStackTrace();
            }
        }
        int tryCount = 0;
        while (tryCount < 2) {
            JsonReader jsonReader = gson.newJsonReader(new StringReader(responseText));
            try {
                return adapter.read(jsonReader);
            } catch (JsonSyntaxException e) {
                if (e.getMessage().contains("Expected BEGIN_ARRAY")) {
                    // 说明data需要[]数组格式的
                    jsonObject.add("data", new JsonArray());
                    responseText = jsonObject.toString();
                    tryCount++;
                    continue;
                } else if (e.getMessage().contains("IllegalStateException")) {
                    //data为""转null
                    jsonObject.add("data", null);
                    responseText = jsonObject.toString();
                    tryCount++;
                    continue;
                }
                e.printStackTrace();
                throw e;
            } finally {
                value.close();
            }
        }
        throw new IOException("解析出错");
    }
}