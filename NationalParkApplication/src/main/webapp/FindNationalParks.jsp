<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Find a National Park</title>
</head>
<body>
	<form action="findnationalparks" method="post">
		<h1>Search for a NationalPark by State</h1>
		<p>
			<label for="state">state</label>
			<input id="state" name="state" value="${fn:escapeXml(param.parkId)}">
		</p>
		<p>
			<input type="submit">
			<br/><br/><br/>
			<span id="successMessage"><b>${messages.success}</b></span>
		</p>
	</form>
	<br/>
	<div id="nationalparkCreate"><a href="NationalParksCreate.jsp">Create NationalPark</a></div>
	<div id="nationalparkDelete"><a href="ParkDelete.jsp">Delete NationalPark</a></div>
	<div id="nationalparkupdate"><a href="NationalparkUpdate.jsp">Update NationalPark</a></div>
	<br/>
	<h1>Matching NationalParks</h1>
        <table border="1">
            <tr>
                <th>ParkId</th>
                <th>Ranking</th>
                <th>ParkName</th>
                <th>City</th>
                <th>State</th>
                <th>Zip</th>
                <th>Description</th>
                <th>Delete NationalPark</th>
                <th>Update NationalPark</th>
            </tr>
            <c:forEach items="${nationalParks}" var="nationalPark" >
                <tr>
                    <td><c:out value="${nationalPark.getParkId()}" /></td>
                    <td><c:out value="${nationalPark.getRanking()}" /></td>
                    <td><c:out value="${nationalPark.getParkName()}" /></td>
                    <td><c:out value="${nationalPark.getCity()}" /></td>
                    <td><c:out value="${nationalPark.getState()}" /></td>
                    <td><c:out value="${nationalPark.getZip()}" /></td>
                    <td><c:out value="${nationalPark.getDescription()}" /></td>
                    <td><a href="ParkDelete.jsp?parkid=<c:out value="${nationalPark.getParkId()}"/>">Delete</a></td>
                    <td><a href="NationalparkUpdate.jsp?parkid=<c:out value="${nationalPark.getParkId()}"/>">Update</a></td>
                </tr>
            </c:forEach>
       </table>
</body>
</html>