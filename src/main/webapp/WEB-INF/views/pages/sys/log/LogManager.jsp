<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/10
  Time: 15:46
  日志查看页面
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>日志查看</title>
</head>
<body>
<div id="tb" style="padding:2px 5px;">
</div>
<table id="dg_log" data-options="toolbar:'#tb'">
</table>
<script type="text/javascript">
    $(function () {
        $('#dg_log').datagrid({
            url: '${projectPath}/sys/log/list',
            width: 'auto',
            height: getIframeHeight() * 0.96,
            pageSize: 100,
            pageList:[100,500,1000],
            fitColumns: true,
            striped: true,
            singleSelect: true,
            pagination: true,// 分页控件
            rownumbers: true,// 行号
            loadMsg: "正在努力加载中...",
            idField: 'id',
            columns: [[
                {
                    title: '操作时间', field: 'createDate', width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        return value != null ? dateFormat(value) : null;
                    }
                },
                {title: '操作人员', field: 'createUserName', width: 50, align: 'center'},
                {title: '操作模块', field: 'operationModular', width: 50, align: 'center'},
                {
                    title: '操作参数', field: 'operationParameter', width: 50, align: 'center',
                    formatter: function (value) {
                        return "<span onmouseover=textTooltip(this)>" + "<a href='#'>" + value + "</a>" + "</span>";
                    }
                },
                {
                    title: '操作类型', field: 'operationType', width: 50, align: 'center',
                    formatter: function (value, row, index) {
                        return parent.getDicShowValue("OPERATION_TYPE", value);
                    }
                }
            ]],
            onLoadSuccess: function (data) {
                console.log(data);
            }
        })
    })
</script>
</body>
</html>