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
                <input type="text" name="name" class="easyui-validatebox" required
                       validType="length[1,3]"/>
            </td>
            <td class="label">菜单链接：</td>
            <td class="inputArea">
                <input type="text" name="name" class="easyui-validatebox" required
                       validType="NOCHS"/>
            </td>
        </tr>
        <tr>
            <td class="label">菜单名称：</td>
            <td class="inputArea">
                <input type="text" name="name" class="easyui-validatebox" required
                       validType="length[1,3]"/>
            </td>
            <td class="label">菜单链接：</td>
            <td class="inputArea">
                <input type="text" name="name" class="easyui-validatebox" required
                       validType="NOCHS"/>
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
