<%@page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>留言板--注册</title>
    <link rel="stylesheet" href="style/main.css">
  </head>
  <body>
    <div class="header"></div>
    <div class="ui-widget-header" style="text-align:center">注册</div>
    <div class="ui-widget-content">
      <s:fielderror/>
      <s:form id="form1" action="reg" method="post">
          <div class="item">
              <span class="label">用户名</span>
              <input name="userName" id="userName">
          </div>
          <div class="item">
              <span class="label">密码</span>
              <input type="password" name="password" id="password">
          </div>
          <div class="item">
              <span class="label">重复密码</span>
              <input type="password" name="passAgain" id="passAgain">
          </div>
          <div class="item">
              <span class="label">性别</span>
              男<input type="radio" name="gender" value="1" checked>
              女<input type="radio" name="gender" value="2"><br>
          </div>
          <div class="item">
              <span class="label">请选择头像</span>
              <img src="images/head/1.gif">
              <input type="radio" name="head" value="1" checked>
              <s:iterator begin="2" end="15">
                  <img src="images/head/<s:property/>.gif"/>
                  <input type="radio" name="head" value="<s:property/>">
              </s:iterator>
          </div>
          <div class="item">
              <input type="submit" value="确认">
              <input type="reset" value="重置">
          </div>
      </s:form>
    </div>
  </body>
</html>
