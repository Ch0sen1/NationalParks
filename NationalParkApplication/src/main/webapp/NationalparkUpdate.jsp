<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update a National Park</title>
</head>
<body>
	<h1>Update NationalPark</h1>
	<form action="nationalparkupdate" method="post">
		<p>
			<label for="parkid">ParkId</label>
			<input id="parkid" name="parkid" value="${fn:escapeXml(param.parkid)}">
		</p>
		<p>
			<label for="description">New Description</label>
			<input id="description" name="description" value="">
		</p> 
		<p>
			<input type="submit">

		</p>
	</form>
	<br/><br/>
	<p>
		<span id="successMessage"><b>${messages.success}</b></span>
	</p>
</body>
</html>