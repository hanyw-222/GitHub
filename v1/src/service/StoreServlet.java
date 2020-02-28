package service;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=GBK");
            response.setCharacterEncoding("utf-8");

            String openid = request.getParameter("openid");
            String userInfo = request.getParameter("rawData");
            JSONObject object1 = JSONObject.fromObject(userInfo);
            String nickName = object1.getString("nickName");
            String gender = object1.getString("gender");
            String language = object1.getString("language");
            String city = object1.getString("city");
            String province = object1.getString("province");
            String country = object1.getString("country");
            String url = object1.getString("avatarUrl");


            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");
            PreparedStatement preparedStatement;

            preparedStatement = con.prepareStatement("insert into userinfor (openid,nickName,gender,language,city,province,country,avatarUrl) " + "values(?,?,?,?,?,?,?,?)");

            preparedStatement.setString(1, openid);
            preparedStatement.setString(2, nickName);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, language);
            preparedStatement.setString(5, city);
            preparedStatement.setString(6, province);
            preparedStatement.setString(7, country);
            preparedStatement.setString(8, url);
            preparedStatement.executeUpdate();
            List<Object> data_list = new ArrayList<>();
            Map<String, Object> map = new HashMap<>();

            map.put("ok", Boolean.TRUE);
            map.put("message", "数据存储成功");
            map.put("data", data_list);

            JSONObject jsonObject = JSONObject.fromObject(map);
            PrintWriter out = response.getWriter();

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
            JSONObject jsonObject = JSONObject.fromObject(map);
            PrintWriter out = response.getWriter();

            out.write(jsonObject.toString());

            out.flush();
            out.close();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
