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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="css/style.css" type="text/css" />

<title>MyLibrary</title>

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

	<br>
	<br>
	<div style="height: 300px; overflow-y: scroll;">

		<table class="simple-little-table" cellspacing='0'>

			<TR>
				<TH><fmt:message key="id" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="title" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="author" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="year" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="price" bundle="${LOCALE}" /></TH>
				<TH><fmt:message key="count" bundle="${LOCALE}" /></TH>
				<TH colspan="3"><fmt:message key="actions" bundle="${LOCALE}" /></TH>
			</TR>
			<TR>
				<form name="search" method="POST" action="Controller">
					<input type="hidden" name="command" value="search" />
					<TD><input name="id" value="" pattern="\-?\d+" size="10" /></TD>
					<TD><input name="title" value="" /></TD>
					<TD><input name="author" value="" /></TD>
					<TD><input type="text" name="year" value="" pattern="[0-9]{4}"
						placeholder="YYYY" /></TD>
					<TD><input name="price" value="" pattern="\-?\d+(\.\d{0,})?" /></TD>
					<TD><input name="count" value="" pattern="\-?\d+" size="10" /></TD>
					<TD colspan="3"><input type="submit"
						value="<fmt:message key="search" bundle="${LOCALE}" />"></TD>
				</form>

			</TR>

			<c:forEach var="book" items="${allbooks}">
				<TR>
					<TD>${book.id}</TD>
					<TD>${book.title}</TD>
					<TD>${book.author}</TD>
					<TD><fmt:formatDate value="${book.year}" pattern="yyyy" /></TD>
					<TD>${book.price}</TD>
					<TD>${book.count}</TD>
					<TD><c:if test="${user.login == 'root' }">
							<form name="deletebook" method="POST" action="Controller">
								<input type="hidden" name="command" value="deletebook" />
								<c:if test="${book.count >= 1 }">
									<input type="submit"
										value="<fmt:message key="delete" bundle="${LOCALE}" />">
								</c:if>
								<input type="hidden" name="id" value="${book.id}" />
							</form>
						</c:if></TD>
					<TD><form name="copydetails" method="POST" action="Controller">
							<input type="hidden" name="command" value="copydetails" />
							<c:if test="${user.login == 'root' }">
								<input type="submit"
									value="<fmt:message key="copy" bundle="${LOCALE}" />">
							</c:if>
							<input type="hidden" name="id" value="${book.id}" />
						</form></TD>
					<TD><form name="addtocart" method="POST" action="Controller">
							<input type="hidden" name="command" value="addtocart" />
							<c:if test="${book.count >= 1 }">
								<input type="submit"
									value="<fmt:message key="addtocart" bundle="${LOCALE}" />">
							</c:if>
							<input type="hidden" name="id" value="${book.id}" />
						</form></TD>
				</TR>
			</c:forEach>


		</TABLE>
	</div>
	<br />




	<c:if test="${user.login == 'root' }"><jsp:include
			page="bookcontrol.jsp" flush="true"></jsp:include></c:if>

	<jsp:include page="cart.jsp" flush="true"></jsp:include>



</body>


</html>