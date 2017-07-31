<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  首页视图添加
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>首页视图添加</title>
</head>
<body>
<form id="home_save" method="post" data-options="novalidate:true">
    <table class="my_table_form">
        <tr>
            <td class="label">标题：</td>
            <td class="inputArea">
                <input name="title" class="easyui-validatebox" required/>
            </td>
            <td class="label">视图内容链接：</td>
            <td class="inputArea">
                <input name="url" class="easyui-validatebox" required/>
            </td>
        </tr>
        <tr>
            <td class="label">排序：</td>
            <td class="inputArea">
                <input name="sort" class="easyui-validatebox" required validType="Number"/>
            </td>
            <td class="label">是否显示：</td>
            <td class="inputArea">
                <input name="isShow" class="easyui-validatebox" required/>
            </td>
        </tr>
        <tr>
            <td class="label">宽度(PX)：</td>
            <td class="inputArea">
                <input name="width" class="easyui-validatebox" required validType="Number"/>
            </td>
            <td class="label">高度(PX)：</td>
            <td class="inputArea">
                <input name="height" class="easyui-validatebox" required validType="Number"/>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    loadScript("${modules_rec}/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#home_save').form('validate')) {
            loadTier();
            $('#home_save').form('submit', {
                url: '${projectPath}/sys/home_view/save',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = jQuery.parseJSON(data);
                    msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_home_add').dialog('close');
                    }
                }
            })
        }
    }
</script>
</body>
</html>
