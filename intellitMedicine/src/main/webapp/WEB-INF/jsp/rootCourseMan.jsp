<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/7/2
  Time: 14:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>教育平台管理</title>
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery211.min.js"></script>
    <script type="text/javascript" src="js/bootstrap337.min.js"></script>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        /* *x选择器：选择所有元素 */
        * {
            box-sizing: border-box;
        }

        .row {
            display: flex;
        }

        /* 左列(菜单) */
        .left {
            flex: 15%; /*让所有弹性盒模型对象的子元素的长度为15%，且忽略它们内部的内容*/
            padding: 15px 0;
            /*height: 100%;*/
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
            padding-left: 15px;
            /*height: 100%;*/
            padding-top: 10px;
        }

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
            display: block;
            /*height: 100%;*/
        }
        .medium1{
            display: flex;
            /*flex-direction: row;*/
            float: right;
        }
        #myMenu input{
            border: none;
            color: white;
            width: 100%;
            height: 50px;
            font-size: 24px;
            background-color:#337ab7;
        }
        .medium21{
            margin-top: 3%;
            margin-left:26%;
        }
        .medium21 ul li{
            font-size: 24px;
            color: black;
            margin-bottom: 10px;
            padding-top:10px;
        }
        .table{
            background-color: white;
        }
        .table th{
            text-align: center;
        }
        .table td{
            vertical-align: middle;
            text-align: center;
        }
        .input-group{
            float:left !important;
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
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="js/jquery211.min.js"></script>
    <script type="text/javascript" src="js/bootstrap337.min.js"></script>

</head>
<body>
<div class="header">
    <div class="head">
        <p >课程管理</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/rootHome.do" style="font-size: x-large;color: white">管理员首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/rootExit.do">退出登录</a></li>
        </ul>
    </div>
</div>

<div class="row" style="margin-top: 20px;">
    <div class="left" style="background-color:#337ab7;">
        <ul id="myMenu" >
            <li><input type="button" id="v1" style="font-size: xx-large" value="新增课程" onclick="v1show()"></li>
            <li><input type="button" id="v2" style="font-size: xx-large" value="管理课程" onclick="v2show()"></li>
        </ul>
    </div>
    <div class="right" >
<%--        <hr>--%>
        <div class="medium21" id="div1">
            <form action="addCourse.do" method="post" >
                <ul>
                    <li>
                        课程名称：<input type="text" id="courseName" name="courseName">
                    </li>
                    <li>
                        所属类别：<select  id="courseType" name="courseType" style="width: 180px;height: 40px;">
                        <option>------请选择-----</option>
                        <option>营养</option>
                        <option>母婴</option>
                        <option>两性</option>
                        <option>生活</option>
                        <option>肿瘤</option>
                        <option>慢病</option>
                    </select>
                    </li>
                    <li>
                        课程形式：<select id="coursePro" name="coursePro" style="width: 180px;height: 40px;">
                        <option>------请选择-----</option>
                        <option value="视频">视频</option>
                        <option value="文章">文章</option>
                    </select>
                    </li>
                    <li>
                        课程简介：<input type="text" id="courseIntro" name="courseIntro">
                    </li>
                    <li>
                        文件地址：<input type="text" id="courseAdd" name="courseAdd">
                    </li>
                </ul>
                <button type="submit" style="margin-left: 24%;margin-top: 3%;width: 10%" onclick="showMsg()">提交</button>
            </form>

        </div>
    <div class="span12  center-block" style="margin-top: 1%;margin-left: 1%;display: none" id="div2" >
            <div style="display: inline">
                    <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
                    <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
                    <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
                    <div class="input-group input-group-sm col-sm-3" style="margin-left:59%">
                        <span class="input-group-addon">课程名称:</span>
                        <input type="text" id="queryCourseName" class="form-control" placeholder="请输入课程名称" name="queryCourseName">
                    </div>
                <button class="btn btn-sm col-sm-1 btn-success" onclick="queryCourseByName()">查询</button>
            </div>
         <br>
            <div style="margin-top: 2%;margin-left: 3%;margin-right: 5%">
                <table class="table" id="courseTable">
                    <tr>
                        <th>课程名称</th>
                        <th>课程类别</th>
                        <th>课程介绍</th>
                        <th>管理课程</th>
                    </tr>
                </table>
                <div class="text-center">
                    <button class="btn btn-success" onclick="queryCourse(1)">首页</button>
                    <button class="btn btn-success" onclick="queryCourse(2)">上一页</button>
                    <span class="control-label" id="showInfo" style="color: #000000"> 第 1 页 / 共 1 页 </span>
                    <button class="btn btn-success" onclick="queryCourse(3)">下一页</button>
                    <button class="btn btn-success" onclick="queryCourse(4)">末页</button>
                </div>
            </div>

        </div>
    </div>
</div>
<div style="  width:100%; height:20px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
<script type="text/javascript">

    function showMsg() {
        <c:if test="${!empty addCourseMsg}">
            alert("${addCourseMsg}");
        </c:if>
        // alert("添加课程成功！");
    }

    function v1show() {
        document.getElementById('div2').style.display="none";
        document.getElementById('div1').style.display="block";
        // queryDoctor(0);
    }

    function v2show() {
        document.getElementById('div1').style.display="none";
        document.getElementById('div2').style.display="block";
        queryCourse(0);
    }
    // $(function(){
    //     querycourse(0);
    // });


    function queryCourseByName(){
        queryCourse(0);
    }

    function queryCourse(key){
        var queryCourseName = $("#queryCourseName").val();
        var curPage = $("#curPage").val();
        var maxPage = $("#maxPage").val();
        var row4Page = $("#row4Page").val();
        switch (key){
            case 0:
                $("#curPage").val(1);
                $("#maxPage").val(1);
                $("#row4Page").val(6);
                curPage = 1;
                maxPage = 1;
                row4Page = 6;
                break;
            case 1:
                $("#curPage").val(1);
                break;
            case 2:
                $("#curPage").val(curPage>1?curPage-1:curPage);
                break;
            case 3:
                $("#curPage").val(curPage<maxPage?curPage*1+1:maxPage);
                break;
            case 4:
                $("#curPage").val(maxPage);
                break;
        }
        // alert($("#symptom").val()) ;
        $.ajax({
            url:"rootQueryCourse.do",
            data:{
                curPage: $("#curPage").val(),
                row4Page: $("#row4Page").val(),
                maxPage: $("#maxPage").val(),
                queryCourseName:  $("#queryCourseName").val(),
            },
            type:"Post",
            success:function(page){
                refreshTable(page);
            },
            error:function(){
                alert("SOMETHING WRONG ");
            }
        });
    }
    /**
     * 刷新表格
     * @param page
     */
    function refreshTable(page){
        var i = 1;
        $("#curPage").val(page.curPage);
        $("#maxPage").val(page.maxPage);
        $("#row4Page").val(page.row4Page);
        $("#showInfo").html(" 第 "+page.curPage+" 页 / 共 "+page.maxPage+" 页 ");
        var userList = page.objList;
        //获取表格行集合
        var trList = $("#courseTable tr");
        //遍历删除表格行
        $.each(trList,function (index,item) {
            if(index>0){
                $(item).remove();
            }
        });
        //刷新表格行
        userList.forEach(function(course){
            // var headAdd =doctor.headAdd;
            var htmlStr = "<tr>" +
                // "<td><a> <img  style='width:100px ;height:65px' src='"+headAdd+"'></a></td>"+
                "<td>"+course.courseName+"</td>"+
                "<td>"+course.courseType+"</td>"+
                "<td>"+course.courseIntro+"</td>";
                // "<td>"+doctor.department+"</td>";
            htmlStr +="<td>" +
                "<button class=\"btn col-sm-6-8  btn-success center-block\" onclick=\"delCourse("+course.courseId+")\">     删除该课程</button>" +
                "</td></tr>";
            $("#courseTable").append(htmlStr);
        });
    }


    /**
     * 删除指定药品
     * @param id
     */
    function delCourse(courseId){
        // alert("id=="+doctorId);
        $.ajax({
            url:"rootDelCourse.do",
            data:
                {
                    courseId:courseId,
                },
            type:"Post",
            success:function(data){
                alert("删除课程成功!");
                v2show();
            },
            error:function(){
                alert("error");
            }
        });
    }
</script>

</body>
</html>
