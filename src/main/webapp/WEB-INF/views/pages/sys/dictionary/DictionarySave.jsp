<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  数据字典添加
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>数据字典添加</title>
</head>
<body>
<form id="dictionary_save" method="post" data-options="novalidate:true">
    <table class="my_table_form">
        <tr>
            <td class="label">键：</td>
            <td class="inputArea">
                <input name="dataKey" class="easyui-validatebox" required/>
            </td>
            <td class="label">值：</td>
            <td class="inputArea">
                <input name="dataValue" class="easyui-validatebox" required/>
            </td>
        </tr>
        <tr>
            <td class="label">排序：</td>
            <td class="inputArea">
                <input name="sort" value="0" class="easyui-validatebox" validType="Number"/>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    loadScript("${modules_rec}/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#dictionary_save').form('validate')) {
            loadTier();
            $('#dictionary_save').form('submit', {
                url: '${projectPath}/sys/dictionary/save',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = parent.toJSON(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_dictionary_add').dialog('close');
                    }
                }
            })
        }
    }
</script>
</body>
</html>