package com.mizu.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class VerifyBrokenLinks {
    public int linkStatus(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection HttpURLConnect = (HttpURLConnection) url.openConnection();
            HttpURLConnect.setConnectTimeout(6000);
            HttpURLConnect.setRequestMethod("GET");
            HttpURLConnect.connect();
            return HttpURLConnect.getResponseCode();
        } catch (Exception e) {
            return -1;
        }
    }
}
