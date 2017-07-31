<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域标签名称（name）" %>
<%@ attribute name="dataKey" type="java.lang.String" required="true" description="数据字典Key（dataKey）" %>
<%@ attribute name="isNull" type="java.lang.String" required="true" description="是否可以为空（isNull）" %>
<input id="${id}_id" name="${name}" type="hidden"/>
<input id="${id}"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#${id}').combobox({
            url: '${url}',
            valueField: 'dataKey',
            textField: 'dataValue',
            onSelect: function (record) {
                $("#${id}_id").val(record.valueField);
            },
            onLoadSuccess: function () {
                if (${isNull == "true"}) {
                    $('#${id}').combobox('setValue', '请选择');
                }
            }
        });
    });
</script>