package cn.pprocket.utils;

import cn.hutool.http.HttpUtil;
import com.google.gson.Gson;

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
     public static String getIP(){
         String str = HttpUtil.get("https://www.ipplus360.com/getIP");
         return new Gson().fromJson(str,Bean2.class).getData();
     }
     public static String getLocation(){
         String str = HttpUtil.get("https://www.ipplus360.com/getLocation");
         Gson gson = new Gson();
         return gson.fromJson(str,NetWorkBean.class).getData();
     }
}
