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
			<a class="btn btn-secondary" href="#">Reports</a>
		</div>
		<ul class="navbar-nav ml-auto">
			<li>
				<span class="navbar-text">Federico Fernandez Barra</span>
			</li>
		</ul>
	</nav>
	<div class="container">
		<hr>
		<div class="card">
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><strong>Furthest calculated distance to Buenos Aires:</strong> ${furthest.coordinates.distanceToArgentina?round} km (${furthest.name})</li>
				<li class="list-group-item"><strong>Closest calculated distance to Buenos Aires:</strong> ${closest.coordinates.distanceToArgentina?round} km (${closest.name})</li>
				<li class="list-group-item"><strong>Average distance to Buenos Aires counting executions:</strong> ${averageDistance} km</li>
				<li class="list-group-item">
					<table class="table table-bordered">
						<thead class="thead-dark">
							<tr>
								<th scope="col">Country</th>
								<th scope="col">Distance to Buenos Aires</th>
								<th scope="col">Executions</th>
							</tr>
						</thead>
						<tbody>
							<#list consultedCountries?sort_by("timesConsultedCount")?reverse as country>
								<tr>
									<td>${country.name}</td>
									<#if country.coordinates??>
										<td>${country.coordinates.distanceToArgentina?round} km</td>
									<#else>
										<td>-</td>
									</#if>
									<td>${country.timesConsultedCount}</td>
								</tr>
							</#list>
						</tbody>
					</table>
				</li>
			</ul>
		</div>
		<hr>
	</div>
</body>
</html>