<%--
  Created by IntelliJ IDEA.
  User: King
  Date: 2017/6/30
  Time: 20:05
  引入头部文件
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<meta name="author" content="yjh"/>
<meta http-equiv="X-UA-Compatible" content="IE=edge"/>

<link rel="shortcut icon" href="${img}/favicon.jpg">

<link href="${easy_rec}/themes/default/easyui.css" type="text/css" rel="stylesheet">
<link href="${easy_rec}/themes/icon.css" type="text/css" rel="stylesheet">

<link href="${modules_rec}/override.css" type="text/css" rel="stylesheet">

<!--[if !IE]> -->
<script type="text/javascript" src="${easy_rec}/jquery.min.js"></script>
<!-- <![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="${easy_rec}/jquery.min-1.11.js"></script>
<![endif]-->
<!--[if gt IE 8]>
<script type="text/javascript" src="${easy_rec}/jquery.min.js"></script>
<![endif]-->

<script type="text/javascript" src="${easy_rec}/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${easy_rec}/locale/easyui-lang-zh_CN.js"></script>

<script type="text/javascript">
    var basePath = '${basePath}';
    var projectPath = '${projectPath}';
    var easy_rec = '${easy_rec}';
    var modules_rec = '${modules_rec}';

    //加载js
    function loadScript(url) {
        var script = document.createElement("script");
        script.type = "text/javascript";
        script.src = url;
        document.body.appendChild(script);
    }

    //获取iframe高度
    function getIframeHeight() {
        return parent.$('#tabs').tabs('getSelected').panel('options').height;
    }

    //获取iframe宽度
    function getIframeWidth() {
        return parent.$('#tabs').tabs('getSelected').panel('options').width;
    }

    // 打开加载层
    function loadTier() {
        $.messager.progress({
            title: '请稍后',
            msg: '页面加载中......'
        });
    }

    // 关闭加载层
    function loadTierClose() {
        $.messager.progress('close');
    }

    // 弹出信息窗口 title:标题 msgString:提示信息 msgType:信息类型 [error,info,question,warning]
    function msgShow(title, msgString, msgType) {
        $.messager.alert(title, msgString, msgType);
    }
</script>
