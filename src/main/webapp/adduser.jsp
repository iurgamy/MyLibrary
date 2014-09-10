<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title><fmt:message key="registerform" bundle="${LOCALE}" /></title>
<link rel="stylesheet" href="css/login.css" />
</head>
<body>

	<div id="wrapper">

		<fieldset>
			<legend><fmt:message key="registerform" bundle="${LOCALE}" /></legend>
			<form name="loginForm" method="POST" action="Controller">
				<input type="hidden" name="command" value="adduser" /> <input
					type="hidden" name="command" value="login" />
				<div>
					<input type="text" name="user" required placeholder="<fmt:message key="user" bundle="${LOCALE}" />" />
				</div>

				<div>
					<input type="password" name="password" required
						placeholder="<fmt:message key="password" bundle="${LOCALE}" />" />
				</div>

				<div>
					<input type="text" name="name" required placeholder="<fmt:message key="firstname" bundle="${LOCALE}" />" />
				</div>

				<div>
					<input type="text" name="surname" required placeholder="<fmt:message key="lastname" bundle="${LOCALE}" />" />
				</div>

				<div>
					<input type="text" name="birthday" required
						placeholder="<fmt:message key="birthday" bundle="${LOCALE}" /> (YYYY-MM-DD)" "/>
				</div>


				<input type="submit" name="submit" value="<fmt:message key="register" bundle="${LOCALE}" />" />
			</form>
			<!-- <form name="logout" method="POST" action="Controller"> -->
			<!-- 	<input type="hidden" name="command" value="logout" />  -->
					<input type="submit" onclick="history.back()" value="<fmt:message key="back" bundle="${LOCALE}" />"><br>
			<!-- </form> -->
		</fieldset>

	</div>














</body>
</html>