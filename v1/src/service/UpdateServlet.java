package service;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.*;
import java.util.*;

public class UpdateServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=GBK");
            response.setCharacterEncoding("utf-8");

            String mark = request.getParameter("mark_color");
            String id = request.getParameter("id");

            JSONObject jsonObject;
            Map<String, Object> map = new LinkedHashMap<>();
            List<Object> datalist = new ArrayList<>();
            PrintWriter out = response.getWriter();

            Connection con = null;
            PreparedStatement preparedStatement = null;
            Class.forName("com.mysql.jdbc.Driver");

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");


            preparedStatement = con.prepareStatement("update user set markcolor=? where id=?");

            preparedStatement.setString(1, mark);
            preparedStatement.setString(2, id);

            preparedStatement.executeUpdate();


            map.put("ok", Boolean.TRUE);
            map.put("message", "数据更新成功");
            map.put("data", datalist);

            jsonObject = JSONObject.fromObject(map);

            out.write(jsonObject.toString());

            out.flush();
            out.close();

        } catch (Exception e) {

            JSONObject jsonObject;
            List<Object> data_list = new ArrayList<>();
            Map<String, Object> map = new LinkedHashMap<>();
            PrintWriter out = response.getWriter();

            map.put("ok", Boolean.FALSE);
            map.put("message", "数据更新失败");
            map.put("data", data_list);

            jsonObject = JSONObject.fromObject(map);
            out.write(jsonObject.toString());

            e.printStackTrace();

        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
