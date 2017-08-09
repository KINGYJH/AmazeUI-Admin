<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  数据字典修改
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>数据字典修改</title>
</head>
<body>
<c:if test="${data == null}">
    <h1>${alertMessage}</h1>
</c:if>
<c:if test="${data != null}">
    <form id="dictionary_edit" method="post" data-options="novalidate:true">
        <input name="id" type="hidden" value="${data.id}">
        <input name="version" type="hidden" value="${data.version}"/>
        <table class="my_table_form">
            <tr>
                <td class="label">键：</td>
                <td class="inputArea">
                    <input name="dataKey" readonly value="${data.dataKey}" class="easyui-validatebox" required/>
                </td>
                <td class="label">值：</td>
                <td class="inputArea">
                    <input name="dataValue" value="${data.dataValue}" class="easyui-validatebox" required/>
                </td>
            </tr>
            <tr>
                <td class="label">排序：</td>
                <td class="inputArea">
                    <input name="sort" value="${data.sort}" class="easyui-validatebox" validType="Number"/>
                </td>
                <td class="label">描述：</td>
                <td class="inputArea">
                    <input name="describes" value="${data.describes}" class="easyui-validatebox"/>
                </td>
            </tr>
        </table>
    </form>
</c:if>
<script type="text/javascript">
    includeJs("/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#dictionary_edit').form('validate')) {
            loadTier();
            $('#dictionary_edit').form('submit', {
                url: '${projectPath}/sys/dictionary/edit',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = JSON.parse(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        initDic();
                        parent.jQuery('#dl_dictionary_edit').dialog('close');
                    } else {
                        if (obj.msg.indexOf("重新提交")) {
                            parent.jQuery('#dl_dictionary_edit').dialog('close');
                        }
                    }
                }
            })
        }
    }
</script>
</body>
</html>