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
<link rel="stylesheet" href="css/login.css" />
<title><fmt:message key="loginform" bundle="${LOCALE}" /></title>
</head>
<body>
	

	<form name="loginForm" method="POST" action="Controller">
		<input type="hidden" name="command" value="local" /> <select
			name="local" onchange="submit()">
			<option value="${lang}">${lang}</option>
			<option value="${lang2}">${lang2}</option>

		</select>
	</form>

	<div id="wrapper">

		<fieldset>
			<legend>
				<fmt:message key="loginform" bundle="${LOCALE}" />
			</legend>
			<form name="loginForm" method="POST" action="Controller">
				<input type="hidden" name="command" value="login" />
				<div>
					<input type="text" name="user" required
						placeholder="<fmt:message key="user" bundle="${LOCALE}"/>" />
				</div>

				<div>
					<input type="password" name="password"
						placeholder="<fmt:message key="password" bundle="${LOCALE}"/>" />
				</div>

				<input type="submit" name="submit"
					value="<fmt:message key="login" bundle="${LOCALE}"/>" />
			</form>
			<form name="loginForm" method="POST" action="Controller">
				<input type="hidden" name="command" value="newuser" /> <input
					type="submit"
					value="<fmt:message key="newuser" bundle="${LOCALE}"/>">
			</form>
		</fieldset>

	</div>
</body>


</html>