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
<div class="span12  center-block" style="margin-top: 3%;margin-left: 5%;margin-right: 5%">
   <div>
       <form id="form1" method="post">
           <input type="hidden" id="curPage" name="curPage" value="${page.curPage}"/>
           <input type="hidden" id="row4Page" name="row4Page" value="${page.row4Page}"/>
           <input type="hidden" id="maxPage" name="maxPage" value="${page.maxPage}"/>
           <div class="input-group input-group-sm col-sm-3" style="margin-left:55%">
               <span class="input-group-addon">疾病症状:</span>
               <input type="text" id="symptom" class="form-control" placeholder="请输入疾病症状" name="symptom">
           </div>
       </form>
       <button class="btn btn-sm col-sm-2 btn-success" onclick="queryMedicine()">查询</button>
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

    function queryMedicine(){
        queryUser(0);
    }

    $(function(){
        queryUser(0);
    });

    function queryUser(key){
        var symptom = $("#symptom").val();
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
            url:"queryAllMedi.do",
            data:{
                curPage: $("#curPage").val(),
                row4Page: $("#row4Page").val(),
                maxPage: $("#maxPage").val(),
                symptom:  $("#symptom").val(),
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


</body>
</html>