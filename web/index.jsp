<%@page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>留言列表</title>
        <link rel="stylesheet" type="text/css" href="style/main.css">
	</head>
	<body>
		<%@include file="header.jsp" %>
		<a href="post.jsp"><img src="images/book_write.png"/></a>
		<div class="ui-widget-header" style="text-align:center;">留言列表</div>
		<div class="col1">留言人</div>
		<div class="col2">主题</div>
        <div class="del">操作</div>
        <s:iterator value="notes" var="note">
            <div class="clear"></div>
            <div class="col1">
                <s:property value="#note.user.userName"/>
            </div>
            <div class="col2">
                <s:a href="detail.action?noteId=%{#note.noteId}">
                    《<s:property value="#note.title"/>》
                </s:a>
            </div>
            <s:if test="#note.user.userId==#session.user.userId">
                <div class="del">
                    <s:a href="deleteNote?noteId=%{#note.noteId}">删除</s:a>
                </div>
            </s:if>
        </s:iterator>
	</body>
</html>