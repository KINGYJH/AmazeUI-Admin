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
<c:set var="basePath" value="${pageContext.request.contextPath}"/>
<c:set var="projectPath" value="${basePath}${fns:getAdminPath()}"/>
<c:set var="easy_rec" value="${pageContext.request.contextPath}/resources/insdep-2.0.0"/>
<c:set var="modules_rec" value="${pageContext.request.contextPath}/resources/modules"/>
<c:set var="img" value="${pageContext.request.contextPath}/resources/img"/>
