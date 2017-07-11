<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/6/30
  Time: 11:04
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')}</title>
    <script src="${modules_rec}/sys/index.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<!--上-->
<div data-options="region:'north',border:false" style="overflow: hidden;height: 10%" class="header">
</div>
<!-- 左 -->
<div data-options="region:'west',split:'true',title:'菜单'" style="width:200px;" id="west">
    <div class="easyui-accordion" data-options="fit:'true',border:false">
        <!--菜单导航内容 -->
        <ul id="menu_tree_id" class="easyui-tree">
        </ul>
    </div>
</div>
<!-- 中 -->
<div id="mainPanel" data-options="region:'center'" style="overflow-y:hidden">
    <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
    </div>
</div>
<!--下-->
<div data-options="region:'south'" style="height: 3%;text-align: center">
    <div class="footer">版权所有：${fns:getConfig('productName')} 技术支持：XXXXXXX 版本：${fns:getConfig('productVersion')}</div>
</div>
</body>
</html>

