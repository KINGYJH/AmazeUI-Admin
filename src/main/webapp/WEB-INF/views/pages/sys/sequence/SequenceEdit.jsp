<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  序列修改
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>序列修改</title>
</head>
<body>
<c:if test="${data == null}">
    <h1>${alertMessage}</h1>
</c:if>
<c:if test="${data != null}">
    <form id="sequence_edit" method="post" data-options="novalidate:true">
        <input type="hidden" name="id" value="${data.id}"/>
        <input type="hidden" name="version" value="${data.version}"/>
        <table class="my_table_form">
            <tr>
                <td class="label">表名：</td>
                <td class="inputArea">
                    <input name="tableName" value="${data.tableName}" class="easyui-validatebox" required/>
                </td>
                <td class="label">长度：</td>
                <td class="inputArea">
                    <input name="length" value="${data.length}" class="easyui-validatebox" required validType="Number"/>
                </td>
            </tr>
            <tr>
                <td class="label">主键名称：</td>
                <td class="inputArea">
                    <input name="pkName" value="${data.pkName}" class="easyui-validatebox" required/>
                </td>
                <td class="label">前缀：</td>
                <td class="inputArea">
                    <input name="prefix" value="${data.prefix}" class="easyui-validatebox" required/>
                </td>
            </tr>
            <tr>
                <td class="label">描述：</td>
                <td class="inputArea" colspan="3">
                    <input name="describes" value="${data.describes}" class="easyui-validatebox width-full"/>
                </td>
            </tr>
        </table>
    </form>
</c:if>
<script type="text/javascript">
    includeJs("${modules_rec}/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#sequence_save').form('validate')) {
            loadTier();
            $('#sequence_edit').form('submit', {
                url: '${projectPath}/sys/sequence/edit',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = JSON.parse(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_sequence_edit').dialog('close');
                    } else {
                        if (obj.msg.indexOf("重新提交")) {
                            parent.jQuery('#dl_sequence_edit').dialog('close');
                        }
                    }
                }
            })
        }
    }
</script>
</body>
</html>