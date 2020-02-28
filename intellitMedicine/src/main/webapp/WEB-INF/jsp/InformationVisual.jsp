<%--
  Created by IntelliJ IDEA.
  User: 18302
  Date: 2019/7/5
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/3.7.2/echarts.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.2.js"></script>
    <script type="text/javascript" src="js/echarts-wordcloud.js"></script>
    <script type="text/javascript" src="js/echarts-wordcloud.min.js"></script>
    <title>平台信息可视化</title>
    <style>
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
        <p >平台信息可视化</p>
    </div>
    <div style="float: right">
        <ul class="daohang">
            <li><a  href="/rootHome.do" style="font-size: x-large;color: white">管理员首页</a></li>
            <li><a  style="font-size: x-large;padding-right:50px;color: white" href="/rootExit.do">退出登录</a></li>
        </ul>
    </div>
</div>
<div>
    <div id="userAgeCount" style="height: 50%;width: 100%;">
        <div id="main"  style="height: 90%;width:45%;background-color: #31b0d5; float:left;">
        </div>
        <div id="doctorTitleCount"  style="height: 90%;width: 45%;background-color: #31b0d5;margin-left: 50%;margin-top: 2%">
        </div>
    </div>

    <div  id="userDoctorCount" style="height: 50%;width: 100%;float: left;display: inline">
        <div id="userDoctor"  style= "height:90%;width: 30%;background-color: #31b0d5;float: left;">
        </div>
        <div style="height:100%;width: 70%;float: left;background-color: white">
            <div id="onlineAskCiYun" style="height: 100%;width:50%;background-color: #31b0d5;margin-left:5%;float: left">
            </div>
            <div id="askAndReply"  style="height: 100%;width:50%;background-color: #31b0d5;margin-left:55%;">
            </div>
        </div>
    </div>
</div>


<div style="width:100%;height:60px; padding-top:10px;text-align:center;">
    <div style="  width:100%; height:20px;border-bottom:#d8d8da 1px solid;">
    </div>
    <p style=" color:#696969;line-height:25px;">服务热线  800861270</p>
    <p style=" color:#696969;line-height:25px;">版权所有 智慧医养公共服务平台</p>
</div>

