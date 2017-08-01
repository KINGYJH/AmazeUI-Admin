<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域标签名称（name）" %>
<%@ attribute name="checkbox" type="java.lang.Boolean" required="true" description="是否多选" %>
<%@ attribute name="isNull" type="java.lang.Boolean" required="true" description="是否可以为空（isNull）" %>
<%@ attribute name="width" type="java.lang.String" required="false" description="宽度" %>
<%@ attribute name="value" type="java.lang.String" required="false" description="默认值" %>
<input id="${id}_id" name="${name}" value="${value}" type="hidden"/>
<input id="${id}" style="width: ${width}"/>
<script type="text/javascript">
    $(document).ready(function () {
        $('#${id}').combotree({
            url: '/admin/sys/menu/get_all_treeData',
            value: '请选择',
            editable: false,
            required: ${isNull},
            checkbox:${checkbox},
            onSelect: function (node) {
                $("#${id}_id").val(node.id);
            },
            onLoadSuccess: function () {
                if (${value != null}) {
                    var tree = $('#${id}').combotree('tree');
                    var node = $(tree).tree('find', "${value}");
                    if (null !== node) {
                        $(tree).tree('select', node.target);
                        $('#${id}').combotree('setValue', node.text);
                    }
                }
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