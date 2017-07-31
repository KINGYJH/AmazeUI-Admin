<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/10
  Time: 15:46
  数据字典管理页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>数据字典管理</title>
</head>
<body>
<div id="tb" style="padding:2px 5px;">
    <a href="#" onclick="add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a href="#" onclick="edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
<table id="dg_dictionary" class="easyui-datagrid" data-options="toolbar:'#tb'">
</table>

<script type="text/javascript">

    $(function () {
        $('#dg_dictionary').datagrid({
            url: '${projectPath}/sys/dictionary/list',
            width: 'auto',
            height: getIframeHeight() * 0.96,
            pageSize: 20,
            fitColumns: true,
            striped: true,
            singleSelect: true,
            pagination: true,// 分页控件
            rownumbers: true,// 行号
            loadMsg: "正在努力加载中...",
            idField: 'id',
            columns: [[
                {title: '键', field: 'dataKey', width: 50, align: 'center'},
                {title: '值', field: 'dataValue', width: 50, align: 'center'},
                {title: '排序', field: 'sort', width: 50, align: 'center'}
            ]],
            onLoadSuccess: function (data) {
            }
        })
    })

    /**
     * 添加
     */
    function add() {
        top.jQuery('<div/>').dialog({
            href: '${projectPath}/sys/dictionary/save_page',
            id: 'dl_dictionary_add',
            title: '数据字典添加',
            width: 700,
            height: 300,
            modal: true,
            shadow: false,
            resizable: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: "icon-ok",
                    handler: function () {
                        parent.submitForm();
                    }
                },
                {
                    text: '取消',
                    iconCls: "icon-cancel",
                    handler: function () {
                        parent.jQuery('#dl_dictionary_add').dialog('close');
                    }
                }],
            onClose: function () {
                reloadDatagrId();
                parent.jQuery(this).dialog('destroy');
            }
        })
    }

    /**
     *修改
     */
    function edit() {
        var row = $('#dg_dictionary').datagrid('getSelected');
        if (row) {
            top.jQuery('<div/>').dialog({
                href: '${projectPath}/sys/dictionary/edit_page?id=' + row.id,
                id: 'dl_dictionary_edit',
                title: '数据字典修改',
                width: 700,
                height: 300,
                modal: true,
                shadow: false,
                resizable: true,
                buttons: [
                    {
                        text: '保存',
                        iconCls: "icon-ok",
                        handler: function () {
                            parent.submitForm();
                        }
                    },
                    {
                        text: '取消',
                        iconCls: "icon-cancel",
                        handler: function () {
                            parent.jQuery('#dl_dictionary_edit').dialog('close');
                        }
                    }],
                onClose: function () {
                    reloadDatagrId();
                    parent.jQuery(this).dialog('destroy');
                }
            })
        } else {
            msgShow("系统提示", "请选择一条数据", "info");
        }
    }

    //重新加载数据
    function reloadDatagrId() {
        $('#dg_dictionary').datagrid('reload');
    }
</script>
</body>
</html>