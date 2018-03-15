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

    <title>BigDataVisualization</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="css/navbar.css" rel="stylesheet">
    <link href="css/index.css" rel="stylesheet">
    <link href="http://jic2.talkingdata.com/observatory/mi/css/iconfont.css" rel="stylesheet" />

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
     <script type="text/javascript">
            $(window).load(function () {
                 $("#index").addClass("active");
            });
      </script>
  </head>
   
  <body>
    <div class="container">
      <jsp:include page="/WEB-INF/header.jsp"/>
      </div>
      <div class="row">
	      <div class="col-md-12 allcount"> 
	      <div class="content"> 
	      
	          <div class="col-md-4 part"></div>
	          <div class="col-md-2 part">
	          <p>WHEN活跃终端指数</p>
	          <div class="numberRun1"></div>
	          </div>
			  <div class="col-md-2 part">
			  <p style="font-size:18px;">WHEN设备种类指数</p>
			  <div class="numberRun2"></div>
			  </div>
			  <div class="col-md-4 part"></div>
			  
              <div class="col-md-12 part">
		      <div class="percent position clearfix">
                  <span class="icon-ios l"></span>
		          <div class="percent-line l">
		             <em style="width:${iOS }%;"><span>${iOS }%</span><b>百分比</b></em>
				     <em style="width:${Android}%;float:right;"> <span>${Android }%</span><b>百分比</b></em>
		          </div>
		          <span class="icon-android l" style="float:right;"></span>
		        </div>
		      </div>

		      <div class="col-md-8 col-md-offset-2 part">
		          <div class="col-md-4">
		          <table class="table">
		               <caption class="description"><button type="button" class="btn btn-large btn-primary">应用排行</button></caption>
					   <thead>
					      <tr>
					         <th>#</th>
					         <th>名称</th>
					         <th>比例</th>
					      </tr>
					   </thead>
					   <tbody>
					      <c:set var="count" value="1"/>
                          <c:forEach items="${appNameList}" var="appName" begin="0" end="4">
					      <tr>
					         <td>${count }</td>
					         <td>${appName.skey }</td>
					         <td>${appName.svalue }</td>
					      </tr>
					      <c:set var="count" value="${count+1}"/>
                          </c:forEach>
					   </tbody>
					</table>
		          </div>
		          <div class="col-md-4">
		          <table class="table">
					   <caption class="description"><button type="button" class="btn btn-large btn-primary">品牌</button></caption>
					   <thead>
					      <tr>
					         <th>#</th>
					         <th>名称</th>
					         <th>比例</th>
					      </tr>
					   </thead>
					   <tbody>
					   <c:set var="count" value="1"/>
					      <c:forEach var="appModel" items="${appModelList}" begin="0" end="4">
					      <tr>
					         <td>${count }</td>
					         <td>${appModel.skey }</td>
					         <td>${appModel.svalue }</td>
					      </tr>
					      <c:set var="count" value="${count+1}"/>
					      </c:forEach>
					   </tbody>
					</table>
		          </div>
		           <div class="col-md-4">
		          <table class="table">
					   <caption class="description"><button type="button" class="btn btn-large btn-primary">操作系统</button></caption>
					   <thead>
					      <tr>
					         <th>#</th>
					         <th>名称</th>
					         <th>比例</th>
					      </tr>
					   </thead>
					   <tbody>
					   <c:set var="count" value="1"/>
					      <c:forEach var="appSystem" items="${appSystemList}" begin="0" end="4">
					      <tr>
					         <td>${count }</td>
					         <td>${appSystem.skey }</td>
					         <td>${appSystem.svalue }</td>
					      </tr>
					      <c:set var="count" value="${count+1}"/>
					      </c:forEach>
					   </tbody>
					</table>
		          </div>
		      </div>
		      
	       </div>
	      </div>
      </div>
     
      <script>
		(function($) {
		  $.fn.numberAnimate = function(setting) {
		    var defaults = {
		      speed : 1000,//动画速度
		      num : "", //初始化值
		      iniAnimate : true, //是否要初始化动画效果
		      symbol : '',//默认的分割符号，千，万，千万
		      dot : 0 //保留几位小数点
		    }
		    //如果setting为空，就取default的值
		    var setting = $.extend(defaults, setting);
		  
		    //如果对象有多个，提示出错
		    if($(this).length > 1){
		      alert("just only one obj!");
		      return;
		    }
		  
		    //如果未设置初始化值。提示出错
		    if(setting.num == ""){
		      alert("must set a num!");
		      return;
		    }
		    var nHtml = '<div class="mt-number-animate-dom" data-num="{{num}}">\
		            <span class="mt-number-animate-span">0</span>\
		            <span class="mt-number-animate-span">1</span>\
		            <span class="mt-number-animate-span">2</span>\
		            <span class="mt-number-animate-span">3</span>\
		            <span class="mt-number-animate-span">4</span>\
		            <span class="mt-number-animate-span">5</span>\
		            <span class="mt-number-animate-span">6</span>\
		            <span class="mt-number-animate-span">7</span>\
		            <span class="mt-number-animate-span">8</span>\
		            <span class="mt-number-animate-span">9</span>\
		            <span class="mt-number-animate-span">.</span>\
		          </div>';
		  
		    //数字处理
		    var numToArr = function(num){
		      num = parseFloat(num).toFixed(setting.dot);
		      if(typeof(num) == 'number'){
		        var arrStr = num.toString().split("");  
		      }else{
		        var arrStr = num.split("");
		      }
		      //console.log(arrStr);
		      return arrStr;
		    }
		  
		    //设置DOM symbol:分割符号
		    var setNumDom = function(arrStr){
		      var shtml = '<div class="mt-number-animate">';
		      for(var i=0,len=arrStr.length; i<len; i++){
		        if(i != 0 && (len-i)%3 == 0 && setting.symbol != "" && arrStr[i]!="."){
		          shtml += '<div class="mt-number-animate-dot">'+setting.symbol+'</div>'+nHtml.replace("{{num}}",arrStr[i]);
		        }else{
		          shtml += nHtml.replace("{{num}}",arrStr[i]);
		        }
		      }
		      shtml += '</div>';
		      return shtml;
		    }
		  
		    //执行动画
		    var runAnimate = function($parent){
		      $parent.find(".mt-number-animate-dom").each(function() {
		        var num = $(this).attr("data-num");
		        num = (num=="."?10:num);
		        var spanHei = $(this).height()/11; //11为元素个数
		        var thisTop = -num*spanHei+"px";
		        if(thisTop != $(this).css("top")){
		          if(setting.iniAnimate){
		            //HTML5不支持
		            if(!window.applicationCache){
		              $(this).animate({
		                top : thisTop
		              }, setting.speed);
		            }else{
		              $(this).css({
		                'transform':'translateY('+thisTop+')',
		                '-ms-transform':'translateY('+thisTop+')',   /* IE 9 */
		                '-moz-transform':'translateY('+thisTop+')',  /* Firefox */
		                '-webkit-transform':'translateY('+thisTop+')', /* Safari 和 Chrome */
		                '-o-transform':'translateY('+thisTop+')',
		                '-ms-transition':setting.speed/1000+'s',
		                '-moz-transition':setting.speed/1000+'s',
		                '-webkit-transition':setting.speed/1000+'s',
		                '-o-transition':setting.speed/1000+'s',
		                'transition':setting.speed/1000+'s'
		              }); 
		            }
		          }else{
		            setting.iniAnimate = true;
		            $(this).css({
		              top : thisTop
		            });
		          }
		        }
		      });
		    }
		  
		    //初始化
		    var init = function($parent){
		      //初始化
		      $parent.html(setNumDom(numToArr(setting.num)));
		      runAnimate($parent);
		    };
		  
		    //重置参数
		    this.resetData = function(num){
		      var newArr = numToArr(num);
		      var $dom = $(this).find(".mt-number-animate-dom");
		      if($dom.length < newArr.length){
		        $(this).html(setNumDom(numToArr(num)));
		      }else{
		        $dom.each(function(index, el) {
		          $(this).attr("data-num",newArr[index]);
		        });
		      }
		      runAnimate($(this));
		    }
		    //init
		    init($(this));
		    return this;
		  }
		})(jQuery);
		  
		$(function(){
		  
		  //初始化
		  /* var numRun = $(".numberRun").numberAnimate({num:'15343242.10', dot:2, speed:2000, symbol:","});
		  var nums = 15343242.10;
		  setInterval(function(){
		    nums+= 3433.24;
		    numRun.resetData(nums);
		  },3000); */
		  
		  var userCount = '${userCount}';
		  var phoneCount = '${phoneCount}';
		  var numRun1 = $(".numberRun1").numberAnimate({num:userCount, speed:2000, symbol:","});
		  var numRun2 = $(".numberRun2").numberAnimate({num:phoneCount, speed:2000, symbol:","});
		  
		  /* var numRun3 = $(".numberRun3").numberAnimate({num:'52353434.343', dot:3, speed:2000});
		  var nums3 = 52353434.343;
		  setInterval(function(){
		    nums3+= 454.521;
		    numRun3.resetData(nums3);
		  },4000);
		  
		  var numRun4 = $(".numberRun4").numberAnimate({num:'52353434', speed:2000});
		  var nums4 = 52353434;
		  setInterval(function(){
		    nums4+= 123454;
		    numRun4.resetData(nums4);
		  },3500); */
		  
		});
		</script>
      </body>
      </html>