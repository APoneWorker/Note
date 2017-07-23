<%@page language="java" pageEncoding="UTF-8"%>
<html>
    <head>
         <title>留言内容</title>
         <link rel="stylesheet" type="text/css" href="style/main.css">
    </head>
    <body>
         <%@include file="header.jsp" %>
         <a href="post.jsp"><img src="images/book_write.png"/></a>
         <div class="ui-widget-header" style="text-align:center;">
         </div>
         <div class="col1">留言人</div>
              <div class="col2">内容</div>
              <div class="clear"></div>
              <div class="col1">
              <img class="user-head" src="images/head/<s:property value="note.user.head"/>.gif"/><br/>
                  	 用户名：<s:property value="note.user.userName"/><br/>
                   	注册时间：<s:property value="note.user.regTime"/> <br/>
              </div>
         <div class="col2">
             <s:property value="note.content" escape="false" default="无"/>
             <s:if test="note.attachment!=null">
                 <a href="download.action?fileName=<s:property value='note.attachment'/>">▷附件下载</a>
             </s:if>
         </div>
         <div class="clear"></div>
         <s:form action="addReply?noteId=%{note.noteId}" theme="simple" enctype="multipart/form-data" method="post">
            <div class="send">
                <div class="show-head"><img class="user-head" src="images/head/<s:property value='#session.user.head'/>.gif"/></div>
                <s:textarea class="container" name="reply.replyContent"/>
                <s:submit value="评论" class="replyButton"/>
            </div>
         </s:form>
         <div class="clear"></div>
         <s:iterator value="replies" var="reply" status="re">
             <div class="showReply">
                 <div class="show-head"><img class="user-head" src="images/head/<s:property value='#reply.user.head'/>.gif"/></div>
                 <div class="show-mess">
                     #<s:property value="#re.count"/>
                     <div style="margin-left: 5px;display: inline"><s:property value="#reply.user.userName"/></div>
                 </div>
                 <div class="show-content"><s:property value="#reply.replyContent"/></div>
                 <div class="show-mess">
                     <s:property value="#reply.replyTime"/>
                     <s:if test="#reply.user.userId==#session.user.userId">
                         <a href="deleteReply.action?noteId=<s:property value='note.noteId'/>&&replyId=<s:property value='#reply.replyId'/>">删除</a>
                     </s:if>
                 </div>
             </div>
         </s:iterator>
    </body>
</html>

