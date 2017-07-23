<%@page language="java"  pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<div class="header">
  <div class="welcomePanel">
     <s:if test="#session.user.userName!=null">
         欢迎<s:property value="#session.user.userName"/>回来
         <img alt="" src="images/split.jpg">
         <a href="logout.action">登出</a>
     </s:if>
  </div>
</div>
