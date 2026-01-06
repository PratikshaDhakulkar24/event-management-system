<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>
/* Section title */
.section-title {
	color: #004080;
	margin-bottom: 20px;
	font-size: 26px;
}

/* EVENTS TABLE */
.events-table {
	width: 100%;
	border-collapse: collapse;
	background: #ffffff;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 6px;
	overflow: hidden;
}

.events-table th {
	background-color: #0059b3;
	color: white;
	padding: 20px;
	text-align: left;
	font-size: 18px;
}

.events-table td {
	padding: 12px;
	border-bottom: 1px solid #ddd;
	font-size: 14px;
}

.events-table tr:hover {
	background-color: #f2f8ff;
}

/* APPLY BUTTON */
.apply-btn {
	display: inline-block;
	padding: 8px 18px;
	background-color: #28a745;
	color: white;
	border-radius: 4px;
	text-decoration: none;
}

.apply-btn:hover {
	background-color: #218838;
}
</style>
</head>
<body>
<body>

	<%
	if ("1".equals(request.getParameter("success"))) {
	%>
	<div
		style="margin: 15px auto; max-width: 800px; padding: 12px; background: #e6fffa; color: #065f46; border-left: 6px solid #22c55e; font-weight: bold;">
		✅ Registration Successful!</div>
	<%
	}
	%>


	<h2 class="section-title">All Events</h2>

	<table class="events-table">
		<thead>
			<tr>
				<th>Event Name</th>
				<th>Date</th>
				<th>Venue</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>Coding Hackathon</td>
				<td>05 Feb 2025</td>
				<td>Computer Lab</td>
				<td><a class="apply-btn"
					href="eventRegistration.jsp?eventName=Music%20Festival">
						Register </a></td>
			</tr>
			<tr>
				<td>AI Workshop</td>
				<td>10 Feb 2025</td>
				<td>Seminar Hall</td>
				<td><a class="apply-btn"
					href="eventRegistration.jsp?eventName=AI%20Workshop"> Register
				</a></td>
			</tr>
			<tr>
				<td>Dance Competition</td>
				<td>15 Feb 2025</td>
				<td>Main Stage</td>
				<td><a class="apply-btn"
					href="eventRegistration.jsp?eventName=Dance%20Competition">
						Register </a></td>
			</tr>
			<tr>
				<td>Music Festival</td>
				<td>20 Feb 2025</td>
				<td>Open Ground</td>
				<td><a class="apply-btn"
					href="eventRegistration.jsp?eventName=Music%20Festival">
						Register </a></td>
			</tr>
		</tbody>
	</table>

</body>
</html>