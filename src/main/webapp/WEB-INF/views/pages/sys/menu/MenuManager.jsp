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
    <table id="dg_tree_menu" class="easyui-treegrid" data-options="toolbar:'#tb'">
    </table>
</div>
<script type="text/javascript">
    $('#dg_tree_menu').treegrid({
        url: '${projectPath}/sys/menu/list',
        width: 'auto',
        loadMsg: '请稍等...',
        rownumbers: true,
        idField: 'id',
        treeField: 'name',
        fitColumns: true,
        columns: [[
            {title: '菜单名称', field: 'name', width: 50, align: 'left'},
            {title: '菜单链接', field: 'href', width: 50, align: 'center'},
            {title: '菜单权限标识', field: 'permission', width: 50, align: 'center'},
            {
                title: '菜单图标', field: 'icon', width: 50, align: 'center',
                formatter: function (value, row, index) {
                    return typeof(value) === "undefined" ? '' : '<img src="' + value + '"/>';
                }
            },
            {title: '菜单同级排序', field: 'sort', width: 50, align: 'center'},
            {
                title: '菜单是否显示', field: 'isShow', width: 50, align: 'center',
                formatter: function (value, row, index) {
                    return value === '0' ? '显示' : '不显示';
                }
            },
        ]],
        onLoadError: function () {
            $.messager.alert("提示", "数据加载出错！", "error");
        },
        onBeforeExpand: function (node) {
            jQuery('#dg_tree_menu').treegrid('options').url = "${projectPath}/sys/menu/list?parentId=" + node.id;
            return true;
        },
        onLoadSuccess: function (row, data) {
            console.log(data);
        }
    })
</script>
</body>
</html>
