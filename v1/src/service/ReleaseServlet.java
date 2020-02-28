package service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ReleaseServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=GBK");
        PrintWriter out = response.getWriter();


        String openid = request.getParameter("openid");
        String mark_color = request.getParameter("mark_color");
        String time_stamp = request.getParameter("time_stamp");
        String location = request.getParameter("location");
        String content = request.getParameter("content");
        String location_text = request.getParameter("location_text");
        String statusInf = request.getParameter("public");
        Connection con = null;
        PreparedStatement preparedStatement;
        JSONObject jsonObject;
        JSONArray jsonArray;
        List<Object> data = new ArrayList<>();


        //数据库调用user表，插入json对象数据（未做实体类）
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");


            preparedStatement = con.prepareStatement("insert into user (openid,markcolor,time,location,location_text,content,statusInf) " + "values(?,?,?,?,?,?,?)");

            preparedStatement.setString(1, openid);
            preparedStatement.setString(2, mark_color);
            preparedStatement.setString(3, time_stamp);
            preparedStatement.setString(4, location);
            preparedStatement.setString(5, location_text);
            preparedStatement.setString(6, content);
            preparedStatement.setString(7, statusInf);
            preparedStatement.executeUpdate();

            String S = urlMapChange(location);
            String[] str = S.split(",");


            preparedStatement = con.prepareStatement("insert into address (openid,province,city) " + "values(?,?,?)");
            preparedStatement.setString(1, openid);
            preparedStatement.setString(2, str[0]);
            preparedStatement.setString(3, str[1]);
            preparedStatement.executeUpdate();


            List<Object> data_list = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();

            map.put("ok", Boolean.TRUE);
            map.put("message", "数据存储成功");
            map.put("data", data_list);

            jsonObject = JSONObject.fromObject(map);

            out.write(jsonObject.toString());

            out.flush();
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
            List<Object> data_list = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();

            map.put("ok", Boolean.FALSE);
            map.put("message", "数据存储失败");
            map.put("data", data_list);
            jsonObject = JSONObject.fromObject(map);

            out.write(jsonObject.toString());

            out.flush();
            out.close();

        }

    }

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

            /*5.9
             * 调用腾讯接口这一部分，设计成单独的函数，可观性太差
             */
            jsonObject1 = JSONObject.fromObject(res);
            JSONObject jsonObject2 = jsonObject1.getJSONObject("result");
            JSONObject jsonObject3 = jsonObject2.getJSONObject("address_component");
            province = jsonObject3.getString("province");
            city = jsonObject3.getString("city");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return province + "," + city;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