<script >

    //表示要在id为main的盒子中绘制图片
    var dom = document.getElementById('main');
    //表示对echarts图表进行初始化
    var echart = echarts.init(dom);
    //options表示进行基本的配置项配置
    var options = {
        title:{
            text:'用户年龄分布统计                               用户总人数：${userCount}',
            left:'center',textStyle:{
                //文字颜色
                color:'#0c0b12',
                //字体风格,'normal','italic','oblique'
                fontStyle:'normal',
                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                fontWeight:'bold',
                //字体系列
                fontFamily:'sans-serif',
                //字体大小
                fontSize:18
            }

        },
        xAxis: {
            data: ['30岁以下','30-45岁','45-60岁','60-75岁','75-90岁','90岁以上']
        },
        yAxis: {
            type:"value"
        },
        series: [{
            name: '用户人数',
            type: 'bar',//line为折线图，scatter为散点图
            data:[${ageCount.low30},${ageCount._30_45},${ageCount._45_60},${ageCount._60_75},${ageCount._75_90},${ageCount.up90}],
            itemStyle: {
                normal: {
                    label: {
                        show: true,		//开启显示
                        position: 'top',	//在上方显示
                        textStyle: {	    //数值样式
                            color: 'black',
                            fontSize: 16
                        }
                    }
                }
            }
        }],
        color: ['rgb(109,112,255)']
    }
    //对配置项进行最终展现，或者说是重新绘制
    echart.setOption(options);


    //表示要在id为main的盒子中绘制图片
    var dom1 = document.getElementById('userDoctor');
    //表示对echarts图表进行初始化
    var echart1 = echarts.init(dom1);
    //options表示进行基本的配置项配置

    var option1 = {
                title:{
                    text:'用户医生人数占比',
                    left:'center',
                    textStyle: {
                        //文字颜色
                        color: '#0c0b12',
                        //字体风格,'normal','italic','oblique'
                        fontStyle: 'normal',
                        //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                        fontWeight: 'bold',
                        //字体系列
                        fontFamily: 'sans-serif',
                        //字体大小
                        fontSize: 18
                    },
                },
                series:[{
                    name:'访问量',
                    type:'pie',
                    radius:'60%',
                    data:[
                        {value:${userCount},name:'用户'},
                        {value:${doctorCount},name:'医生'}
                    ],
                    itemStyle: {
                        normal:{
                            label:{
                                show: true,
                                formatter: '{b} : {c} ({d}%)'
                            },
                            labelLine :{show:true}
                        }
                    }
                }]
            };
    echart1.setOption(option1);


    //表示要在id为main的盒子中绘制图片
    var dom2 = document.getElementById('askAndReply');
    //表示对echarts图表进行初始化
    var echart2 = echarts.init(dom2);
    //options表示进行基本的配置项配置
    var option2 = {
        title:{
            text:'在线问诊回复情况',
            left:'center',
            textStyle: {
                //文字颜色
                color: '#0c0b12',
                //字体风格,'normal','italic','oblique'
                fontStyle: 'normal',
                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                fontWeight: 'bold',
                //字体系列
                fontFamily: 'sans-serif',
                //字体大小
                fontSize: 18
            },
        },
        series:[{
            name:'访问量',
            type:'pie',
            radius:'60%',
            data:[
                {value:${replyed},name:'已回复'},
                {value:${unreplyed},name:'未回复'}
            ],
            itemStyle: {
                normal:{
                    label:{
                        show: true,
                        formatter: '{b} : {c} ({d}%)'
                    },
                    labelLine :{show:true},
                    // color:['#f6da22','#5e7ee8'],
                }
            }
        }]
    };
    echart2.setOption(option2);


    //医生职称统计
    var dom3 = document.getElementById('doctorTitleCount');
    //表示对echarts图表进行初始化
    var echart = echarts.init(dom3);
    //options表示进行基本的配置项配置
    var options = {
        title:{
            text:'平台医生职称统计                               医生总人数：${doctorCount}',
            left:'center',
            textStyle:{
                //文字颜色
                color:'#0c0b12',
                //字体风格,'normal','italic','oblique'
                fontStyle:'normal',
                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                fontWeight:'bold',
                //字体系列
                fontFamily:'sans-serif',
                //字体大小
                fontSize:18
            }
        },
        xAxis: {
            data: ['主任医师','副主任医师','主治医师','医师']
        },
        yAxis: {
            type:"value"
        },
        series: [{
            name: '医生人数',
            type: 'bar',//line为折线图，scatter为散点图
            data:[${zhurenyishi},${fuzhuren},${zhuzhiyishi},${yishi}],
            itemStyle: {
                normal: {
                    label: {
                        show: true,		//开启显示
                        position: 'top',	//在上方显示
                        textStyle: {	    //数值样式
                            color: 'black',
                            fontSize: 16
                        }
                    }
                }
            }
        }],
        color: ['rgb(109,112,255)']
    }
    //对配置项进行最终展现，或者说是重新绘制
    echart.setOption(options);


    //在线问诊词云
    var echart3 = echarts.init(document.getElementById('onlineAskCiYun'));

    var option3 = {
        title : {
            text: '在线问诊热度词云' ,
            x:'center',
            textStyle:{
                //文字颜色
                color:'#0c0b12',
                //字体风格,'normal','italic','oblique'
                fontStyle:'normal',
                //字体粗细 'normal','bold','bolder','lighter',100 | 200 | 300 | 400...
                fontWeight:'bold',
                //字体系列
                fontFamily:'sans-serif',
                //字体大小
                fontSize:18
            }
        },
        tooltip: {},
        series: [ {
            type: 'wordCloud',
            gridSize: 2,
            sizeRange: [12, 50],
            rotationRange: [-90, 90],
            shape: 'triangle',
            // width: 900,
            // height: 900,
            drawOutOfBound: true,
            textStyle: {
                        normal: {
                            color: function () {
                                return 'rgb(' + [
                                    Math.round(Math.random() * 255),
                                    Math.round(Math.random() * 255),
                                    Math.round(Math.random() * 255)
                                ].join(',') + ')';
                            }
                },
                emphasis: {
                    shadowBlur: 10,
                    shadowColor: '#333'
                }
            },
            data: [
                {
                    name: '感冒发烧',
                    value: ${ganmaofashao}
                },
                {
                    name: '跌打损伤',
                    value: ${diedasunshang}
                },
                {
                    name: '高血压',
                    value: ${gaoxueya}
                },
                {
                    name: '风湿骨病',
                    value: ${fengshigubing}
                },
                {
                    name: '糖尿病',
                    value: ${tangniaobing}
                },
                {
                    name: '慢性支气管炎',
                    value: ${zhiqiguanyan}
                },
                {
                    name: '冠心病',
                    value: ${guanxinbing}
                },
                {
                    name: '抑郁症',
                    value: ${yiyuzheng}
                }
            ]
        } ]
    };
    echart3.setOption(option3);
    window.onresize = echart3.resize;

</script>
</body>
</html>
