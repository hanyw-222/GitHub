<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/6/26
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        /* *x选择器：选择所有元素 */
        * {
            box-sizing: border-box;
        }

        /* 使用Flexbox创建一个列布局
        flex 的定义和用法
        1.flex 属性用于设置或检索弹性盒模型对象的子元素如何分配空间。
        2.flex 属性是 flex-grow、flex-shrink 和 flex-basis 属性的简写属性。
        注意：如果元素不是弹性盒模型对象的子元素，则 flex 属性不起作用。
        */
        .row {
            display: flex;
        }

        /* 左列(菜单) */
        .left {
            flex: 15%; /*让所有弹性盒模型对象的子元素的长度为15%，且忽略它们内部的内容*/
            padding: 15px 0;
            height: 100%;
            padding-top: 20px;
            margin-left: 20px;
        }

        .left h2 {
            padding-left: 8px;
            background-color: #337ab7;
        }

        /* 右侧(内容) */
        .right {
            flex: 85%; /*让所有弹性盒模型对象的子元素的长度为85%，且忽略它们内部的内容*/
            /*padding-top: 20px;*/
            padding-left: 15px;
            height: 100%
        }

        /* 搜索框 */
        /*  #mySearch {
              width: 100%;
              font-size: 18px;
              padding: 11px;
              border: 1px solid #ddd;
          }*/

        /* 左侧导航 */
        #myMenu {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        /*左侧导航标签*/
        #myMenu li a {
            background-color: #1784bb;
            padding: 12px;
            text-decoration: none;
            font-size: 26px;
            color: white;
            display: block
        }

        /*左侧导航标签效果*/
        #myMenu li a:hover {
            background-color: #eee;
        }
        .head{
            background-color: #337ab7;
            height: 100px;
            margin-bottom: 20px;
        }

        .header{
            height: 100px;
            line-height: 40px;
            background:#337ab7;
        }
        .head{
            height: 90px;
            float: left;
            margin-bottom: 20px;
        }
        .daohang {
            display:block;
            float: right;
            margin-top: 37px;
        }
        ul
        {
            list-style-type:none;
            margin:0;
            padding:0;
        }
        .daohang li
        {
            display:inline;
            padding: 0 15px;
        }
        a{
            color: #fff;
        }
        h1{
            color: #fff;
        }
        .head p{
            margin-top: 25px;
            margin-left: 30px;
            font-size: 45px;
            text-align: left;
            color: white;
        }
    </style>
    <script type="text/javascript">
        function myFunction() {
            var input, filter, ul, li, a, i;
            //input = document.getElementById("mySearch");
            //filter = input.value.toUpperCase();
            ul = document.getElementById("myMenu");
            li = ul.getElementsByTagName("li");
            for (i = 0; i < li.length; i++) {
                a = li[i].getElementsByTagName("a")[0];
                if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
                    li[i].style.display = "";
                } else {
                    li[i].style.display = "none";
                }
            }
        }
    </script>
</head>
<body>
<div class="header">
    <div class="head">
        <p >我的中心</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/userHome.do" style="font-size: x-large;color: white">用户首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/userExit.do">退出登录</a></li>
        </ul>
    </div>
</div>
<div class="row" style="margin-top: 20px">
    <div class="left" style="background-color:#337ab7;">
        <ul id="myMenu">
            <li><a href="/userInformation.do" target="abc">个人基本信息</a></li>
<%--            <li><a href="/userHealthyReport.do" target="abc">健康档案</a></li>--%>
<%--            <li><a href="/userAskRec.do" target="abc">我的咨询</a></li>--%>
        </ul>
    </div>

    <div class="right" >
        <iframe name="abc" style="width: 100%;height: 100%" src="/userInformation.do"/>
    </div>
</div>
</body>
</html>
