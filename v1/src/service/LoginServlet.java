package service;


import net.sf.json.JSONObject;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;
import java.util.*;

public class LoginServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) {

        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();
        try {
            String code = request.getParameter("js_code");//307d0198dc5ac5474a52a797347e8df1
            URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid=wx0b0bfb0d06705e16&secret=3d3da6567d28921e6b9dfd5aacf791f4&js_code=" + code + "&grant_type=authorization_code");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            InputStream inputStream = conn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }


            //只获取open_id，有加密的必要在此修改
            str = buffer.toString();
            System.out.println("测试" + str);
            String[] ID = str.split(",");


            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "990222");


            if (ID[1].substring(1, 7).equals("openid")) {

                Map<String, Object> map1 = new LinkedHashMap<>();
                String openid = ID[1].substring(10, ID[1].length() - 2);
                provinceCheck(openid);

                Statement sta1 = con.createStatement();
                ResultSet res1 = sta1.executeQuery("Select * From userinfor Where openid='" + openid + "'");

                String nickName;
                String gender;
                String language;
                String city;
                String province;
                String country;
                String a_url;

                Map<String, Object> map2 = new LinkedHashMap<>();

                while (res1.next()) {

                    nickName = res1.getString("nickName");
                    gender = res1.getString("gender");
                    language = res1.getString("language");
                    city = res1.getString("city");
                    province = res1.getString("province");
                    country = res1.getString("country");
                    a_url = res1.getString("avatarUrl");

                    map2.put("nickName", nickName);
                    map2.put("gender", gender);
                    map2.put("language", language);
                    map2.put("city", city);
                    map2.put("province", province);
                    map2.put("country", country);
                    map2.put("avatarUrl", a_url);
                }

                map1.put("openid", openid);
                map1.put("ok", Boolean.TRUE);
                map1.put("userInfo", map2);

                jsonObject = JSONObject.fromObject(map1);


            } else {

                Map<String, Object> map2 = new HashMap<>();
                map2.put("msg", ID[1].substring(10, ID[1].length() - 2));
                map2.put("ok", Boolean.FALSE);

                jsonObject = JSONObject.fromObject(map2);
            }

            response.setContentType("application/json;charset=GBK");
            PrintWriter out = response.getWriter();
            out.write(jsonObject.toString());

            out.flush();
            out.close();

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*6-29更改
     * 34个省及存储顺序：
     * {香港，澳门，北京，天津，上海，重庆，新疆，内蒙，西藏，广西，宁夏，
     * 河北，河南，山西，山东，陕西，黑龙江，吉林，辽宁，江苏，浙江，安徽，
     * 福建，江西，台湾，海南，湖北，湖南，广东，四川，贵州，云南，甘肃，青海}
     * */
    protected void provinceCheck(String id) throws Exception {
        String[] check_map = {"香港", "澳门", "北京", "天津", "上海", "重庆", "新疆", "内蒙", "西藏", "广西", "宁夏",
                "河北", "河南", "山西", "山东", "陕西", "黑龙", "吉林", "辽宁", "江苏", "浙江", "安徽",
                "福建", "江西", "台湾", "海南", "湖北", "湖南", "广东", "四川", "贵州", "云南", "甘肃", "青海"
        };
        String sql1 = "Select * from address ";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users?useUnicode=true&characterEncoding=utf8", "root", "");

            Statement sta = con.createStatement();
            ResultSet result = sta.executeQuery(sql1);

            Map<String, String[]> map1 = new LinkedHashMap<>();
            while (result.next()) {
                String province = result.getString("province");
                String openid = result.getString("openid");

                String[] number;
                if (!map1.containsKey(openid)) {
                    number = new String[34];
                    for (int i = 0; i < number.length; i++) {
                        number[i] = "0";
                    }
                } else {
                    number = map1.get(openid);
                }
                for (int i = 0; i < check_map.length; i++) {
                    if (province.substring(0, 2).equals(check_map[i])) {
                        number[i] = "1";
                    }
                }
                map1.put(openid, number);

            }

            Iterator<Map.Entry<String, String[]>> iter = map1.entrySet().iterator();

            while (iter.hasNext()) {

                Map.Entry<String, String[]> entry = iter.next();
                String key = entry.getKey();
                String[] check = entry.getValue();
                String data_list = "";
                for (String s : check) {
                    data_list += s;
                }

                Statement sta2 = con.createStatement();
                ResultSet res2 = sta2.executeQuery("Select count(openid) From data Where openid='" + key + "'");
                int a = 0;
                while (res2.next()) {
                    a = res2.getInt(1);
                }

                System.out.println("测试：" + a);

                if (a == 0) {
                    String sql2 = "insert into data (openid,data_list) values(?,?)";
                    PreparedStatement sta1 = con.prepareStatement(sql2);
                    sta1.setString(1, key);
                    sta1.setString(2, data_list);
                    sta1.executeUpdate();
                    System.out.println("插入成功");
                } else {
                    String sql3 = "update data set data_list= '" + data_list + "'" + "where openid='" + key + "'";
                    Statement sta3 = con.createStatement();
                    sta3.executeUpdate(sql3);
                    System.out.println("更新成功");
                }


            }


        } catch (
                Exception e) {
            e.printStackTrace();

        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
