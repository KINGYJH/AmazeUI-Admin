<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  菜单添加
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>菜单添加</title>
</head>
<body>
<form id="menu_save" method="post" data-options="novalidate:true">
    <table class="my_table_form">
        <tr>
            <td class="label">菜单名称：</td>
            <td class="inputArea">
                <input name="name" class="easyui-validatebox" required/>
            </td>
            <td class="label">权限标识：</td>
            <td class="inputArea">
                <input name="permission" class="easyui-validatebox" required validType="NOCHS"/>
            </td>
        </tr>
        <tr>
            <td class="label">上级菜单：</td>
            <td class="inputArea">
                <tags:menu_comboTree id="parent" name="parent.id" checkbox="false" isNull="false"/>
            </td>
            <td class="label">排序：</td>
            <td class="inputArea">
                <input name="sort" class="easyui-validatebox" required validType="Number"/>
            </td>
        </tr>
        <tr>
            <td class="label">菜单图标：</td>
            <td class="inputArea">
                <input name="icon" class="easyui-validatebox"/>
            </td>
            <td class="label">是否显示：</td>
            <td class="inputArea">
                <tags:dic_comboBox id="isShow" name="isShow" dataKey="IS_SHOW" isNull="true"/>
            </td>
        </tr>
        <tr>
            <td class="label">菜单链接：</td>
            <td class="inputArea" colspan="2">
                <input name="href" class="easyui-validatebox width-full"/>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    includeJs("/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#menu_save').form('validate')) {
            loadTier();
            $('#menu_save').form('submit', {
                url: '${projectPath}/sys/menu/save',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = toJSON(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_menu_add').dialog('close');
                    }
                }
            })
        }
    }
</script>
</body>
</html>
