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
    <script src='js/esl.js'></script>
    <script src='js/config.js'></script>
    <script src='js/jquery.min.js'></script>
    <script src='js/echarts.js'></script>
    <link rel="icon" href="../../favicon.ico">

    <title>BigDataVisualization</title>

    <!-- Bootstrap core CSS -->
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/navbar.css" rel="stylesheet">
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
		body{
		  width: 100%;
		  height:auto;
		  background-color:#000;
		  background-size: 100%;
		}
    </style>
    <script type="text/javascript">
            $(window).load(function () {
                 $("#user").addClass("active");
            });
      </script>
    
  </head>
   
  <body>
    
      <div class="container">
     <jsp:include page="/WEB-INF/header.jsp"/>
      </div>
      <div class="col-md-12">
          <div style="background:#000;">
          <div id='main' style="width:100%;height:800px;background:#000;"></div>
          </div>
      </div>
      <script language="javascript">
           
           //取消异步  
           $.ajaxSetup({  
             async : false
           });
           
            //获取数据
            function getCaseInfoForMap(){
               var mydata='';
               $.get("getalluserlocate", function(data){
                  mydata = data;
                });
               return mydata;
		    } 
		    
		    /* //返回随机颜色
		    function getColor() {
		        var colors = ['#00F5FF','#00E5EE','#00FFFF','#00C5CD']; 
		        var colors = ['#00F5FF','#EEC900','	#B0E2FF','#CD3700'];
		        var index = Math.floor((Math.random() * colors.length)); 
		        return colors[index];
		    } */
       
           require([
                '${ pageContext.request.contextPath }/js/echarts'
                // 'echarts/chart/map',
                // 'echarts/chart/scatter',
                // 'echarts/component/legend',
                // 'echarts/component/geo',
                // 'echarts/component/visualMap'
            ], function (echarts) {
                $.get('js/world.json', function (worldJson) {
                    echarts.registerMap('world', worldJson);
                    var latlong = JSON.parse(getCaseInfoForMap());
                    var chart = echarts.init(document.getElementById('main'), null, {
                    });
                    var max = -Infinity;
                    var min = Infinity;
                    console.profile('setOption');
                    chart.setOption({
                        title : {
                            x:'center',
                            y:'top'
                        },
                        tooltip : {
                            trigger: 'item',
                            formatter : function (params) {
                                return '';
                            }
                        },
                        visualMap: {
                            show: false,
                            min: 0,
                            max: max,
                            inRange: {
                                symbolSize: [1.2]
                            }
                        },
                        geo: {
                            name: '',
                            //类型
                            type: 'map',
                            //地图
                            map: 'world',
                            roam: false,
                            z:1,
                            //图形上的文本标签,这里不显示
                            label: {
                                emphasis: {
                                    show: false
                                }
                            },
                            //地图区域的多边形 图形样式
                            itemStyle: {
                               //普通状态
                                normal: {
                                    areaColor: '#000',
                                    borderColor: '#1C86EE'
                                },
                                //高亮状态
                                emphasis: {
                                    color: 'rgba(0,191,255, 0.2)'
                                }
                            }
                        },
                         series : [
                            {
                                type: 'scatter',
                                coordinateSystem: 'geo',
                                //large :ture, 否开启大规模散点图的优化，在数据图形特别多的时候（>=5k）可以开启，开启后会默认的颜色和禁用动画，自定义的将失效
                                large :false,
                                symbol:'pin',
                                animation:false,
                                data: latlong.map(function (itemOpt) {
                                    return {
                                        value: [
                                            itemOpt.longitude,
                                            itemOpt.latitude
                                        ],
                                        label: {
                                            emphasis: {
                                                show: false
                                            }
                                        },
                                        itemStyle: {
                                            normal: {
                                                color: '#ccc',
                                            }
                                        },
                                      z:2,
                                    };
                                })				            
                            }
                        ]
                    });
                    console.profileEnd('setOption');
                });
            });
        </script>
    </body>
</html>