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
    <link rel="icon" href="../../favicon.ico">

    <title>BigDataVisualization</title>

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
     <script src="js/esl.js"></script>
        <script src="js/config.js"></script>
        <script src='js/jquery.min.js'></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
      <script type="text/javascript">
            $(window).load(function () {
                 $("#function").addClass("active");
            });
      </script>
  </head>

  <body>

    <div class="container">
      <jsp:include page="/WEB-INF/header.jsp"/>
      <div class="col-md-12" style="text-align:center;margin-top:20px;">
          <a href="gofunction"><button type="button" class="btn btn-info col-md-offset-10 col-md-1">效果图</button></a>
          <a href="gocreatefunction"><button type="button" class="btn btn-warning col-md-1">自定义功能</button></a>
      </div>
      <div class="col-md-12">
          <div id="main" style="width:100%; height:600px;margin-top:30px;"></div>
      </div>
        <script>
           //取消异步  
           $.ajaxSetup({  
             async : false
           });
           
            //获取横轴数据和颜色
            function getfunctiondetail(){
               var mydata='';
               $.getJSON("getdetail", function(data){
                  mydata = data;
                });
               return mydata;
		    } 
		    
		    //获取内容数据
            function getfunctiondata(){
               var mydata='';
               $.getJSON("getfunction", function(data) {
                  mydata = data;
                });
               return mydata;
		    } 
		    
            require([
                '${ pageContext.request.contextPath }/js/echarts'
                // 'echarts/chart/bar',
                // 'echarts/component/legend',
                // 'echarts/component/grid',
                // 'echarts/component/tooltip'
            ], function (echarts) {

                var chart = echarts.init(document.getElementById('main'), null, {

                });
                
                //接收横轴和颜色数据
                var functiondetail = getfunctiondetail();
                
                //接收详细数据
                var functiondata = getfunctiondata();

                var labelOption = {
                    normal: {
                        show: true,
                        position: 'insideBottom',
                        rotate: 90,
                        textStyle: {
                            align: 'left',
                            verticalAlign: 'middle'
                        }
                    }
                };

                option = {
                    color: functiondetail[2],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data:functiondetail[0]
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            magicType: {show: true, type: ['line', 'bar', 'stack', 'tiled']},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            axisTick: {show: false},
                            data: functiondetail[1]
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    
                    series: functiondata
                }

                chart.setOption(option);
            });

        </script>
        </div>
  </body>
</html>
