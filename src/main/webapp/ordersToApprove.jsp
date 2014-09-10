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
<title>Submitted Orders</title>
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
	<c:forEach var="order" items="${orderlist}">
		<table class="simple-little-table" cellspacing='0'>
			<TR>

				<TH colspan="6"><fmt:message key="orderid" bundle="${LOCALE}" />
					= ${order.id}</TH>
			</TR>
			<TR>
				<TH colspan="6"><fmt:message key="client" bundle="${LOCALE}" />:</TH>
			</TR>
			<TR>
				<TH><fmt:message key="id" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="subscriptionid" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="firstname" bundle="${LOCALE}" /></TH>
				<TH colspan="1"><fmt:message key="lastname" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="birthday" bundle="${LOCALE}" /></TH>
			</TR>
			<TR>
				<TD>${order.client.id}</TD>
				<TD>${order.client.subscriptionid}</TD>
				<TD>${order.client.name}</TD>
				<TD>${order.client.surname}</TD>
				<TD>${order.client.birthday}</TD>
			</TR>
			<TR>
				<TH colspan="6"><fmt:message key="books" bundle="${LOCALE}" />:</TH>
			</TR>
			<TR>
				<TH><fmt:message key="id" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="title" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="author" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="year" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="price" bundle="${LOCALE}" /></TH>
			</TR>


			<c:forEach var="book" items="${order.orderItems}">
				<TR>
					<TD>${book.id}</TD>
					<TD>${book.title}</TD>
					<TD>${book.author}</TD>
					<TD>${book.year}</TD>
					<TD>${book.price}</TD>
				</TR>
			</c:forEach>


			<TR>
				<TD colspan="6" align="center">
					<form name="tosub" method="POST" action="Controller">
						<input type="hidden" name="command" value="tosub" /> <input
							type="hidden" name="orderid" value="${order.id}" /> <input
							type="submit"
							value="<fmt:message key="tohome" bundle="${LOCALE}" />"
							align="middle">
					</form>
					<form name="toread" method="POST" action="Controller">
						<input type="hidden" name="command" value="toread" /> <input
							type="hidden" name="orderid" value="${order.id}" /> <input
							type="submit"
							value="<fmt:message key="toreadingroom" bundle="${LOCALE}" />"
							align="middle">
					</form>
				</TD>
			</TR>
		</table>
		<br>
	</c:forEach>
</body>
</html>