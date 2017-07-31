<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/10
  Time: 15:46
  首页视图管理
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>首页视图管理</title>
    <style type="text/css">
        .panel {
            float: left;
            margin: 5px 5px;
        }
    </style>
</head>
<body>
<div style="padding:5px 0;">
    <a href="#" onclick="add()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
</div>
<c:if test="${fn:length(data) > 0}">
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
</c:if>
<script type="text/javascript">

    //添加
    function add() {
        top.jQuery('<div/>').dialog({
            href: '${projectPath}/sys/home_view/save_page',
            id: 'dl_home_add',
            title: '新增首页视图',
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
                        parent.submitForm();
                    }
                },
                {
                    text: '取消',
                    iconCls: "icon-cancel",
                    handler: function () {
                        parent.jQuery('#dl_home_add').dialog('close');
                    }
                }],
            onClose: function () {
                reload();
                parent.jQuery(this).dialog('destroy');
            }
        })
    }

    //修改
    function edit(id) {
        top.jQuery('<div/>').dialog({
            href: '${projectPath}/sys/home_view/edit_page?id=' + id,
            id: 'dl_home_add',
            title: '修改首页视图',
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
                        parent.submitForm();
                    }
                },
                {
                    text: '取消',
                    iconCls: "icon-cancel",
                    handler: function () {
                        parent.jQuery('#dl_home_add').dialog('close');
                    }
                }],
            onClose: function () {
                reload();
                parent.jQuery(this).dialog('destroy');
            }
        })
    }

    //删除
    function del(id) {
        jQuery.messager.confirm('系统提示', '确定要删除当前首页视图吗？', function (data) {
            if (data) {
                loadTier();
                $.ajax({
                    url: '${projectPath}/sys/home_view/del',
                    type: 'POST',
                    dataType:'json',
                    data: {id:id},
                    success: function (data) {
                        console.log(data);
                        loadTierClose();
                        msgShow('系统提示', data.msg, 'info');
                        reload();
                    },
                    error: function () {
                        loadTierClose();
                        msgShow('系统提示', "系统错误", 'error');
                    }
                })
            }
        });
    }

    function reload() {
        window.location.reload();
    }
</script>
</body>
</html>
