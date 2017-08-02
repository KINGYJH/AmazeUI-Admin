<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  角色修改
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>角色修改</title>
</head>
<body>
<form id="role_save" method="post" data-options="novalidate:true">
    <input type="hidden" name="id" value="${data.id}">
    <input type="hidden" name="version" value="${data.version}"/>
    <table class="my_table_form">
        <tr>
            <td class="label">名称：</td>
            <td class="inputArea">
                <input name="name" value="${data.name}" class="easyui-validatebox" required/>
            </td>
            <td class="label">描述：</td>
            <td class="inputArea">
                <input name="describes" value="${data.describes}" class="easyui-validatebox"/>
            </td>
        </tr>
        <tr>
            <td class="label">排序：</td>
            <td class="inputArea">
                <input name="sort" value="${data.sort}" class="easyui-validatebox" validType="Number"/>
            </td>
        </tr>
        <tr>
            <td class="label" valign="top">权限菜单选择：</td>
            <td class="inputArea">
                <input type="hidden" id="permission_ids" value="${data.permissionIds}" name="permissionIds"/>
                <div style="float:left;margin-top:10px;margin-left: 15px;">
                    <ul id="menu_choose_tree" class="easyui-tree"></ul>
                </div>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    includeJs("/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#role_save').form('validate')) {
            loadTier();
            $('#role_save').form('submit', {
                url: '${projectPath}/sys/role/edit',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = parent.toJSON(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_role_edit').dialog('close');
                    }
                }
            })
        }
    }

    $(function () {
        $('#menu_choose_tree').tree({
            url: '${projectPath}/sys/menu/get_all_treeData',
            lines: true,
            checkbox: true,
            cascadeCheck: true,
            onLoadSuccess: function (node, data) {
                $('#menu_choose_tree').tree('expandAll');
                var _menuIds = $('#permission_ids').val().split(",");
                for (var _index in _menuIds) {
                    var _checkNode = $('#menu_choose_tree').tree('find', _menuIds[_index]);
                    $('#menu_choose_tree').tree('check', _checkNode.target);
                }
            },
            onCheck: function (node, checked) {
                var _checked = $('#menu_choose_tree').tree('getChecked');
                var _menuIds = "";
                for (var _index in _checked) {
                    _menuIds += _checked[_index].id + ",";
                }
                _menuIds = $.trim(_menuIds).substring(0, _menuIds.length - 1);
                $('#permission_ids').val(_menuIds);
            },
            loadFilter: function (data) {
                if (data.status === "SUCCESS") {
                    return data.data;
                } else {
                    return [];
                }
            }
        })
    })
</script>
</body>
</html>