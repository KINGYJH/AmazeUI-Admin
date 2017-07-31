<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  序列添加
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>序列添加</title>
</head>
<body>
<form id="sequence_save" method="post" data-options="novalidate:true">
    <table class="my_table_form">
        <tr>
            <td class="label">表名：</td>
            <td class="inputArea">
                <input name="tableName" class="easyui-validatebox" required/>
            </td>
            <td class="label">长度：</td>
            <td class="inputArea">
                <input name="length" class="easyui-validatebox" required validType="Number"/>
            </td>
        </tr>
        <tr>
            <td class="label">主键名称：</td>
            <td class="inputArea">
                <input name="pkName" class="easyui-validatebox" required/>
            </td>
            <td class="label">前缀：</td>
            <td class="inputArea">
                <input name="prefix" class="easyui-validatebox" required/>
            </td>
        </tr>
        <tr>
            <td class="label">描述：</td>
            <td class="inputArea" colspan="3">
                <input name="describes" class="easyui-validatebox width-full"/>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    loadScript("${modules_rec}/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#sequence_save').form('validate')) {
            loadTier();
            $('#sequence_save').form('submit', {
                url: '${projectPath}/sys/sequence/save',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = toJSON(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_sequence_add').dialog('close');
                    }
                }
            })
        }
    }
</script>
</body>
</html>