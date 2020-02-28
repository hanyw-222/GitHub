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
    <title>药品信息管理</title>
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery211.min.js"></script>
    <script type="text/javascript" src="js/bootstrap337.min.js"></script>
    <style>
        .head{
            background-color: #337ab7;
            height: 100px;
            margin-bottom: 20px;
        }
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        /* *x选择器：选择所有元素 */
        * {
            box-sizing: border-box;
        }

        .row {
            display: flex;
            height: 100%;
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
            padding-left: 15px;
            height: 100%;
            /*padding-top: 10px;*/
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
            display: block
        }
        .medium1{
            display: flex;
            flex-direction: row;
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
            display: block;
            /*margin-top: 10px;*/
            margin-left: 20%;
            margin-bottom: 15%;
        }
        .medium21 ul{
            /*margin-top: 10px;*/
            margin-left: 13%;
        }
        .medium21 ul li{
            font-size: 24px;
            color: black;
            margin-bottom: 10px;
            padding-top:10px;
        }
        .medium22{
            display:none;
        }
        /*table{*/
        /*    width: 100%;*/
        /*    border-spacing: 10px;*/
        /*}*/
        /*table tr{*/
        /*    text-align: center;*/
        /*}*/
        /*tr th{*/
        /*    background-color: #9dcce3;*/
        /*    font-size: 22px;*/
        /*}*/

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


</head>
<body>
<c:if test="${!empty insertMedicineState}">
    <script type="text/javascript">
        alert("${insertMedicineState}");
    </script>
</c:if>
<div class="header">
    <div class="head">
        <p >药品管理</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/rootHome.do" style="font-size: x-large;color: white">管理员首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/rootExit.do">退出登录</a></li>
        </ul>
    </div>
</div>

<div class="row" style="margin-top: 20px">
    <div class="left" style="background-color:#337ab7;">
        <ul id="myMenu" >
            <li><input type="button" id="v1" value="新增药品" onclick="v1show()"></li>
            <li><input type="button" id="v2" value="管理药品" onclick="v2show()"></li>
        </ul>
    </div>
    <div class="right" >
        <hr>
        <div class="medium21" id="div1">
