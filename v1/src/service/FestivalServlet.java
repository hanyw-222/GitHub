package service;

import control.LunarCalendarController;
import control.SolarTermsController;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FestivalServlet extends HttpServlet {
    static final int New_Year_Eve = 1230;
    static final int Dragon_Boat_Festival = 505;
    static final int Mid_Autumn_festival = 815;
    static final String[] FESTIVAL = {"Yuan_Dan_Visit", "New_Year_Eve_Visit", "Qing_ming_Visit", "Labour_Day_Visit",
            "Dragon_Boat_Festival_Visit", "Mid_Autumn_festival_Visit",
            "National_Day_Visit"
    };

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {


        /*
         * 调用card。share中的求出当前年份   finish
         * 引入新对象求出  除夕清明端午中秋  日期，返回时间戳
         * 在当前时间戳向前后推各三天，求出最接近的记录id
         * 利用id在address表中做城市的查询
         * 返回map对象{}，包含在datalist中

         * */
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=GBK");
            response.setCharacterEncoding("utf-8");

            String openid = request.getParameter("openid");

            String sql1 = "select id,time , is_deleted from user where openid='" + openid + "'";
            String sql2 = "select city from address where id='";
            JSONObject jsonObject;
            Map<String, Object> map = new LinkedHashMap<>();
            List<Object> datalist = new ArrayList<>();
            PrintWriter out = response.getWriter();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");

            Statement sta = con.createStatement();
            ResultSet result = sta.executeQuery(sql1);
            Statement sta2 = con.createStatement();
            List<String> year_list = new ArrayList<>();

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
                int year_0 = Integer.valueOf(year);
                SolarTermsController solarTermsController = new SolarTermsController(year);
                String Qingming = solarTermsController.getQingming();
                String string1 = dateGet(year_0 - 1, New_Year_Eve);
                String string2 = dateGet(year_0, Dragon_Boat_Festival);
                String string3 = dateGet(year_0, Mid_Autumn_festival);

                String id_select;
                String time_select;

                result = sta.executeQuery(sql1);
                Map<String, Object> map0 = new LinkedHashMap<>();
                while (result.next()) {
                    String is_deleted = result.getString("is_deleted");

                    if (is_deleted.equals("true")) continue;

                    id_select = result.getString("id");
                    time_select = result.getString("time");

                    ResultSet result2 = sta2.executeQuery(sql2 + id_select + "'");
                    String city = null;


                    map0.put("year", year);

                    while (result2.next()) {

                        if (Long.valueOf(time_select) >= Long.valueOf(changeToTimeStamp(year + "-01-01")) - Long.valueOf("259200000") &&
                                Long.valueOf(time_select) <= Long.valueOf(changeToTimeStamp(year + "-01-01")) + Long.valueOf("259200000")) {

                            city = result2.getString("city");
                            map0.put(FESTIVAL[0], city);
                        } else if (Long.valueOf(time_select) >= Long.valueOf(string1) - Long.valueOf("259200000") &&
                                Long.valueOf(time_select) <= Long.valueOf(string1) + Long.valueOf("259200000")) {

                            city = result2.getString("city");
                            map0.put(FESTIVAL[1], city);

                        } else if (Long.valueOf(time_select) >= Long.valueOf(Qingming) - Long.valueOf("259200000") && Long.valueOf(time_select) <= Long.valueOf(Qingming) + Long.valueOf("259200000")) {
                            city = result2.getString("city");
                            map0.put(FESTIVAL[2], city);
                        } else if (Long.valueOf(time_select) >= Long.valueOf(changeToTimeStamp(year + "-05-01")) - Long.valueOf("259200000") &&
                                Long.valueOf(time_select) <= Long.valueOf(changeToTimeStamp(year + "-05-01")) + Long.valueOf("259200000")) {

                            city = result2.getString("city");
                            map0.put(FESTIVAL[3], city);

                        } else if (Long.valueOf(time_select) >= Long.valueOf(string2) - Long.valueOf("259200000") &&
                                Long.valueOf(time_select) <= Long.valueOf(string2) + Long.valueOf("259200000")) {

                            city = result2.getString("city");
                            map0.put(FESTIVAL[4], city);

                        } else if (Long.valueOf(time_select) >= Long.valueOf(string3) - Long.valueOf("259200000") &&
                                Long.valueOf(time_select) <= Long.valueOf(string3) + Long.valueOf("259200000")) {

                            city = result2.getString("city");
                            map0.put(FESTIVAL[5], city);

                        } else if (Long.valueOf(time_select) >= Long.valueOf(changeToTimeStamp(year + "-10-01")) - Long.valueOf("259200000") &&
                                Long.valueOf(time_select) <= Long.valueOf(changeToTimeStamp(year + "-10-01")) + Long.valueOf("259200000")) {

                            city = result2.getString("city");
                            map0.put(FESTIVAL[6], city);

                        }
                    }
                }

                for (int i = 0; i < FESTIVAL.length; i++) {
                    if (!map0.containsKey(FESTIVAL[i])) {
                        map0.put(FESTIVAL[i], null);
                    }
                }

                datalist.add(map0);
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

    public String dateGet(int year, int FestivalDate) throws Exception {

        int month = FestivalDate / 100;
        int day = FestivalDate % 100;

        LunarCalendarController lunarCalendarController = new LunarCalendarController();
        String string = lunarCalendarController.ReturnTimeStamp(year, month, day);
        return string;
    }

    protected String changeToTimeStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
