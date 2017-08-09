<%@ tag language="java" pageEncoding="UTF-8" %>
<%--<%@ include file="/WEB-INF/views/include/taglib.jsp" %>--%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域标签名称（name）" %>
<%@ attribute name="dataKey" type="java.lang.String" required="true" description="数据字典Key（dataKey）" %>
<%@ attribute name="isNull" type="java.lang.Boolean" required="true" description="是否可以为空（isNull）" %>
<%@ attribute name="value" type="java.lang.String" required="false" description="默认值" %>
<input id="${id}_id" name="${name}" value="${value}" type="hidden"/>
<input id="${id}"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#${id}').combobox({
            <%--url: '/admin/sys/dictionary/get_by_dataKey?dataKey=${dataKey}',--%>
            data: getDictList('${dataKey}'),
            valueField: 'id',
            textField: 'dataValue',
            editable: false,
            required: ${isNull},
            onSelect: function (node) {
                $("#${id}_id").val(node.id);
            },
            onLoadSuccess: function (data) {
                if (${value != null}) {
                    var value = '${value}';
                    for (var i = 0; i < data.data.length; i++) {
                        if (value === data.data[i].id) {
                            $('#${id}').combobox('setValue', data.data[i].dataValue);
                        }
                    }
                }
            }
        });
    });
</script>