<%--            <input type="button" id="change" name="change" value="上传图片" style="width: 120px;margin-left: 90px">--%>
            <form  action="/addMedicine.do" id="medicineInformation" style="border: black 1px solid;width:60%;">
                <div id="upload">
                    <img src="img/yuanHui.png"  onclick="imgClick()" style="padding-left:20%;padding-top: 10px;width: 80%;height: 40%"><br>
                </div>
                <ul>
                    <li>
                        药品名称&nbsp;&nbsp;&nbsp;&nbsp;：<input style="width: 55%" type="text" id="medicineName" name="medicineName">
                    </li>
                    <li>
                        所属类别&nbsp;&nbsp;&nbsp;&nbsp;：
                        <select  id="medicineLabel" name="medicineLabel" style="width:50%;height: 5%">
                        <option>-------请选择-------</option>
                        <option value="家庭常备">家庭常备</option>
                        <option value="食品滋补">保健养生</option>
                        <option value="营养保健">肠胃疾病</option>
                    </select>
                    </li>
                    <li>
                        主要功效&nbsp;&nbsp;&nbsp;&nbsp;：<input style="width: 55%" type="text" id="medicineEffect" name="medicineEffect">
                    </li>
                    <li>
                        药品简介&nbsp;&nbsp;&nbsp;&nbsp;：<br>
                        <textarea id="medicinePro" name="medicinePro" style="height: 15%;width: 80%;font-size: x-large"></textarea>
                    </li>
                </ul>
                <button style="margin-left: 42%;width: 15%;margin-bottom:2%" type="submit" onclick="showMsg()">提交</button>
            </form>
        </div>
        <div class="span12  center-block" style="margin-top: 3%;margin-left: 3%;display: none" id="div2" >

            <div style="display: inline">
                <form>
                    <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
                    <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
                    <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
                    <div class="input-group input-group-sm col-sm-3" style="margin-left:55%">
                        <span class="input-group-addon">药品名称:</span>
                        <input type="text" id="queryMedicineName" class="form-control" placeholder="请输入药品名称" name="queryMedicineName">
                    </div>
                </form>
                <button class="btn btn-sm col-sm-1 btn-success" onclick="queryMedicine()">查询</button>
            </div>
            <br>
            <div style="margin-top: 2%">
                <table class="table" id="userTable">
                    <tr>
                        <th>药品图片</th>
                        <th>药品名</th>
                        <th>主要简介</th>
                        <th>药品管理</th>
                    </tr>
                </table>
                <div class="text-center">
                    <button class="btn btn-success" onclick="queryUser(1)">首页</button>
                    <button class="btn btn-success" onclick="queryUser(2)">上一页</button>
                    <span class="control-label" id="showInfo" style="color: #000000"> 第 1 页 / 共 1 页 </span>
                    <button class="btn btn-success" onclick="queryUser(3)">下一页</button>
                    <button class="btn btn-success" onclick="queryUser(4)">末页</button>
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
<script>

    function showMsg() {
        <c:if test="${!empty addMedicineMsg}">
        alert("${addMedicineMsg}");
        </c:if>
    }

    function v1show() {
        document.getElementById('div2').style.display="none";
        document.getElementById('div1').style.display="block";

    }

    function v2show() {
        document.getElementById('div1').style.display="none";
        document.getElementById('div2').style.display="block";
        queryUser(0);
    }
    function imgClick(){
        //动态创建文件选择框
        var fileObj = document.createElement("input");
        fileObj.type="file";
        //文件选择框的onchange事件
        fileObj.onchange = function(){
            HeadPortUpload(fileObj);
        }
        //激活文件选择框
        fileObj.click();
    }
    //ajax的文件上传
    function HeadPortUpload(fileObj){
        //alert(fileObj.files.length+"\n"+fileObj.files[0].name);
        var formData = new FormData();
        formData.append("file", fileObj.files[0]);
        // alert("start");
        $.ajax({
            url: "medicineHeadImg.do",
            type: "POST",
            data: formData,
            cache: false,//上传文件无需缓存
            processData: false,//用于对data参数进行序列化处理 这里必须false
            contentType: false, //必须
            success: function (data) {
                $("#headPort").val(data);
                $("#upload").html("<img style=\"padding-left:20%;padding-top: 10px;width: 80%;height: 40%\" src='"+data+"' width='200px' onclick='imgClick()'>");
            },
            error: function () {
                alert("上传失败！");
            }
        });
    }

    function queryMedicine(){
        queryUser(0);
    }

    function queryUser(key){
        var queryMedicineName = $("#queryMedicineName").val();
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
            url:"rootQueryAllMedi.do",
            data:{
                curPage: $("#curPage").val(),
                row4Page: $("#row4Page").val(),
                maxPage: $("#maxPage").val(),
                queryMedicineName:  $("#queryMedicineName").val(),
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
        var trList = $("#userTable tr");
        //遍历删除表格行
        $.each(trList,function (index,item) {
            if(index>0){
                $(item).remove();
            }
        });
        //刷新表格行
        userList.forEach(function(medicine){
            var headAdd = medicine.headAdd;
            // alert(headAdd);
            var htmlStr = "<tr>" +
                "<td><a> <img  style='width:100px ;height:65px' src='"+headAdd+"'></a></td>"+
                "<td>"+medicine.medicineName+"</td>"+
                "<td>"+medicine.medicinePro+"</td>";
            htmlStr +="<td>" +
                "<button class=\"btn col-sm-6-8  btn-success center-block\" onclick=\"delUser("+medicine.medicineId+")\">删除药品</button>" +
                "</td></tr>";
            $("#userTable").append(htmlStr);
        });
    }

    /**
     * 删除指定药品
     * @param id
     */
    function delUser(medicineId){
        // alert("id=="+medicineId);
        $.ajax({
            url:"delMedicine.do",
            data:
                {
                medicineId:medicineId,
            },
            type:"Post",
            success:function(data){
                alert("删除该药品成功!");
                queryUser(0);
                // refreshTable(page);
            },
            error:function(){
                alert("error");
            }
        });
    }


</script>
</body>
</html>
