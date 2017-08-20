<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  角色添加
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>角色添加</title>
</head>
<body>
<form id="role_save" method="post" data-options="novalidate:true">
    <table class="my_table_form">
        <tr>
            <td class="label">名称：</td>
            <td class="inputArea">
                <input name="name" class="easyui-validatebox" required/>
            </td>
            <td class="label">描述：</td>
            <td class="inputArea">
                <input name="describes" class="easyui-validatebox"/>
            </td>
        </tr>
        <tr>
            <td class="label">排序：</td>
            <td class="inputArea">
                <input name="sort" value="0" class="easyui-validatebox" validType="Number"/>
            </td>
        </tr>
        <tr>
            <td class="label" valign="top">权限菜单选择：</td>
            <td class="inputArea">
                <input type="hidden" id="permission_ids" name="permissionIds"/>
                <%--<a class="easyui-linkbutton" id="check_btn">全选</a>--%>
                <div style="float:left;margin-top:10px;margin-left: 15px;">
                    <ul id="menu_choose_tree" class="easyui-tree"></ul>
                </div>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    includeJs("${modules_rec}/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#role_save').form('validate')) {
            loadTier();
            $('#role_save').form('submit', {
                url: '${projectPath}/sys/role/save',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = JSON.parse(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_role_add').dialog('close');
                    }
                }
            })
        }
    }

    $(function () {
        $('#menu_choose_tree').tree({
            url: '${projectPath}/sys/menu/get_all_tree_data',
            lines: true,
            checkbox: true,
            cascadeCheck: false,
            onLoadSuccess: function (node, data) {
                $('#menu_choose_tree').tree('expandAll');
            },
            onCheck: function (node, checked) {
                if (checked == true) {
                    checkedParent(node);
                } else {
                    clearChild(node);
                }
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

    function checkedParent(node) {
        if ($('#menu_choose_tree').tree('getParent', node.target) !== null) {
            $('#menu_choose_tree').tree('check', $('#menu_choose_tree').tree('getParent', node.target).target);
            checkedParent($('#menu_choose_tree').tree('getParent', node.target));
        }
    }

    $('#check_btn').click(function () {
        console.log($('#menu_choose_tree').tree('getSelected'));
        if ($('#menu_choose_tree').tree('getSelected') !== null) {
            clearAll();
            $(this).text('全选');
        } else {
            checkAll();
            $(this).text('清空');
        }
    })

    function checkAll() {
        var _nodes = $('#menu_choose_tree').tree('getRoots');
        for (var _index in _nodes) {
            checkChild(_nodes[_index]);
        }
    }

    function clearAll() {
        var _nodes = $('#menu_choose_tree').tree('getRoots');
        for (var _index in _nodes) {
            clearChild(_nodes[_index]);
        }
    }

    function checkChild(node) {
        var childs = $('#menu_choose_tree').tree('getChildren', node.target);
        for (var _index in childs) {
            $('#menu_choose_tree').tree('check', childs[_index].target);
            clearChild(childs[_index]);
        }
    }

    function clearChild(node) {
        var childs = $('#menu_choose_tree').tree('getChildren', node.target);
        for (var _index in childs) {
            $('#menu_choose_tree').tree('uncheck', childs[_index].target);
            clearChild(childs[_index]);
        }
    }

</script>
</body>
</html>