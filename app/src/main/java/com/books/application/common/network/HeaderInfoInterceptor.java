package com.books.application.common.network;

import okhttp3.*;

import java.io.IOException;
import java.util.List;


public class HeaderInfoInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder result = request.newBuilder();

        List<Cookie> cookies = OKHttpClientFactory.okHttpClient().cookieJar().loadForRequest(request.url());
        if (!cookies.isEmpty()) {
            result.header("Cookie", cookieHeader(cookies));
        }
        Response response = chain.proceed(result.build());

        if (OKHttpClientFactory.okHttpClient().cookieJar() != CookieJar.NO_COOKIES) {
            List<Cookie> responseCookies = Cookie.parseAll(request.url(), response.headers());
            if (!cookies.isEmpty()) {
                OKHttpClientFactory.okHttpClient().cookieJar().saveFromResponse(request.url(), responseCookies);
            }
        }
        return response;
    }


    private String cookieHeader(List<Cookie> cookies) {
        StringBuilder cookieHeader = new StringBuilder();
        for (int i = 0, size = cookies.size(); i < size; i++) {
            if (i > 0) {
                cookieHeader.append("; ");
            }
            Cookie cookie = cookies.get(i);
            cookieHeader.append(cookie.name()).append('=').append(cookie.value());
        }
        return cookieHeader.toString();
    }
}
