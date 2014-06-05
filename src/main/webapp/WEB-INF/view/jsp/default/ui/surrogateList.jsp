<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:directive.include file="includes/top.jsp" />
<h2>Choose one of your accounts:</h2>
<form:form method="post" id="fm1" commandName="${commandName}" htmlEscape="true">
    <select name="surrogateTarget" id="surrogateTarget">
        <c:forEach items="${referenceData.surrogates}" var="i">
            <option value="${i.key}">${i.value}</option>
        </c:forEach>
    </select>
    <div class="row btn-row">
        <input type="hidden" name="lt" value="${loginTicket}" />
        <input type="hidden" name="execution" value="${flowExecutionKey}" />
        <input type="hidden" name="_eventId" value="submit" />

        <input class="btn-submit" name="submit" accesskey="l" value="<spring:message code="screen.welcome.button.login" />" tabindex="4" type="submit" />
    </div>
</form:form>
<jsp:directive.include file="includes/bottom.jsp" />