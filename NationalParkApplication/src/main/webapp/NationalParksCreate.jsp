<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a National Park</title>
</head>
<body>
	<h1>Create National Park</h1>
	<form action="nationalparkcreate" method="post">
		<p>
			<label for="parkid">ParkId</label>
			<input id="parkid" name="parkId" value="">
		</p>
		<p>
			<label for="ranking">Ranking</label>
			<input id="ranking" name="ranking" value="">
		</p>
		<p>
			<label for="parkname">ParkName</label>
			<input id="parkname" name="parkname" value="">
		</p>
		<p>
			<label for="acres">Acres</label>
			<input id="acres" name="acres" value="">
		</p>
		<p>
			<label for="latitude">Latitude</label>
			<input id="latitude" name="latitude" value="">
		</p>
		<p>
			<label for="longitude">Longitude</label>
			<input id="longitude" name="longitude" value="">
		</p>
		<p>
			<label for="active">Active</label>
			<input id="active" name="active" value="">
		</p>
		<p>
			<label for="city">City</label>
			<input id="city" name="city" value="">
		</p>
		<p>
			<label for="state">State</label>
			<input id="state" name="state" value="">
		</p>
		<p>
			<label for="zip">Zip</label>
			<input id="zip" name="zip" value="">
		</p>
		<p>
			<label for="description">Description</label>
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