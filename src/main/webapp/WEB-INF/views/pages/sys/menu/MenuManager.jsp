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
<div id="tb" style="padding:2px 5px;">
    <a href="#" onclick="add()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
    <a href="#" onclick="edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
    <a href="#" onclick="del()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
</div>
<table id="dg_tree_menu" class="easyui-treegrid" data-options="toolbar:'#tb'">
</table>
<script type="text/javascript">

    $(function () {
        $('#dg_tree_menu').treegrid({
            url: '${projectPath}/sys/menu/list',
            width: 'auto',
            height: getIframeHeight() * 0.96,
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
                        return typeof(value) === "undefined" ? '' : '<img src="' + basePath + value + '" id="show_icon" style="width: 16px;height: 18px;border: 0px;">';
                    }
                },
                {title: '菜单同级排序', field: 'sort', width: 50, align: 'center'},
                {
                    title: '菜单是否显示', field: 'isShow', width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        return parent.getDictValue(value);
                    }
                }
            ]],
            onLoadError: function (arguments) {
                console.log(arguments);
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
    })

    //添加
    function add() {
        top.jQuery('<div/>').dialog({
            href: '${projectPath}/sys/menu/save_page',
            id: 'dl_menu_add',
            title: '新增菜单',
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
                        parent.jQuery('#dl_menu_add').dialog('close');
                    }
                }],
            onClose: function () {
                reloadTreegrId();
                parent.jQuery(this).dialog('destroy');
            }
        })
    }

    /**
     * 修改
     */
    function edit() {
        var row = $('#dg_tree_menu').treegrid('getSelected');
        if (row) {
            var id = row.id;
            top.jQuery('<div/>').dialog({
                href: '${projectPath}/sys/menu/edit_page?id=' + id,
                id: 'dl_menu_edit',
                title: '修改菜单',
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
                            parent.jQuery('#dl_menu_edit').dialog('close');
                        }
                    }],
                onClose: function () {
                    reloadTreegrId();
                    parent.jQuery(this).dialog('destroy');
                }
            })
        } else {
            msgShow("系统提示", "请选择一条数据", "info");
        }
    }

    //重新加载数据
    function reloadTreegrId() {
        jQuery('#dg_tree_menu').treegrid('options').url = '${projectPath}/sys/menu/list';
        $('#dg_tree_menu').treegrid('reload');
    }
</script>
</body>
</html>
