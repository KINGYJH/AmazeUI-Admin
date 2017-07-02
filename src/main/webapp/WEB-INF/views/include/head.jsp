<%--
  Created by IntelliJ IDEA.
  User: King
  Date: 2017/7/2
  Time: 17:49
  头部
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div title="north" region="north" class="app-header" bodyStyle="overflow:hidden;" height="80" showHeader="false"
     showSplit="false">
    <div class="logo">MiniUI 在线示例</div>

    <div class="topNav">
        <a href="../index.html">首页</a> |
        <a href="../demo/index.html">在线示例</a> |
        <a href="../docs/api/index.html">Api手册</a> |
        <a href="../index.html#tutorial">开发教程</a> |
        <a href="../index.html#quickstart">快速入门</a>
    </div>

    <div style="position:absolute;right:12px;bottom:8px;font-size:12px;line-height:25px;font-weight:normal;">
        皮肤：
        <select id="selectSkin" onchange="onSkinChange(this.value)" style="width:100px;margin-right:10px;">
            <option value="">Default</option>
            <option value="blue">Blue</option>
            <option value="pure">Pure</option>
            <option value="gray">Gray</option>
            <option value="olive2003">Olive2003</option>
            <option value="blue2003">Blue2003</option>
            <option value="blue2010">Blue2010</option>
            <option value="bootstrap">Bootstrap</option>
            <option value="metro">metro</option>
            <option value="metro-green">metro-green</option>
            <option value="metro-orange">metro-orange</option>
            <option value="jqueryui-cupertino">jqueryui-cupertino</option>
            <option value="jqueryui-smoothness">jqueryui-smoothness</option>
        </select>
        尺寸：
        <select id="selectMode" onchange="onModeChange(this.value)" style="width:100px;">
            <option value="">Default</option>
            <option value="medium">Medium</option>
            <option value="large">Large</option>
        </select>
    </div>
</div>