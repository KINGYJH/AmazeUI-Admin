<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号" %>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域标签名称（name）" %>
<%@ attribute name="checkbox" type="java.lang.Boolean" required="true" description="是否多选" %>
<%@ attribute name="isNull" type="java.lang.Boolean" required="true" description="是否可以为空（isNull）" %>
<%@ attribute name="width" type="java.lang.String" required="false" description="宽度" %>
<%@ attribute name="value" type="java.lang.String" required="false" description="默认值" %>
<input id="${id}_id" name="${name}" value="${value}" type="hidden"/>
<select id="${id}" style="width: ${width}"></select>
<script type="text/javascript">
    $(document).ready(function () {
        $('#${id}').combo({
            editable: false,
            readonly: false,
            required:${isNull}
        })

        $.ajax({
            url: '/admin/sys/role/get_all_data',
            async: false,
            type: 'post',
            success: function (data) {
                var obj = parent.toJSON(data);
                if (obj.status === "SUCCESS") {
                    var _panelHtml = '<div id="sp">' +
                        '<div style="color:#99BBE8;background:#fafafa;padding:5px;text-align: center">选择角色</div>' +
                        '<div style="padding:10px auto">';
                    for (var _index in obj.data) {
                        var item = obj.data[_index];
                        _panelHtml += '<span style="float: left;margin-left: 5px"><input type="checkbox" value="' + item.id + '"><span>' + item.name + '(' + item.describes + ')</span></span>';
                    }
                    _panelHtml += '</div></div>';
                    $(_panelHtml).appendTo($('#${id}').combo('panel'));
                } else {
                    msgShow('加载角色信息出错');
                }
            },
            error: function () {
                msgShow('加载角色信息出错');
            }
        })

        $('#sp').find('input').click(function () {
            var v = $(this).val();
            var s = $(this).next('span').text();

            var _inputValue = $('#${id}_id').val();
            var _comboText = $('#${id}').combo('getText');
            if ($(this)[0].checked) {
                _inputValue += v + ',';
                _comboText += s + ',';
            } else {
                _comboText = _comboText.replace((',' + s), '');
                _comboText = _comboText.replace((s + ','), '');

                _inputValue = _inputValue.replace((',' + v), '');
                _inputValue = _inputValue.replace((v + ','), '');
            }

            $('#${id}').combo('setValue', v).combo('setText', _comboText);
            $('#${id}_id').val(_inputValue);
        });
    })
</script>