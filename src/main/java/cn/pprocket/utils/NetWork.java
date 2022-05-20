package cn.pprocket.utils;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


class NetWorkBean {
    private String data;
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
class Bean2{

    /**
     * success : true
     * code : 200
     * msg : 获取用户端IP成功
     * data : 49.67.39.96
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

public class NetWork {
    private static String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        if (OS.getVersion().contains("Windows")){
            return new String(response.toString().getBytes(), StandardCharsets.UTF_8);
        } else {
            return response.toString();
        }

    }
     public static String getIP() throws Exception {
         String str = sendGet("https://www.ipplus360.com/getIP");
         return new Gson().fromJson(str,Bean2.class).getData();
     }
     public static String getLocation() throws Exception {
         String str = sendGet("https://www.ipplus360.com/getLocation");
         Gson gson = new Gson();
         return gson.fromJson(str,NetWorkBean.class).getData();
     }
}
