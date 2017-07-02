<%--
  Created by IntelliJ IDEA.
  User: King
  Date: 2017/6/30
  Time: 20:06
  标签库的导入，以及全局的配置
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fns" uri="/WEB-INF/views/tlds/fns.tld" %>
<c:set var="basepath" value="${pageContext.request.contextPath}"/>
<c:set var="ctx" value="${basepath}${fns:getAdminPath()}"/>
<c:set var="mini_res" value="${pageContext.request.contextPath}/resources/scripts"/>
