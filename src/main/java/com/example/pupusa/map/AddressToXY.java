package com.example.pupusa.map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class AddressToXY {
    private static String GEOCODE_URL="http://dapi.kakao.com/v2/local/search/address.json?query=";
    private static String GEOCODE_USER_INFO="KakaoAK 042904868f6231cacd0fabdbc716fcb0";

    public static double getX(String address){
        URL obj;
        double xL;

        try{
            //인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
            String address_send = URLEncoder.encode(address, "UTF-8");

            obj = new URL(GEOCODE_URL+address_send);

            HttpURLConnection con = (HttpURLConnection)obj.openConnection();

            //get으로 받아오면 된다. 자세한 사항은 카카오개발자센터에 나와있다.
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization",GEOCODE_USER_INFO);
            con.setRequestProperty("content-type", "application/json");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //response 객체를 출력해보자
            JsonParser jp = new JsonParser();
            JsonObject jsonObject = (JsonObject) jp.parse(response.toString());
            JsonArray jsonObject2 = jsonObject.get("documents").getAsJsonArray();
            JsonObject jsonObject3 = jsonObject2.get(0).getAsJsonObject();
            xL = jsonObject3.get("x").getAsDouble();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            xL = 0D;
        }
        return xL;
    }
    public static double getY(String address){
        URL obj;
        double yL;

        try{
            //인코딩한 String을 넘겨야 원하는 데이터를 받을 수 있다.
            String address_send = URLEncoder.encode(address, "UTF-8");

            obj = new URL(GEOCODE_URL+address_send);

            HttpURLConnection con = (HttpURLConnection)obj.openConnection();

            //get으로 받아오면 된다. 자세한 사항은 카카오개발자센터에 나와있다.
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization",GEOCODE_USER_INFO);
            con.setRequestProperty("content-type", "application/json");
            con.setDoOutput(true);
            con.setUseCaches(false);
            con.setDefaultUseCaches(false);

            Charset charset = Charset.forName("UTF-8");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            //response 객체를 출력해보자
            JsonParser jp = new JsonParser();
            JsonObject jsonObject = (JsonObject) jp.parse(response.toString());
            JsonArray jsonObject2 = jsonObject.get("documents").getAsJsonArray();
            JsonObject jsonObject3 = jsonObject2.get(0).getAsJsonObject();
            yL = jsonObject3.get("y").getAsDouble();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            yL = 0D;
        }
        return yL;
    }

    public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {

        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));

        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        if (unit == "kilometer") {
            dist = dist * 1.609344;
        } else if(unit == "meter"){
            dist = dist * 1609.344;
        }

        return (dist);
    }

    // This function converts decimal degrees to radians
    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    // This function converts radians to decimal degrees
    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}
