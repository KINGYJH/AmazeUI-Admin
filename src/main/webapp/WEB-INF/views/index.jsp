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
</head>
<body>
<div class="mini-layout" style="width:100%;height:100%;">
    <!-- 头部 -->
    <%@ include file="include/head.jsp" %>

    <!-- 底部 -->
    <%@ include file="include/foot.jsp" %>

    <!-- 菜单 -->
    <%@ include file="include/menu.jsp" %>

    <!-- 内容 -->
    <div title="center" region="center" style="border:0;">
        <div id="tabs" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false">
            <div title="首页">
                ${user.username}
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    mini.parse();
    function onBeforeExpand(e) {
        var tree = e.sender;
        var nowNode = e.node;
        var level = tree.getLevel(nowNode);

        var root = tree.getRootNode();
        tree.cascadeChild(root, function (node) {
            if (tree.isExpandedNode(node)) {
                var level2 = tree.getLevel(node);
                if (node != nowNode && !tree.isAncestor(node, nowNode) && level == level2) {
                    tree.collapseNode(node, true);
                }
            }
        });
    }
</script>
</body>
</html>

