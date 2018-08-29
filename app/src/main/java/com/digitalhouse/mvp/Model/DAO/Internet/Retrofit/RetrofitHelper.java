package com.digitalhouse.mvp.Model.DAO.Internet.Retrofit;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitHelper {

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(String base_url, final Map<String, String> headers) {
        if (retrofit == null){
            new RetrofitHelper(base_url, headers);
        }
        return retrofit;
    }

    private RetrofitHelper(String base_url, final Map<String, String> headers) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (headers != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request.Builder requestBuilder = chain.request().newBuilder();

                    for (String key : headers.keySet()) {
                        requestBuilder = requestBuilder
                                .addHeader(key, headers.get(key));
                    }


                    return chain.proceed(requestBuilder.build());
                }
            });
        }


        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create());

        this.retrofit = builder
                .client(httpClient.build())
                .build();
    }


}
