package service;


import net.sf.json.JSONObject;

import java.io.*;
import java.net.*;
import java.sql.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetDataServlet extends HttpServlet {

    String sql = "SELECT openid,id,time,location,markcolor,location_text,content,is_deleted,statusInf FROM user WHERE openid=";
    String sql1 = "SELECT province,city FROM address WHERE id=";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=GBK");
        response.setCharacterEncoding("utf-8");

        String openid = request.getParameter("openid");
        String location_now = request.getParameter("province");
        String year = request.getParameter("year");
        String month = request.getParameter("month");
        String id = request.getParameter("id");

        try {
            JSONObject jsonObject;
            Map<String, Object> map = new LinkedHashMap<>();
            List<Object> datalist = new ArrayList<>();
            PrintWriter out = response.getWriter();

            /*
             * 时间戳转换必须添加异常处理，
             * 如果抛出java.lang.IllegalArgumentException，添加String到long类型的时间戳类型转换
             */
            /*String sd = null;
            String location_now = null;

            if (timestamp != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
                long date = Long.parseLong(timestamp);
                sd = sdf.format(new Date(date));
                System.out.println(sd);
            }

            if (location != null) {
                location_now = urlMapChange(location);
            }*/

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");


            if (month != null && year == null) throw new Exception("缺少年份");
            if (id != null) {
                datalist = selectWithId(con, openid, id);
                System.out.println("id 方法");
            } else if (location_now == null && month == null && year == null) {
                datalist = selectWithNull(con, openid);
                System.out.println("只openid 方法");
            } else if (location_now != null && month == null && year == null) {
                datalist = selectWithLocation(con, openid, location_now);
                System.out.println("lcoation 方法");
            } else if (year != null && month != null && location_now == null) {
                datalist = selectWithTimestamp(con, openid, year, month);
                System.out.println("time 方法");
            } else {
                datalist = selectWithAll(con, openid, location_now, year, month);
                System.out.println("全参 方法");
            }

            map.put("ok", Boolean.TRUE);
            map.put("message", "提取数据成功");
            map.put("data", datalist);
            jsonObject = JSONObject.fromObject(map);
            out.write(jsonObject.toString());


        } catch (Exception e) {
            JSONObject jsonObject;
            List<Object> data_list = new ArrayList<>();
            Map<String, Object> map = new LinkedHashMap<>();
            PrintWriter out = response.getWriter();

            map.put("ok", Boolean.FALSE);
            map.put("message", "提取数据失败");
            map.put("data", data_list);

            jsonObject = JSONObject.fromObject(map);
            out.write(jsonObject.toString());

            e.printStackTrace();
        }

    }

    protected List<Object> selectWithAll(Connection con, String openid, String location, String year, String month) throws SQLException {
        List<Object> list = new ArrayList<>();


        String id_rs = null;
        String location_rs = null;
        String time_rs = null;
        String content_rs = null;
        String mark = null;
        String address = null;
        String province_0 = null;
        String city_0 = null;
        String status=null;

        Statement sta1 = con.createStatement();
        Statement sta2 = con.createStatement();
        ResultSet result1 = sta1.executeQuery(sql + "'" + openid + "'");


        while (result1.next()) {
            String is_deleted = result1.getString("is_deleted");
            if (is_deleted.equals("true")) continue;

            Map<String, Object> map0 = new LinkedHashMap<>();

            id_rs = result1.getString("id");
            time_rs = result1.getString("time");
            location_rs = result1.getString("location");
            content_rs = result1.getString("content");
            mark = result1.getString("markcolor");
            address = result1.getString("location_text");
            status=result1.getString("statusInf");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
            long date = Long.parseLong(time_rs);
            String sd1 = sdf.format(new Date(date));

            ResultSet result2 = sta2.executeQuery(sql1 + "'" + id_rs + "'");
            while (result2.next()) {
                province_0 = result2.getString("province");
                city_0 = result2.getString("city");


                if (province_0.equals(location) && sd1.substring(0, 4).equals(year) && sd1.substring(5).equals(month)) {
                    map0.put("id", id_rs);
                    map0.put("year", sd1.substring(0, 4));
                    map0.put("month", sd1.substring(5));
                    map0.put("province", province_0);
                    map0.put("city", city_0);
                    map0.put("mark_color", mark);
                    map0.put("location_text", address);
                    map0.put("content", content_rs);
                    map0.put("location", location_rs);
                    map0.put("time_stamp", time_rs);
                    map0.put("public",status);
                    list.add(map0);
                }

            }
        }
        return list;
    }

    protected List<Object> selectWithNull(Connection con, String openid) throws SQLException {

        List<Object> list = new ArrayList<>();

        String id_rs = null;
        String location_rs = null;
        String time_rs = null;
        String content_rs = null;
        String mark = null;
        String address = null;
        String province_0 = null;
        String city_0 = null;
        String status=null;
        Statement sta1 = con.createStatement();
        Statement sta2 = con.createStatement();
        ResultSet result1 = sta1.executeQuery(sql + "'" + openid + "'");


        while (result1.next()) {
            String is_deleted = result1.getString("is_deleted");
            if (is_deleted.equals("true")) continue;

            Map<String, Object> map0 = new LinkedHashMap<>();

            id_rs = result1.getString("id");
            time_rs = result1.getString("time");
            location_rs = result1.getString("location");
            content_rs = result1.getString("content");
            mark = result1.getString("markcolor");
            address = result1.getString("location_text");
            status=result1.getString("statusInf");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
            long date = Long.parseLong(time_rs);
            String sd1 = sdf.format(new Date(date));

            ResultSet result2 = sta2.executeQuery(sql1 + "'" + id_rs + "'");

            while (result2.next()) {
                province_0 = result2.getString("province");
                city_0 = result2.getString("city");

                map0.put("id", id_rs);
                map0.put("year", sd1.substring(0, 4));
                map0.put("month", sd1.substring(5));
                map0.put("province", province_0);
                map0.put("city", city_0);
                map0.put("mark_color", mark);
                map0.put("location_text", address);
                map0.put("content", content_rs);
                map0.put("location", location_rs);
                map0.put("time_stamp", time_rs);
                map0.put("public",status);
                list.add(map0);
            }


        }

        return list;
    }

    protected List<Object> selectWithLocation(Connection con, String openid, String location) {

        List<Object> list = new ArrayList<>();

        String id_rs = null;
        String location_rs = null;
        String time_rs = null;
        String content_rs = null;
        String mark = null;
        String address = null;
        String province_0 = null;
        String city_0 = null;
        String status=null;
        try {
            Statement sta1 = con.createStatement();
            Statement sta2 = con.createStatement();
            ResultSet result1 = sta1.executeQuery(sql + "'" + openid + "'");


            while (result1.next()) {
                String is_deleted = result1.getString("is_deleted");
                if (is_deleted.equals("true")) continue;

                Map<String, Object> map0 = new LinkedHashMap<>();

                id_rs = result1.getString("id");
                time_rs = result1.getString("time");
                location_rs = result1.getString("location");
                content_rs = result1.getString("content");
                mark = result1.getString("markcolor");
                address = result1.getString("location_text");
                status=result1.getString("statusInf");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
                long date = Long.parseLong(time_rs);
                String sd1 = sdf.format(new Date(date));

                ResultSet result2 = sta2.executeQuery(sql1 + "'" + id_rs + "'");
                while (result2.next()) {
                    province_0 = result2.getString("province");
                    city_0 = result2.getString("city");

                    if (province_0.equals(location)) {

                        map0.put("id", id_rs);
                        map0.put("year", sd1.substring(0, 4));
                        map0.put("month", sd1.substring(5));
                        map0.put("province", province_0);
                        map0.put("city", city_0);
                        map0.put("mark_color", mark);
                        map0.put("location_text", address);
                        map0.put("content", content_rs);
                        map0.put("location", location_rs);
                        map0.put("time_stamp", time_rs);
                        map0.put("public",status);
                        list.add(map0);
                    }
                }
            }

        } catch (Exception e0) {
            e0.printStackTrace();

        }
        return list;
    }

    protected List<Object> selectWithTimestamp(Connection con, String openid, String year, String month) {

        List<Object> list = new ArrayList<>();

        String id_rs = null;
        String location_rs = null;
        String time_rs = null;
        String content_rs = null;
        String mark = null;
        String address = null;
        String province_0 = null;
        String city_0 = null;
        String status=null;
        try {
            Statement sta1 = con.createStatement();
            Statement sta2 = con.createStatement();
            ResultSet result1 = sta1.executeQuery(sql + "'" + openid + "'");


            while (result1.next()) {
                String is_deleted = result1.getString("is_deleted");
                if (is_deleted.equals("true")) continue;

                Map<String, Object> map0 = new LinkedHashMap<>();

                id_rs = result1.getString("id");
                time_rs = result1.getString("time");
                location_rs = result1.getString("location");
                content_rs = result1.getString("content");
                mark = result1.getString("markcolor");
                address = result1.getString("location_text");
                status=result1.getString("statusInf");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
                long date = Long.parseLong(time_rs);
                String sd1 = sdf.format(new Date(date));

                ResultSet result2 = sta2.executeQuery(sql1 + "'" + id_rs + "'");
                while (result2.next()) {
                    province_0 = result2.getString("province");
                    city_0 = result2.getString("city");

                    if (sd1.substring(0, 4).equals(year) && sd1.substring(5).equals(month)) {

                        map0.put("id", id_rs);
                        map0.put("year", sd1.substring(0, 4));
                        map0.put("month", sd1.substring(5));
                        map0.put("province", province_0);
                        map0.put("city", city_0);
                        map0.put("mark_color", mark);
                        map0.put("location_text", address);
                        map0.put("content", content_rs);
                        map0.put("location", location_rs);
                        map0.put("time_stamp", time_rs);
                        map0.put("public",status);
                        list.add(map0);
                    }
                }
            }
        } catch (Exception e0) {
            e0.printStackTrace();

        }
        return list;
    }

    protected List<Object> selectWithId(Connection con, String openid, String id) throws SQLException {

        List<Object> list = new ArrayList<>();

        String id_rs = null;
        String location_rs = null;
        String time_rs = null;
        String content_rs = null;
        String mark = null;
        String address = null;
        String province_0 = null;
        String city_0 = null;
        String status=null;
        try {
            Statement sta1 = con.createStatement();
            Statement sta2 = con.createStatement();
            ResultSet result1 = sta1.executeQuery(sql + "'" + openid + "'");


            while (result1.next()) {
                String is_deleted = result1.getString("is_deleted");
                if (is_deleted.equals("true")) continue;

                Map<String, Object> map0 = new LinkedHashMap<>();

                id_rs = result1.getString("id");
                time_rs = result1.getString("time");
                location_rs = result1.getString("location");
                content_rs = result1.getString("content");
                mark = result1.getString("markcolor");
                address = result1.getString("location_text");
                status=result1.getString("statusInf");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM");
                long date = Long.parseLong(time_rs);
                String sd1 = sdf.format(new Date(date));

                ResultSet result2 = sta2.executeQuery(sql1 + "'" + id_rs + "'");
                while (result2.next()) {

                    province_0 = result2.getString("province");
                    city_0 = result2.getString("city");
                    if (id_rs.equals(id)) {
                        map0.put("id", id_rs);
                        map0.put("year", sd1.substring(0, 4));
                        map0.put("month", sd1.substring(5));
                        map0.put("province", province_0);
                        map0.put("city", city_0);
                        map0.put("mark_color", mark);
                        map0.put("location_text", address);
                        map0.put("content", content_rs);
                        map0.put("location", location_rs);
                        map0.put("time_stamp", time_rs);
                        map0.put("public",status);
                        list.add(map0);
                    }
                }
            }
        } catch (Exception e) {

            e.printStackTrace();
            throw new SQLException();

        }

        return list;
    }


    /*
    为降低接口请求次数，减少1秒内的并发次数，防止失败；
    protected String urlMapChange(String map_location) {

        String province = null;
        String city = null;
        JSONObject jsonObject1;

        try {
            URL url = new URL("https://apis.map.qq.com/ws/geocoder/v1/?key=DAGBZ-4BNCW-6O7RJ-RKM3W-QT3EF-S6FLA&get_poi=0&location=" + map_location);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestMethod("GET");

            InputStream inputStream = con.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader in = new BufferedReader(inputStreamReader);

            String res = "";
            String line;

            while ((line = in.readLine()) != null) {
                res += line;
            }

            *//*5.9
             * 调用腾讯接口这一部分，设计成单独的函数，可观性太差
             *//*
            jsonObject1 = c;
            JSONObject jsonObject2 = jsonObject1.getJSONObject("result");
            JSONObject jsonObject3 = jsonObject2.getJSONObject("address_component");
            province = jsonObject3.getString("province");
            city = jsonObject3.getString("city");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return province + "," + city;
    }
*/

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        doGet(request, response);
    }
}
