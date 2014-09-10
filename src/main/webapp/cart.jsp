<%@page import="com.epam.library.entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="submit" method="POST" action="Controller">
		<input type="hidden" name="command" value="submit" />
		<table class="simple-little-table" cellspacing='0'>
		
			<TR>
				<TH colspan="6"><fmt:message key="cart" bundle="${LOCALE}" /><c:if test="${order.id != null}"> (<fmt:message key="orderid" bundle="${LOCALE}" /> = ${order.id})</c:if></TH>
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
				<TD>${client.id}</TD>
				<TD>${client.subscriptionid}</TD>
				<TD>${client.name}</TD>
				<TD colspan="1">${client.surname}</TD>
				<TD>${client.birthday}</TD>
			</TR>
			<TR>
				<TH colspan="6"><fmt:message key="books" bundle="${LOCALE}" />:</TH>
			</TR>
			<TR>
				<TH><fmt:message key="id" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="title" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="author" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="year" bundle="${LOCALE}" /></TH>
				<TH colspan="1"><fmt:message key="price" bundle="${LOCALE}" /></TH>
			</TR>
			<c:forEach var="book" items="${cart}">
				<TR>
					<TD>${book.id}</TD>
					<TD>${book.title}</TD>
					<TD>${book.author}</TD>
					<TD><fmt:formatDate value="${book.year}" pattern="yyyy" /></TD>
					<TD colspan="1">${book.price}</TD>
				</TR>
			</c:forEach>
			<TR>
				<TD colspan="6" align="center">
				<c:if test="${order.id != null}">
				<input type="submit"
					value="<fmt:message key="submit" bundle="${LOCALE}" />"></c:if></TD>
			</TR>
		</TABLE>
		<br />
	</form>
</body>
</html>