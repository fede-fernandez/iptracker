<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<title>IP Tracker - Federico Fernandez</title>
</head>
<body>
	<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
		<a class="navbar-brand" href="#">IP Tracker</a>
		<div class="btn-group" role="group" aria-label="Basic example">
			<a class="btn btn-secondary" href="/">Track IP</a>
			<a class="btn btn-secondary" href="/reports">Reports</a>
		</div>
		<ul class="navbar-nav ml-auto">
			<li>
				<span class="navbar-text">Federico Fernandez Barra</span>
			</li>
		</ul>
	</nav>
	<div class="container">
		<hr>
		<div class="alert alert-danger" role="alert">
			${errorMessage}
		</div>
		<hr>
	</div>
</body>
</html>