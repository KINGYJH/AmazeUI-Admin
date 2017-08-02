<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  菜单修改
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>菜单修改</title>
</head>
<body>
<c:if test="${data == null}">
    <h1>${alertMessage}</h1>
</c:if>
<c:if test="${data != null}">
    <form id="menu_save" method="post" data-options="novalidate:true">
        <input type="hidden" name="id" value="${data.id}"/>
        <input type="hidden" name="version" value="${data.version}"/>
        <table class="my_table_form">
            <tr>
                <td class="label">菜单名称：</td>
                <td class="inputArea">
                    <input name="name" value="${data.name}" class="easyui-validatebox" required/>
                </td>
                <td class="label">权限标识：</td>
                <td class="inputArea">
                    <input name="permission" value="${data.permission}" class="easyui-validatebox" required
                           validType="NOCHS"/>
                </td>
            </tr>
            <tr>
                <td class="label">上级菜单：</td>
                <td class="inputArea">
                    <tags:menu_comboTree id="parent" name="parent.id" checkbox="false" isNull="false"
                                         value="${data.parent.id}"/>
                </td>
                <td class="label">排序：</td>
                <td class="inputArea">
                    <input name="sort" value="${data.sort}" class="easyui-validatebox" required validType="Number"/>
                </td>
            </tr>
            <tr>
                <td class="label">菜单图标：</td>
                <td class="inputArea">
                    <input name="icon" value="${data.icon}" class="easyui-validatebox"/>
                </td>
                <td class="label">是否显示：</td>
                <td class="inputArea">
                    <tags:dic_comboBox id="isShow" name="isShow" dataKey="IS_SHOW" isNull="true"
                                       value="${data.isShow}"/>
                </td>
            </tr>
            <tr>
                <td class="label">菜单链接：</td>
                <td class="inputArea" colspan="2">
                    <input name="href" value="${data.href}" class="easyui-validatebox width-full"/>
                </td>
            </tr>
        </table>
    </form>
</c:if>
<script type="text/javascript">
    includeJs("/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#menu_save').form('validate')) {
            loadTier();
            $('#menu_save').form('submit', {
                url: '${projectPath}/sys/menu/edit',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = toJSON(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_menu_edit').dialog('close');
                    } else {
                        if (obj.msg.indexOf("重新提交")) {
                            parent.jQuery('#dl_menu_edit').dialog('close');
                        }
                    }
                }
            })
        }
    }
</script>
</body>
</html>
