<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<c:set var="ctxStatic" value="${pageContext.request.contextPath}/static"/>



<script src="${ctxStatic}/js/jquery-1.11.3.min.js"></script>
<script src="${ctxStatic}/js/public.js"></script>

<script type="text/javascript">var ctxPath = '${ctxPath}', ctxStatic='${ctxStatic}';</script>