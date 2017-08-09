<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <!--解决 IE6 背景缓存-->
    <!--[if IE 6]>
    <script type="text/javascript">document.execCommand("BackgroundImageCache", false, true);</script>
    <![endif]-->
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>权限不足</title>
</head>
<body>
<script type="text/javascript">
    var css = document.createElement('link');
    css.href = '${modules_rec}/error/page.css';
    css.type = 'text/css';
    css.rel = 'stylesheet';
    document.body.appendChild(css);
</script>
<div id="wrapper">
    <a href="#"><img class="fade" src="${img}/denied.png"></a>
    <div>
        <h1 class="fade">温馨提示：权限不足！</h1>
    </div>
</div>
</body>
</html>