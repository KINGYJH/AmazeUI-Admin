<%@ tag language="java" pageEncoding="UTF-8" %>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp" %>--%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域标签名称（name）" %>
<%@ attribute name="dataKey" type="java.lang.String" required="true" description="数据字典Key（dataKey）" %>
<%@ attribute name="isNull" type="java.lang.Boolean" required="true" description="是否可以为空（isNull）" %>
<input id="${id}_id" name="${name}" type="hidden"/>
<input id="${id}"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#${id}').combobox({
            url: '/admin/sys/dictionary/get_by_dataKey?dataKey=${dataKey}',
            valueField: 'id',
            textField: 'dataValue',
            editable: false,
            required: ${isNull},
            onSelect: function (node) {
                $("#${id}_id").val(node.valueField);
            },
            onLoadSuccess: function () {
            },
            loadFilter: function (data) {
                if (data.status === "SUCCESS") {
                    return data.data;
                } else {
                    return [];
                }
            }
        });
    });
</script>