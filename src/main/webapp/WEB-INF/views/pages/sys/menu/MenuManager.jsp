<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/10
  Time: 15:46
  菜单管理页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>菜单管理</title>
</head>
<body>
<div class="iframe-content">
    <div id="tb" style="padding:2px 5px;">
        <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
    </div>
    <table class="easyui-datagrid" data-options="toolbar:'#tb'">
        <thead>
        <tr>
            <th data-options="field:'itemid',width:80">Item ID</th>
            <th data-options="field:'productid',width:100">Product</th>
            <th data-options="field:'listprice',width:80,align:'right'">List Price</th>
            <th data-options="field:'unitcost',width:80,align:'right'">Unit Cost</th>
            <th data-options="field:'attr1',width:240">Attribute</th>
            <th data-options="field:'status',width:60,align:'center'">Status</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">

</script>
</body>
</html>
