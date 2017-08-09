<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/10
  Time: 15:46
  序列管理页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>序列管理</title>
</head>
<body>
<div id="tb" style="padding:2px 5px;">
    <a href="#" onclick="add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a href="#" onclick="edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    <a href="#" onclick="del()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
<table id="dg_sequence" class="easyui-datagrid" data-options="toolbar:'#tb'">
</table>

<script type="text/javascript">

    $(function () {
        $('#dg_sequence').datagrid({
            url: '${projectPath}/sys/sequence/list',
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
                {title: '表名', field: 'tableName', width: 50, align: 'center'},
                {title: '描述', field: 'describes', width: 50, align: 'center'},
                {title: '主键名称', field: 'pkName', width: 50, align: 'center'},
                {title: '前缀', field: 'prefix', width: 50, align: 'center'},
                {title: '长度', field: 'length', width: 50, align: 'center'},
                {title: '当前数据值', field: 'newValue', width: 50, align: 'center'}
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
            href: '${projectPath}/sys/sequence/save_page',
            id: 'dl_sequence_add',
            title: '新增序列',
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
                        parent.jQuery('#dl_sequence_add').dialog('close');
                    }
                }],
            onClose: function () {
                reloadDatagrId();
                parent.jQuery(this).dialog('destroy');
            }
        })
    }

    /**
     * 修改
     */
    function edit() {
        var row = $('#dg_sequence').datagrid('getSelected');
        if (row) {
            var id = row.id;
            top.jQuery('<div/>').dialog({
                href: '${projectPath}/sys/sequence/edit_page?id=' + id,
                id: 'dl_sequence_edit',
                title: '修改序列',
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
                            parent.jQuery('#dl_sequence_edit').dialog('close');
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

    /**
     * 删除
     */
    function del() {
        var row = $('#dg_sequence').datagrid('getSelected');
        if (row) {
            var id = row.id;
            var version = row.version;
            $.messager.confirm('系统提示', '你确定删除此数据吗(删除后将不可恢复)?', function (r) {
                if (r) {
                    loadTierClose();
                    $.ajax({
                        url: '${projectPath}/sys/sequence/del',
                        dataType: 'json',
                        data: {id: id, version: version},
                        type: 'post',
                        success: function (data) {
                            loadTierClose();
                            var obj = JSON.parse(data);
                            msgShow('系统提示', obj.msg, 'info');
                            if (obj.status === "SUCCESS") {
                                reloadDatagrId();
                            }
                        },
                        error: function () {
                            msgShow('系统提示', '系统错误', 'error');
                            loadTierClose();
                        }
                    })
                }
            });
        } else {
            msgShow("系统提示", "请选择一条数据", "info");
        }
    }

    //重新加载数据
    function reloadDatagrId() {
        $('#dg_sequence').datagrid('reload');
    }
</script>
</body>
</html>