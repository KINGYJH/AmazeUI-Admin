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


<script type="text/javascript" src="${easy_rec}/jquery.min.js"></script>
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
</script>
