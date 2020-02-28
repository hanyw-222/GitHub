<%--
  Created by IntelliJ IDEA.
  User: 18302
  Date: 2019/7/1
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="font-awesome-4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="js/jquery211.min.js"></script>
    <script type="text/javascript" src="js/bootstrap337.min.js"></script>
    <title>用户根据疾病症状查药</title>
    <style>
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
    </style>
</head>
<body>
<div class="span12  center-block" style="margin-left: 5%;margin-right: 5%">
    <div>
        <form id="form1" method="post">
            <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
            <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
            <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
        </form>
    </div>
    <br>
    <div style="margin-top: 2%">
        <table class="table" id="userTable">
            <tr>
                <th>图片</th>
                <th>药品名</th>
                <th>主要功效</th>
                <th>药品简介</th>
            </tr>
        </table>
        <div class="text-center">
            <button class="btn btn-success" onclick="queryUser(1)">首页</button>
            <button class="btn btn-success" onclick="queryUser(2)">上一页</button>
            <span class="control-label" id="showInfo" style="color: #08ff06"> 第 1 页 / 共 1 页 </span>
            <button class="btn btn-success" onclick="queryUser(3)">下一页</button>
            <button class="btn btn-success" onclick="queryUser(4)">末页</button>
        </div>
    </div>

</div>

<script type="application/javascript">
     var medicineLabel = ${medicineLabel};
     var label;

     if(medicineLabel==1){
         label = "家庭常备";
     } else  if(medicineLabel==2){
         label = "保健养生";
     } else if(medicineLabel==3){
         label = "肠胃疾病";
     } else{
         label = "";
     }

    function queryMedicine(){
        queryUser(0);
    }

    $(function(){
        queryUser(0);
    });

    function queryUser(key){
      //  var label = $("#symptom").val();
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
            url:"queryMediByLabel.do",
            data:{
                curPage: $("#curPage").val(),
                row4Page: $("#row4Page").val(),
                maxPage: $("#maxPage").val(),
                label:  label,
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
            var htmlStr = "<tr>" +
                "<td><a> <img  style='width:100px ;height:65px' src='"+headAdd+"'></a></td>"+
                "<td>"+medicine.medicineName+"</td>"+
                "<td>"+medicine.medicineEffect+"</td>"+
                "<td>"+medicine.medicinePro+"</td></tr>";
            i = i+1;
            $("#userTable").append(htmlStr);
        });
    }

    function onlineAsk(doctorId) {
        // alert("doctor.doctorId;"+doctorId);
        window.location.href="onlineAskDoctor.do?doctorId="+doctorId;

    }
</script>
<div style="  width:100%; height:55px;border-bottom:#d8d8da 1px solid;">
</div>


</body>
</html>