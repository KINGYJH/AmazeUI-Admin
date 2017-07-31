<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/12
  Time: 9:52
  首页视图修改
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>首页视图修改</title>
</head>
<body>
<c:if test="${homeView == null}">
    <h1>${alertMessage}</h1>
</c:if>
<c:if test="${homeView != null}">
    <form id="home_save" method="post" data-options="novalidate:true">
        <input type="hidden" name="id" value="${homeView.id}"/>
        <table class="my_table_form">
            <tr>
                <td class="label">标题：</td>
                <td class="inputArea">
                    <input name="title" value="${homeView.title}" class="easyui-validatebox" required/>
                </td>
                <td class="label">视图内容链接：</td>
                <td class="inputArea">
                    <input name="url" value="${homeView.url}" class="easyui-validatebox" required/>
                </td>
            </tr>
            <tr>
                <td class="label">排序：</td>
                <td class="inputArea">
                    <input name="sort" value="${homeView.sort}" class="easyui-validatebox" required validType="Number"/>
                </td>
                <td class="label">是否显示：</td>
                <td class="inputArea">
                    <input name="isShow" value="${homeView.isShow}" class="easyui-validatebox" required/>
                </td>
            </tr>
            <tr>
                <td class="label">宽度(PX)：</td>
                <td class="inputArea">
                    <input name="width" value="${homeView.width}" class="easyui-validatebox" required
                           validType="Number"/>
                </td>
                <td class="label">高度(PX)：</td>
                <td class="inputArea">
                    <input name="height" value="${homeView.height}" class="easyui-validatebox" required
                           validType="Number"/>
                </td>
            </tr>
        </table>
    </form>
</c:if>
<script type="text/javascript">
    loadScript("${modules_rec}/sys/validatebox-extend.js");

    function submitForm() {
        if (jQuery('#home_save').form('validate')) {
            loadTier();
            $('#home_save').form('submit', {
                url: '${projectPath}/sys/home_view/edit',
                onLoadError: function () {
                    loadTierClose();
                    msgShow('系统提示', "系统出现错误请重试", 'info');
                }, success: function (data) {
                    loadTierClose();
                    var obj = jQuery.parseJSON(data);
                    parent.msgShow('系统提示', obj.msg, 'info');
                    if (obj.status === "SUCCESS") {
                        parent.jQuery('#dl_home_edit').dialog('close');
                    }
                }
            })
        }
    }
</script>
</body>
</html>
