package service;

import net.sf.json.JSONObject;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;

public class MapServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=GBK");
            response.setCharacterEncoding("utf-8");

            String openid = request.getParameter("openid");
            String sql1 = "select id from user where openid='" + openid + "'";
            String sql2 = "select province from address where id='";
            String sql3 = "select city from address where province='";

            Map<String, Object> return_map = new LinkedHashMap<>();
            List<Object> datalist = new LinkedList<>();
            JSONObject jsonObject;
            PrintWriter out = response.getWriter();

            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");

            Statement sta = con.createStatement();
            Statement sta2 = con.createStatement();
            ResultSet result = sta.executeQuery(sql1);
            Map<String, Integer> pro_map = new LinkedHashMap<>();

            while (result.next()) {

                String id = result.getString("id");

                ResultSet res = sta2.executeQuery(sql2 + id + "'");

                while (res.next()) {
                    String province = res.getString("province");

                    if (!pro_map.containsKey(province)) {
                        pro_map.put(province, 1);

                    } else {
                        int count = pro_map.get(province);
                        pro_map.put(province, count + 1);
                    }
                }

            }
            Statement sta3 = con.createStatement();
            Iterator<Map.Entry<String, Integer>> iter = pro_map.entrySet().iterator();
            Map<String, List> map_city = new LinkedHashMap<>();
            Map<String,Object>map_qita=new LinkedHashMap<>();

            int city_sum = 0;
            int province_sum = pro_map.size();
            while (iter.hasNext()) {

                Map.Entry<String, Integer> entry = iter.next();
                String key = entry.getKey();
                ResultSet result1 = sta3.executeQuery(sql3 + key + "'");
                List<String> list = new ArrayList<>();

                while (result1.next()) {
                    String city = result1.getString("city");

                    if (!list.contains(city)) {
                        list.add(city);
                    }

                }
                city_sum += list.size();
                map_city.put(key , list);
            }

            map_qita.put("province_count", province_sum);
            map_qita.put("city_count", city_sum);


            Statement sta5 = con.createStatement();
            ResultSet result5 = sta5.executeQuery(" select openid ,count(id) from user  where is_deleted= 'false' group by openid");
            List<Integer>list=new ArrayList<>();
            double bottom=0.00;
            double top=0.00;
            double percent;

            while (result5.next()) {
                int count=result5.getInt("count(id)");

                list.add(count);
            }
            for(int i:list){
                if(i>city_sum){
                    bottom++;
                    top++;
                }else{
                    bottom++;
                }
            }
            percent=1-(top/bottom);

            map_qita.put("proportion",percent);

            datalist.add(map_qita);
            datalist.add(map_city);
            datalist.add(pro_map);
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

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
