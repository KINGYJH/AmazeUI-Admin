<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="标签id" %>
<img id="${id}" src="/verificationCode" style="margin:0 0 0 3px;vertical-align:middle;height:26px;width: 35%" data-toggle="tooltip" data-placement="bottom" title="点击切换验证码"/>
<script type="text/javascript">
    $(function () {
        $("#${id}").on('click', function (e) {
            var act = "/verificationCode";
            $(this).attr("src", act + "?" + new Date().getTime());
        }).mouseover(function () {
            $(this).css("cursor", "pointer");
        });
    });
</script>