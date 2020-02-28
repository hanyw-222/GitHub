<%--
  Created by IntelliJ IDEA.
  User: 18302
  Date: 2019/7/9
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/6/26
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>健康档案</title>
    <style>
        .header{
            display: flex;
            flex-direction: row;
            font-size: 20px;
            padding-top: 10px;
            padding-bottom: 10px;
            padding-left: 20px;
        }
        .bottom{
            font-size: 20px;
            padding-top: 5px;
            padding-bottom: 10px;
        }
        .bottom ul li{
            padding-bottom: 8px;
        }
    </style>
    <%--<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
    <script type="text/javascript">
        function insert2() {
            $.ajax({
                type: "POST",
                url: "/userHealthReportStore.do",
                data: $('#form1').serialize(),
                success: function () {
                    alert("保存成功！")
                },
                error: function () {
                    alert("异常！");
                }
            });
        }
    </script>--%>
</head>
<body>
<form id="form1" action="\userHealthReportStore.do" method="post">
    <div class="header">
        身高：<input type="text" id="high" name="high" style="margin-right: 20px;margin-left: 20px" >
        体重：<input type="text" id="weight" name="weight" style="margin-right: 20px;margin-left: 20px">
    </div>
    <hr>
    <div class="bottom">
        <ul>
            <li>
                抽烟：<input type="radio" name="smoke" value="1">是
                <input type="radio" name="smoke" value="0">否
            </li>
            <li>
                饮酒：<input type="radio" name="drink" value="1">从不
                <input type="radio" name="drink" value="2">偶尔
                <input type="radio" name="drink" value="3">经常
                <input type="radio" name="drink" value="4">每天
            </li>
            <li>
                饮食规律：<input type="radio" name="food" value="1">是
                <input type="radio" name="food" value="0">否
            </li>
            <li>
                大小便正常：<input type="radio" name="shit" value="1">是
                <input type="radio" name="shit" value="0">否
            </li>
            <li>
                是否有过敏史：<input type="radio" name="allergy" value="1">是
                <input type="radio" name="allergy" value="0">否
            </li>
        </ul>
        <input type="submit" value="保存" style="font-size:20px;margin-left: 50px;width:200px;height:30px"/>
    </div>
</form>

</body>
</html>
