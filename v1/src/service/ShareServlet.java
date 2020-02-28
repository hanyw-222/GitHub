package service;

import control.SolarTermsController;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


import java.net.*;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ShareServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=GBK");
        response.setCharacterEncoding("utf-8");

        String openid = request.getParameter("openid");

        String sql1 = "select time , is_deleted from user where openid='" + openid + "'";
        String sql2 = "select time,location,id, is_deleted from user where openid='" + openid + "'";
        String sql3 = "select city from address where id='";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");

            Statement sta = con.createStatement();
            ResultSet result = sta.executeQuery(sql1);

            List<String> year_list = new ArrayList<>();
            Map<String, Object> return_map = new LinkedHashMap<>();
            JSONObject jsonObject;

            List<Object> datalist = new ArrayList<>();
            PrintWriter out = response.getWriter();


            while (result.next()) {
                String is_deleted = result.getString("is_deleted");
                String time_stamp0 = result.getString("time");


                if (is_deleted.equals("true")) continue;
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                long date = Long.parseLong(time_stamp0);
                String y = sdf.format(new Date(date));
                if (!year_list.contains(y)) {
                    year_list.add(y);

                }
            }

            for (String year : year_list) {


                //农历提取春夏秋冬具体时间戳，传参为year（String）
                SolarTermsController solarTermsController = new SolarTermsController(year);
                String spring = solarTermsController.getSpring();
                String summer = solarTermsController.getSummer();
                String autumn = solarTermsController.getAutumn();
                String winter = solarTermsController.getWinter();

                String min_time = changeToTimeStamp(year + "-01-01 00:00:00");
                String max_time = changeToTimeStamp(year + "-12-31 23:59:59");
                String city0 = null;
                Map<String, Integer> map = new LinkedHashMap<>();
                Map<String, Object> map_season = new LinkedHashMap<>();
                Long min = new Long(min_time);
                Long max = new Long(max_time);
                Long[] min_difference = {Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE, Long.MAX_VALUE};

                result = sta.executeQuery(sql2);
                Statement sta2 = con.createStatement();

                while (result.next()) {
                    String is_deleted = result.getString("is_deleted");
                    String time_stamp1 = result.getString("time");
                    String local_text = result.getString("location");
                    String id = result.getString("id");

                    if (is_deleted.equals("true")) continue;

                    ResultSet result2 = sta2.executeQuery(sql3 + id + "'");

                    if (Long.valueOf(time_stamp1) > Long.valueOf(spring) && Long.valueOf(time_stamp1) < Long.valueOf(summer)) {
                        Long diff = Long.valueOf(time_stamp1) - Long.valueOf(spring);
                        if (min_difference[0] > diff) {
                            min_difference[0] = diff;
                            while (result2.next()) {
                                String city_0 = result2.getString("city");
                                map_season.put("spring_visit", city_0);
                            }

                        }
                    } else if (Long.valueOf(time_stamp1) >= Long.valueOf(summer) && Long.valueOf(time_stamp1) < Long.valueOf(autumn)) {
                        Long diff = Long.valueOf(time_stamp1) - Long.valueOf(summer);
                        if (min_difference[1] > diff) {
                            min_difference[1] = diff;
                            while (result2.next()) {
                                String city_0 = result2.getString("city");
                                map_season.put("summer_visit", city_0);
                            }
                        }
                    } else if (Long.valueOf(time_stamp1) >= Long.valueOf(autumn) && Long.valueOf(time_stamp1) < Long.valueOf(winter)) {
                        Long diff = Long.valueOf(time_stamp1) - Long.valueOf(autumn);
                        if (min_difference[2] > diff) {
                            min_difference[2] = diff;
                            while (result2.next()) {
                                String city_0 = result2.getString("city");
                                map_season.put("autumn_visit", city_0);
                            }
                        }
                    } else if(Long.valueOf(time_stamp1)>= Long.valueOf(winter)){
                        Long diff = Long.valueOf(time_stamp1) - Long.valueOf(winter);
                        if (min_difference[3] > diff) {
                            min_difference[3] = diff;
                            while (result2.next()) {
                                String city_0 = result2.getString("city");
                                map_season.put("winter_visit", city_0);
                            }
                        }
                    }

                    result2 = sta2.executeQuery(sql3 + id + "'");
                    Long a = new Long(time_stamp1);

                    while (result2.next()) {
                        if (a > min && a < max) {
                            city0 = result2.getString("city");

                            if (map.containsKey(city0)) {
                                int value = map.get(city0);
                                value++;
                                map.put(city0, value);
                            } else {
                                map.put(city0, 1);
                            }

                        }
                    }

                }
                if (!map_season.containsKey("spring_visit")) {
                    map_season.put("spring_visit", null);
                }
                if (!map_season.containsKey("summer_visit")) {
                    map_season.put("summer_visit", null);
                }
                if (!map_season.containsKey("autumn_visit")) {
                    map_season.put("autumn_visit", null);
                }
                if (!map_season.containsKey("winter_visit")) {
                    map_season.put("winter_visit", null);
                }

                Iterator<Map.Entry<String, Integer>> iter = map.entrySet().iterator();
                int card_count = 0;
                int city_count = 0;
                int card_max = 0;
                String city_max = null;

                while (iter.hasNext()) {
                    Map.Entry<String, Integer> entry = iter.next();
                    String key = entry.getKey();
                    int value = entry.getValue();

                    card_count += value;
                    city_count++;

                    if (card_max <= value) {
                        card_max = value;
                        city_max = key;
                    }
                }

                map_season.put("year",year);
                map_season.put("clock_count", card_count);
                map_season.put("city_count", city_count);
                map_season.put("city_most", city_max);
                map_season.put("clock_most", card_max);

                System.out.println("打卡数：" + card_count);
                System.out.println("城市数：" + city_count);
                System.out.println("常去城市：" + city_max);
                System.out.println("去了" + card_max + "次");
                System.out.println(map.toString());

                datalist.add(map_season);

            }

            return_map.put("ok", Boolean.TRUE);
            return_map.put("message", "提取数据成功");
            return_map.put("data", datalist);
            jsonObject = JSONObject.fromObject(return_map);
            out.write(jsonObject.toString());
            out.flush();
            out.close();

        } catch (Exception e) {
            JSONObject jsonObject;
            List<Object> data_list = new ArrayList<>();
            Map<String, Object> return_map = new LinkedHashMap<>();
            PrintWriter out = response.getWriter();

            return_map.put("ok", Boolean.FALSE);
            return_map.put("message", "提取数据失败");
            return_map.put("data", data_list);

            jsonObject = JSONObject.fromObject(return_map);
            out.write(jsonObject.toString());

            e.printStackTrace();
        }

    }


    /*protected String urlMapChange(String map_location) throws JSONException {

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

            jsonObject1 = JSONObject.fromObject(res);
            System.out.println(jsonObject1.toString());
            JSONObject jsonObject2 = jsonObject1.getJSONObject("result");

            System.out.println(jsonObject2.toString());
            //!!!错误点在下一行！！！，提取出来city或addressc-component为null
            JSONObject jsonObject3 = jsonObject2.getJSONObject("address_component");

            city = jsonObject3.getString("city");
            System.out.println(city);
        } catch (Exception e) {
            e.printStackTrace();
            throw new JSONException("腾讯api提取失败");
        }
        return city;
    }*/


    protected String changeToTimeStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
