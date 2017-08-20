<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/6/30
  Time: 11:04
  首页
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>首页</title>
    <style type="text/css">
        .panel {
            float: left;
            margin: 5px 5px;
        }
    </style>
</head>
<body>
<div style="width: 100%;height: 100%;">
    <button type="button" onclick="fileUpload()">文件长传</button>
    <c:forEach var="view" items="${data}">
        <div class="easyui-panel home_view" title="${view.title}"
             style="width:${view.width};height:${view.height};background:#fafafa;"
             data-options="closable:true,collapsible:true">
            <div style="padding:5px 5px;">
                <a href="#" onclick="edit('${view.id}')" class="easyui-linkbutton"
                   data-options="iconCls:'icon-edit'">修改</a>
                <a href="#" onclick="del('${view.id}')" class="easyui-linkbutton"
                   data-options="iconCls:'icon-remove'">删除</a>
            </div>
            <iframe src="${view.url}" scrolling="auto" frameborder="0" style="width:100%;height:90%;">
            </iframe>
        </div>
    </c:forEach>
</div>
<script type="text/javascript">
    function fileUpload() {
        top.jQuery('<div/>').dialog({
            href: '${projectPath}/upload_page?type=1',
            id: 'dl_file_upload',
            title: '文件上传',
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