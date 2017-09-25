<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/10
  Time: 15:46
  用户管理页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>用户管理</title>
</head>
<body>
<div id="tb" style="padding:2px 5px;">
    <a href="#" onclick="add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a href="#" onclick="del()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
<table id="dg_user" class="easyui-datagrid" data-options="toolbar:'#tb'">
</table>

<script type="text/javascript">

    $(function () {
        $('#dg_user').datagrid({
            url: '${projectPath}/sys/user/list',
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
                {title: '用户名', field: 'acctName', width: 50, align: 'center'},
                {title: '昵称', field: 'nickName', width: 50, align: 'center'},
                {
                    title: '登录时间', field: 'lastLoginDate', width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        return value != null ? dateFormat(value) : null;
                    }
                },
                {title: '登录IP', field: 'lastLoginIP', width: 50, align: 'center'},
                {title: '登录地址', field: 'lastLoginAddress', width: 50, align: 'center'},
                {title: '登录平台', field: 'lastLoginPlatform', width: 50, align: 'center'},
                {
                    title: '用户状态', field: 'status', width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        return parent.getDicShowValue('STATUS', value);
                    }
                }
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
            href: '${projectPath}/sys/user/save_page',
            id: 'dl_user_add',
            title: '用户添加',
            width: 700,
            height: 400,
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
                        parent.jQuery('#dl_user_add').dialog('close');
                    }
                }],
            onClose: function () {
                reloadDatagrId();
                parent.jQuery(this).dialog('destroy');
            }
        })
    }

    /**
     * 删除
     */
    function del() {
        var row = $('#dg_user').datagrid('getSelected');
        if (row) {
            var id = row.id;
            var version = row.version;
            $.messager.confirm('系统提示', '你确定删除此数据吗(删除后将不可恢复)?', function (r) {
                if (r) {
                    loadTierClose();
                    $.ajax({
                        url: '${projectPath}/sys/user/del',
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
        $('#dg_user').datagrid('reload');
    }
</script>
</body>
</html>