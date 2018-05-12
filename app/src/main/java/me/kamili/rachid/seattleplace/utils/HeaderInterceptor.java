package me.kamili.rachid.seattleplace.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private static final String CLIENT_ID = "ACGXOJLHVA020OMFLI5CATIF3A2DMRN01X3EZARNEWWJ2K5B";
    private static final String CLIENT_SECRET = "CFGNMB3ROH04BMT32GAGUHMHMRFH21YWD45AGVL2XQIIONR5";
    private static final String CITY = "Seattle,WA";

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter("client_id", CLIENT_ID)
                .addQueryParameter("client_secret", CLIENT_SECRET)
                .addQueryParameter("near", CITY)
                .addQueryParameter("v", getCurrentDate())
                .build();

        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }

    private String getCurrentDate() {
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        return sdf.format(currentTime);
    }
}
