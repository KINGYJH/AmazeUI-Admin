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
                <input type="text" name="name" class="easyui-validatebox" required/>
            </td>
            <td class="label">权限标识：</td>
            <td class="inputArea">
                <input type="text" name="parentId" class="easyui-validatebox" required validType="NOCHS"/>
            </td>
        </tr>
        <tr>
            <td class="label">上级菜单：</td>
            <td class="inputArea">
                <input type="text" name="name" class="easyui-validatebox"/>
            </td>
            <td class="label">排序：</td>
            <td class="inputArea">
                <input type="text" name="sort" class="easyui-validatebox" required validType="Number"/>
            </td>
        </tr>
        <tr>
            <td class="label">菜单图标：</td>
            <td class="inputArea">
                <input type="text" name="icon" class="easyui-validatebox"/>
            </td>
            <td class="label">是否显示：</td>
            <td class="inputArea">
                <input type="text" name="isShow" class="easyui-validatebox" required validType="Number"/>
            </td>
        </tr>
        <tr>
            <td class="label">菜单链接：</td>
            <td class="inputArea" colspan="3">
                <input type="text" name="href" class="easyui-validatebox width-full"/>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    loadScript("${modules_rec}/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery("#menu_save").form("validate")) {
            console.log("提交通过");
        } else {
            console.log("提交未通过");
        }
    }
</script>
</body>
</html>
