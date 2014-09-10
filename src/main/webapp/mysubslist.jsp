<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tlds/mytag.tld" prefix="my"%>
<c:if test="${lang == null }">
	<c:set var="lang" scope="session" value="en_US" />
</c:if>
<fmt:requestEncoding value="UTF-8" />
<fmt:setLocale value="${lang}" />
<fmt:setBundle basename="MyBundle" var="LOCALE" />
<c:choose>
	<c:when test="${lang == 'en_US'}">
		<c:set var="lang2" scope="session" value="ru_RU" />
	</c:when>
	<c:otherwise>
		<c:set var="lang2" scope="session" value="en_US" />
	</c:otherwise>
</c:choose>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/style.css" type="text/css" />
<title><fmt:message key="submittedorders" bundle="${LOCALE}" /></title>
</head>
<body>
	<table class="simple-little-table" align="center" border="">
		<tr>
			<td colspan="4"><my:useronline /></td>
		</tr>
		<tr>

			<td><jsp:include page="books.jsp" flush="true"></jsp:include></td>
			<td><c:if test="${user.login == 'root' }"><jsp:include
						page="orders.jsp" flush="true"></jsp:include></c:if></td>
			<td><jsp:include page="mysubs.jsp" flush="true"></jsp:include></td>
			<td><jsp:include page="logout.jsp" flush="true"></jsp:include></td>

		</tr>
	</table>

	<br />
	<br />
	<table class="simple-little-table" cellspacing='0'>


		<TR>

			<TH colspan="5"><fmt:message key="subscriptionid"
					bundle="${LOCALE}" /> = ${client.subscriptionid}</TH>
		</TR>
		<TR>
			<TH colspan="5"><fmt:message key="client" bundle="${LOCALE}" />:</TH>
		</TR>
		<TR>
			<TH><fmt:message key="id" bundle="${LOCALE}" /></TH>
			<TH><fmt:message key="subscriptionid" bundle="${LOCALE}" /></TH>
			<TH><fmt:message key="firstname" bundle="${LOCALE}" /></TH>
			<TH colspan="1"><fmt:message key="lastname" bundle="${LOCALE}" /></TH>
			<TH><fmt:message key="birthday" bundle="${LOCALE}" /></TH>
		</TR>

		<TR>
			<TD>${client.id}</TD>
			<TD>${client.subscriptionid}</TD>
			<TD>${client.name}</TD>
			<TD colspan="1">${client.surname}</TD>
			<TD>${client.birthday}</TD>
		</TR>
		<TR>
			<TH colspan="5"><fmt:message key="items" bundle="${LOCALE}" />:</TH>
		</TR>
		<TR>
			<TH colspan="2"><fmt:message key="bookid" bundle="${LOCALE}" /></TH>
			<TH colspan="2"><fmt:message key="location" bundle="${LOCALE}" /></TH>
			<TH><fmt:message key="actions" bundle="${LOCALE}" /></TH>
		</TR>
		<c:forEach var="item" items="${SubItems}">
			<TR>
				<TD colspan="2">${item.bookid}</TD>
				<TD colspan="2">${item.type}</TD>
				<TD><form name="return" method="POST" action="Controller">
						<input type="hidden" name="command" value="return" /> <input
							type="submit"
							value="<fmt:message key="return" bundle="${LOCALE}" />">

						<input type="hidden" name="id" value="${item.id}" />
					</form></TD>
			</TR>
		</c:forEach>

	</table>
	<br>

</body>
</html>