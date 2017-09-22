<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/6/30
  Time: 11:04
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')}</title>
    <script src="${modules_rec}/sys/index.js"></script>
    <script src="${modules_rec}/sys/webSocket.js"></script>
    <script src="${rec}/sockjs/sockjs.min.js"></script>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
<!--上-->
<div data-options="region:'north',border:false" style="overflow: hidden;height: 10%" class="header">
    <div class="main-header" data-options="region:'north',border:false,split:true">
        <div class="main-header-left">
            <h1>King丶Yang</h1>
        </div>
        <div class="main-header-right">
            <p>
                <strong class="easyui-tooltip" title="2条未读消息">admin</strong>，欢迎您！
            </p>
            <p>
                <span>
                    更换主题 :
                    <select id="theme-change" style="width: 90px;height: 20px">
                        <option value="default">default</option>
                        <option value="black">black</option>
                        <option value="bootstrap">bootstrap</option>
                        <option value="gray">gray</option>
                        <option value="material">material</option>
                        <option value="metro">metro</option>
                    </select>
                </span>
                |  <a href="/logout">安全退出</a>
            </p>
        </div>
    </div>
</div>
<!-- 左 -->
<div data-options="region:'west',split:'true',title:'菜单'" style="width:200px;" id="west">
    <div class="easyui-accordion" data-options="fit:'true',border:false">
        <!--菜单导航内容 -->
        <ul id="menu_tree_id" class="easyui-tree">
        </ul>
    </div>
</div>
<!-- 中 -->
<div id="mainPanel" data-options="region:'center'" style="overflow-y:hidden">
    <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
    </div>
</div>
<!--下-->
<div data-options="region:'south'" style="height: 3%;text-align: center">
    <div class="footer">版权所有：${fns:getConfig('productName')} 技术支持：XXXXXXX 版本：${fns:getConfig('productVersion')}</div>
</div>
<script type="text/javascript">

</script>
</body>
</html>

