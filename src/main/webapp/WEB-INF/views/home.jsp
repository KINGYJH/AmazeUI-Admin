<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/6/30
  Time: 11:04
  首页
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>首页</title>
    <style type="text/css">
        .panel {
            float: left;
            margin: 5px 5px;
        }
    </style>
</head>
<body>
<div style="width: 100%;height: 100%;">
    <c:forEach var="view" items="${data}">
        <div class="easyui-panel home_view" title="${view.title}"
             style="width:${view.width};height:${view.height};background:#fafafa;"
             data-options="closable:true,collapsible:true">
            <div style="padding:5px 5px;">
                <a href="#" onclick="edit('${view.id}')" class="easyui-linkbutton"
                   data-options="iconCls:'icon-edit'">修改</a>
                <a href="#" onclick="del('${view.id}')" class="easyui-linkbutton"
                   data-options="iconCls:'icon-remove'">删除</a>
            </div>
            <iframe src="${view.url}" scrolling="auto" frameborder="0" style="width:100%;height:90%;">
            </iframe>
        </div>
    </c:forEach>
</div>
</body>
</html>