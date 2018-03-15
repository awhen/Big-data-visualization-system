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
      <div class="row">
         <div class="col-md-12">
             <h4>修改功能</h4>
             <form role="form" action="changefunction" method="post">
                  <div class="form-group">
				    <label for="name">功能名称</label>
				    <input type="text" class="form-control" placeholder="名称" value="${function.name }" name="name" required>
				  </div>
				  <div class="form-group">
				    <label for="name">功能备注说明</label>
				    <input type="text" class="form-control" placeholder="备注说明" value="${function.description }" name="description" required>
				  </div>
				  <label for="name">选择事件</label>
					<div>
					    <c:forEach var="e" items="${elist}">
					        <c:choose>
					            <c:when test="${e.check == 1}">
					                <label class="checkbox-inline col-md-5">
										<input type="checkbox" name="eventId" value="${e.id }" checked>${e.eventName }(${e.content })
									</label>
					            </c:when>
					            <c:otherwise>
					                <label class="checkbox-inline col-md-5">
										<input type="checkbox" name="eventId" value="${e.id }">${e.eventName }(${e.content })
									</label>
					            </c:otherwise>
					        </c:choose>
						</c:forEach>
					</div>
					<div class="form-group">
					  <input type="hidden" name="functionId" value="${function.id }" >
				      <input class="btn btn-primary col-md-12" type="submit" value="提交" style="margin-top:30px;">
				  </div>
			 </form>
         </div>
      </div>
      <script>
      </script>
        </div>
  </body>
</html>
