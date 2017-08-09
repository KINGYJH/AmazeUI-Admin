<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  用户添加
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>用户添加</title>
</head>
<body>
<form id="user_save" method="post" data-options="novalidate:true">
    <table class="my_table_form">
        <tr>
            <td class="label">用户名：</td>
            <td class="inputArea">
                <input name="acctName" class="easyui-validatebox" required/>
            </td>
            <td class="label">昵称：</td>
            <td class="inputArea">
                <input name="nickName" class="easyui-validatebox"/>
            </td>
        </tr>
        <tr>
            <td class="label">密码：</td>
            <td class="inputArea">
                <input type="password" id="pwd" name="pwd" class="easyui-validatebox" validType="length[6,32]"
                       required/>
            </td>
            <td class="label">密码：</td>
            <td class="inputArea">
                <input type="password" name="confirm_pwd" class="easyui-validatebox" validType="equalToPwd['#pwd']"
                       required/>
            </td>
        </tr>
        <tr>
            <td class="label" valign="top">头像：</td>
            <td class="inputArea">
                <input name="headImg" class="easyui-validatebox"/>
            </td>
            <td class="label">是否启用：</td>
            <td class="inputArea">
                <tags:dic_comboBox id="user_status" name="status" dataKey="STATUS" isNull="true"/>
            </td>
        </tr>
        <tr>
            <td class="label">选择角色：</td>
            <td class="inputArea" colspan="3">
                <tags:role_combo id="user_role" name="roleIds" checkbox="true" width="92.5%" isNull="true"/>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    includeJs("/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#user_save').form('validate')) {
            loadTier();
            $('#user_save').form('submit', {
                url: '${projectPath}/sys/user/save',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = JSON.parse(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_user_add').dialog('close');
                    }
                }
            })
        }
    }

</script>
</body>
</html>