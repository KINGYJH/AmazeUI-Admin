<%--
  Created by IntelliJ IDEA.
  User: YJH
  Date: 2017/7/10
  Time: 15:46
  文件上传
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/include.jsp" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<c:if test="${alertMessage != null}">
    <h1>${alertMessage}</h1>
</c:if>
<c:if test="${alertMessage == null}">
    <form id="file_form" method="post" enctype="multipart/form-data"
          data-options="novalidate:true">
        <input type="hidden" name="type" value="${type}"/>
        <div style="margin:20px">
            <input class="easyui-filebox" id="input_file" name="files" data-options="prompt:'Choose a file...'"
                   style="width:90%">
            <a href="#" onclick="upload()" class="easyui-linkbutton" iconCls="icon-add" plain="true">上传</a>
        </div>
    </form>
    <div id="fileList">

    </div>
</c:if>
<script type="text/javascript">
    includeJs("${modules_rec}/sys/css/file-upload.css");

    includeJs("${modules_rec}/sys/htmlEncodes.js");

    var fileResult = new Array();


    $(function () {
        $('#fileList').on('click', '.del_file', function () {
            var _this = $(this);
            var _delUrl = $(_this).attr('delUrl');
            $.ajax({
                url: '${projectPath}' + _delUrl,
                async: false,
                success: function (data) {
                    var _obj = JSON.parse(data);
                    if (_obj.status === "SUCCESS") {
                        $(_this).parent().remove();
                        delArray(_delUrl);
                    }
                    msgShow('系统提示', _obj.msg, 'info');

                    console.log(fileResult);
                }
            })
        })
    })

    $("#fileList").find('.del_file').each(function (n) {
        $(this).click(function () {
            var _this = $(this);
            var _delUrl = $(_this).attr('delUrl');
            $.ajax({
                url: '${projectPath}' + _delUrl,
                async: false,
                success: function (data) {
                    var _obj = JSON.parse(data);
                    if (_obj.status === "SUCCESS") {
                        $(_this).parent().remove();
                        delArray(_delUrl);
                    }
                    msgShow('系统提示', _obj.msg, 'info');

                    console.log(fileResult);
                }
            })
        });
    });

    function upload() {
        if ($('#input_file').filebox("getValue") === '') {
            msgShow('系统提示', '请选择文件', 'info');
            return;
        }
        loadTier();
        $("#file_form").form('submit', {
            url: '${projectPath}/upload',
            onLoadError: function () {
                loadTierClose();
                parent.msgShow('系统提示', "上传失败", 'error');
            },
            success: function (data) {
                loadTierClose();
                var obj = JSON.parse(data);

                var alertMes = '';
                for (var _index in obj.files) {
                    var result = obj.files[_index];
                    if (result.error !== undefined) {
                        alertMes += '文件[' + result.name + ']上传失败,' + result.error + '\n';
                    } else {
                        alertMes += '文件[' + result.name + ']上传成功!\n';
                        var _file = '<div class="fileName-div">' + result.name + '<span class="del_file" delUrl=' + html2Escape(result.deleteUrl) + '>X</span></div>';
                        $('#fileList').append(_file);
                        fileResult.push(result);

                        $('#input_file').filebox('clear');

                    }
                }
                msgShow('系统提示', alertMes, 'info');
            }
        })
    }

    /**
     * 删除数组元素
     * @param delUrl
     */
    function delArray(delUrl) {
        for (var i = 0; i < fileResult.length; i++) {
            if (fileResult[i].deleteUrl === delUrl) {
                fileResult.remove(i);
                break;
            }
        }
    }

    /**
     * 获取数组
     * @returns {Array}
     */
    function getFileArray() {
        return fileResult;
    }
</script>
</body>
</html>