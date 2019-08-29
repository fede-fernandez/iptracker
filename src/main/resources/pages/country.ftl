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
		<div class="card">
			<ul class="list-group list-group-flush">
				<li class="list-group-item"><strong>IP:</strong> ${ip} | <strong>Server time:</strong> ${servertime}</li>
				<li class="list-group-item"><strong>Country:</strong> ${country.name}</li>
				<li class="list-group-item"><strong>ISO Code:</strong> ${country.isoCode}</li>
				<#if country.languages?has_content>
					<li class="list-group-item"><strong>Languages:</strong> 
						<br>
						<#list country.languages as language>
							- ${language.name} (${language.isoCode})
							<br>
						</#list>
					</li>
				<#else>
					<li class="list-group-item list-group-item-warning"><strong>Could not retrieve languages</strong></li>
				</#if>
				<#if country.currencies?has_content>
					<li class="list-group-item"><strong>Currencies:</strong> 
						<br>
						<#list country.currencies as currency>
							- ${currency.code} (1 ${currency.code} = ${currency.rate} u$s)
							<br>
						</#list>
					</li>
				<#else>
					<li class="list-group-item list-group-item-warning"><strong>Could not retrieve currencies</strong></li>
				</#if>
				<#if country.times?has_content>
					<li class="list-group-item"><strong>Times:</strong> 
						<br>
						<#list country.times as time>
							- ${time}
							<br>
						</#list>
					</li>
				<#else>
					<li class="list-group-item list-group-item-warning"><strong>Could not retrieve times</strong></li>
				</#if>
				<#if country.coordinates??>
					<li class="list-group-item"><strong>Estimated distance to Buenos Aires:</strong> ${country.coordinates.distanceToArgentina?round} km (-34, -64) to (${country.coordinates.latitude?round}, ${country.coordinates.longitude?round})</li>
				<#else>
					<li class="list-group-item list-group-item-danger"><strong>Could not calculate distance to Buenos Aires</strong></li>
				</#if>
			</ul>
		</div>
		<hr>
	</div>
</body>
</html>