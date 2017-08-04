<%--
                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| -_- |)
                  O\  =  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
         佛祖保佑       永无BUG

  Created by IntelliJ IDEA.
  User: King
  Date: 2017/6/30
  Time: 20:05
  登录
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
<html>
<head>
    <title>${fns:getConfig('productName')}</title>
</head>
<body style="background-image: url('${img}/login-background.jpg');background-size:100% 100%;">
<div id="w" class="easyui-window" title="登录"
     data-options="modal:true,closed:false,iconCls:'icon-lock',collapsible:false,minimizable:false,maximizable:false,closable:false,resizable:false"
     style="width:400px;padding:20px 70px 20px 70px;">

    <div id="error_msg" style="text-align: center;margin-bottom: 20px;color: red;">
    </div>
    <form id="login-form" method="post">
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="acctName" id="acctName" style="width:100%;height:30px;padding:12px"
                   required
                   data-options="prompt:'登录账号',iconCls:'icon-man',iconWidth:38">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="pwd" id="pwd" type="password"
                   style="width:100%;height:30px;padding:12px" required
                   data-options="prompt:'登录密码',iconCls:'icon-lock',iconWidth:38">
        </div>
        <div style="margin-bottom:20px">
            <input class="easyui-textbox" name="captcha" type="text" id="captcha"
                   style="width:60%;height:30px;padding:12px;"
                   required data-options="prompt:'验证码'">
            <tags:verification_code id="ver_code"/>
        </div>
        <%--<div style="margin-bottom:20px">--%>
        <%--<input type="checkbox" checked="checked" id="logrem">--%>
        <%--<span>记住我</span>--%>
        <%--</div>--%>
        <div>
            <a href="javascript:;" onclick="doLogin()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'"
               style="padding:5px 0px;width:100%;">
                <span style="font-size:14px;">登录</span>
            </a>
        </div>
    </form>
</div>
<script type="text/javascript">

    //判断是否在IFRAME中
    if (self.frameElement && self.frameElement.tagName === "IFRAME") {
        parent.location.reload();
    }

    document.onkeydown = function mykeyDown(e) {
        e = e || event;
        if (e.keyCode === 13) {//回车键的键值为13
            doLogin(); //调用登录按钮的登录事件
        }
    }

    /**
     *登录
     */
    function doLogin() {
        if (jQuery('#login-form').form("validate")) {
            $.messager.progress({
                title: '请稍后',
                msg: '登录中......'
            });
            $('#login-form').form('submit', {
                url: '${basePath}/login',
                onLoadError: function () {
                    $.messager.progress('close');
                    $.messager.alert('系统提示', '系统出现错误请重试', 'error');
                }, success: function (data) {
                    var obj = jQuery.parseJSON(data);
                    if (obj.status === 'SUCCESS') {
                        window.location.href = '/admin';
                    } else {
                        $('#error_msg').text(obj.msg);
                        setTimeout(function () {
                            $('#error_msg').text("");
                        }, 5000);
                        $("#ver_code").trigger("click");
                        $.messager.progress('close');
                    }
                }
            })
        }
    }
</script>
</body>
</html>