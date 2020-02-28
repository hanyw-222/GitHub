package service;

import control.dataMatchController;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class MatcherServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=GBK");
            response.setCharacterEncoding("utf-8");

            String openid = request.getParameter("openid");

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");

            Statement sta = con.createStatement();
            Statement sta2 = con.createStatement();

            String sql1 = "Select * from data Where openid !='" + openid + "'";
            String sql2 = "Select * from data Where openid ='" + openid + "'";
            ResultSet res1 = sta.executeQuery(sql1);
            ResultSet res2 = sta2.executeQuery(sql2);

            Map<String, List<Integer>> map = new LinkedHashMap<>();
            List<Double> list_now = new LinkedList<>();

            while (res2.next()) {
                String list = res2.getString("data_list");
                for (int i = 0; i < list.length(); i++) {
                    int a = Integer.parseInt(list.substring(i, i + 1));
                    list_now.add((double) a);
                }
            }

            while (res1.next()) {
                String id = res1.getString("openid");
                String list = res1.getString("data_list");
                List<Integer> list2 = new LinkedList<>();

                for (int i = 0; i < list.length(); i++) {
                    list2.add(Integer.parseInt(list.substring(i, i + 1)));
                }

                if (!map.containsKey(id)) {
                    map.put(id, list2);
                }
            }
            Map<String, Object> map2 = new LinkedHashMap<>();

            Iterator<Map.Entry<String, List<Integer>>> iter = map.entrySet().iterator();

            while (iter.hasNext()) {

                Map.Entry<String, List<Integer>> entry = iter.next();
                String key = entry.getKey();
                List<Integer> list = entry.getValue();
                List<Double> d_list = new LinkedList<>();

                for (int a : list) {
                    d_list.add((double) a);
                }
                double answer = dataMatchController.getPearsonCorrelationScore(list_now, d_list);
                map2.put(key, answer);
                System.out.println(answer);
            }


            List<Map<String, Object>> list1 = new LinkedList<>();
            for (int i = 0; i < 4; i++) {
                double max = 0.0;
                String str = "";
                Iterator<Map.Entry<String, Object>> iter2 = map2.entrySet().iterator();
                while (iter2.hasNext()) {
                    Map.Entry<String, Object> entry = iter2.next();
                    String key = entry.getKey();
                    Double val = (double) entry.getValue();
                    if (val > max) {
                        max = val;
                        str = key;
                    }
                }
                map2.remove(str);

                if (!str.equals("")) {
                    if (max >= 0.5) {
                        Map<String, Object> map3 = new LinkedHashMap<>();
                        map3.put("openid", str);
                        map3.put("匹配度", max);

                        Statement sta3 = con.createStatement();
                        ResultSet res3 = sta3.executeQuery("Select * From address Where openid='" + str + "'");
                        int id = 0;
                        String city = "";
                        while (res3.next()) {
                            id = Integer.parseInt(res3.getString("id"));
                            city = res3.getString("city");

                        }
                        map3.put("cardId", id);
                        map3.put("city", city);

                        Statement sta4 = con.createStatement();
                        ResultSet res4 = sta4.executeQuery("Select * From userinfor Where openid='" + str + "'");
                        Map<String, Object> map_inf = null;
                        while (res4.next()) {
                            map_inf = new LinkedHashMap<>();
                            String nickName = res4.getString("nickName");
                            String gender = res4.getString("gender");
                            String language = res4.getString("language");
                            String city_0 = res4.getString("city");
                            String province_0 = res4.getString("province");
                            String country = res4.getString("country");
                            String avatarUrl = res4.getString("avatarUrl");

                            map_inf.put("nickName", nickName);
                            map_inf.put("gender", gender);
                            map_inf.put("language", language);
                            map_inf.put("city", city_0);
                            map_inf.put("province", province_0);
                            map_inf.put("country", country);
                            map_inf.put("avatarUrl", avatarUrl);
                        }
                        map3.put("userInfo", map_inf);
                        list1.add(map3);
                    }
                }

            }

            Map<String, Object> return_map = new LinkedHashMap<>();
            /*
            List<Object> datalist = new LinkedList<>();
            datalist.add(list1);
            */
            JSONObject jsonObject;
            PrintWriter out = response.getWriter();
            return_map.put("ok", Boolean.TRUE);
            return_map.put("message", "提取数据成功");
            return_map.put("data", list1);
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


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}