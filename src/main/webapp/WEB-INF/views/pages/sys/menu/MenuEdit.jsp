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
                    <img src="${data.icon}" id="show_icon"
                         style="width: 16px;height: 18px;border: 0px;background: url(${easy_rec}/themes/default/images/tree_icons.png) no-repeat -224px 0;">
                    <a href="#" class="easyui-linkbutton" iconCls="icon-disk_upload"
                       style="margin-top: -10px;margin-left: 10px;" onclick="fileUpload()">上传</a>
                    <input type="hidden" name="icon" value="${data.icon}" class="easyui-validatebox"/>
                </td>
                <td class="label">是否显示：</td>
                <td class="inputArea">
                    <select id="isShow" class="easyui-combobox" name="isShow" required>
                        <option value="YES">显示</option>
                        <option value="NO">不显示</option>
                    </select>
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
    includeJs("${modules_rec}/sys/validatebox-extend.js");

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
                    var obj = JSON.parse(data);
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

    function fileUpload() {
        top.jQuery('<div/>').dialog({
            href: '${projectPath}/upload_page?type=1',
            id: 'dl_file_upload',
            title: '图标上传',
            width: 700,
            height: 400,
            modal: true,
            shadow: false,
            resizable: true,
            buttons: [
                {
                    text: '保存',
                    iconCls: "icon-ok",
                    handler: function () {
                        var _array = parent.getFileArray();
                        if (_array.length > 0) {
                            var _url = escape2Html(_array[0].url);
                            $('#show_icon').attr('src', basePath + _url);
                            $('#menu_save').find("input[name='icon']").val(_url);
                        }

                        parent.jQuery('#dl_file_upload').dialog('close');
                    }
                },
                {
                    text: '取消',
                    iconCls: "icon-cancel",
                    handler: function () {
                        parent.jQuery('#dl_file_upload').dialog('close');
                    }
                }],
            onClose: function () {
                parent.jQuery(this).dialog('destroy');
            }
        })
    }
</script>
</body>
</html>
