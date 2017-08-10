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
            <input class="easyui-filebox" name="files" data-options="prompt:'Choose a file...'" style="width:90%">
            <a href="#" onclick="upload()" class="easyui-linkbutton" iconCls="icon-add" plain="true">上传</a>
        </div>
    </form>
    <div id="fileList">

    </div>
</c:if>
<script type="text/javascript">
    includeJs("${modules_rec}/sys/css/file-upload.css");

    var fileResult = new Array();

    function upload() {
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
                        var _file = '<div class="fileName-div">' + result.name + '<span class="del_file" delUrl=' + result.deleteUrl + '>X</span></div>';
                        $('#fileList').append(_file);
                        fileResult.push(result);
                    }
                }
                msgShow('系统提示', alertMes, 'info');
                console.log(fileResult);
            }
        })
    }
</script>
</body>
</html>