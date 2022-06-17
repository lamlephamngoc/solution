package com.propine.solution;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static java.lang.String.format;

public class ExchangeRateProcessor {
    public static final String CRYPTO_COMPARE_API = "https://min-api.cryptocompare.com/data/price?fsym=%s&tsyms=USD";

    private final String token;

    public ExchangeRateProcessor(String token) {
        this.token = token;
    }

    public Double exchangeRateByToken() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(format(CRYPTO_COMPARE_API, token))
                .build();

        try (Response response = client.newCall(request).execute()) {
            JSONObject json = JSON.parseObject(response.body().string());
            return Double.parseDouble(json.get("USD").toString());
        }
    }
}
