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
    <title>BEAST_ADMIN</title>
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
	          height: 700px;
	          margin-top:50px;
	      }
	  </style>
        <script type="text/javascript">
            $(window).load(function () {
                 $("#machine").addClass("active");
            });
      </script>
  </head>

  <body>

    <div class="container">
      <jsp:include page="/WEB-INF/header.jsp"/>
      </div>
        
      <div class="col-md-12">
         <!-- <button type="button" class="btn btn-info col-md-offset-2 col-md-1">App</button>
         <button type="button" class="btn btn-info col-md-offset-2 col-md-1">设备型号</button>
         <button type="button" class="btn btn-info col-md-offset-3 col-md-1">设备操作系统</button> -->
         <div class="chart" id="main"></div>
      </div>
        <script>
        
        
           //取消异步  
           $.ajaxSetup({  
             async : false
           });
		    
		    //获取内容数据
            function getindexpei(){
               var mydata='';
               $.getJSON("getindexpei", function(data) {
                  mydata = data;
                });
               return mydata;
		    } 
        
 
            require([
                '${ pageContext.request.contextPath }/js/echarts'
                // 'echarts/chart/pie',
                // 'echarts/component/tooltip',
                // 'echarts/component/title'
            ], function (echarts) {

                var peidata = getindexpei();
                /* alert(peidata[0]); */

                var chart = echarts.init(document.getElementById('main'), null, {

                });
                chart.setOption({
                    tooltip: {
                    	//模板变量有 {a}、{b}、{c}、{d}，分别表示系列名，数据名，数据值，百分比
                        formatter: '{d}%'
                    },
                    series : [
                        {
                            type : 'pie',
                            center : ['20%', '45%'],
                            radius : '30%',
                            percentPrecision: 0,
                            data : peidata[0],
                            label: {
                                normal: {
                                    formatter: '{b} 占 {d}%'
                                }
                            }
                        },
                        {
                            type : 'pie',
                            center : ['48%', '45%'],
                            radius : '30%',
                            percentPrecision: 1,
                            data : peidata[1],
                            label: {
                                normal: {
                                    formatter: '{b} 占 {d}%'
                                }
                            }
                        },
                        {
                            type : 'pie',
                            center : ['75%', '45%'],
                            radius : '30%',
                            percentPrecision: 5,
                            data : peidata[2],
                            label: {
                                normal: {
                                    formatter: '{b} 占 {d}%'
                                }
                            }
                        }
                    ]
                });
            })

        </script>
    </body>
</html>