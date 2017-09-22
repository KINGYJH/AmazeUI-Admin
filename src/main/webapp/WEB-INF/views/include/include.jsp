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

<link id="easyuiTheme" href="${easy_rec}/themes/default/easyui.css" type="text/css" rel="stylesheet">
<link href="${easy_rec}/themes/icon.css" type="text/css" rel="stylesheet">
<link href="${easy_rec}/themes/IconExtension.css" type="text/css" rel="stylesheet">

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

<script type="text/javascript" href="${modules_rec}/common/json2.js"></script>

<script type="text/javascript">
    var basePath = '${basePath}';
    var projectPath = '${projectPath}';
    var rec = '${rec}';
    var easy_rec = '${easy_rec}';
    var modules_rec = '${modules_rec}';

    //加载js
    function loadScript(url) {
        var script = document.createElement('script');
        script.type = 'text/javascript';
        script.src = url;
        document.body.appendChild(script);
    }

    //加载css
    function loadCss(url) {
        var css = document.createElement('link');
        css.href = url;
        css.type = 'text/css';
        css.rel = 'stylesheet';
        document.body.appendChild(css);
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

    /**
     * 引入js/css文件
     * @param name js/css文件名称
     * @returns {boolean}
     */
    function includeJs(name) {
        var js = /js$/i.test(name);
        var es = document.getElementsByTagName(js ? 'script' : 'link');
        var isInclude = true;
        for (var i = 0; i < es.length; i++) {
            if (es[i][js ? 'src' : 'href'].indexOf(name) !== -1) {
                isInclude = false;
                break;
            }
        }
        if (isInclude) {
            js ? loadScript(name) : loadCss(name);
        }
    }

    /**
     * 字符串去重
     * @param str 字符串
     * @param regex 分隔符
     * @param joinStr 连接符
     * @returns {string} 结果
     */
    function duplicateRemoval(str, regex, joinStr) {
        var strArr = str.split(regex);//把字符串分割成一个数组
        strArr.sort();//排序
        var result = new Array();//创建出一个结果数组
        var tempStr = "";
        for (var i in strArr) {
            if (strArr[i] != tempStr) {
                result.push(strArr[i]);
                tempStr = strArr[i];
            }
            else {
                continue;
            }
        }
        return result.join(joinStr);
    }

    Date.prototype.Format = function (fmt) { //author: meizz
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }

    /**
     * Array.remove(dx) 通过遍历,重构数组
     * 删除数组元素.
     * @param dx 删除元素的下标.
     * @returns {boolean}
     */
    Array.prototype.remove = function (dx) {
        if (isNaN(dx) || dx > this.length) {
            return false;
        }
        for (var i = 0, n = 0; i < this.length; i++) {
            if (this[i] != this[dx]) {
                this[n++] = this[i];
            }
        }
        this.length -= 1;
    }

    //cookie
    jQuery.cookie = function (key, value, options) {

        // key and value given, set cookie...
        if (arguments.length > 1 && (value === null || typeof value !== "object")) {
            options = jQuery.extend({}, options);

            if (value === null) {
                options.expires = -1;
            }

            if (typeof options.expires === 'number') {
                var days = options.expires, t = options.expires = new Date();
                t.setDate(t.getDate() + days);
            }

            return (document.cookie = [
                encodeURIComponent(key), '=',
                options.raw ? String(value) : encodeURIComponent(String(value)),
                options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
                options.path ? '; path=' + options.path : '',
                options.domain ? '; domain=' + options.domain : '',
                options.secure ? '; secure' : ''
            ].join(''));
        }

        // key and possibly options given, get cookie...
        options = value || {};
        var result, decode = options.raw ? function (s) {
            return s;
        } : decodeURIComponent;
        return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
    };
</script>
