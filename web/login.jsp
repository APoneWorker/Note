<%@page language="java" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>欢迎登录JSP留言板</title>
    <link rel="stylesheet" href="style/main.css">
  </head>
  <body>
    <%@include file="header.jsp" %>
    <div class="ui-widget-header" style="text-align:center">登录</div>
    <div class="ui-widget-content">
      <s:fielderror/>
      <s:form id="form1" action="login" method="POST">
        <div class="item">
           <span class="label">用户名</span>
           <input name="userName" id="userName"/>
        </div>
        <div class="item">
           <span class="label">密码</span>
           <input type="password" name="password" id="password"/>
        </div>
        <div class="item">
          <input type="submit" value="确认">
          <input type="reset" value="重置">
          <a href="reg.jsp">注册账号</a>
        </div>
      </s:form>
    </div>
  </body>
</html>
