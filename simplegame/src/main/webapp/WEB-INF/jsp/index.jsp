<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simple Game</title>
</head>

<body>
	<h1>
		<center>A simple betting game</center>
	</h1>
	<hr>
	<center>
		<a href="/play">Play New Round</a> <span style="padding-left: 50px;">${roundStatus}</span>
		
		<span
			style="height: 50px; width: 100%; float: left;"></span>
		<c:if test="${not empty gameRounds}">
			<table style="border: 1px solid black; display: block; height: 500px; width: fit-content; overflow-y: scroll;">
				<thead>
					<tr>
						<th style="padding-right: 5px;">Round ID</th>
						<th style="padding-left: 5px;">Winning Amount</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="gr" items="${gameRounds}">
						<tr>
							<td style="padding-right: 5px; text-align: center;">${gr.id}</td>
							<td style="padding-left: 5px; text-align: center;">${gr.winningAmount}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</center>
</body>
</html>