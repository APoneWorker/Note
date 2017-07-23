<%@page language="java" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>新留言</title>
		<link rel="stylesheet" type="text/css" href="style/main.css">
	</head>
	<body>
		<%@include file="header.jsp"%>
		<div class="ui-widget-header" style="text-align:center;">新留言</div>
		<div class="mainDiv">
			<s:form name="form1" action="addNote" theme="simple" enctype="multipart/form-data" method="post">
				<div class="col3">标题</div>
				<div class="col4">
					<s:textfield name="note.title" size="50"/>
                </div>
                <div class="clear"></div>
                    <div class="col3">内容</div>
                    <div class="col4">
                    <s:textarea rows="20" cols="60" name="note.content"/>
                        <br/>(少于200字)
                    </div>
					<div class="clear"></div>
					<div class="col3" style="text-align: right"></div>
					<div class="col4">
						<s:file name="attachment"/>
					</div>
                    <div class="col4">
                        <s:submit value="确定"/>
                        <s:reset value="重填"/>
                    </div>
			</s:form>
		</div>
	</body>
</html>