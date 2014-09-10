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
	<fmt:formatDate value="${copybook.year}" pattern="yyyy"
		var="formateddate" />
	<form name="bookcontrol" method="POST" action="Controller">
		<input type="hidden" name="command" value="addbook" />
		<table class="simple-little-table" cellspacing='0'>
			<TR>
				<TH><fmt:message key="title" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="author" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="year" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="price" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="actions" bundle="${LOCALE}" /></TH>

			</TR>
			<TR>
				<TD><input name="title" required value="${copybook.title}" /></TD>
				<TD><input name="author" required value="${copybook.author}" /></TD>
				<TD><input name="year" required value="${formateddate}"
					placeholder="YYYY" pattern="[0-9]{4}" /></TD>
				<TD><input name="price" required value="${copybook.price}"
					pattern="\-?\d+(\.\d{0,})?" /></TD>
				<TD><c:if test="${user.login == 'root' }">
						<input type="submit" value="<fmt:message key="add" bundle="${LOCALE}" />">
					</c:if></TD>
			</TR>
		</TABLE>
		<br />
	</form>
</body>
</html>