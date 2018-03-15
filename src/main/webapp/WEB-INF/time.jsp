<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/common.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <title>BigDataVisualization</title>
    <script src='js/esl.js'></script>
    <script src='js/config.js'></script>
    <script src='js/jquery.min.js'></script>
    <script src='js/echarts.js'></script>
	<script src="js/facePrint.js"></script>
	<link rel="stylesheet" href="css/reset.css">
	  <style>
	      h1 {
	          line-height: 60px;
	          height: 60px;
	          background: #360;
	          text-align: center;
	          font-weight: bold;
	          color: #eee;
	          font-size: 14px;
	      }
	      .chart {
	          height: 600px;
	          margin-top:50px;
	      }
	  </style>
        <script type="text/javascript">
            $(window).load(function () {
                 $("#time").addClass("active");
            });
      </script>
  </head>

  <body>

    <div class="container">
      <jsp:include page="/WEB-INF/header.jsp"/>
      </div>
        
      <div class="col-md-12">
         <div class="chart" id="sync"></div>
      </div>

        <script>
        
        
          //取消异步  
           $.ajaxSetup({  
             async : false
           });
           
            //获取横轴数据和颜色
            function gethours(){
               var mydata='';
               $.getJSON("gethours", function(data){
                  mydata = data;
                });
               return mydata;
		    } 
		    
		    //获取内容数据
            function getmax(){
               var mydata='';
               $.getJSON("getmax", function(data) {
                  mydata = data;
                });
               return mydata;
		    } 

            var echarts;
            var colorTool;
            var chart;
            var myChart;
            var groupCategories = [];
            var groupColors = [];

            require([
                '${ pageContext.request.contextPath }/js/echarts',
                // 'zrender/tool/color',
                // 'echarts/chart/line',
                // 'echarts/chart/parallel',
                // 'echarts/component/grid',
                // 'echarts/component/legend',
                // 'echarts/component/tooltip',
                // 'echarts/component/toolbox',
                // 'echarts/component/dataZoom'
            ], function (ec) {
                echarts = ec;
                colorTool = echarts.color;
                chart = myChart = echarts.init(document.getElementById('sync'));

                option = {
                    dataZoom:[
                        {type:'slider',show:'true',xAxisIndex: [0, 1] },
                        {type:'inside',show:'true',xAxisIndex: [0, 1] }
                    ],
                    tooltip: {
                        trigger: 'axis'
                    },
                    toolbox: {
                        feature: {
                            dataView: {
                                show: true,
                                readOnly: false
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    grid: {
                        containLabel: true
                    },
                    legend: {
                        data: ['每个时间段使用频率']
                    },
                    xAxis: [{
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24']
                    },
                    {
                        type: 'category',
                        axisTick: {
                            alignWithLabel: true
                        },
                        data: ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20','21','22','23','24']
                    }],
                    yAxis: [{
                        type: 'value',
                        name: '频率',
                        min: 0,
                        max: getmax().max,
                        position: 'left'
                    }],
                    series: [{
                        name: '每个时间段使用频率',
                        type: 'line',
                        yAxisIndex: 0,
                        xAxisIndex:1,
                        stack: '总量',
                        label: {
                            normal: {
                                show: true,
                                position: 'top'
                            }
                        },
                        data: gethours()
                    }]
                };

                chart.setOption(option);
            });

        </script>
    </body>
</html>