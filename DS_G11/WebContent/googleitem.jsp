<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html><!--(網頁設定值) --> 
<head>
<meta charset="UTF-8">
<title></title>
<style>
.logo{
float:right;
text-align:right;

}
.url{
clear:left
}
.snow-container {
  position: absolute;
  height: 1000px;
  width: 100%;
  max-width: 100%;
  top: 0;
  overflow-y: visible;
  z-index: 2;
  pointer-events: none;
}

.snow {
  display: block;
  position: absolute;
  z-index: 2;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  pointer-events: none;
  -webkit-transform: translate3d(0, -100%, 0);
          transform: translate3d(0, -100%, 0);
  -webkit-animation: snow linear infinite;
          animation: snow linear infinite;
}
.snow.foreground {
  background-image: url("https://dl6rt3mwcjzxg.cloudfront.net/assets/snow/snow-large-075d267ecbc42e3564c8ed43516dd557.png");
  -webkit-animation-duration: 14s;
          animation-duration: 14s;
}
.snow.foreground.layered {
  -webkit-animation-delay: 8.5s;
          animation-delay: 8.5s;
}

@-webkit-keyframes snow {
  0% {
    -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
  }
  100% {
    -webkit-transform: translate3d(15%, 100%, 0);
            transform: translate3d(15%, 100%, 0);
  }
}

@keyframes snow {
  0% {
    -webkit-transform: translate3d(0, -100%, 0);
            transform: translate3d(0, -100%, 0);
  }
  100% {
    -webkit-transform: translate3d(15%, 100%, 0);
            transform: translate3d(15%, 100%, 0);
  }
}
</style>
</head>
<body  style="background-color:#343d46;">
<div class="logo">
<img src="https://pbs.twimg.com/media/EqiLK_RUUAApwIv?format=png&name=360x360" alt="这是一个图片" 
    style="float: left;"  width= "100";height="100"/>
</div>
<div class="snow-container">
  <div class="snow foreground"></div>
  <div class="snow foreground layered"></div>
</div>
<div class = "url">
<%
String[][] orderList = (String[][])  request.getAttribute("query");
for(int i =0 ; i < orderList.length;i++){%>
<img src="https://pbs.twimg.com/media/EqiL5MqVEAM4M6-?format=png&name=120x120" alt="这是一个图片" width= "40";height="40";>
<a href='<%= orderList[i][1] %>' style="color:white;"><%= orderList[i][0] %></a><p>
<%
}
%>
</div>
</body><!--(網頁內的樣子) --> 
</html>