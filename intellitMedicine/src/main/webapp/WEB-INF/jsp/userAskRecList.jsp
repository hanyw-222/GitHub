<%--
  Created by IntelliJ IDEA.
  User: cielab
  Date: 2019/6/27
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery211.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="js/bootstrap337.min.js"></script>
    <title>我的咨询记录</title>
    <style>
        .head{
            background-color: #337ab7;
            height: 100px;
            /*margin-bottom: 20px;*/
        }
        .choose{
            font-size: 2em;
            width: 100%;
            display: flex;
            align-items: center;
            justify-items: center;
            margin-top: 20px;
            margin-left: 100px;
        }
        .choose ul{
            display: flex;
            flex-direction: row;
            list-style-type: none;
        }
        .choose ul li{
            text-align: center;
        }
        .choose ul li a{
            margin-right: 100px;
        }
        .block1{
            display: flex;
            flex-direction: column;
            width: 100%;
        }
        .block2 ul{
            display: flex;
            flex-direction: row;
            list-style-type: none;
        }
        .block2 ul li{
            font-size: 2em;
            padding-top: 20px;
            margin-right: 50px;
            margin-left: 50px;
            text-align: center;
        }

        .table{
            background-color: white;
            width: 100%;
            align-items: center;
            justify-items: center;
            margin-left: 100px;

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
</head>
<body>
<div class="header">
    <div class="head">
        <p >我的咨询记录列表</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/userHome.do" style="font-size: x-large;color: white">用户首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/userExit.do">退出登录</a></li>
        </ul>
    </div>
</div>
<hr>
<form id="form1" method="post">
    <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
    <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
    <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
</form>
<hr>
<div style="align-items: center;margin-right: 5%;margin-left: 5%">
    <table class="table" id="userTable" border="1" align="right" style="align-content: center">
        <tr>
            <th>头像</th>
            <th>医生姓名</th>
            <th>科室</th>
            <th>咨询时间</th>
            <th>状态</th>
            <th>查看详情</th>
        </tr>
    </table>
</div>
<div class="text-center" style="width: 100%">
    <button class="btn btn-success" onclick="queryUser(1)">首页</button>
    <button class="btn btn-success" onclick="queryUser(2)">上一页</button>
    <span class="control-label" id="showInfo" style="color: #201b41"> 第 1 页 / 共 1 页 </span>
    <button class="btn btn-success" onclick="queryUser(3)">下一页</button>
    <button class="btn btn-success" onclick="queryUser(4)">末页</button>
</div>
</div>

<div style="  width:100%; height:20px;border-bottom:#d8d8da 1px solid;">
</div>
<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>
<script type="application/javascript">
   // var department;
    // var i =1;

    $(function(){
        queryUser(0);
    });

    function queryUser(key){

        // if(key==0){
        //      i = 1;
        // } else if(key==1){
        //      i=1;
        // } else if(key==2){
        //      if(i<=4)
        //          return false;
        //      if(i>4){
        //          yu =$("#maxPage").val()%4
        //          i=
        //      }
        // }

        var curPage = $("#curPage").val();
        var maxPage = $("#maxPage").val();
        var row4Page = $("#row4Page").val();
        switch (key){
            case 0:
                $("#curPage").val(1);
                $("#maxPage").val(1);
                $("#row4Page").val(4);
                curPage = 1;
                maxPage = 1;
                row4Page = 4;
                break;
            case 1:
                $("#curPage").val(1);
                // i=1;
                break;
            case 2:
                $("#curPage").val(curPage>1?curPage-1:curPage);
                // i = ($("#curPage").val)*4
                break;
            case 3:
                $("#curPage").val(curPage<maxPage?curPage*1+1:maxPage);
                // i = ($("#curPage").val-1)*4
                break;
            case 4:
                $("#curPage").val(maxPage);
                // i = ($("#maxPage").val-1)*4
                break;
        }
        $.ajax({
            url:"queryInquiryByUserId.do",
            data:{
                curPage: $("#curPage").val(),
                row4Page: $("#row4Page").val(),
                maxPage: $("#maxPage").val(),
                // department:department,
            },
            type:"Post",
            success:function(page){
              //  alert(page);
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
        var trList = $("#userTable tr");
        //遍历删除表格行
        $.each(trList,function (index,item) {
            if(index>0){
                $(item).remove();
            }
        });
        //刷新表格行
        userList.forEach(function(inquiry){
             var headAdd =  inquiry.doctor.headAdd;
            var htmlStr = "<tr>" +
                // "<td id='"+inquiry.inquiryId+"'>"+i+"</td>" +
                "<td><a> <img  style='width:100px ;height:65px' src='"+headAdd+"'></a></td>"+
                "<td>"+inquiry.doctor.doctorName+"</td>"+
                "<td>"+inquiry.doctor.department+"</td>"+
                "<td>"+new Date(inquiry.inquiryTime).Format("yyyy-MM-dd:hh:mm ")+"</td>" +
                "<td>"+inquiry.state+"</td>"+
                "<td>" +
                "<button class=\"btn   btn-success center-block\"   onclick=\"onlineAsk("+inquiry.inquiryId+")\">查看详情</button>" +
                "</td></tr>";
            i = i+1;
            $("#userTable").append(htmlStr);
        });
    }

    function onlineAsk(inquiryId) {
        window.location.href="/userAskRecDetial.do?inquiryId="+inquiryId;
    }

   Date.prototype.Format = function(fmt) {
       var o = {
           "M+" : this.getMonth()+1,                 //月份
           "d+" : this.getDate(),                    //日
           "h+" : this.getHours(),                   //小时
           "m+" : this.getMinutes(),                 //分
           "s+" : this.getSeconds(),                 //秒
           "q+" : Math.floor((this.getMonth()+3)/3), //季度
           "S"  : this.getMilliseconds()             //毫秒
       };
       if(/(y+)/.test(fmt))
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
       for(var k in o)
           if(new RegExp("("+ k +")").test(fmt))
               fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
       return fmt;
   }
</script>
</body>

</html>
