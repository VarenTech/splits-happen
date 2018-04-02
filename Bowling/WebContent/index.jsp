<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calculate A Bowling Score</title>
<script src="jquery-3.3.1.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="DataTables/datatables.min.css" />
<script type="text/javascript" src="DataTables/datatables.min.js"></script>
<script type="text/javascript" src="Bowling.js"></script>
</head>
<body>
	<button id="addPlayer">Add new player</button>

	<table id="scoresTable" class="display" style="width: 100%">
		<thead>
			<tr>
				<th>Frame</th>
				<th>1</th>
				<th>2</th>
				<th>3</th>
				<th>4</th>
				<th>5</th>
				<th>6</th>
				<th>7</th>
				<th>8</th>
				<th>9</th>
				<th>10</th>
				<th>Bonus</th>
				<th>Score</th>
			</tr>
		</thead>
	</table>
	<button id="createTextVersion">Create Text File Version</button>
	<br>
	<button id="download" style="display: none"><a href="/Bowling/Recorder">Download Text File Version</a></button>
</body>
</html>